package com.betting.feed.adapter.feed.v1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportradar.unifiedodds.sdk.UofSdk;
import com.sportradar.unifiedodds.sdk.entities.Competition;
import com.sportradar.unifiedodds.sdk.entities.Competitor;
import com.sportradar.unifiedodds.sdk.entities.Sport;
import com.sportradar.unifiedodds.sdk.entities.SportEvent;
import com.sportradar.unifiedodds.sdk.entities.markets.MarketDescription;
import com.sportradar.utils.Urn;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SdkService extends BaseService {
    private final UofSdk prematchFeed;
    private final RedisService redisService;
    private final ObjectMapper objectMapper;

    public SdkService(UofSdk prematchFeed, RedisService redisService, ObjectMapper objectMapper) {
        this.prematchFeed = prematchFeed;
        this.redisService = redisService;
        this.objectMapper = objectMapper;
        super.logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
        super.className = this.getClass().getSimpleName();

    }

    public List<Sport> getSportsAndCategories(String lang, String logId) {
        final String methodName = "getSports";
        Locale localeLang = Locale.forLanguageTag(lang);
        List<Sport> sports = prematchFeed.getSportDataProvider().getSports(localeLang);
        logMessage(methodName, logId, "Sport extracted: " + sports.size());
        logOut(methodName, logId);
        return sports;
    }

    public List<Competition> getCompetitionsByDay(String date, String logId) {
        final String methodName = "getDailyCompetition";
        logIn(methodName, logId);
        List<Competition> competitionList = prematchFeed.getSportDataProvider().getCompetitionsFor(new Date(), Locale.forLanguageTag(getLang()));
        logMessage(methodName, logId, "Daily competitions extracted: " + competitionList.size());
        logOut(methodName, logId);
        return competitionList;
    }

    public Competitor getCompetitorById(Urn urn, String lang, String logId) {
        final String methodName = "getDailyCompetition";
        logIn(methodName, logId);
        Locale localeLang = Locale.forLanguageTag(lang);
        Competitor competitor =  prematchFeed.getSportDataProvider().getCompetitor(urn, localeLang);
        logMessage(methodName, logId, "Competitor extracted: " + competitor);
        logOut(methodName, logId);
        return competitor;
    }

    public List<SportEvent> getSportActiveTournaments(String lang, String sport, String logId) {
        final String methodName = "getSportActiveTournaments";
        logIn(methodName, logId);
        Locale localeLang = Locale.forLanguageTag(getLang());
        List<SportEvent> activeTournamentList = prematchFeed.getSportDataProvider().getActiveTournaments(sport, localeLang);
        logMessage(methodName, logId, "Active tournament extracted: " + activeTournamentList.size());
        logOut(methodName, logId);
        return activeTournamentList;
    }

    public List<MarketDescription> getMarketsDescriptions(String lang, String logId) {
        final String methodName = "getMarketsDescriptions";
        logIn(methodName, logId);
        Locale localeLang = Locale.forLanguageTag(getLang());
        List<MarketDescription> marketDescriptionList = prematchFeed.getMarketDescriptionManager().getMarketDescriptions(localeLang);
        logMessage(methodName, logId, "Markets extracted: " + marketDescriptionList.size());
        logOut(methodName, logId);
        return marketDescriptionList;
    }
}
