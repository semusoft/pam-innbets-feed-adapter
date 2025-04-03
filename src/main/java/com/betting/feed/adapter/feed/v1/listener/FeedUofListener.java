package com.betting.feed.adapter.feed.v1.listener;

import com.betting.feed.adapter.kafka.Producer;
import com.betting.feed.adapter.commons.FeedType;
import com.sportradar.unifiedodds.sdk.UofListener;
import com.sportradar.unifiedodds.sdk.UofSession;
import com.sportradar.unifiedodds.sdk.entities.SportEvent;
import com.sportradar.unifiedodds.sdk.oddsentities.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeedUofListener implements UofListener {

    private final com.betting.feed.adapter.kafka.Producer producer;
    private final FeedType feedType;

    public FeedUofListener(Producer producer, FeedType feedType) {
        this.producer = producer;
        this.feedType = feedType;
    }
    
    protected boolean isLive(FeedType feedType) {
        return this.feedType.getValue().equals("LIVE");
    }

    private void sendMessageToKafkaServer(byte[] message, ActionType actionType) {
        log.debug("📩 Received Event: {} - XML Data: {}", message);
        producer.sendTransaction(message, actionType.getValue(), isLive(this.feedType));
        log.debug("📤 Event '{}' sent to Kafka ✅", actionType.getValue());
    }

    @Override
    public void onOddsChange(UofSession uofSession, OddsChange<SportEvent> oddsChange) {
        log.debug("📌 ON ODDS CHANGE: Session={} - Data={}", uofSession, oddsChange);
        sendMessageToKafkaServer(oddsChange.getRawMessage(), ActionType.ODDS_CHANGE);
    }

    @Override
    public void onBetStop(UofSession uofSession, BetStop<SportEvent> betStop) {
        log.debug("📌 ON BET STOP: Session={} - Data={}", uofSession, betStop);
        sendMessageToKafkaServer(betStop.getRawMessage(), ActionType.BET_STOP);
    }

    @Override
    public void onBetSettlement(UofSession uofSession, BetSettlement<SportEvent> betSettlement) {
        log.debug("📌 ON BET SETTLEMENT: Session={} - Data={}", uofSession, betSettlement);
        sendMessageToKafkaServer(betSettlement.getRawMessage(), ActionType.BET_SETTLEMENT);
    }

    @Override
    public void onRollbackBetSettlement(UofSession uofSession, RollbackBetSettlement<SportEvent> rollbackBetSettlement) {
        log.debug("📌 ON ROLLBACK BET SETTLEMENT: Session={} - Data={}", uofSession, rollbackBetSettlement);
        sendMessageToKafkaServer(rollbackBetSettlement.getRawMessage(), ActionType.ROLLBACK_BET_SETTLEMENT);
    }

    @Override
    public void onBetCancel(UofSession uofSession, BetCancel<SportEvent> betCancel) {
        log.debug("📌 ON BET CANCEL: Session={} - Data={}", uofSession, betCancel);
        sendMessageToKafkaServer(betCancel.getRawMessage(), ActionType.BET_CANCEL);
    }

    @Override
    public void onRollbackBetCancel(UofSession uofSession, RollbackBetCancel<SportEvent> rollbackBetCancel) {
        log.debug("📌 ON ROLLBACK BET CANCEL: Session={} - Data={}", uofSession, rollbackBetCancel);
        sendMessageToKafkaServer(rollbackBetCancel.getRawMessage(), ActionType.ROLLBACK_BET_CANCEL);
    }

    @Override
    public void onFixtureChange(UofSession uofSession, FixtureChange<SportEvent> fixtureChange) {
        log.debug("📌 ON FIXTURE CHANGE: Session={} - Data={}", uofSession, fixtureChange);
        sendMessageToKafkaServer(fixtureChange.getRawMessage(), ActionType.FIXTURE_CHANGE);
    }

    @Override
    public void onUnparsableMessage(UofSession uofSession, UnparsableMessage unparsableMessage) {
        log.warn("⚠️ UNPARSABLE MESSAGE: Session={} - Data={}", uofSession, unparsableMessage);
        //sendMessageToKafkaServer("UnparsableMessage", unparsableMessage.getRawMessage(), ActionType.UNPARSABLE_MESSAGE);
    }

    @Override
    public void onUserUnhandledException(UofSession uofSession, Exception e) {
        log.error("🔥 UNHANDLED EXCEPTION: Session={} - Exception={}", uofSession, e.getMessage(), e);
        //sendMessageToKafkaServer("UserUnhandledException", e.getMessage().getBytes(), ActionType.USER_UNHANDLED_EXCEPTION);
    }
}