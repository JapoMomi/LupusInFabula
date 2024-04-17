package it.unipd.dei.webapp.lupus.resource;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class GameAction extends AbstractResource {

    private static final String JSON_NAME = "gameAction";

    private final String player;
    private final String role;
    private final String target;

    public GameAction(String player, String role, String target) {
        this.player = player;
        this.role = role;
        this.target = target;
    }

    public String getPlayer() {
        return player;
    }

    public String getRole() {
        return role;
    }

    public String getTarget() {
        return target;
    }

    @Override
    protected void writeJSON(OutputStream out) throws Exception {
    }

    public static List<GameAction> fromJSON(final InputStream in) throws IOException {
        List<GameAction> gameActions = new ArrayList<>();

        try {
            final JsonParser jp = JSON_FACTORY.createParser(in);

            // while we are not on the start of an element or the element is not
            // a token element, advance to the next element (if any)
            while (jp.getCurrentToken() != JsonToken.FIELD_NAME || !JSON_NAME.equals(jp.getCurrentName())) { //TODO controllare il nome

                // there are no more events
                if (jp.nextToken() == null) {
                    LOGGER.error("No GameAction object found in the stream.");
                    throw new EOFException("Unable to parse JSON: no GameAction object found.");
                }
            }

            jp.nextToken();
            if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
                while (jp.nextToken() != JsonToken.END_ARRAY) {
                    gameActions.add(handleGameAction(jp));
                }
            } else
                gameActions.add(handleGameAction(jp));

            return gameActions;

        } catch (IOException e) {
            LOGGER.error("Unable to parse an GameAction object from JSON.", e);
            throw e;
        }
    }

    private static GameAction handleGameAction(final JsonParser jp) throws IOException {

        String jPlayer = null;
        String jRole = null;
        String jTarget = null;

        while (jp.nextToken() != JsonToken.END_OBJECT) {
            if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

                switch (jp.getCurrentName()) {
                    case "player":
                        jp.nextToken();
                        jPlayer = jp.getText();
                        break;
                    case "role":
                        jp.nextToken();
                        jRole = jp.getText();
                        break;
                    case "target":
                        jp.nextToken();
                        jTarget = jp.getText();
                        break;
                    default:
                        // Handle unexpected fields if needed
                        break;
                }
            }

        }
        if (jPlayer == null || jRole == null || jTarget == null) {
            throw new IOException("Unable to parse JSON: GameAction contains null value.");
        }

        return new GameAction(jPlayer, jRole, jTarget);
    }

}
