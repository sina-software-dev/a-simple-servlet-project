package servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
/**
 * @author Sina Ramezani, 7/24/2025
 */

@WebServlet(name = "DownloadServlet", value = "/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Read parameter from form that contains the file name to download
        String fileToDownload = request.getParameter("filename");
        // Call the download method with the given file
        System.err.println("Downloading file now...");
        doDownload(request, response, fileToDownload);
    }
    /**
     * Send a file to the output stream.
     *
     * @param request The request
     * @param response The response
     * @param originalFile The name the browser should receive.
     */
    private void doDownload( HttpServletRequest request, HttpServletResponse response,
                             String originalFile) throws IOException {
        final int BYTES = 1024;
        int length;
        ServletOutputStream outStream = response.getOutputStream();
        ServletContext context = getServletConfig().getServletContext();
        response.setContentType((context.getMimeType( originalFile ) != null) ?
                context.getMimeType( originalFile ) : "text/plain" );
        response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFile
                + "\"" );
        InputStream in = context.getResourceAsStream("/" + originalFile);
        byte[] bbuf = new byte[BYTES];
        while ((in != null) && ((length = in.read(bbuf)) != -1))
        {outStream.write(bbuf,0,length);
        }
        outStream.flush();
        outStream.close();
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
