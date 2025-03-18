package com.pam.sportradar.innbets.sportsfeed.controller;

import com.pam.sportradar.innbets.sportsfeed.bean.UnifiedFeed;
import com.pam.sportradar.innbets.sportsfeed.model.*;
import com.pam.sportradar.innbets.sportsfeed.service.FeedAdapterXMLParserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping()
@Tag(name = "JSON Feed Adapter Controller", description = "Endpoints for managing feed data")
public class JSONFeedAdapterXmlController {

    private final FeedAdapterXMLParserService feedAdapterXMLParserService;

    public JSONFeedAdapterXmlController(FeedAdapterXMLParserService feedAdapterXMLParserService) {
        this.feedAdapterXMLParserService = feedAdapterXMLParserService;
    }

    @Operation(summary = "Get all sports", description = "Retrieve all sports data from the XML feed")
    @GetMapping(value = "/{lang}/sports", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sport>> getAllSports(
            @Parameter(description = "Language code", example = "en") @PathVariable("lang") String language) {
        try {
            List<Sport> sports = feedAdapterXMLParserService.getAllSports(language);
            return ResponseEntity.ok(sports);
        } catch (Exception ex) {
            log.error("Error fetching sports", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get categories by sport", description = "Retrieve sport categories for a given sport")
    @GetMapping(value = "/{lang}/categories/{sport}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SportCategories> getCategoriesBySport(
            @Parameter(description = "Language code", example = "en") @PathVariable("lang") String language,
            @Parameter(description = "Sport name", example = "Soccer") @PathVariable("sport") String sport) {
        try {
            SportCategories categories = feedAdapterXMLParserService.getCategoriesBySport(language, sport);
            return ResponseEntity.ok(categories);
        } catch (Exception ex) {
            log.error("Error fetching categories", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get tournaments by category", description = "Retrieve tournaments for a specific category")
    @GetMapping(value = "/{lang}/tournaments/{category}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tournament>> getTournamentsByCategory(
            @Parameter(description = "Language code", example = "en") @PathVariable("lang") String language,
            @Parameter(description = "Category name", example = "Italy") @PathVariable("category") String category) {
        try {
            List<Tournament> tournamentList = feedAdapterXMLParserService.getAvailableTournamentsByCategory(language, category);
            return ResponseEntity.ok(tournamentList);
        } catch (Exception ex) {
            log.error("Error fetching tournaments", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get tournament seasons", description = "Retrieve seasons for a given tournament")
    @GetMapping(value = "/{lang}/tournaments/{tournament}/seasons", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TournamentSeasons> getSeasonsByTournament(
            @Parameter(description = "Language code", example = "en") @PathVariable("lang") String language,
            @Parameter(description = "Tournament name", example = "Serie A") @PathVariable("tournament") String tournament) {
        try {
            TournamentSeasons tournamentSeasons = feedAdapterXMLParserService.getAvailableTournamentSeasons(language, tournament);
            return ResponseEntity.ok(tournamentSeasons);
        } catch (Exception ex) {
            log.error("Error fetching seasons", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get tournament schedule", description = "Retrieve schedule details for a tournament using URN components")
    @GetMapping(value = "/{lang}/tournaments/{prefix}/{type}/{id}/schedule", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TournamentSchedule> getScheduleByDate(
            @Parameter(description = "Language code", example = "en") @PathVariable("lang") String language,
            @Parameter(description = "URN prefix", example = "sr") @PathVariable("prefix") String prefix,
            @Parameter(description = "URN type", example = "tournament") @PathVariable("type") String type,
            @Parameter(description = "URN id", example = "23") @PathVariable("id") String id) {
        try {
            TournamentSchedule tournamentSchedule = feedAdapterXMLParserService.getTournamentSchedule(language, prefix, type, id);
            return ResponseEntity.ok(tournamentSchedule);
        } catch (Exception ex) {
            log.error("Error fetching schedule", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get schedule", description = "Retrieve schedule details for a given date")
    @GetMapping(value = "/{lang}/schedule/{date}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Schedule> getScheduleByDate(
            @Parameter(description = "Language code", example = "en") @PathVariable("lang") String language,
            @Parameter(description = "Date", example = "2025-03-17") @PathVariable("date") String date) {
        try {
            Schedule schedule = feedAdapterXMLParserService.getSchedule(language, date);
            return ResponseEntity.ok(schedule);
        } catch (Exception ex) {
            log.error("Error fetching schedule", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get market descriptions", description = "Retrieve market descriptions for a given language")
    @GetMapping(value = "/{lang}/markets", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MarketDescriptions> getMarkets(
            @Parameter(description = "Language code", example = "en") @PathVariable("lang") String language) {
        try {
            MarketDescriptions marketDescriptions = feedAdapterXMLParserService.getMarketsDetails(language);
            return ResponseEntity.ok(marketDescriptions);
        } catch (Exception ex) {
            log.error("Error fetching markets", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get sport event fixture", description = "Retrieve fixture details for a sport event using URN components")
    @GetMapping(value = "/{lang}/match/{prefix}/{type}/{id}/fixtures", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FixturesFixture> getSportEventFixture(
            @Parameter(description = "Language code", example = "en") @PathVariable("lang") String language,
            @Parameter(description = "URN prefix", example = "sr") @PathVariable("prefix") String prefix,
            @Parameter(description = "URN type", example = "match") @PathVariable("type") String type,
            @Parameter(description = "URN id", example = "48878667") @PathVariable("id") String id) {
        try {
            FixturesFixture fixturesFixture = feedAdapterXMLParserService.getSportEventFixture(language, prefix, type, id);
            return ResponseEntity.ok(fixturesFixture);
        } catch (Exception ex) {
            log.error("Error fetching fixture", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get match fixtures summary", description = "Retrieve match fixtures summary for a given sport event using URN components")
    @GetMapping(value = "/{lang}/match/fixtures/{prefix}/{type}/{id}/summary", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MatchSummary> getMatchFixturesSummary(
            @Parameter(description = "Language code", example = "en") @PathVariable("lang") String language,
            @Parameter(description = "URN prefix", example = "sr") @PathVariable("prefix") String prefix,
            @Parameter(description = "URN type", example = "match") @PathVariable("type") String type,
            @Parameter(description = "URN id", example = "48878667") @PathVariable("id") String id) {
        try {
            MatchSummary matchSummary = feedAdapterXMLParserService.getMatchFixturesSummary(language, prefix, type, id);
            return ResponseEntity.ok(matchSummary);
        } catch (Exception ex) {
            log.error("Error fetching match summary", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get match timeline", description = "Retrieve the timeline for a given sport event using URN components")
    @GetMapping(value = "/{lang}/match/{prefix}/{type}/{id}/timeline", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MatchTimeline> getMatchTimeline(
            @Parameter(description = "Language code", example = "en") @PathVariable("lang") String language,
            @Parameter(description = "URN prefix", example = "sr") @PathVariable("prefix") String prefix,
            @Parameter(description = "URN type", example = "match") @PathVariable("type") String type,
            @Parameter(description = "URN id", example = "48878667") @PathVariable("id") String id) {
        try {
            MatchTimeline matchTimeline = feedAdapterXMLParserService.getMatchTimeline(language, prefix, type, id);
            return ResponseEntity.ok(matchTimeline);
        } catch (Exception ex) {
            log.error("Error fetching match timeline", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get tournament details", description = "Retrieve detailed information about a tournament using URN components")
    @GetMapping(value = "/{lang}/tournament/{prefix}/{type}/{id}/info", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TournamentInfo> getTournamentDetails(
            @Parameter(description = "Language code", example = "en") @PathVariable("lang") String language,
            @Parameter(description = "URN prefix", example = "sr") @PathVariable("prefix") String prefix,
            @Parameter(description = "URN type", example = "tournament") @PathVariable("type") String type,
            @Parameter(description = "URN id", example = "23") @PathVariable("id") String id) {
        try {
            TournamentInfo tournamentInfo = feedAdapterXMLParserService.getTournamentDetails(language, prefix, type, id);
            return ResponseEntity.ok(tournamentInfo);
        } catch (Exception ex) {
            log.error("Error fetching tournament details", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get bet stop reasons", description = "Retrieve bet stop reasons descriptions")
    @GetMapping(value = "/description/bet-stop/reasons", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BetstopReasonsDescriptions> getBetStopReasons() {
        try {
            BetstopReasonsDescriptions betstopReasonsDescriptions = feedAdapterXMLParserService.getBetStopReasons();
            return ResponseEntity.ok(betstopReasonsDescriptions);
        } catch (Exception ex) {
            log.error("Error fetching bet stop reasons", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get bet status", description = "Retrieve bet status descriptions")
    @GetMapping(value = "/description/betting/status", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BettingStatusDescriptions> getBetStatus() {
        try {
            BettingStatusDescriptions bettingStatusDescriptions = feedAdapterXMLParserService.getBetStatusDescriptions();
            return ResponseEntity.ok(bettingStatusDescriptions);
        } catch (Exception ex) {
            log.error("Error fetching bet status descriptions", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get void reasons", description = "Retrieve void reasons descriptions")
    @GetMapping(value = "/description/betting/void/reasons", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<VoidReasonsDescriptions> getVoidReasons() {
        try {
            VoidReasonsDescriptions bettingStatusDescriptions = feedAdapterXMLParserService.getVoidReasons();
            return ResponseEntity.ok(bettingStatusDescriptions);
        } catch (Exception ex) {
            log.error("Error fetching bet status descriptions", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get player profile", description = "Retrieve detailed information about a player profile using URN components")
    @GetMapping(value = "/{lang}/player/{prefix}/{type}/{id}/profile", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerProfile> getPlayerProfile(
            @Parameter(description = "Language code", example = "en") @PathVariable("lang") String language,
            @Parameter(description = "URN prefix", example = "sr") @PathVariable("prefix") String prefix,
            @Parameter(description = "URN type", example = "tournament") @PathVariable("type") String type,
            @Parameter(description = "URN id", example = "23") @PathVariable("id") String id) {
        try {
            PlayerProfile playerProfile = feedAdapterXMLParserService.getPlayerProfile(language, prefix, type, id);
            return ResponseEntity.ok(playerProfile);
        } catch (Exception ex) {
            log.error("Error fetching player profile", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get competitor profile", description = "Retrieve competitor profile details using URN components")
    @GetMapping(value = "/{lang}/competitor/{prefix}/{type}/{id}/profile", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CompetitorProfile> getCompetitorProfile(
            @Parameter(description = "Language code", example = "en") @PathVariable("lang") String language,
            @Parameter(description = "URN prefix", example = "sr") @PathVariable("prefix") String prefix,
            @Parameter(description = "URN type", example = "competitor") @PathVariable("type") String type,
            @Parameter(description = "URN id", example = "3944") @PathVariable("id") String id) {
        try {
            CompetitorProfile competitorProfile = feedAdapterXMLParserService.getCompetitorDetails(language, prefix, type, id);
            return ResponseEntity.ok(competitorProfile);
        } catch (Exception ex) {
            log.error("Error fetching competitor profile", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get venue profile", description = "Retrieve detailed information about a venue info using URN components")
    @GetMapping(value = "/venue/{prefix}/{type}/{id}/profile", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<VenueSummary> getVenueProfile(
            @Parameter(description = "URN prefix", example = "sr") @PathVariable("prefix") String prefix,
            @Parameter(description = "URN type", example = "venue") @PathVariable("type") String type,
            @Parameter(description = "URN id", example = "5974") @PathVariable("id") String id) {
        try {
            VenueSummary playerProfile = feedAdapterXMLParserService.getVenueProfile(prefix, type, id);
            return ResponseEntity.ok(playerProfile);
        } catch (Exception ex) {
            log.error("Error fetching venue profile", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get variant descriptions", description = "Retrieve detailed information about variants")
    @GetMapping(value = "/{lang}/descriptions/variant", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<VariantDescriptions> getVariantDescriptions(
            @Parameter(description = "Language", example = "en") @PathVariable("lang") String lang) {
        try {
            VariantDescriptions variantDescriptions = feedAdapterXMLParserService.getVariantDescriptions(lang);
            return ResponseEntity.ok(variantDescriptions);
        } catch (Exception ex) {
            log.error("Error fetching variant descriptions", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get market variant descriptions", description = "Retrieve detailed information about market variants info using URN components")
    @GetMapping(value = "/{lang}/descriptions/market/{marketId}/variant/{prefix}:{type}:{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MarketDescriptions> getMarketVariantDescriptions(
            @Parameter(description = "Language", example = "en") @PathVariable("lang") String lang,
            @Parameter(description = "Market ID", example = "101160") String marketId,
            @Parameter(description = "Prefix", example = "sr") String prefix,
            @Parameter(description = "Type", example = "correct_score:bestof") String type,
            @Parameter(description = "Id", example = "12") String id) {
        try {
            MarketDescriptions variantDescriptions = feedAdapterXMLParserService.getMarketVariantDescriptions(lang, marketId, prefix, type, id);
            return ResponseEntity.ok(variantDescriptions);
        } catch (Exception ex) {
            log.error("Error fetching variant descriptions", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get unified sport information", description = "Retrieve detailed information about all static information")
    @GetMapping(value = "/{lang}/unified/prematch", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UnifiedFeed> getUnifiedPrematchInfo(
            @Parameter(description = "Language", example = "en") @PathVariable("lang") String lang) {
        try {
            UnifiedFeed unifiedFeed = feedAdapterXMLParserService.getUnifiedFeed(lang);
            return ResponseEntity.ok(unifiedFeed);
        } catch (Exception ex) {
            log.error("Error fetching unified feed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
