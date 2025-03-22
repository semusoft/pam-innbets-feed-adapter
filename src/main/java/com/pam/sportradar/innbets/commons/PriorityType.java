package com.pam.sportradar.innbets.commons;

public enum PriorityType {
    HIGH("high"),
    LOW("low");

    private String value;

    public String getValue() {
        return this.value;
    }

    PriorityType(String value) {
        this.value = value;
    }
}
