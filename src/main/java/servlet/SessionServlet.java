package servlet;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/**
 * @author Sina Ramezani, 7/22/2025
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/SessionServlet"})
public class SessionServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // Obtain the Session object
        HttpSession session = req.getSession(true);
        // Set up a session attribute
        String email = (String)
                session.getAttribute("session.email");
        if (email == null) {
            email = req.getParameter("email");
            session.setAttribute("session.email", email);
        }
        String sessionId = session.getId();
        res.setContentType("text/html");
        try (PrintWriter out = res.getWriter()) {
            out.printf("""
                     <html>
                     <head>
                     <title>Working with sessions</title>
                     </head>
                    <body>
                     <h1>Session Test</h1>
                    Your email address is: %s <br/>
                    Your session id: %s
                     </body>
                     </html>""", email, sessionId);
        }
    }
}