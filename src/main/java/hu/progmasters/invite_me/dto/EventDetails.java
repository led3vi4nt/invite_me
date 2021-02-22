package hu.progmasters.invite_me.dto;

import hu.progmasters.invite_me.domain.InviteEvent;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class EventDetails {
    private String hash;

    private String eventName;
    private String location;
    private String dateTime;
    private String replyAddress;
    private List<GuestDetails> guestList;
    public EventDetails(InviteEvent event) {
        this.hash = event.getHash();
        this.eventName = event.getEventName();
        this.location = event.getLocation();
        this.dateTime = event.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.replyAddress = event.getReplyAddress();
        this.guestList = event.getGuestList().stream().map(GuestDetails::new).collect(Collectors.toList());
    }

    public EventDetails() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getReplyAddress() {
        return replyAddress;
    }

    public void setReplyAddress(String replyAddress) {
        this.replyAddress = replyAddress;
    }

    public List<GuestDetails> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<GuestDetails> guestList) {
        this.guestList = guestList;
    }
}
