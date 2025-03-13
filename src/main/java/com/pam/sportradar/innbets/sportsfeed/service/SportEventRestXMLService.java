package com.pam.sportradar.innbets.sportsfeed.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service
public class SportEventRestXMLService {

    @Value("${uof.protocol-host}")
    private String restApiHost;

    @Value("${uof.access-token}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders headers;

    @Autowired
    public void initHeaders(@Value("${uof.access-token}") String apiKey) {
        headers = new HttpHeaders();
        headers.set("x-access-token", apiKey);
    }

    private String getRequest(String endpoint) {
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.GET, request, String.class);
        return response.getBody();
    }

    private String buildUrl(String endpoint) {
        return String.format("%s/v1/%s", restApiHost, endpoint);
    }

    private String executeRequest(String endpointFormat, Object... args) {
        String formattedEndpoint = String.format(endpointFormat, args);
        String url = buildUrl(formattedEndpoint);
        log.info("Request to URL: {}", url);
        String res = getRequest(url);
        log.info("Response: {}", res);
        return res;
    }

    public String getSchedule(String language, String date) { // OK
        return executeRequest("sports/%s/schedules/%s/schedule.xml", language, date);
    }

    public String getSportEventSummary(String language, String prefix, String type, String id) {
        return executeRequest("sports/%s/sport_events/%s:%s:%s/summary.xml", language, prefix, type, id);
    }

    public String getFixture(String language, String prefix, String type, String id) {
        return executeRequest("sports/%s/sport_events/%s:%s:%s/fixture.xml", language, prefix, type, id);
    }

    public String getLiveSchedule() {
        return executeRequest("sports/en/schedules/live/schedule.xml");
    }

    public String getPreMatchSchedule(String language) {
        return executeRequest("sports/%s/schedules/pre/schedule.xml", language);
    }

    public String getTournamentSchedule(String language, String prefix, String urn, String id) {
        return executeRequest("sports/%s/tournaments/%s:%s:%s/schedule.xml", language, prefix, urn, id);
    }

    public String getFixtureChanges(String language) {
        return executeRequest("sports/%s/fixtures/changes.xml", language);
    }

    public String getAllSports(String language) { // OK
        return executeRequest("sports/%s/sports.xml", language);
    }

    public String getCategories(String language, String prefix, String type, String id) { // OK
        return executeRequest("sports/%s/sports/%s:%s:%s/categories.xml", language, prefix, type, id);
    }

    public String getTournaments(String language) { // OK
        return executeRequest("sports/%s/tournaments.xml", language);
    }

    public String getTournamentDetail(String language, String prefix, String type, String id) { // OK
        return executeRequest("sports/%s/tournaments/%s:%s:%s/info.xml", language, prefix, type, id);
    }

    public String getAvailableSportTournaments(String language, String prefix, String type, String id) { // OK
        return executeRequest("sports/%s/sports/%s:%s:%s/tournaments.xml", language, prefix, type, id);
    }

    public String getAvailableSeasons(String language, String prefix, String type, String id) { // OK
        return executeRequest("sports/%s/tournaments/%s:%s:%s/seasons.xml", language, prefix, type, id);
    }

    public String getCompetitorProfile(String language, String prefix, String type, String id) {
        return executeRequest("sports/%s/competitors/%s:%s:%s/profile.xml", language, prefix, type, id);
    }

    public String getPlayerProfile(String language, String prefix, String id) {
        return executeRequest("sports/%s/players/%s:player:%s/profile.xml", language, prefix, id);
    }

    public String getVenueProfile(String prefix, String type, String id) {
        return executeRequest("sports/en/venues/%s:%s:%s/profile.xml", prefix, type, id);
    }

    public String getOddsRecovery(String product, String urnType, String id) {
        return executeRequest("%s/odds/events/%s:%s:initiate_request", product, urnType, id);
    }

    public String getStatefulMessagesRecovery(String product, String urnType, String id) {
        return executeRequest("%s/stateful_messages/events/%s:%s:initiate_request", product, urnType, id);
    }

    public String getMarketDescription(String language) {
        return executeRequest("descriptions/%s/markets.xml", language);
    }

    public String getVoidReasons() {
        return executeRequest("descriptions/void_reasons.xml");
    }

    public String getBetStopReasons() {
        return executeRequest("descriptions/betting_status.xml");
    }
}
