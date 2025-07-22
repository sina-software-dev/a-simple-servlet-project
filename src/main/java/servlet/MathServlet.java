package servlet;

import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;

/**
 * @author Sina Ramezani, 7/22/2025
 */
@WebServlet(name = "servlet.MathServlet", urlPatterns = {"/servlet.MathServlet"})
public class MathServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.setContentType("text/html");
        // Store the input parameter values into Strings
        String numA = req.getParameter("numa");
        String numB = req.getParameter("numb");
        int solution = 0;
        try (PrintWriter out = res.getWriter()) {
            solution = Integer.parseInt(numA) + Integer.parseInt(numB);
            out.printf("""
                     <html>
                     <head>
                     <title>Test Math Servlet</title>
                     </head>
                     <body>
                     Solution: %d + %d = %d
                    <br/>
                     <a href='form.html'>Add Two More Numbers</a>
                     </body>
                     </html>""", Integer.parseInt(numA), Integer.parseInt(numB), solution);
        }
    }
}
