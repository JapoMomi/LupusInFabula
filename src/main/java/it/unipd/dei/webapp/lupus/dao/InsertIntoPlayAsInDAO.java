package it.unipd.dei.webapp.lupus.dao;

import it.unipd.dei.webapp.lupus.resource.PlaysAsIn;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Adds a new record indicating the role a player has in a game.
 *
 * @author LupusInFabula Group
 * @version 1.0
 * @since 1.0
 */
public class InsertIntoPlayAsInDAO extends AbstractDAO {

    /**
     * The SQL statement to be executed.
     */
    private final static String STATEMENT = "INSERT INTO PLAYS_AS_IN VALUES(?, ?, ?)";

    /**
     * The PlaysAsIn object representing the player's role in a game.
     */
    private final PlaysAsIn playsAsIn;

    /**
     * Creates a new object for ad PlaysAsIn into database
     *
     * @param con       the connection to the database.
     * @param playsAsIn the PlaysAsIn object representing the player's role in a game.
     */
    public InsertIntoPlayAsInDAO(final Connection con, final PlaysAsIn playsAsIn) {
        super(con);
        this.playsAsIn = playsAsIn;
    }

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, playsAsIn.getPlayerUsername());
            pstmt.setInt(2, playsAsIn.getGameId());
            pstmt.setInt(3, playsAsIn.getRoleId());

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
    }
}
