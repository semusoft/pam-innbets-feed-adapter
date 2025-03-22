package com.pam.sportradar.innbets.innbetsfeed.v1.listener;

import com.pam.sportradar.innbets.kafka.Producer;
import com.sportradar.unifiedodds.sdk.UofListener;
import com.sportradar.unifiedodds.sdk.UofSession;
import com.sportradar.unifiedodds.sdk.entities.SportEvent;
import com.sportradar.unifiedodds.sdk.oddsentities.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeedUofListener implements UofListener {

    private final Producer producer;
    private final String priority;

    public FeedUofListener(Producer producer, String priority) {
        this.producer = producer;
        this.priority = priority;
    }

    private void sendMessageToKafka(String eventType, byte[] rawXmlMessage, ActionType actionType) {
        log.debug("📩 Received Event: {} - XML Data: {}", eventType, rawXmlMessage);
        producer.sendTransaction(rawXmlMessage, actionType.getValue(), this.priority);
        log.debug("📤 Event '{}' sent to Kafka ✅", eventType);
    }

    @Override
    public void onOddsChange(UofSession uofSession, OddsChange<SportEvent> oddsChange) {
        log.debug("📌 ON ODDS CHANGE: Session={} - Data={}", uofSession, oddsChange);
        sendMessageToKafka("OddsChange", oddsChange.getRawMessage(), ActionType.ODDS_CHANGE);
    }

    @Override
    public void onBetStop(UofSession uofSession, BetStop<SportEvent> betStop) {
        log.debug("📌 ON BET STOP: Session={} - Data={}", uofSession, betStop);
        sendMessageToKafka("BetStop", betStop.getRawMessage(), ActionType.BET_STOP);
    }

    @Override
    public void onBetSettlement(UofSession uofSession, BetSettlement<SportEvent> betSettlement) {
        log.debug("📌 ON BET SETTLEMENT: Session={} - Data={}", uofSession, betSettlement);
        sendMessageToKafka("BetSettlement", betSettlement.getRawMessage(), ActionType.BET_SETTLEMENT);
    }

    @Override
    public void onRollbackBetSettlement(UofSession uofSession, RollbackBetSettlement<SportEvent> rollbackBetSettlement) {
        log.debug("📌 ON ROLLBACK BET SETTLEMENT: Session={} - Data={}", uofSession, rollbackBetSettlement);
        sendMessageToKafka("RollbackBetSettlement", rollbackBetSettlement.getRawMessage(), ActionType.ROLLBACK_BET_SETTLEMENT);
    }

    @Override
    public void onBetCancel(UofSession uofSession, BetCancel<SportEvent> betCancel) {
        log.debug("📌 ON BET CANCEL: Session={} - Data={}", uofSession, betCancel);
        sendMessageToKafka("BetCancel", betCancel.getRawMessage(), ActionType.BET_CANCEL);
    }

    @Override
    public void onRollbackBetCancel(UofSession uofSession, RollbackBetCancel<SportEvent> rollbackBetCancel) {
        log.debug("📌 ON ROLLBACK BET CANCEL: Session={} - Data={}", uofSession, rollbackBetCancel);
        sendMessageToKafka("RollbackBetCancel", rollbackBetCancel.getRawMessage(), ActionType.ROLLBACK_BET_CANCEL);
    }

    @Override
    public void onFixtureChange(UofSession uofSession, FixtureChange<SportEvent> fixtureChange) {
        log.debug("📌 ON FIXTURE CHANGE: Session={} - Data={}", uofSession, fixtureChange);
        sendMessageToKafka("FixtureChange", fixtureChange.getRawMessage(), ActionType.FIXTURE_CHANGE);
    }

    @Override
    public void onUnparsableMessage(UofSession uofSession, UnparsableMessage unparsableMessage) {
        log.warn("⚠️ UNPARSABLE MESSAGE: Session={} - Data={}", uofSession, unparsableMessage);
        //sendMessageToKafka("UnparsableMessage", unparsableMessage.getRawMessage(), ActionType.UNPARSABLE_MESSAGE);
    }

    @Override
    public void onUserUnhandledException(UofSession uofSession, Exception e) {
        log.error("🔥 UNHANDLED EXCEPTION: Session={} - Exception={}", uofSession, e.getMessage(), e);
        //sendMessageToKafka("UserUnhandledException", e.getMessage().getBytes(), ActionType.USER_UNHANDLED_EXCEPTION);
    }
}