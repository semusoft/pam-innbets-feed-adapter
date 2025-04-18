package com.betting.feed.adapter.feed.v1.controller;

import com.betting.feed.adapter.feed.v1.model.TournamentInfo;
import com.betting.feed.adapter.feed.v1.service.FeedAdapterXMLParserService;
import com.betting.feed.adapter.feed.v1.service.InnBetsRestApiXMLService;
import com.betting.feed.adapter.feed.v1.service.SdkService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recovery")
@Tag(name = "XML Recovery Controller", description = "Endpoints for managing odds and handling recovery operations. " +
        "Each endpoint is protected by a rate limiter named 'recoveryRateLimiter', " +
        "which enforces a maximum number of requests. If the limit is exceeded, a fallback error message is returned.")
public class RecoveryController {

    @Autowired
    private InnBetsRestApiXMLService innBetsRestApiXMLService;
    @Autowired
    private FeedAdapterXMLParserService feedAdapterXMLParserService;
    @Autowired
    private SdkService sdkService;

    @GetMapping(value = "/{product}/recovery/initiate_request", produces = MediaType.APPLICATION_XML_VALUE)
    @RateLimiter(name = "recoveryRateLimiter", fallbackMethod = "fallbackFullOddsRecovery")
    @Operation(
            summary = "Initiate full odds recovery",
            description = "This endpoint initiates full odds recovery for the specified product. " +
                    "For live odds producers, the maximum recovery period is 10 hours, while for all other producers, it is 72 hours. " +
                    "Optional query parameters include 'after' (a timestamp) and 'request_id'. " +
                    "This endpoint is protected by the 'recoveryRateLimiter'. " +
                    "If the rate limit is exceeded, a fallback error response is returned."
    )
    public String getFullOddsRecovery(@PathVariable("product") String product,
                                      @RequestParam(value = "after", required = false) Long after,
                                      @RequestParam(value = "request_id", required = false) Integer requestId) {
        long currentTime = System.currentTimeMillis();
        long maxAllowedRecoveryMillis;
        if ("liveodds".equalsIgnoreCase(product)) {
            maxAllowedRecoveryMillis = 10L * 60L * 60L * 1000L;
        } else {
            maxAllowedRecoveryMillis = 72L * 60L * 60L * 1000L;
        }
        if (after != null && (currentTime - after) > maxAllowedRecoveryMillis) {
            return "<error>Recovery period exceeds maximum allowed for " + product +
                    " (max allowed: " + (maxAllowedRecoveryMillis / (60 * 60 * 1000)) + " hours).</error>";
        }
        return innBetsRestApiXMLService.getFullOddsRecovery(product, after, requestId);
    }

    @GetMapping(value = "/{product}/odds/events/{prefix}:{type}:{id}/initiate_request", produces = MediaType.APPLICATION_XML_VALUE)
    @RateLimiter(name = "recoveryRateLimiter", fallbackMethod = "fallbackSportEventRecovery")
    @Operation(
            summary = "Initiate odds recovery for a specific sport event",
            description = "This endpoint retrieves odds recovery for a specific sport event using the URN constructed as prefix:type:id. " +
                    "It is rate-limited by 'recoveryRateLimiter'. Exceeding the rate limit will result in a fallback error response."
    )
    public String getSportEventRecovery(
            @PathVariable("product") String product,
            @PathVariable("prefix") String prefix,
            @PathVariable("type") String type,
            @PathVariable("id") String id) {
        String urn = prefix + ":" + type + ":" + id;
        return innBetsRestApiXMLService.getSportEventOddsRecovery(product, urn);
    }

    @GetMapping(value = "/{product}/odds/stateful_messages/events/{prefix}:{type}:{id}/initiate_request", produces = MediaType.APPLICATION_XML_VALUE)
    @RateLimiter(name = "recoveryRateLimiter", fallbackMethod = "fallbackStatefulRecovery")
    @Operation(
            summary = "Resend stateful messages for odds recovery",
            description = "This endpoint resends stateful messages for odds recovery for a specific sport event identified by the URN (prefix:type:id). " +
                    "It is protected by the 'recoveryRateLimiter'. If the rate limit is exceeded, a fallback error message is returned."
    )
    public String resendSportEventRecovery(
            @PathVariable("product") String product,
            @PathVariable("prefix") String prefix,
            @PathVariable("type") String type,
            @PathVariable("id") String id) {
        String urn = prefix + ":" + type + ":" + id;
        return innBetsRestApiXMLService.resendSportEventOddsRecovery(product, urn);
    }

    @GetMapping("/match/summary")
    public String getMatchSummary(
            @RequestParam("lang") String lang,
            @RequestParam("prefix") String prefix,
            @RequestParam("type") String type,
            @RequestParam("id") String id
    ) {
        return innBetsRestApiXMLService.getSportEventSummary(lang, prefix, type, id);
    }

    @GetMapping("/season")
    public String getSeasonInfo(
            @RequestParam("language") String language,
            @RequestParam("prefix") String prefix,
            @RequestParam("type") String type,
            @RequestParam("id") String id
    ) {
        return innBetsRestApiXMLService.getAvailableSeasons(language, prefix, type, id);
    }

    @GetMapping("/fixtures")
    public String getFixtures(
            @RequestParam("language") String language,
            @RequestParam("prefix") String prefix,
            @RequestParam("type") String type,
            @RequestParam("id") String id
    ) {
        return innBetsRestApiXMLService.getFixture(language, prefix, type, id);
    }

    @GetMapping("/outright/fixtures")
    public ResponseEntity<TournamentInfo> getOutrightFixtures(
            @RequestParam("language") String language,
            @RequestParam("prefix") String prefix,
            @RequestParam("type") String type,
            @RequestParam("id") String id
    ) {
        return ResponseEntity.ok().body(feedAdapterXMLParserService.getOutrightFixtures(language, prefix, type, id));
    }

    public String fallbackFullOddsRecovery(String product, Long after, Integer requestId, Throwable throwable) {
        return "<error>Rate limit exceeded for full odds recovery. Please try again later.</error>";
    }

    public String fallbackSportEventRecovery(String product, String prefix, String type, String id, Throwable throwable) {
        return "<error>Rate limit exceeded for sport event recovery. Please try again later.</error>";
    }

    public String fallbackStatefulRecovery(String product, String prefix, String type, String id, Throwable throwable) {
        return "<error>Rate limit exceeded for stateful messages recovery. Please try again later.</error>";
    }
}
