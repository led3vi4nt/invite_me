package hu.progmasters.invite_me;

import hu.progmasters.invite_me.service.MailService;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;

public class MailTest {

    @Test

    public void mailTest() throws MessagingException {
        MailService mailService = new MailService();
        mailService.sendMail("antal.gyuri.88@gmail.com","subj","content");
    }
}
