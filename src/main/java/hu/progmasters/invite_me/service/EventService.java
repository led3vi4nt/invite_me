package hu.progmasters.invite_me.service;

import hu.progmasters.invite_me.domain.Guest;
import hu.progmasters.invite_me.domain.InviteEvent;
import hu.progmasters.invite_me.domain.InviteResponseState;
import hu.progmasters.invite_me.dto.EventDetails;
import hu.progmasters.invite_me.dto.GuestCommand;
import hu.progmasters.invite_me.dto.EventCommand;
import hu.progmasters.invite_me.dto.ResponseCommand;
import hu.progmasters.invite_me.repository.GuestRepository;
import hu.progmasters.invite_me.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

@Service
@Transactional
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    EventRepository eventRepository;
    GuestRepository guestRepository;
    MailService mailService;

    public EventService(EventRepository eventRepository, GuestRepository guestRepository, MailService mailService) {
        this.eventRepository = eventRepository;
        this.guestRepository = guestRepository;
        this.mailService = mailService;
    }

    public void recordInvite(EventCommand eventCommand) {
        InviteEvent newInviteEvent = new InviteEvent(eventCommand);
        String guestSubject = mailService.getGuestSubject(newInviteEvent.getEventName());
        String founderSubject = mailService.getFounderSubject(newInviteEvent.getEventName());
        for (GuestCommand guestCommand : eventCommand.getGuestList()) {
            Guest newGuest = new Guest(guestCommand);
            String guestContent = mailService.getGuestContent(newInviteEvent, newGuest);
            try {
                mailService.sendMail(newGuest.getMailAddress(), guestSubject, guestContent);
            } catch (MessagingException e) {
                continue;
            }
            guestRepository.save(newGuest);
            newInviteEvent.getGuestList().add(newGuest);
        }
        String founderContent = mailService.getFounderContent(newInviteEvent);
        try {
            mailService.sendMail(newInviteEvent.getReplyAddress(), founderSubject, founderContent);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        InviteEvent event = eventRepository.save(newInviteEvent);
        logger.info("New Event has been recorded (eventHash: '{}')", event.getHash());
    }

    public EventDetails getEventDetails(String hash) {
        InviteEvent event = eventRepository.getOne(hash);
        logger.info("Event details has been requested (eventHash: '{}')", hash);
        return new EventDetails(event);
    }

    public static String getHash() {
        String code = Math.floor(System.currentTimeMillis() * Math.floor(Math.random() * 10000)) + "";
        code = code.replaceAll("[^\\d]", "");
        StringBuilder hash = new StringBuilder();
        for (int i = 0; i < 8; i += 2) {
            long base = Integer.parseInt(code.substring(i, i + 2));
            byte b = (byte) (base % 25 + 65);
            hash.append((char) b);
        }
        logger.info("Hashcode generated: '{}')", hash.toString());
        return hash.toString();
    }

    public void recordResponse(ResponseCommand responseCommand) {
        InviteEvent event = eventRepository.getOne(responseCommand.getHash());
        for (Guest guest : event.getGuestList()) {
            if (guest.getHash().equals(responseCommand.getGuest())) {
                InviteResponseState responseState = InviteResponseState.valueOf(responseCommand.getResponse());
                guest.setResponseState(responseState);
            }
        }
        logger.info("Guest response has been recorded (guestHash: '{}', response: '{}')", responseCommand.getGuest(), responseCommand.getResponse());
    }
}
