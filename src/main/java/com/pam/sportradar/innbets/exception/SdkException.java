package com.pam.sportradar.innbets.exception;

import java.sql.Timestamp;

public class SdkException extends RuntimeException {
    private final long code;
    private final Timestamp timestamp;

    public SdkException(String message, long code, Timestamp timestamp) {
        super(message);
        this.code = code;
        this.timestamp = timestamp;
    }
}