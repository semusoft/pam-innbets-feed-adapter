package com.pam.sportradar.innbets.sportsfeed.controller;

import com.pam.sportradar.innbets.exception.SdkException;
import com.pam.sportradar.innbets.sportsfeed.model.FeedSport;
import com.pam.sportradar.innbets.sportsfeed.service.SdkService;
import com.sportradar.unifiedodds.sdk.entities.Competition;
import com.sportradar.unifiedodds.sdk.entities.SportEvent;
import com.sportradar.unifiedodds.sdk.entities.markets.MarketDescription;
import com.sportradar.utils.Urn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/sdk/innbets/unified")
@Tag(name = "BetRadar SDK Controller", description = "Endpoints for managing SDK actions.")
public class SdkController {

    private final SdkService sdkService;

    public SdkController(SdkService sdkService) {
        this.sdkService = sdkService;
    }

    @GetMapping(value = "/sports/{lang}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all available sports and categories.")
    public ResponseEntity<Object> getAllAvailableSportsAndCategories(@PathVariable("lang") String lang) {
        final String methodName = "getAllAvailableSportsAndCategories";
        final String logId = UUID.randomUUID().toString();
        log.info("{} - {} - START", logId, methodName);
        try {
            List<FeedSport> result = sdkService.getSportsAndCategories(lang, logId);
            return ResponseEntity.ok(result);
        } catch (SdkException ex) {
            log.error("Error in {}: {}", methodName, logId, ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        } catch (Exception ex) {
            log.error("Error in {}: {}", methodName, logId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

    @GetMapping(value = "/competitions/{lang}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all available competitions for the given date.")
    public ResponseEntity<Object> getAllAvailableCompetitionsByDate(@PathVariable("lang") String lang, @PathVariable("date") String date) {
        final String methodName = "getAllAvailableCompetitionsByDate";
        final String logId = UUID.randomUUID().toString();
        log.info("{} - {} - START", logId, methodName);
        try {
            List<Competition> result = sdkService.getCompetitionsByDay(lang, logId);
            return ResponseEntity.ok(result);
        } catch (SdkException ex) {
            log.error("Error in {}: {}", methodName, logId, ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        } catch (Exception ex) {
            log.error("Error in {}: {}", methodName, logId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

    @GetMapping(value = "/tournaments/{lang}/{sport}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all active tournaments by the given sport.")
    public ResponseEntity<Object> getAllAvailableTournamentsBySport(@PathVariable("lang") String lang, @PathVariable("sport") String sport) {
        final String methodName = "getAllAvailableTournamentsBySport";
        final String logId = UUID.randomUUID().toString();
        log.info("{} - {} - START", logId, methodName);
        try {
            List<SportEvent> result = sdkService.getSportActiveTournaments(lang, sport, logId);
            return ResponseEntity.ok(result);
        } catch (SdkException ex) {
            log.error("Error in {}: {}", methodName, logId, ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        } catch (Exception ex) {
            log.error("Error in {}: {}", methodName, logId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

    @GetMapping(value = "/markets/{lang}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get markets description.")
    public ResponseEntity<Object> getMarketsDescriptions(@PathVariable("lang") String lang) {
        final String methodName = "getMarketsDescriptions";
        final String logId = UUID.randomUUID().toString();
        log.info("{} - {} - START", logId, methodName);
        try {
            List<MarketDescription> result = sdkService.getMarketsDescriptions(lang, logId);
            return ResponseEntity.ok(result);
        } catch (SdkException ex) {
            log.error("Error in {}: {}", methodName, logId, ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        } catch (Exception ex) {
            log.error("Error in {}: {}", methodName, logId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

    @GetMapping(value = "/competitors/{lang}/{prefix}/{type}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get competitor by id.")
    public ResponseEntity<Object> getCompetitorById(@PathVariable("lang") String lang,
                                                    @PathVariable("prefix") String prefix,
                                                    @PathVariable("type") String type,
                                                    @PathVariable("id") long id) {
        final String methodName = "getCompetitorById";
        final String logId = UUID.randomUUID().toString();
        log.info("{} - {} - START", logId, methodName);
        try {
            Urn urn = new Urn(prefix, type, id);
            Object result = sdkService.getCompetitorById(urn, lang, logId);
            return ResponseEntity.ok(result);
        } catch (SdkException ex) {
            log.error("Error in {}: {}", methodName, logId, ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        } catch (Exception ex) {
            log.error("Error in {}: {}", methodName, logId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }
}
