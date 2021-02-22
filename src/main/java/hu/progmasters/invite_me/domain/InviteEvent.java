package hu.progmasters.invite_me.domain;

import hu.progmasters.invite_me.dto.EventCommand;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class InviteEvent {

    @Id
    private String hash;

    private String eventName;

    private LocalDateTime dateTime;

    private String location;

    private String replyAddress;

    @OneToMany
    private List<Guest> guestList;

    private String invitationContext;

    public InviteEvent() {
    }

    public InviteEvent(EventCommand eventCommand) {
        this.hash = eventCommand.getHash();
        this.eventName = eventCommand.getEventName();
        this.dateTime = LocalDateTime.parse(eventCommand.getDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.location = eventCommand.getLocation();
        this.replyAddress = eventCommand.getReplyAddress();
        this.invitationContext = eventCommand.getInvitationContext();
        this.guestList = new ArrayList<>();
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReplyAddress() {
        return replyAddress;
    }

    public void setReplyAddress(String replyAddress) {
        this.replyAddress = replyAddress;
    }

    public List<Guest> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<Guest> guestList) {
        this.guestList = guestList;
    }

    public String getInvitationContext() {
        return invitationContext;
    }

    public void setInvitationContext(String invitationContext) {
        this.invitationContext = invitationContext;
    }
}
