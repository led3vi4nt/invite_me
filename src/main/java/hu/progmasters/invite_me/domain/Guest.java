package hu.progmasters.invite_me.domain;

import hu.progmasters.invite_me.dto.GuestCommand;
import hu.progmasters.invite_me.service.EventService;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Guest {

    @Id
    private String hash;

    private String name;

    private String mailAddress;

    private InviteResponseState responseState;

    public Guest() {
    }

    public Guest(GuestCommand guestCommand) {
        this.hash = EventService.getHash();
        this.name = guestCommand.getName();
        this.mailAddress = guestCommand.getMail();
        this.responseState = InviteResponseState.PENDING;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public InviteResponseState getResponseState() {
        return responseState;
    }

    public void setResponseState(InviteResponseState responseState) {
        this.responseState = responseState;
    }
}
