package dominio.Notificacion;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class CorreoAPI {
    private final String username;
    private final String password;

    public CorreoAPI(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void enviarCorreo(String to, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Nuevos incidentes reportados");
            message.setText(body);

            Transport.send(message);

            System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
