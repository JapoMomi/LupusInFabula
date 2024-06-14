package it.unipd.dei.webapp.lupus.filter;

import it.unipd.dei.webapp.lupus.dao.AuthenticateTokenDAO;
import it.unipd.dei.webapp.lupus.servlet.LoginSignupServlet;
import it.unipd.dei.webapp.lupus.servlet.LoginSignupServlet.*;
import it.unipd.dei.webapp.lupus.resource.*;
import it.unipd.dei.webapp.lupus.utils.ErrorCode;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
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

import static it.unipd.dei.webapp.lupus.servlet.LoginSignupServlet.LoginToken;

/**
 * Filter to manage access to the protected resources.
 *
 * @author LupusInFabula Group
 * @version 1.0
 * @since 1.0
 */
public class UserFilter implements Filter {
    /**
     * A LOGGER available for all the subclasses.
     */
    protected static final Logger LOGGER = LogManager.getLogger(UserFilter.class,
            StringFormatterMessageFactory.INSTANCE);


    /**
     * The Base64 Decoder
     */
    private static final Base64.Decoder DECODER = Base64.getDecoder();

    /**
     * The name of the user attribute in the session
     */
    public static final String USER_ATTRIBUTE = "user";

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
        LogContext.setAction(Actions.AUTHENTICATE_USER);

        try {
            if (!(servletRequest instanceof HttpServletRequest req) || !(servletResponse instanceof HttpServletResponse res)) {
                LOGGER.error("Only HTTP requests/responses are allowed.");
                throw new ServletException("Only HTTP requests/responses are allowed.");
            }

            LOGGER.info("request URL =  %s", req.getRequestURL());

            final HttpSession session = req.getSession(false);

            // if we do not have a session, try to authenticate the user
            if (session == null) {

                Player p = getPlayerByToken(req);
                if (p == null) {
                    LOGGER.warn("Authentication required to access resource %s with method %s.", req.getRequestURI(),
                            req.getMethod());

                    ErrorCode ec = ErrorCode.NOT_LOGGED;
                    res.setStatus(ec.getHTTPCode());
                    Message m = new Message("Authentication required", ec.getErrorCode(), ec.getErrorMessage());

                    m.toJSON(res.getOutputStream());

                    return;
                }

                HttpSession newSession = req.getSession();
                newSession.setAttribute(UserFilter.USER_ATTRIBUTE, p);


            } else {
                final Player player = (Player) session.getAttribute(USER_ATTRIBUTE);

                // there might exist a session but without any user in it
                if (player == null) {
                    Player p = getPlayerByToken(req);
                    if (p == null) {
                        // invalidate the session
                        session.invalidate();

                        LOGGER.warn(
                                "Authentication required to access resource %s with method %s. Session %s exists but no user found in session. Session invalidated.",
                                req.getRequestURI(), req.getMethod(), session.getId());

                        ErrorCode ec = ErrorCode.NOT_LOGGED;
                        res.setStatus(ec.getHTTPCode());
                        Message m = new Message("Authentication required, not logged in", ec.getErrorCode(), ec.getErrorMessage());

                        m.toJSON(res.getOutputStream());

                        res.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
                        return;
                    }

                    HttpSession newSession = req.getSession();
                    newSession.setAttribute(UserFilter.USER_ATTRIBUTE, p);
                }
            }

            // the user is properly authenticated and in session, continue the processing
            chain.doFilter(servletRequest, servletResponse);
        } catch (SQLException ex) {
            LOGGER.error("Unable to perform the protected resource filtering.", ex);
        } catch (Exception e) {
            LOGGER.error("Unable to perform the protected resource filtering.", e);
            throw e;
        } finally {
            LogContext.removeUser();
            LogContext.removeIPAddress();
            LogContext.removeAction();
        }
    }


    /**
     * Retrieves a player object based on the session token stored in the request cookies.
     *
     * @param request the HttpServletRequest object containing the incoming request.
     * @return the Player object associated with the session token, or null if the token is not found or invalid.
     * @throws SQLException if a database access error occurs.
     */
    public Player getPlayerByToken(HttpServletRequest request) throws SQLException {
        String token = "";

        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                LOGGER.info("cookie: " + cookie.getName() + " " + cookie.getValue());
                if (LoginToken.equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }

        if (token.isEmpty())
            return null; // Return null if loginToken cookie is not found

        return new AuthenticateTokenDAO(ds.getConnection(), token).access().getOutputParam();
    }

    @Override
    public void destroy() {
        config = null;
        ds = null;
    }
}
