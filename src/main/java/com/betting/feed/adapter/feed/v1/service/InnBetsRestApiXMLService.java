package com.betting.feed.adapter.feed.v1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class InnBetsRestApiXMLService {
    @Autowired
    private RestTemplate restTemplate;
    private HttpHeaders headers;

    @Value("${uof.protocol-host}")
    private String restApiHost;

    @Value("${uof.access-token}")
    private String apiKey;

    public String getFullOddsRecovery(String product) {
        return executeRequest(HttpMethod.POST, product + "/recovery/initiate_request");
    }

    public String getFullOddsRecovery(String product, Long after, Integer requestId) {
        long currentTime = System.currentTimeMillis();
        long maxAllowedRecoveryMillis;
        if ("liveodds".equalsIgnoreCase(product)) {
            maxAllowedRecoveryMillis = 10L * 60L * 60L * 1000L; // 10 hours
        } else {
            maxAllowedRecoveryMillis = 72L * 60L * 60L * 1000L; // 72 hours
        }
        if (after != null && (currentTime - after) > maxAllowedRecoveryMillis) {
            return "<error>Recovery period exceeds maximum allowed for " + product
                    + " (max allowed: " + (maxAllowedRecoveryMillis / (60 * 60 * 1000)) + " hours).</error>";
        }
        String endpoint = product + "/recovery/initiate_request";
        if (after != null) {
            endpoint += "?after=" + after;
            if (requestId != null) {
                endpoint += "&request_id=" + requestId;
            }
        }
        return executeRequest(HttpMethod.POST, endpoint);
    }

    public String getSportEventOddsRecovery(String product, String urn) {
        return executeRequest(HttpMethod.POST, product + "/odds/events/" + urn + "/initiate_request");
    }

    public String resendSportEventOddsRecovery(String product, String urn) {
        return executeRequest(HttpMethod.POST, product + "/stateful_messages/events/" + urn + "/initiate_request");
    }

    public String getSchedule(String language, String date) {
        return executeRequest(HttpMethod.GET, "sports/%s/schedules/%s/schedule.xml", language, date);
    }

    public String getSportEventSummary(String language, String prefix, String type, String id) {
        return executeRequest(HttpMethod.GET, "sports/%s/sport_events/%s:%s:%s/summary.xml", language, prefix, type, id);
    }

    public String getFixture(String language, String prefix, String type, String id) {
        return executeRequest(HttpMethod.GET, "sports/" + language + "/sport_events/"
                + prefix + ":" + type + ":" + id + "/fixture.xml", language, prefix, type, id);
    }

    public String getLiveSchedule() {
        return executeRequest(HttpMethod.GET, "sports/en/schedules/live/schedule.xml");
    }

    public String getPreMatchSchedule(String language) {
        return executeRequest(HttpMethod.GET, "sports/%s/schedules/pre/schedule.xml", language);
    }

    public String getTournamentSchedule(String language, String prefix, String urn, String id) {
        return executeRequest(HttpMethod.GET, "sports/%s/tournaments/%s:%s:%s/schedule.xml", language, prefix, urn, id);
    }

    public String getFixtureChanges(String language) {
        return executeRequest(HttpMethod.GET, "sports/%s/fixtures/changes.xml", language);
    }

    public String getAllSports(String language) {
        return executeRequest(HttpMethod.GET, "sports/%s/sports.xml", language);
    }

    public String getCategories(String language, String prefix, String type, String id) {
        return executeRequest(HttpMethod.GET, "sports/%s/sports/%s:%s:%s/categories.xml", language, prefix, type, id);
    }

    public String getTournaments(String language) {
        return executeRequest(HttpMethod.GET, "sports/%s/tournaments.xml", language);
    }

    public String getTournamentDetail(String language, String prefix, String type, String id) {
        return executeRequest(HttpMethod.GET, "sports/%s/tournaments/%s:%s:%s/info.xml", language, prefix, type, id);
    }

    public String getAvailableSeasons(String language, String prefix, String type, String id) {
        return executeRequest(HttpMethod.GET, "sports/%s/tournaments/%s:%s:%s/seasons.xml", language, prefix, type, id);
    }

    public String getCompetitorProfile(String language, String prefix, String type, String id) {
        return executeRequest(HttpMethod.GET, "sports/%s/competitors/%s:%s:%s/profile.xml", language, prefix, type, id);
    }

    public String getPlayerProfile(String language, String prefix, String type, String id) {
        return executeRequest(HttpMethod.GET, "sports/%s/players/%s:" + type + ":%s/profile.xml", language, prefix, id);
    }

    public String getVenueProfile(String prefix, String type, String id) {
        return executeRequest(HttpMethod.GET, "sports/en/venues/%s:%s:%s/profile.xml", prefix, type, id);
    }

    public String getMarketDescription(String language) {
        return executeRequest(HttpMethod.GET, "descriptions/%s/markets.xml", language);
    }

    public String getVoidReasons() {
        return executeRequest(HttpMethod.GET, "descriptions/void_reasons.xml");
    }

    public String getBetStopReasons() {
        return executeRequest(HttpMethod.GET, "descriptions/betstop_reasons.xml");
    }

    public String getBetStatusDescriptions() {
        return executeRequest(HttpMethod.GET, "descriptions/betting_status.xml");
    }

    public String getOnGoingSportEventInfo(String language, String prefix, String type, String id) {
        return executeRequest(HttpMethod.GET, "sports/" + language + "/sport_events/" + prefix + ":" + type + ":" + id + "/timeline.xml");
    }

    public String getMarketVariantDescriptions(String language, String marketId, String prefix, String type, String id) {
        return executeRequest(HttpMethod.GET, "descriptions/" + language + "/markets/" + marketId + "/variants/" + prefix + ":" + type + ":" + id);
    }

    public String getVariantDescriptions(String language) {
        return executeRequest(HttpMethod.GET, "descriptions/" + language + "/variants.xml");
    }

    public String getOddsRecovery(String product, String urnType, String id) {
        return executeRequest(HttpMethod.GET, "%s/odds/events/%s:%s:initiate_request", product, urnType, id);
    }

    public String getStatefulMessagesRecovery(String product, String urnType, String id) {
        return executeRequest(HttpMethod.GET, "%s/stateful_messages/events/%s:%s:initiate_request", product, urnType, id);
    }

    public String getProducers() { return executeRequest(HttpMethod.GET, "descriptions/producers.xml");}

    // ============= Helper Methods ============= //
    @Autowired
    public void initHeaders(@Value("${uof.access-token}") String apiKey) {
        headers = new HttpHeaders();
        headers.set("x-access-token", apiKey);
    }

    private String buildUrl(String endpoint) {
        return String.format("%s/v1/%s", restApiHost, endpoint);
    }

    private String sendRequest(String endpoint, HttpMethod method) {
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(endpoint, method, request, String.class);
        return response.getBody();
    }

    private String executeRequest(HttpMethod method, String endpointFormat, Object... args) {
        String formattedEndpoint = String.format(endpointFormat, args);
        String url = buildUrl(formattedEndpoint);
        log.info("Request to URL: {}", url);
        String res = sendRequest(url, method);
        log.info("Response: {}", res);
        return res;
    }
}
