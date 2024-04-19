package it.unipd.dei.webapp.lupus.filter;

import it.unipd.dei.webapp.lupus.dao.GetGameIdFormPubblicGameIdDAO;
import it.unipd.dei.webapp.lupus.dao.GetMasterFromIdGameDAO;
import it.unipd.dei.webapp.lupus.dao.LoginPlayerDAO;
import it.unipd.dei.webapp.lupus.resource.Actions;
import it.unipd.dei.webapp.lupus.resource.LogContext;
import it.unipd.dei.webapp.lupus.resource.Message;
import it.unipd.dei.webapp.lupus.resource.Player;
import it.unipd.dei.webapp.lupus.utils.ErrorCode;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

public class GameMasterFilter implements Filter {
    /**
     * A LOGGER available for all the subclasses.
     */
    protected static final Logger LOGGER = LogManager.getLogger(GameMasterFilter.class,
            StringFormatterMessageFactory.INSTANCE);


    /**
     * The Base64 Decoder
     */
    private static final Base64.Decoder DECODER = Base64.getDecoder();

    /**
     * The name of the user attribute in the session
     */
    public static final String GAMEMASTER_ATTRIBUTE = "mater";

    /**
     * The configuration for the filter
     */
    private FilterConfig config = null;

    /**
     * The connection pool to the database.
     */
    private DataSource ds;


    @Override
    public void init(final FilterConfig config) throws ServletException {

        if (config == null) {
            LOGGER.error("Filter configuration cannot be null.");
            throw new ServletException("Filter configuration cannot be null.");
        }
        this.config = config;

        /*
        Here we could pass configuration parameters to the filter, if needed.
         */

        // the JNDI lookup context
        InitialContext cxt;

        try {
            cxt = new InitialContext();
            ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/lupusdb");
        } catch (NamingException e) {
            ds = null;

            LOGGER.error("Unable to acquire the connection pool to the database.", e);
            throw new ServletException("Unable to acquire the connection pool to the database", e);
        }
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws
            IOException, ServletException {

        LogContext.setIPAddress(servletRequest.getRemoteAddr());

        try {
            if (!(servletRequest instanceof HttpServletRequest req) || !(servletResponse instanceof HttpServletResponse res)) {
                LOGGER.error("Only HTTP requests/responses are allowed.");
                throw new ServletException("Only HTTP requests/responses are allowed.");
            }

            LOGGER.info("request URL =  %s", req.getRequestURL());
            String path = req.getRequestURI();

            // this filter accept only request that start with /game/*
            if (path.endsWith("/master")) {
                // if the path contain /master check if the user is the master in the game

                final HttpSession session = req.getSession(false);

                path = path.substring(path.lastIndexOf("/game/") + 6);
                if (path.contains("log/"))
                    path = path.substring(path.lastIndexOf("log/") + 4);

                final String publicGame = path.substring(0, path.lastIndexOf("/master"));

                LOGGER.info("Pubblic GameId found on URL: " + publicGame);
                int gameID = new GetGameIdFormPubblicGameIdDAO(ds.getConnection(), publicGame).access().getOutputParam();

                // if we do not have a session, try to authenticate the user
                if (session == null) {
                    LOGGER.warn("Authentication required to access resource %s with method %s.", req.getRequestURI(),
                            req.getMethod());

                    ErrorCode ec = ErrorCode.NOT_LOGGED;
                    res.setStatus(ec.getHTTPCode());
                    Message m = new Message("Authentication required, not logged in", "" + ec.getErrorCode(), ec.getErrorMessage());

                    m.toJSON(res.getOutputStream());

                    return; // in this case the master is not even logged in
                } else {

                    final Object gmAttribute = session.getAttribute(GAMEMASTER_ATTRIBUTE);

                    // there might exist a session but without any user in it
                    if (gmAttribute == null) {

                        LOGGER.warn(
                                "Authentication required to access resource %s with method %s. Session %s exists but no GameID found in session.",
                                req.getRequestURI(), req.getMethod(), session.getId());


                        Player currentPlayer = (Player) session.getAttribute(UserFilter.USER_ATTRIBUTE); // master's username
                        LOGGER.info("Trying to authenticate the currentPlayer %s in the game %d", currentPlayer.getUsername(), gameID);

                        String masterOfGame = new GetMasterFromIdGameDAO(ds.getConnection(), gameID).access().getOutputParam();

                        if(masterOfGame == null){
                            LOGGER.warn("There is no game with id %s", publicGame);

                            ErrorCode ec = ErrorCode.NOT_LOGGED;
                            res.setStatus(ec.getHTTPCode());
                            Message m = new Message("There is no game with id " + publicGame, "" + ec.getErrorCode(), ec.getErrorMessage());

                            m.toJSON(res.getOutputStream());
//                            res.sendRedirect(req.getContextPath() + "/jsp/home.jsp");
                            return;
                        }
                        else if (masterOfGame.equals(currentPlayer.getUsername())) {
                            session.setAttribute(GAMEMASTER_ATTRIBUTE, gameID);
                        } else {
                            LOGGER.warn("%s is not the gamemaster in game %s" , currentPlayer.getUsername(), publicGame);

                            ErrorCode ec = ErrorCode.NOT_MASTER;
                            res.setStatus(ec.getHTTPCode());
                            Message m = new Message("You are not the gamemaster in game " + publicGame, "" + ec.getErrorCode(), ec.getErrorMessage());

                            m.toJSON(res.getOutputStream());
//                            res.sendRedirect(req.getContextPath() + "/jsp/home.jsp");
                            return;
                        }

                    } else {
                        int sessionGameID = (int) gmAttribute;
                        // check if it's the same game
                        if (sessionGameID != gameID) {
                            LOGGER.warn("Different gameID");

                            ErrorCode ec = ErrorCode.DIFFERENT_GAMEID;
                            res.setStatus(ec.getHTTPCode());
                            Message m = new Message("Different gameID founded", "" + ec.getErrorCode(), ec.getErrorMessage());

                            m.toJSON(res.getOutputStream());
//                            res.sendRedirect(req.getContextPath() + "/jsp/home.jsp");
                            return;
                        }
                    }
                }
            }

            // the user is properly authenticated and in session, continue the processing
            chain.doFilter(servletRequest, servletResponse);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            LOGGER.error("Unable to perform the protected resource filtering.", e);
            throw e;
        } finally {
            LogContext.removeUser();
            LogContext.removeIPAddress();
            LogContext.removeAction();
        }
    }

    @Override
    public void destroy() {
        config = null;
        ds = null;
    }

}
