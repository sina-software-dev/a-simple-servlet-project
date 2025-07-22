package listener;


import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;
/**
 * @author Sina Ramezani, 7/22/2025
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    private int numberOfSessions;
    public SessionListener() {
        numberOfSessions = 0;
    }
    public int getNumberOfSessions() {
        return numberOfSessions;
    }
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        session.setMaxInactiveInterval(60);
        session.setAttribute("testAttr", "testVal");
        synchronized (this) {
            numberOfSessions++;
        }
        System.out.println("Session created, current count: " + numberOfSessions);
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        synchronized (this) {
            numberOfSessions--;
        }
        System.out.println("Session destroyed, current count: " + numberOfSessions);
        System.out.println("The attribute value: " + session.getAttribute(("testAttr")));
    }
}
