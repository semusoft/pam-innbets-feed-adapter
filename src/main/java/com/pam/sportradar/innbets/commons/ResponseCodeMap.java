package com.pam.sportradar.innbets.commons;

public enum ResponseCodeMap {

    COMPETITOR_NOT_FOUND(-2000, "Competitor not found");

    private String value;
    private long code;
    ResponseCodeMap(long code, String value) {
        this.value = value;
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }

    public long getCode() {
        return this.code;
    }
}
