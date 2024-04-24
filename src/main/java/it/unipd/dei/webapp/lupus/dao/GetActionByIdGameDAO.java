package it.unipd.dei.webapp.lupus.dao;

import it.unipd.dei.webapp.lupus.resource.Action;
import it.unipd.dei.webapp.lupus.resource.Actions;
import it.unipd.dei.webapp.lupus.resource.GameAction;
import it.unipd.dei.webapp.lupus.resource.LogContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetActionByIdGameDAO extends AbstractDAO<ArrayList<Action>> {
    private static final String STATEMENT = "SELECT * "+
    "FROM type_action ty JOIN action a ON(a.type_of_action=ty.id) "+
    "WHERE a.game_id = ? "+
    "ORDER BY a.round, a.phase, a.subphase";

    /*
    SELECT *  FROM Action a, TYPE_ACTION t, Game g JOIN a.type_of_action=t.ID AND g.ID=a.game_id WHERE g.public_ID = ? ORDER BY a.round, a.phase, a.subphase;*/

    private final int idPart;

    public GetActionByIdGameDAO(final Connection con, final int idPart)
    {
        super(con);
        this.idPart = idPart;
    }

    @Override
    public final void doAccess() throws SQLException
    {
        PreparedStatement query = null;
        ResultSet rs = null;
        ArrayList<Action> r  = new ArrayList<>();

        try
        {
            query = con.prepareStatement(STATEMENT);
            query.setInt(1, idPart);

            LOGGER.info(query);

            rs = query.executeQuery();

            while (rs.next()) r.add(new Action(rs));
        }
        finally
        {
            if (query != null) query.close();
            if (rs != null)    rs.close();
        }
        this.outputParam = r;
    }
}
