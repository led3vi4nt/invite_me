package hu.progmasters.invite_me.dto;

import java.util.List;

public class EventCommand {

    private String hash;
    private String eventName;
    private String location;
    private String dateTime;
    private String replyAddress;
    private List<GuestCommand> guestList;
    private String invitationContext;

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

    public List<GuestCommand> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<GuestCommand> guestList) {
        this.guestList = guestList;
    }

    public String getInvitationContext() {
        return invitationContext;
    }

    public void setInvitationContext(String invitationContext) {
        this.invitationContext = invitationContext;
    }

    @Override
    public String toString() {
        return "EventCommand{" +
                "hash='" + hash + '\'' +
                ", eventName='" + eventName + '\'' +
                ", location='" + location + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", replyAddress='" + replyAddress + '\'' +
                ", guestList=" + guestList +
                ", invitationContext='" + invitationContext + '\'' +
                '}';
    }
}
