package hu.progmasters.invite_me.dto;

import hu.progmasters.invite_me.domain.Guest;

public class GuestDetails {
    private String hash;
    private String name;
    private String mail;
    private String response;

    public GuestDetails() {
    }

    public GuestDetails(Guest guest) {
        this.hash = guest.getHash();
        this.name = guest.getName();
        this.mail = guest.getMailAddress();
        this.response = guest.getResponseState().getDisplayName();
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
