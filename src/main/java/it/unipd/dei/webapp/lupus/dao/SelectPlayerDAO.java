package it.unipd.dei.webapp.lupus.dao;

import it.unipd.dei.webapp.lupus.resource.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectPlayerDAO extends AbstractDAO<List<Player>>{

    private static final String STATEMENT = "SELECT * FROM player";

    public SelectPlayerDAO(final Connection con) {
        super(con);
    }

    @Override
    public final void doAccess() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        final List<Player> players = new ArrayList<Player>();

        try {
            ps = con.prepareStatement(STATEMENT);
            rs = ps.executeQuery();

            while (rs.next()) {
                players.add(new Player(rs.getInt("id"), rs.getString("username"),
                        rs.getString("email"), rs.getString("password"), rs.getDate("registerDate")));
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        this.outputParam = players;
    }
}
