package listener;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
/**
 * @author Sina Ramezani, 7/22/2025
 */
/*
Different types of Listeners for different Events

jakarta.servlet.ServletRequestListener
jakarta.servlet.ServletRequestAttributeListener
jakarta.servlet.ServletContextListener
jakarta.servlet.ServletContextAttributeListener
jakarta.servlet.http.HttpSessionListener
jakarta.servlet.http.HttpSessionAttributeListener
jakarta.servlet.http.HttpSessionIdListener
*/
@WebListener
public class StartupShutdownListener  implements ServletContextListener{
    private final String TAG = "StartupShutdownListener: ";

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println(TAG + "Servlet startup...");
        System.out.println(TAG + event.getServletContext().getServerInfo());
        System.out.println(TAG + System.currentTimeMillis());
        sendEmail("Servlet context has initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println(TAG + "Servlet shutdown...");
        System.out.println(TAG + event.getServletContext().getServerInfo());
        System.out.println(TAG + System.currentTimeMillis());
        sendEmail("Servlet context has been destroyed...");
    }

    private void sendEmail(String message) {
        // Gmail SMTP settings
        String smtpHost = "smtp.gmail.com";
        int smtpPort = 587; // TLS port
        String smtpUsername = "Origin@gmail.com"; // your Gmail address
        String smtpPassword = "yourPassword"; // your Gmail App Password
        String to = "Destination@gmail.com"; // recipient email address

        System.out.println(TAG + "sending email...");
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", String.valueOf(smtpPort));
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(smtpUsername, smtpPassword);
                }
            });
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(smtpUsername));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(TAG + " Servlet container event");
            msg.setText(message);
            Transport.send(msg);
            System.out.println(TAG + "email successfully sent");
        } catch (MessagingException ex) {
            System.out.println(TAG + "Failed to send email: " + ex.getMessage());
        }
    }
}
