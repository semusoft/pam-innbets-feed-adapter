package com.betting.feed.adapter.commons;

public enum FeedType {
    Prematch("PREMATCH"),
    Live("LIVE");

    private String value;

    public String getValue() {
        return this.value;
    }

    FeedType(String value) {
        this.value = value;
    }
}
