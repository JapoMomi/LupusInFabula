package it.unipd.dei.webapp.lupus.rest;

import it.unipd.dei.webapp.lupus.dao.GetActionByIdGameDAO;
import it.unipd.dei.webapp.lupus.filter.UserFilter;
import it.unipd.dei.webapp.lupus.resource.*;
import it.unipd.dei.webapp.lupus.utils.GamePhase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static it.unipd.dei.webapp.lupus.utils.ErrorCode.LOGS_NOT_EXIST;

/**
 * class to manege the log of a game
 *
 * @author LupusInFabula Group
 * @version 1.0
 * @since 1.0
 */
public class GameLogGetRR extends AbstractRR
{
    /**
     * interface to create ab object that represent a function that don't have a return parameter and take as parameter the request and response for a url
     * */
    private interface HttpServletFunct {public void exe(HttpServletRequest request, HttpServletResponse response);}

    /**
     * numeric identifier for the game
     * */
    private final int idPart;
    /**
     * boolean that represent if a user logged is a master or not
     * */
    private final boolean isMaster;
    /***
     * username of the logged in player
     */
    private final String nmPlayer;


    /**
     * Constructor for the class
     *
     * @param  request  request arrive from the server
     * @param  response response to return
     * @param  isMaster boolean that represent if a user logged is a master or not
     * @param  idPart numeric identifier for the game
     */
    public GameLogGetRR(int idPart, boolean isMaster, final HttpServletRequest request, final HttpServletResponse response, DataSource ds)
    {
        super(Actions.GET_LOGS, request, response, ds);
        this.idPart = idPart;
        this.isMaster = isMaster;
        this.nmPlayer =((Player) request.getSession().getAttribute(UserFilter.USER_ATTRIBUTE)).getUsername();
    }

    /**
     * function to serve the te request make to the url game/logs/{idPart} and game/logs/{idPart}/master
     */
    @Override
    protected void doServe() throws IOException
    {
        HttpServletFunct a = (req, res)->
        {
            Message m = null;

            try
            {
                ArrayList<Action> r = this.getLog();
                if (r != null)
                {
                    LOGGER.info(String.format("Action successfully listed for game %d.", idPart));
                    res.setStatus(HttpServletResponse.SC_OK);
                    new ResourceList<Action>(r).toJSON(res.getOutputStream());
                }
                else
                {
                    LOGGER.error("Fatal error while listing Action or logs not exist.");

                    m = new Message("Cannot list Action: unexpected error or log not exist.", LOGS_NOT_EXIST.getErrorCode(),  null);
                    res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    m.toJSON(res.getOutputStream());
                }
            }
            catch (SQLException e) {LOGGER.error("Cannot list Action: unexpected database error.", e);}
            catch (IOException e)  {LOGGER.error("Cannot list Action: unexpected input output error.", e);}//to do change error

        };

        log(a, req, res);
    }

    /**
     * function to get all log from db
     */
    private ArrayList<Action> getLog() throws SQLException
    {
        ArrayList<Action> r = new GetActionByIdGameDAO(ds.getConnection(), idPart).access().getOutputParam();

        if(!this.isMaster && r.size()>0)
            r.removeIf(x -> (!x.getPlayer().equals(nmPlayer) && x.getPhase()==GamePhase.NIGHT.ordinal()));

        return r;
    }

    /**
     * function to get all log from db
     * @param  request  request arrive from the server
     * @param  response response to return
     * @param  function function to exec between LogContext
     */
    private void log(HttpServletFunct function, HttpServletRequest request, HttpServletResponse response)
    {
        LogContext.setIPAddress(request.getRemoteAddr());
        LogContext.setGame(idPart);
        LogContext.setUser(nmPlayer);

        function.exe(request, response);

        LogContext.removeIPAddress();
        LogContext.removeGame();
        LogContext.removeUser();
    }
}
