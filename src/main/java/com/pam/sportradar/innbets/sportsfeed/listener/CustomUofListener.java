package com.pam.sportradar.innbets.sportsfeed.listener;

import com.pam.sportradar.innbets.kafka.Producer;
import com.pam.sportradar.innbets.kafka.Transaction;
import com.sportradar.unifiedodds.sdk.UofListener;
import com.sportradar.unifiedodds.sdk.UofSession;
import com.sportradar.unifiedodds.sdk.entities.SportEvent;
import com.sportradar.unifiedodds.sdk.oddsentities.*;
import lombok.extern.slf4j.Slf4j;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Slf4j
public class CustomUofListener implements UofListener {

    private final Producer producer;

    public CustomUofListener(Producer producer) {
        this.producer = producer;
    }

    protected Transaction createTransaction(String info, ActionType action) {
        String transactionId = UUID.randomUUID().toString();
        Timestamp now = Timestamp.from(Instant.now());
        return new Transaction(transactionId, now, action.getValue(), info);
    }

    private void sendMessageToKafka(String eventType, String eventData, ActionType actionType) {
        log.info("📩 Received Event: {} - Data: {}", eventType, eventData);
        producer.sendTransaction(eventData, actionType.getValue());
        log.info("📤 Event '{}' sent to Kafka ✅", eventType);
    }

    @Override
    public void onOddsChange(UofSession uofSession, OddsChange<SportEvent> oddsChange) {
        sendMessageToKafka("OddsChange", oddsChange.toString(), ActionType.ODDS_CHANGE);
    }

    @Override
    public void onBetStop(UofSession uofSession, BetStop<SportEvent> betStop) {
        sendMessageToKafka("BetStop", betStop.toString(), ActionType.BET_STOP);
    }

    @Override
    public void onBetSettlement(UofSession uofSession, BetSettlement<SportEvent> betSettlement) {
        sendMessageToKafka("BetSettlement", betSettlement.toString(), ActionType.BET_SETTLEMENT);
    }

    @Override
    public void onRollbackBetSettlement(UofSession uofSession, RollbackBetSettlement<SportEvent> rollbackBetSettlement) {
        sendMessageToKafka("RollbackBetSettlement", rollbackBetSettlement.toString(), ActionType.ROLLBACK_BET_SETTLEMENT);
    }

    @Override
    public void onBetCancel(UofSession uofSession, BetCancel<SportEvent> betCancel) {
        sendMessageToKafka("BetCancel", betCancel.toString(), ActionType.BET_CANCEL);
    }

    @Override
    public void onRollbackBetCancel(UofSession uofSession, RollbackBetCancel<SportEvent> rollbackBetCancel) {
        sendMessageToKafka("RollbackBetCancel", rollbackBetCancel.toString(), ActionType.ROLLBACK_BET_CANCEL);
    }

    @Override
    public void onFixtureChange(UofSession uofSession, FixtureChange<SportEvent> fixtureChange) {
        sendMessageToKafka("FixtureChange", fixtureChange.toString(), ActionType.FIXTURE_CHANGE);
    }

    @Override
    public void onUnparsableMessage(UofSession uofSession, UnparsableMessage unparsableMessage) {
        log.warn("⚠️ Unparsable Message Received: {}", unparsableMessage);
        sendMessageToKafka("UnparsableMessage", unparsableMessage.toString(), ActionType.UNPARSABLE_MESSAGE);
    }

    @Override
    public void onUserUnhandledException(UofSession uofSession, Exception e) {
        log.error("🔥 Unhandled Exception: {}", e.getMessage(), e);
        sendMessageToKafka("UserUnhandledException", e.toString(), ActionType.USER_UNHANDLED_EXCEPTION);
    }
}
