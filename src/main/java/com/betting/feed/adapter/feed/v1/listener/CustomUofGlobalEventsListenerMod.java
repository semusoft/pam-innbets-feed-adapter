package com.betting.feed.adapter.feed.v1.listener;

import com.sportradar.unifiedodds.sdk.UofGlobalEventsListener;
import com.sportradar.unifiedodds.sdk.oddsentities.ProducerStatus;
import com.sportradar.unifiedodds.sdk.oddsentities.RecoveryInitiated;
import com.sportradar.utils.Urn;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUofGlobalEventsListenerMod implements UofGlobalEventsListener {

    public CustomUofGlobalEventsListenerMod() {}

    @Override
    public void onConnectionDown() {
        log.warn("Connection DOWN detected!");
    }

    @Override
    public void onConnectionException(Throwable throwable) {
        log.error("Connection Exception occurred!", throwable);
    }

    @Override
    public void onEventRecoveryCompleted(Urn eventId, long requestId) {
        log.info("Event Recovery COMPLETED for eventId={}, requestId={}", eventId, requestId);
    }

    @Override
    public void onProducerStatusChange(ProducerStatus producerStatus) {
        log.info("Producer[{}] status changed: downReason={}",
                producerStatus.getProducer().getId(),
                producerStatus.getProducerStatusReason());
    }

    @Override
    public void onRecoveryInitiated(RecoveryInitiated recoveryInitiated) {
    }
}
