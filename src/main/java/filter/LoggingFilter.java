package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * @author Sina Ramezani, 7/22/2025
 */
@WebFilter("/*")
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String userIP = servletRequest.getRemoteHost();
        System.out.println("Visitor User IP: " + userIP);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
