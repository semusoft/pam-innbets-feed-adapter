package com.betting.feed.adapter.feed.v1.listener;

import com.sportradar.unifiedodds.sdk.UofGlobalEventsListener;
import com.sportradar.unifiedodds.sdk.oddsentities.ProducerStatus;
import com.sportradar.unifiedodds.sdk.oddsentities.RecoveryInitiated;
import com.sportradar.utils.Urn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomUofGlobalEventsListener implements UofGlobalEventsListener {

    private static final Logger log = LoggerFactory.getLogger(CustomUofGlobalEventsListenerMod.class);

    @Override
    public void onConnectionDown() {
        log.warn("⚠️ Connection to Betradar is DOWN! Trying to reconnect... 🔄");
    }

    @Override
    public void onConnectionException(Throwable throwable) {
        log.error("🚨 Connection Exception Occurred: {} ⚡", throwable.getMessage(), throwable);
    }

    @Override
    public void onEventRecoveryCompleted(Urn urn, long timestamp) {
        log.info("✅ Event Recovery Completed for URN: {} at timestamp: {} ⏳", urn, timestamp);
    }

    @Override
    public void onProducerStatusChange(ProducerStatus producerStatus) {
        log.info("🔄 Producer Status Changed: {} 🏆 (Active: {})",
                producerStatus.getProducerStatusReason(), producerStatus.isDown());
    }

    @Override
    public void onRecoveryInitiated(RecoveryInitiated recoveryInitiated) {
        log.info("🛠️ Recovery Process Initiated for Producer: {} (Request ID: {}) 🔄",
                recoveryInitiated.getEventId(), recoveryInitiated.getRequestId());
    }
}

