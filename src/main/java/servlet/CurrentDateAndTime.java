package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author Sina Ramezani, 7/22/2025
 */
@WebServlet(name = "servlet.CurrentDateAndTime", urlPatterns = {"/servlet.CurrentDateAndTime"})
public class CurrentDateAndTime extends HttpServlet {
    Date currDateAndTime = new Date();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        synchronized (currDateAndTime) {
            currDateAndTime = new Date();
        }
        try (PrintWriter out = response.getWriter()) {
            out.printf("""
                     <html>
                     <head>
                     <title>Servlet Current Date And Time</title>
                     </head>
                     <body>
                     <h1>Servlet servlet.CurrentDateAndTime at %s</h1><br/>
                    The current date and time is: %s
                     </body>
                     </html>""", request.getContextPath(), currDateAndTime);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }
}
