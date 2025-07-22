package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Sina Ramezani, 7/21/2025
 */
@WebServlet(name = "servlet.SimpleServlet",urlPatterns = {"/servlet.SimpleServlet"})
public class SimpleServlet extends HttpServlet {
    private String message;

    @Override
    public void init() {
        message = "Welcome to Java EE to Jakarta EE 10 Recipes!";
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {// Place page output here
            out.printf("""
                     <html>
                     <head>
                     <title>Simple Servlet</title>
                     </head>
                    <body>
                     <h2>Simple Servlet at %s</h2>
                    <br/>%s
                     </body>
                     </html>
                    """, request.getContextPath(), message);
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