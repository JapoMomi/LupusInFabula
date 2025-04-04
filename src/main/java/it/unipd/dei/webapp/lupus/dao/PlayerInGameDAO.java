package it.unipd.dei.webapp.lupus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Searches whether a player is in a game; if so, returns the game public ID
 *
 * @author LupusInFabula Group
 * @version 1.0
 * @since 1.0
 */
public class PlayerInGameDAO extends AbstractDAO<String> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT g.public_id FROM game g join public.plays_as_in pai on g.id = pai.game_id " +
            "where g.who_wins = -1 and player_username = ?";

    /**
     * The username to search
     */
    private final String username;

    /**
     * Creates a new object to search if the player is already in a game.
     *
     * @param con      the connection to the database.
     * @param username the username of the player to search if already in a game.
     */
    public PlayerInGameDAO(final Connection con, final String username) {
        super(con);
        this.username = username;
    }

    /**
     * Executes the DAO operation to search if a player is already in a game.
     *
     * @throws Exception if there is an error executing the SQL query
     */
    @Override
    protected void doAccess() throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String gameId = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                gameId = rs.getString("public_id");
//                LOGGER.info("Player " + username + " found in a gameID: " + gameId);
            }

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        this.outputParam = gameId;
    }
}
