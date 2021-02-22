package hu.progmasters.invite_me.service;

import hu.progmasters.invite_me.domain.Guest;
import hu.progmasters.invite_me.domain.InviteEvent;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

@Service
public class MailService {

    private final String BASE_URL = "http://localhost:4200/";

    public void sendMail(String to, String subject, String content) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("inviteme.noreply@gmail.com", "hlqljqfwhejogbrv");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("inviteme.noreply@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(subject);
        msg.setContent(content, "text/html; charset=UTF-8");
        msg.setSentDate(new Date());
        Transport.send(msg);
    }

    public String getGuestSubject(String eventName) {
        return "You have an Invitation to: " + eventName;
    }

    public String getGuestContent(InviteEvent event, Guest guest) {
        String replyUrl = BASE_URL + event.getHash() + "/" + guest.getHash();
        return "<!DOCTYPE html><html><head><meta charset=\"utf-8\"></head><body>" +
                "<h3>Greetings " + guest.getName() + "!</h3> " +
                "<p> You have been invited to <strong>" + event.getEventName() + "</strong>" +
                " on <strong>" +
                event.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                "</strong></p><p>" +
                event.getInvitationContext().replaceAll("(\r\n|\n)", "<br />") +
                "</p><h4> Please reply at the link below: </h4>" +
                "<h3><a href=\"" + replyUrl + "\">" + replyUrl + "</a></h3>" +
                "</body></html>";
    }

    public String getFounderSubject(String eventName) {
        return "Your event page link for " + eventName;
    }

    public String getFounderContent(InviteEvent event) {
        String eventUrl = BASE_URL + "check/" + event.getHash();
        return "<!DOCTYPE html><html><head><meta charset=\"utf-8\"></head><body>" +
                "<h3>Thank you for using invite.me!</h3> " +
                "<p> Your event page to <strong>" + event.getEventName() + "</strong> can be reached at the link below</p><br>" +
                "<h3><a href=\"" + eventUrl + "\">" + eventUrl + "</a></h3>" +
                "</body></html>";
    }
}
