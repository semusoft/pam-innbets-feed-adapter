package com.pam.sportradar.innbets.innbetsfeed.v1.listener;

public enum ActionType {
    ODDS_CHANGE("ODDS_CHANGE"),
    BET_STOP("BET_STOP"),
    BET_SETTLEMENT("BET_SETTLEMENT"),
    ROLLBACK_BET_SETTLEMENT("ROLLBACK_BET_SETTLEMENT"),
    BET_CANCEL("BET_CANCEL"),
    ROLLBACK_BET_CANCEL("ROLLBACK_BET_CANCEL"),
    FIXTURE_CHANGE("FIXTURE_CHANGE"),
    UNPARSABLE_MESSAGE("UNPARSABLE_MESSAGE"),
    USER_UNHANDLED_EXCEPTION("USER_UNHANDLED_EXCEPTION"),
    LIVE_EVENT("LIVE_EVENT"),
    SPORT("SPORT"),
    SPORT_EVENT("SPORT_EVENT");

    private final String value;

    ActionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
