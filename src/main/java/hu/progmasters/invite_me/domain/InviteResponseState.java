package hu.progmasters.invite_me.domain;

public enum InviteResponseState {
    PENDING("Pending"),
    IS_GOING("Is Going"),
    CANT_GO("Can't Go"),
    NOT_SURE("Not Sure");

    private String displayName;

    InviteResponseState(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
