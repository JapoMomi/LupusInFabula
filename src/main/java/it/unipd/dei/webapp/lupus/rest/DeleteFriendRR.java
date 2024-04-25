package it.unipd.dei.webapp.lupus.rest;

import it.unipd.dei.webapp.lupus.dao.DeleteFriendDAO;
import it.unipd.dei.webapp.lupus.resource.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteFriendRR extends AbstractRR{

    public DeleteFriendRR(final HttpServletRequest req, final HttpServletResponse res, DataSource ds) {
        super(Actions.DELETE_FRIEND, req, res, ds);
    }


    @Override
    protected void doServe() throws IOException {

        Message m = null;

        try{
            Player player = (Player) req.getSession().getAttribute("user");
            String friend_username = Friend.fromJSON(req.getInputStream()).getUsername();

            // creates a new DAO for accessing the database and deletes the employee
            Friend f = new DeleteFriendDAO(ds.getConnection(), player.getUsername(), friend_username).access().getOutputParam();

            if(f != null) {
                LOGGER.info("Friend successfully deleted.");

                res.setStatus(HttpServletResponse.SC_OK);
                f.toJSON(res.getOutputStream());
            } else {
                LOGGER.warn("Friend not found. Cannot delete it.");

                m = new Message(String.format("Friend not found. Cannot delete it."), "E5A3", null);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch(IndexOutOfBoundsException | NumberFormatException ex) {
            LOGGER.warn("Cannot delete the friend", ex);

            m = new Message("Cannot delete the friend", "E4A7",
                    ex.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            m.toJSON(res.getOutputStream());
        } catch (SQLException ex) {
            if ("23503".equals(ex.getSQLState())) {
                LOGGER.warn("Cannot delete the friend");

                m = new Message("Cannot delete the friend", "E5A4", ex.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } else {
                LOGGER.error("Cannot delete the friend: unexpected database error.", ex);

                m = new Message("Cannot delete the friend: unexpected database error.", "E5A1", ex.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }

}
