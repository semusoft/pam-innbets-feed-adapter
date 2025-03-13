package com.pam.sportradar.innbets.sportsfeed.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import java.util.UUID;

public abstract class BaseService {

    protected Logger logger;

    @Getter
    @Value("${feed.lang}")
    protected String lang;

    protected String className;

    public BaseService() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    protected String getLogId() {
        return UUID.randomUUID().toString();
    }

    protected void logIn(String methodName, String logId) {
        logger.info("[{}-{}] IN", logId, methodName);
    }

    protected void logOut(String methodName, String logId) {
        logger.info("[{}-{}] OUT", logId, methodName);
    }

    protected void logMessage(String methodName, String logId, String message) {
        logger.info("[{}-{}] {}", logId, methodName, message);
    }

    @PostConstruct
    private void initLogger() {
        logger = LoggerFactory.getLogger(getClass());
    }

    protected String generateLogId() {
        return UUID.randomUUID().toString();
    }
}
