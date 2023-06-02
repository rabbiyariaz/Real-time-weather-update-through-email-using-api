import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.Properties;
public class Email extends Weather_update{
    public void function(String data,String email){
        final  String username="rabbiyariaz2@gmail.com";
        final String password="pmvvhbucvsqmvxoj";
        String recipient = email;
        String subject = "Weather Alarm System";
        String body = data.toString();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props,
               new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });
        try
        {
            InternetAddress[] recipientAddress = InternetAddress.parse(recipient, true);
            System.out.println(recipientAddress);
            if (recipientAddress.length == 0) {
                JOptionPane.showMessageDialog(null, "Invalid email address. Please provide a valid email address.");
                return;
            }
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, recipientAddress);
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Email has been sent successfully.");
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Failed to send email. Please try again later.");
        }
    }
      }

