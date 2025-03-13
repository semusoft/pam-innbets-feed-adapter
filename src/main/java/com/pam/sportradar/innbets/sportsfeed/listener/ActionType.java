package com.pam.sportradar.innbets.sportsfeed.listener;

public enum ActionType {
    ODDS_CHANGE("[ODDS-CHANGE]"),
    BET_STOP("[BET-STOP]"),
    BET_SETTLEMENT("[BET-SETTLEMENT]"),
    ROLLBACK_BET_SETTLEMENT("[ROLLBACK-BET-SETTLEMENT]"),
    BET_CANCEL("[BET-CANCEL]"),
    ROLLBACK_BET_CANCEL("[ROLLBACK-BET-CANCEL]"),
    FIXTURE_CHANGE("[FIXTURE-CHANGE]"),
    UNPARSABLE_MESSAGE("[UNPARSABLE-MESSAGE]"),
    USER_UNHANDLED_EXCEPTION("[USER-UNHANDLED-EXCEPTION]"),
    LIVE_EVENT("[LIVE-EVENT]"),
    SPORT("[SPORT]"),
    SPORT_EVENT("[SPORT-EVENT]");


    private final String value;

    ActionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
