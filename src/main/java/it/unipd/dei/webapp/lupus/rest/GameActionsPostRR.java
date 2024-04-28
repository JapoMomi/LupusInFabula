package it.unipd.dei.webapp.lupus.rest;

import it.unipd.dei.webapp.lupus.dao.*;
import it.unipd.dei.webapp.lupus.resource.*;
import it.unipd.dei.webapp.lupus.utils.GamePhase;
import it.unipd.dei.webapp.lupus.utils.GameRoleAction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.tags.shaded.org.apache.xalan.templates.AVT;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class GameActionsPostRR extends AbstractRR {

    /**
     * Map containing the actions available during the night phase of the game.
     * The key is the role name, and the value is the corresponding action.
     */
    private static final Map<String, String> nightAction = new HashMap<>();

    /**
     * Map indicating whether players are dead or alive in the game.
     * The key is the player's name, and the value is a boolean indicating if the player is dead.
     */
    private final Map<String, Boolean> deadPlayers;

    /**
     * Map containing the players and their roles in the game.
     * The key is the player's name, and the value is the player's role.
     */
    private final Map<String, String> playersRole;

    /**
     * The ID of the game.
     */
    private final int gameID;

    int currentRound, currentPhase;

    /**
     * Constructs a new GameActionsPostRR object with the specified game ID, request, response, and data source.
     *
     * @param gameID The ID of the game.
     * @param req    The HTTP servlet request.
     * @param res    The HTTP servlet response.
     * @param ds     The data source.
     * @throws SQLException If an SQL error occurs.
     */
    public GameActionsPostRR(int gameID, final HttpServletRequest req, final HttpServletResponse res, DataSource ds) throws SQLException {
        super(Actions.ADD_ACTIONS, req, res, ds);
        this.gameID = gameID;

        for (GameRoleAction role : GameRoleAction.values())
            if (role.getAction() != null)
                nightAction.put(role.getName(), role.getAction());

        deadPlayers = new GetDeadPlayersByGameIdDAO(ds.getConnection(), gameID).access().getOutputParam();
        playersRole = new SelectPlayersAndRolesByGameIdDAO(ds.getConnection(), gameID).access().getOutputParam();
    }


    @Override
    protected void doServe() throws IOException {

        try {

            List<GameAction> gameActions = GameAction.fromJSON(req.getInputStream()); // todo to handle throws IOException

            // check of the correctness of the actions
            if (!correctnessOfActions(gameActions))
                return;

            LOGGER.info("correctness of actions done");
            // contain all player in the game (gameID) with their role
            // first String: playerUsername , second String: role of the player

            Game game = new GetGameByGameIdDAO(ds.getConnection(), gameID).access().getOutputParam();
            currentRound = game.getRounds();
            currentPhase = game.getPhase();

            if (currentPhase == GamePhase.NIGHT.getId()) {
                if (handleNightPhase(gameActions))
                    return;
            } else if (handleDayPhase(gameActions))
                return;

            if (currentRound != 0) {
                if (currentPhase == GamePhase.DAY.getId()) {
                    currentRound++;
                    currentPhase = GamePhase.NIGHT.getId();
                } else {
                    currentPhase = GamePhase.DAY.getId();
                }
            } else {
                currentRound = 1;
                currentPhase = GamePhase.NIGHT.getId();
            }

            LOGGER.info("Checked all actions");

            // updatePlayerDeath(username); // TODO --> update player death


            // TODO --> update game table (last thing to do, before doing it i have to check if someone wins)

//            for (GameAction gameAction : gameActions) {
//                if (nightAction.get(gameAction.getRole()) != null) {
//                    new InsertIntoActionDAO(ds.getConnection(), new Action(gameID, gameAction.getPlayer(), currentRound, currentPhase, 0,
//                            currentPhase == GamePhase.NIGHT.getId() ? nightAction.get(gameAction.getRole()) : Action.VOTE, gameAction.getTarget())
//                    ).access();
//                }
//            }

//            LOGGER.info("updating round " + currentRound + " phase " + currentPhase);
//            new UpdateGameDAO(ds.getConnection(), gameID, currentPhase, currentRound).access();
//            LOGGER.info("updating game");

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }


    private boolean handleDayPhase(List<GameAction> gameActions) throws SQLException, IOException {
        //The first thing to do is handling the vote
        //Then update the database based on the action and the death player
        Map<String, Integer> votesMap = getVotesMap(gameActions);

        for (GameAction gameAction : gameActions) {
            String player = gameAction.getPlayer();
            String role = gameAction.getRole();
            String target = gameAction.getTarget();
            LOGGER.info("%s with role %s has voted %s", player, role, target);

            // DAO for add the action to the database

            votesMap.put(target, votesMap.get(target) + 1);
        }

        List<Map.Entry<String, Integer>> votesList = new ArrayList<>(votesMap.entrySet());
        Collections.sort(votesList, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        for (Map.Entry<String, Integer> entry : votesList) {
            LOGGER.info("Player: " + entry.getKey() + ", Votes received: " + entry.getValue());
        }

        if (votesList.get(0).getValue() == votesList.get(1).getValue()) {
            LOGGER.info("Ballottaggio");
        }

        LOGGER.info("Players %s is voted", votesList.get(0).getKey());

        //DAO for update the database with the dead of the player

        return true;
    }


    private int wolfCount() throws SQLException {

        // count of wolves still alive for some effects
        int number_of_wolves = 0;
        //Map<String, Boolean> deadPlayers = new GetDeadPlayersByGameIdDAO(ds.getConnection(), gameID).access().getOutputParam();
        for (Map.Entry<String, String> playerRole : playersRole.entrySet())
            if ((playerRole.getValue().equals(GameRoleAction.WOLF.getName())
                    || playerRole.getValue().equals(GameRoleAction.BERSERKER.getName())
                    || playerRole.getValue().equals(GameRoleAction.EXPLORER.getName())
                    || playerRole.getValue().equals(GameRoleAction.DORKY.getName()))
                    && !deadPlayers.get(playerRole.getKey()))
                number_of_wolves++;
            else if (playerRole.getValue().equals(GameRoleAction.PUPPY.getName())
                    && new IsPuppyAWolfDAO(ds.getConnection(), gameID).access().getOutputParam()
                    && !deadPlayers.get(playerRole.getKey()))
                number_of_wolves++;

        return number_of_wolves;
    }


    private boolean handleNightPhase(List<GameAction> gameActions) throws SQLException, IOException {

        Map<String, Map<String, Boolean>> actionsMap = getActionsMap(gameActions, playersRole);
        int number_of_wolves = wolfCount();

        // check of the actions
        if (!actionCheck(actionsMap, playersRole))
            return false;

        // for each player in the map i check the associated map. Then for each element in this map i check if the player is a target of which action
        for (Map.Entry<String, Map<String, Boolean>> entry : actionsMap.entrySet()) {

            String target = entry.getKey();
            Map<String, Boolean> actionPlayerMap = entry.getValue();

            if (!deadPlayers.get(entry.getKey())) {

                // check for the "explore" action --> EXPLORER
                // (he can explore only once in a game, so if he decides to do it, after that he will become a normal wolf)
                if (actionPlayerMap.get(GameRoleAction.EXPLORER.getAction())) {
                    if (!new GetRoleByGameIdAndPlayerUsernameDAO(ds.getConnection(), gameID, target).access().getOutputParam().equals(GameRoleAction.HAMSTER.getAction())) {
                        LOGGER.info("The target " + target + " has been killed by the explorer");
                        String explorer = "";
                        for (GameAction gameAction : gameActions) {
                            if (gameAction.getTarget().equals(target)) {
                                explorer = gameAction.getPlayer();
                            }
                        }
                        new InsertIntoActionDAO(ds.getConnection(), new Action(gameID, explorer, currentRound, currentPhase, 0, GameRoleAction.EXPLORER.getAction(), target)).access();
                    }
                }

                // check of the "block" action --> ILLUSIONIST
                // (if the target is blocked his action must be blocked (put them to false)
                if (actionPlayerMap.get(GameRoleAction.ILLUSIONIST.getAction())) {

                    String illusionist = "";
                    String player_role = "";
                    for (GameAction gameAction : gameActions) {
                        if (gameAction.getPlayer().equals(target)) {
                            illusionist = gameAction.getPlayer();
                            player_role = gameAction.getRole();
                        }
                    }

                    Map<String, Boolean> tmp = actionsMap.get(target);
                    String player_action = nightAction.get(player_role);
                    tmp.put(player_action, false);
                    actionsMap.put(target, tmp);

                    LOGGER.info("The target " + target + " has been blocked during the night");
                    new InsertIntoActionDAO(ds.getConnection(), new Action(gameID, illusionist, currentRound, currentPhase, 0, GameRoleAction.ILLUSIONIST.getAction(), target)).access();
                }

                // check for the "protect" action --> KNIGHT
                // (if the current target is the same as the previous turn target, tha knight has to change target)
                if (actionPlayerMap.get(GameRoleAction.KNIGHT.getAction())
                        && target.equals(new LastPlayerProtectedByKnightDAO(ds.getConnection(), gameID).access().getOutputParam())) {
                    Message m = new Message("ACTION NOT POSSIBLE: you have already protected " + target + " the previous turn");
                    LOGGER.warn("ACTION NOT POSSIBLE: you have already protected " + target + " the previous turn");
                    m.toJSON(res.getOutputStream());
                    return false;
                } else {
                    LOGGER.info("The knight has protect " + target);
                    String knight = "";
                    for (GameAction gameAction : gameActions) {
                        if (gameAction.getTarget().equals(target)) {
                            knight = gameAction.getPlayer();
                        }
                    }
                    new InsertIntoActionDAO(ds.getConnection(), new Action(gameID, knight, currentRound, currentPhase, 0, GameRoleAction.KNIGHT.getAction(), target)).access();
                }

                // check of the "maul" action --> WOLVES
                // (if the target is protected, or is the hamster, or is the hobbit and in the game are still alive more than 1 wolf, then the target will not die)
                if (actionPlayerMap.get(GameRoleAction.WOLF.getAction())) {

                    String wolf = "";
                    for (GameAction gameAction : gameActions) {
                        if (gameAction.getTarget().equals(target)) {
                            wolf = gameAction.getPlayer();
                        }
                    }

                    if ((!actionPlayerMap.get(GameRoleAction.KNIGHT.getAction())
                            && !new GetRoleByGameIdAndPlayerUsernameDAO(ds.getConnection(), gameID, target).access().getOutputParam().equals(GameRoleAction.HAMSTER.getAction()))
                            && !new GetRoleByGameIdAndPlayerUsernameDAO(ds.getConnection(), gameID, target).access().getOutputParam().equals(GameRoleAction.HOBBIT.getName())) {

                        //updatePlayerDeath(target);
                        LOGGER.info("The target " + target + " has been killed by the wolves during the night");
                        new InsertIntoActionDAO(ds.getConnection(), new Action(gameID, wolf, currentRound, currentPhase, 0, GameRoleAction.WOLF.getAction(), target)).access();

                    } else if (new GetRoleByGameIdAndPlayerUsernameDAO(ds.getConnection(), gameID, target).access().getOutputParam().equals(GameRoleAction.HOBBIT.getName()) && (number_of_wolves <= 1)) {
                        //updatePlayerDeath(target);
                        LOGGER.info("The target " + target + " has been killed by the wolves during the night (number of wolves: " + number_of_wolves + ")");
                        new InsertIntoActionDAO(ds.getConnection(), new Action(gameID, wolf, currentRound, currentPhase, 0, GameRoleAction.WOLF.getAction(), target)).access();
                    }

                }

                // check for the "point" action --> DORKY
                // (only if the dorky had not already pointed a wolf pack member: if the target target is a member of the pack, then the dorky becomes a wolf)
                if (!new IsDorkyAWolfDAO(ds.getConnection(), ds, gameID).access().getOutputParam()) {
                    if (actionPlayerMap.get(GameRoleAction.DORKY.getAction())) {

                        String dorky = "";
                        for (GameAction gameAction : gameActions)
                            if (gameAction.getTarget().equals(target))
                                dorky = gameAction.getPlayer();

                        //add the action point to action table since the dorky is not a wolf
                        LOGGER.info("The target " + target + " has been pointed by the dorky");
                        new InsertIntoActionDAO(ds.getConnection(), new Action(gameID, dorky, currentRound, currentPhase, 0, GameRoleAction.DORKY.getAction(), target)).access();
                    }
                }

                // check of the "investigate" action --> SEER
                if (actionPlayerMap.get(GameRoleAction.SEER.getAction())) {

                    String seer = "";
                    for (GameAction gameAction : gameActions) {
                        if (gameAction.getTarget().equals(target)) {
                            seer = gameAction.getPlayer();
                        }
                    }
                    // in case the seer sees the puppy he will see that is a good role
                    LOGGER.info("The target " + target + " has been seen during the night");
                    new InsertIntoActionDAO(ds.getConnection(), new Action(gameID, seer, currentRound, currentPhase, 0, GameRoleAction.SEER.getAction(), target)).access();

                }

                // check of the "shot" action --> SHERIFF
                // (if the target is a member of the wolf pack or a victory stealer he will die, otherwise the sheriff will die)
                if (actionPlayerMap.get(GameRoleAction.SHERIFF.getAction())) {

                    String sheriff = "";
                    for (GameAction gameAction : gameActions)
                        if (gameAction.getTarget().equals(target))
                            sheriff = gameAction.getPlayer();

                    GameRoleAction player_role = GameRoleAction.valueOfName(playersRole.get(target));
                    assert player_role != null;
                    if (player_role.getRoleType().getType() == 1 || player_role.getRoleType().getType() == 2) {
                        LOGGER.info("The target " + target + " has been killed by the sheriff during the night");
                        new InsertIntoActionDAO(ds.getConnection(), new Action(gameID, sheriff, currentRound, currentPhase, 0, GameRoleAction.SHERIFF.getAction(), target)).access();
                    } else {
                        LOGGER.info("The sheriff " + sheriff + " has killed himself during the night");
                        new InsertIntoActionDAO(ds.getConnection(), new Action(gameID, sheriff, currentRound, currentPhase, 0, GameRoleAction.SHERIFF.getAction(), sheriff)).access();
                    }

                }

                // check for the "blowup" action --> KAMIKAZE
                // (if the target is the kamikaze and the action is true, the kamikaze kill himself and the wolf)
                if (playersRole.get(target).equals(GameRoleAction.KAMIKAZE.getName())
                        && actionPlayerMap.get(GameRoleAction.WOLF.getAction())) {
                    String wolf = "";
                    for (GameAction gameAction : gameActions)
                        if (gameAction.getTarget().equals(target))
                            wolf = gameAction.getPlayer();

                    LOGGER.info("The target " + target + " is blown up with the wolf " + wolf);
                    new InsertIntoActionDAO(ds.getConnection(), new Action(gameID, target, currentRound, currentPhase, 0, GameRoleAction.KAMIKAZE.getName(), wolf)).access();
                }

                // check for the "plague" action --> PLAGUE SPREADER
                if (actionPlayerMap.get(GameRoleAction.PLAGUE_SPREADER.getAction())) {

                    String plague_spreader = "";
                    for (GameAction gameAction : gameActions) {
                        if (gameAction.getTarget().equals(target)) {
                            plague_spreader = gameAction.getPlayer();
                        }
                    }

                    LOGGER.info("The target " + target + " is anointed");
                    new InsertIntoActionDAO(ds.getConnection(), new Action(gameID, plague_spreader, currentRound, currentPhase, 0, GameRoleAction.PLAGUE_SPREADER.getAction(), target)).access();

                }

                // check for the "rage" action --> BERSERKER
                // he can maul two target (so in the json he will appear two time), bypassing the knight and dying after he activated his effect
                if (actionPlayerMap.get(GameRoleAction.BERSERKER.getAction())) {

                    if (!new GetRoleByGameIdAndPlayerUsernameDAO(ds.getConnection(), gameID, target).access().getOutputParam().equals(GameRoleAction.HAMSTER.getAction())) {

                        String berserker = "";
                        for (GameAction gameAction : gameActions) {
                            if (gameAction.getTarget().equals(target)) {
                                berserker = gameAction.getPlayer();
                            }
                        }

                        LOGGER.info("The target " + target + " has been killed by the berserker");
                        LOGGER.info("The berserker has killed also himself during the night");
                        new InsertIntoActionDAO(ds.getConnection(), new Action(gameID, berserker, currentRound, currentPhase, 0, GameRoleAction.BERSERKER.getAction(), target)).access();

                    }

                }

                // check for the "look" action --> MEDIUM
                // (he looks at the RoleType of the target that died by the stake during the day)
                if (actionPlayerMap.get(GameRoleAction.MEDIUM.getAction())) {

                    String medium = "";
                    for (GameAction gameAction : gameActions) {
                        if (gameAction.getTarget().equals(target)) {
                            medium = gameAction.getPlayer();
                        }
                    }

                    LOGGER.info("The target " + target + " have seen if the stake dead target is good, evil or neutral");
                    new InsertIntoActionDAO(ds.getConnection(), new Action(gameID, medium, currentRound, currentPhase, 0, GameRoleAction.MEDIUM.getAction(), target)).access();

                }

            }

        }

        return true;

    }


    private boolean correctnessOfActions(List<GameAction> gameActions) throws SQLException, IOException {
        // TODO --> fix the Logger.info error
        Message m;
        boolean wolfActionDone = false;
        int berserkerCount = 0;


        for (GameAction gameAction : gameActions) {

            int game_id = new GetGameIdByPlayerUsernameDAO(ds.getConnection(), gameAction.getPlayer()).access().getOutputParam();
            //check if the player is in the game
            if (game_id != gameID) {
                m = new Message("ERROR, the player " + gameAction.getPlayer() + " is not in the game");
                LOGGER.warn("ERROR, the player " + gameAction.getPlayer() + " is not in the game");
                m.toJSON(res.getOutputStream());
                return false;
            }

            game_id = new GetGameIdByPlayerUsernameDAO(ds.getConnection(), gameAction.getTarget()).access().getOutputParam();
            //check if target is in the game
            if (game_id != gameID) {
                m = new Message("ERROR, the target " + gameAction.getTarget() + " is not in the game");
                LOGGER.warn("ERROR, the target " + gameAction.getTarget() + " is not in the game");
                m.toJSON(res.getOutputStream());
                return false;
            }

            String player_role = new GetRoleByGameIdAndPlayerUsernameDAO(ds.getConnection(), game_id, gameAction.getPlayer()).access().getOutputParam();
            //check if the player has the correct role in the game
            if (!player_role.equals(gameAction.getRole())) {
                m = new Message("ERROR, the player " + gameAction.getPlayer() + " has not the correct role (" + gameAction.getRole() + " != " + player_role + ") in the game");
                LOGGER.warn("ERROR, the player " + gameAction.getPlayer() + " has not the correct role (" + gameAction.getRole() + " != " + player_role + ") in the game");
                m.toJSON(res.getOutputStream());
                return false;
            }

            // check if the target and the player are alive
            if (deadPlayers.get(gameAction.getPlayer())) {
                m = new Message("ERROR, the player " + gameAction.getPlayer() + " is dead");
                LOGGER.warn("ERROR, the player " + gameAction.getPlayer() + " is dead");
                m.toJSON(res.getOutputStream());
                return false;
            } else if (deadPlayers.get(gameAction.getTarget())) {
                m =  new Message("ERROR, the target " + gameAction.getTarget() + " is dead");
                LOGGER.warn("ERROR, the target " + gameAction.getTarget() + " is dead");
                m.toJSON(res.getOutputStream());
                return false;
            }

            //check for the correct number of wolf pack action
            if (gameAction.getRole().equals(GameRoleAction.WOLF.getName())
                    || gameAction.getRole().equals(GameRoleAction.EXPLORER.getName())
                    || gameAction.getRole().equals(GameRoleAction.BERSERKER.getName())) {

                if (!gameAction.getRole().equals(GameRoleAction.BERSERKER.getName())) {

                    if (!wolfActionDone) {

                        wolfActionDone = true;

                    } else {

                        m = new Message("ERROR, the wolves has already done their action this night");
                        LOGGER.warn("ERROR, the wolves has already done their action this night");
                        m.toJSON(res.getOutputStream());
                        return false;

                    }
                } else {

                    if (!wolfActionDone) {

                        switch (berserkerCount) {
                            case 0:
                                berserkerCount++;
                                break;
                            case 1:
                                berserkerCount++;
                                wolfActionDone = true;
                                break;

                        }

                    } else {

                        m = new Message("ERROR, there's too many action for the wolf pack");
                        LOGGER.warn("ERROR, there's too many action for the wolf pack");
                        m.toJSON(res.getOutputStream());
                        return false;

                    }

                }

            } else if (gameAction.getRole().equals(GameRoleAction.PUPPY.getName())
                    && !new IsPuppyAWolfDAO(ds.getConnection(), gameID).access().getOutputParam()) {

                m = new Message("ACTION NOT POSSIBLE: the puppy can't maul anyone since there's still some wolves alive");
                LOGGER.warn("ACTION NOT POSSIBLE: the puppy can't maul anyone since there's still some wolves alive");
                m.toJSON(res.getOutputStream());
                return false;

            }
        }

        //map with the player and his role in the game (only roles with a night active effect (e.g. kamikaze has a passive effect because he activates it only if a wolf attack him) and alive)
        //if in the game there's the berserker he can do two action
        Map<String, String> rolesWithEffect = new HashMap<>();
        for (Map.Entry<String, String> playerRoleEntry : playersRole.entrySet()) {

            GameRoleAction gameRoleAction = GameRoleAction.valueOfName(playerRoleEntry.getValue());
            assert gameRoleAction != null;
            if (gameRoleAction.getAction() != null
                    && !gameRoleAction.getName().equals(GameRoleAction.KAMIKAZE.getName())
                    && !gameRoleAction.getName().equals(GameRoleAction.PUPPY.getName())
                    && !deadPlayers.get(playerRoleEntry.getKey())) {

                rolesWithEffect.put(playerRoleEntry.getKey(), playerRoleEntry.getValue());

            } else if (gameRoleAction.getAction() != null
                    && gameRoleAction.getName().equals(GameRoleAction.PUPPY.getName())
                    && new IsPuppyAWolfDAO(ds.getConnection(), gameID).access().getOutputParam())

                rolesWithEffect.put(playerRoleEntry.getKey(), playerRoleEntry.getValue());

        }

        //check if each role with an effect has done the action
        if (berserkerCount == 0) {
            if (gameActions.size() != (rolesWithEffect.size() - wolfCount() + 1)) {

                m = new Message("ERROR, someone has not done his action, or has done too many actions this turn");
                LOGGER.warn("ERROR, someone has not done his action, or has done too many actions this turn");
                m.toJSON(res.getOutputStream());
                return false;

            }
        } else if (berserkerCount == 1) {
            if (gameActions.size() != (rolesWithEffect.size() - wolfCount() + 1)) {

                m = new Message("ERROR, someone has not done his action this turn");
                LOGGER.warn("ERROR, someone has not done his action this turn " + rolesWithEffect.size() + " " + gameActions.size() + " " + wolfCount());
                m.toJSON(res.getOutputStream());
                return false;

            }
        } else if (berserkerCount == 2) {
            if (gameActions.size() != (rolesWithEffect.size() - wolfCount() + 2)) {

                m = new Message("ERROR, someone has not done his action, or has done too many actions this turn (berserker case)");
                LOGGER.warn("ERROR, someone has not done his action, or has done too many actions this turn (berserker case)");
                m.toJSON(res.getOutputStream());
                return false;

            }
        }

        return true;

    }


    private boolean actionCheck(Map<String, Map<String, Boolean>> actionsMap, Map<String, String> playerRole) throws IOException {

        if (actionsMap == null)
            return false;

        // for all targeted players
        for (Map.Entry<String, Map<String, Boolean>> entry : actionsMap.entrySet()) {

            String player = entry.getKey();
            String roleOfPlayer = playerRole.get(player);
            Map<String, Boolean> actionTarget = entry.getValue();

            for (Map.Entry<String, Boolean> entry1 : actionTarget.entrySet()) {
                String action = entry1.getKey();
                Boolean target = entry1.getValue();
                //LOGGER.info("The action " + action + " is " + target + " " + player + " " + roleOfPlayer);
                if (target) {
                    //LOGGER.info(nightAction.get(roleOfPlayer) + " " + action + " " + player + " " + roleOfPlayer);
                    //check if player can be a target of the action
                    if (nightAction.get(roleOfPlayer) != null
                            && nightAction.get(roleOfPlayer).equals(action)
                            && !roleOfPlayer.equals(GameRoleAction.KNIGHT.getName())
                            && !roleOfPlayer.equals(GameRoleAction.PLAGUE_SPREADER.getName())) {
                        LOGGER.warn("Error, the target of " + action + " is not a valid target");
                        Message m = new Message("Error, the target of " + action + " is not a valid target");
                        m.toJSON(res.getOutputStream());
                        //nope --> log - Error
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private Map<String, Map<String, Boolean>> getActionsMap(List<GameAction> gameActions, Map<String, String> playerRole) throws IOException, SQLException {

        // first String: playerUsername , second String: action , boolean: if playerUsername is the target of action
        Map<String, Map<String, Boolean>> actions = new HashMap<>();

        for (Map.Entry<String, String> entry : playerRole.entrySet()) {

            Map<String, Boolean> tmp = new HashMap<>();
            // take each action in nightAction and put it in tmp
            for (Map.Entry<String, String> entry1 : nightAction.entrySet()) {

                if (entry1.getKey().equals(GameRoleAction.DORKY.getName())) {
                    // if the dorky has pointed a wolf during the game, his action will be maul
                    if (!new IsDorkyAWolfDAO(ds.getConnection(), ds, gameID).access().getOutputParam())
                        tmp.put(entry1.getValue(), false);
                    else
                        tmp.put(GameRoleAction.WOLF.getAction(), false);
                } else if (entry1.getKey().equals(GameRoleAction.PUPPY.getName())) {
                    // if the puppy is the last wolf pack member alive he can start to maul
                    if (new IsPuppyAWolfDAO(ds.getConnection(), gameID).access().getOutputParam())
                        tmp.put(GameRoleAction.WOLF.getAction(), false);
                } else if (entry1.getKey().equals(GameRoleAction.EXPLORER.getName())) {
                    // if the explorer has already activated his effect then he can only maul
                    if (new IsExplorerAWolfDAO(ds.getConnection(), gameID).access().getOutputParam())
                        tmp.put(GameRoleAction.WOLF.getAction(), false);
                    else
                        tmp.put(entry1.getValue(), false);
                } else
                    tmp.put(entry1.getValue(), false);

            }

            // take each playerUsername and the map tmp and put them into action
            actions.put(entry.getKey(), tmp);

        }

        for (GameAction gameAction : gameActions) {
            // take the map relative to the target in gameAction, update the value into true and then update action with the new tmp
            if (nightAction.get(gameAction.getRole()) != null) {
                LOGGER.info(gameAction.getRole() + " " + gameAction.getPlayer());
                if (!deadPlayers.get(gameAction.getPlayer())) {
                    // if the player of the action is puppy and if he's the last wolf alive, then i can start to maul
                    if (!gameAction.getRole().equals(GameRoleAction.PUPPY.getName())) {

                        Map<String, Boolean> tmp = actions.get(gameAction.getTarget());
                        tmp.put(nightAction.get(gameAction.getRole()), true);
                        actions.put(gameAction.getTarget(), tmp);

                    } else if (new IsPuppyAWolfDAO(ds.getConnection(), gameID).access().getOutputParam()) {

                        Map<String, Boolean> tmp = actions.get(gameAction.getTarget());
                        tmp.put(nightAction.get(gameAction.getRole()), true);
                        actions.put(gameAction.getTarget(), tmp);

                    }

                }

            } else {

                LOGGER.warn("ERROR, the action is null");
                // todo --> to change
                // ErrorCode ec = ErrorCode.
                Message m = new Message("ERROR, the action is null");
                m.toJSON(res.getOutputStream());
                return null;

            }

            // special cases: dorky, puppy, explorer
            if (!deadPlayers.get(gameAction.getPlayer())) {

                if (gameAction.getRole().equals(GameRoleAction.DORKY.getName())
                        && new IsDorkyAWolfDAO(ds.getConnection(), ds, gameID).access().getOutputParam()) {

                    Map<String, Boolean> tmp = actions.get(gameAction.getTarget());
                    tmp.put(nightAction.get(GameRoleAction.WOLF.getName()), true);
                    actions.put(gameAction.getTarget(), tmp);

                }

                if (gameAction.getRole().equals(GameRoleAction.PUPPY.getName())
                        && new IsPuppyAWolfDAO(ds.getConnection(), gameID).access().getOutputParam()) {

                    Map<String, Boolean> tmp = actions.get(gameAction.getTarget());
                    tmp.put(nightAction.get(GameRoleAction.WOLF.getName()), true);
                    actions.put(gameAction.getTarget(), tmp);

                }

                if (gameAction.getRole().equals(GameRoleAction.EXPLORER.getName())
                        && new IsExplorerAWolfDAO(ds.getConnection(), gameID).access().getOutputParam()) {

                    Map<String, Boolean> tmp = actions.get(gameAction.getTarget());
                    tmp.put(nightAction.get(GameRoleAction.WOLF.getName()), true);
                    actions.put(gameAction.getTarget(), tmp);

                }

            }

        }

        return actions;

    }


    private Map<String, Integer> getVotesMap(List<GameAction> gameActions) {
        Map<String, Integer> votesMap = new HashMap<>();

        for (GameAction gameAction : gameActions) {
            votesMap.put(gameAction.getPlayer(), 0);
        }

        return votesMap;
    }

    /**
     * Updates the information about a player's death in the game.
     *
     * @param player The username of the player who died.
     * @throws SQLException if a database access error occurs.
     */
    private void updatePlayerDeath(String player) throws SQLException {
        PlaysAsIn playsAsIn = new PlaysAsIn(player, gameID, playersRole.get(player), currentRound, currentPhase);
        new UpdateDeathOfPlayerInTheGameDAO(ds.getConnection(), playsAsIn).access();
    }

}