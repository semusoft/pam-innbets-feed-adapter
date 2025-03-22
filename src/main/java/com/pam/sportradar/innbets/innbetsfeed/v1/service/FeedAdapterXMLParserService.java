package com.pam.sportradar.innbets.innbetsfeed.v1.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.pam.sportradar.innbets.commons.IdComponent;
import com.pam.sportradar.innbets.innbetsfeed.v1.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FeedAdapterXMLParserService {

    private final InnBetsRestApiXMLService xmlFeedService;
    private final Jaxb2Marshaller jaxb2Marshaller;
    private final RedisService redisService;
    private final String redisPrefix = "unifiedfeed-";
    private Gson gson;

    @Autowired
    public FeedAdapterXMLParserService(InnBetsRestApiXMLService xmlFeedService, Jaxb2Marshaller jaxb2Marshaller, RedisService redisService) {
        this.xmlFeedService = xmlFeedService;
        this.jaxb2Marshaller = jaxb2Marshaller;
        this.redisService = redisService;
        this.gson = new Gson();
    }

    public <T> T parseXml(String xml, Class<T> clazz) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(xml, clazz);
        } catch (Exception e) {
            log.error("Error while parsing xml string", e);
            throw new RuntimeException("Error while parsing xml string", e);
        }
    }

    public <T> T parseRedisString(String data, Class<T> clazz) {
        try {
            return gson.fromJson(data, clazz);
        } catch (Exception e) {
            log.error("Error while parsing redis string.", e);
            throw new RuntimeException("Error while parsing redis string.", e);
        }
    }

    private IdComponent extractIdComponents(String sportId) {
        String[] parts = sportId.split(":");
        if (parts.length < 3) {
            throw new RuntimeException("Invalid sport id format: " + sportId);
        }
        return new IdComponent(parts[0], parts[1], parts[2]);
    }

    private Sport extractSelectedSportInfo(String sport, String language) {
        Sports sports;
        if (redisService.exists(redisPrefix + "sports")) sports = parseRedisString(redisService.getData(redisPrefix + "sports"), Sports.class);
        else sports = getSports(language);
        return sports.getSports().stream()
                .filter(sp -> sp.getName().equalsIgnoreCase(sport))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sport not found: " + sport));
    }

    public List<Sport> getAllSports(String language) {
        try {
            String xml = xmlFeedService.getAllSports(language);
            Sports sports = parseXml(xml, Sports.class);
            if (!redisService.exists(redisPrefix + "sports")) {
                redisService.save(redisPrefix + "sports", sports);
            }
            log.info("Sport extracted: {}", Optional.of(sports.getSports().size()));
            return sports.getSports();
        } catch (Exception ex) {
            log.error("Error processing XML feed", ex);
            throw new RuntimeException("Error processing XML feed", ex);
        }
    }

    public Sports getSports(String language) {
        try {
            String xml = xmlFeedService.getAllSports(language);
            Sports sports = parseXml(xml, Sports.class);
            if (!redisService.exists(redisPrefix + "sports")) {
                redisService.save(redisPrefix + "sports", sports);
            }
            log.info("Sport extracted: {}", Optional.of(sports.getSports().size()));
            return sports;
        } catch (Exception ex) {
            log.error("Error processing XML feed", ex);
            throw new RuntimeException("Error processing XML feed", ex);
        }
    }

    public SportCategories getCategoriesBySport(String language, String sport) {
        try {
            Sport selectedSport = extractSelectedSportInfo(sport, language);
            IdComponent idComponent = extractIdComponents(selectedSport.getId());
            String xml = xmlFeedService.getCategories(language, idComponent.getPrefix(), idComponent.getType(), idComponent.getId());
            SportCategories categories = parseXml(xml, SportCategories.class);
            if (!redisService.exists(redisPrefix + sport + "-categories")) {
                redisService.save(redisPrefix + sport + "-categories", categories);
            }
            log.info("Categories extracted: {}", Optional.of(categories.getCategories().size()));
            return categories;
        } catch (Exception ex) {
            log.error("Error processing XML feed", ex);
            throw new RuntimeException("Error processing XML feed", ex);
        }
    }

    public List<Tournament> getAvailableTournamentsByCategory(String language, String category) {
        try {
            List<Tournament> tournaments;
            if (redisService.exists(redisPrefix + category + "-tournaments"))
                tournaments = Collections.singletonList(parseRedisString(redisService.getData(redisPrefix + category + "-tournaments"), Tournament.class));
            else {
                String xml = xmlFeedService.getTournaments(language);
                Tournaments xmlTournaments = parseXml(xml, Tournaments.class);
                tournaments = xmlTournaments.getTournaments().stream().filter(tr -> tr.getCategory().getName().equalsIgnoreCase(category)).toList();
                if (!redisService.exists(redisPrefix + category + "-tournaments")) {
                    redisService.save(redisPrefix + category + "-tournaments", tournaments);
                }
            }
            log.info("Tournaments extracted for category " + category + " are: " + tournaments.size());
            return tournaments;
        } catch (Exception ex) {
            log.error("Error processing XML feed", ex);
            throw new RuntimeException("Error processing XML feed", ex);
        }
    }

    public TournamentSeasons getAvailableTournamentSeasons(String language, String tournamentName) {
        try {
            String tournamentsXml = xmlFeedService.getTournaments(language);
            Tournaments tournamentsWrapper = parseXml(tournamentsXml, Tournaments.class);
            Optional<Tournament> selectedTournamentOpt = tournamentsWrapper.getTournaments().stream()
                    .filter(t -> t.getName().equalsIgnoreCase(tournamentName))
                    .findFirst();
            if (selectedTournamentOpt.isEmpty()) {
                throw new RuntimeException("Tournament " + tournamentName + " not found.");
            }
            Tournament selectedTournament = selectedTournamentOpt.get();
            IdComponent urn = extractIdComponents(selectedTournament.getId());
            String seasonsXml = xmlFeedService.getAvailableSeasons(language, urn.getPrefix(), urn.getType(), urn.getId());
            TournamentSeasons tournamentSeasons = parseXml(seasonsXml, TournamentSeasons.class);
            log.info("Tournament seasons extracted: " + tournamentSeasons.getSeasons());
            return tournamentSeasons;
        } catch (Exception ex) {
            log.error("Error processing XML feed", ex);
            throw new RuntimeException("Error processing XML feed", ex);
        }
    }

    public Schedule getSchedule(String language, String date) {
        try {
            String xml = xmlFeedService.getSchedule(language, date);
            Schedule schedule = parseXml(xml, Schedule.class);
            log.info("Schedule extracted with {} sport events",
                    schedule.getSportEvents() != null ? schedule.getSportEvents().size() : 0);
            return schedule;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public CompetitorProfile getCompetitorDetails(String language, String prefix, String type, String id) {
        try {
            String xml = xmlFeedService.getCompetitorProfile(language, prefix, type, id);
            CompetitorProfile competitorProfile = parseXml(xml, CompetitorProfile.class);
            log.info("Competitor profile extracted: " + competitorProfile);
            return competitorProfile;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public MarketDescriptions getMarketsDetails(String language) {
        try {
            String xml = xmlFeedService.getMarketDescription(language);
            MarketDescriptions marketDescriptions = parseXml(xml, MarketDescriptions.class);
            log.info("Market descriptions: " + marketDescriptions);
            return marketDescriptions;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public FixturesFixture getSportEventFixture(String language, String prefix, String type, String id) {
        try {
            String xml = xmlFeedService.getFixture(language, prefix, type, id);
            FixturesFixture fixturesFixture = parseXml(xml, FixturesFixture.class);
            log.info("Sport event fixtures: " + fixturesFixture);
            return fixturesFixture;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public TournamentSchedule getTournamentSchedule(String language, String prefix, String type, String id) {
        try {
            String xml = xmlFeedService.getTournamentSchedule(language, prefix, type, id);
            TournamentSchedule tournamentSchedule = parseXml(xml, TournamentSchedule.class);
            log.info("Tournament schedule: " + tournamentSchedule);
            return tournamentSchedule;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public MatchSummary getMatchFixturesSummary(String language, String prefix, String type, String id) {
        try {
            String xml = xmlFeedService.getSportEventSummary(language, prefix, type, id);
            MatchSummary matchSummary = parseXml(xml, MatchSummary.class);
            log.info("Match summary: " + matchSummary);
            return matchSummary;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public MatchSummary getMatchSummary(String language, String prefix, String type, String id) {
        try {
            String xml = xmlFeedService.getSportEventSummary(language, prefix, type, id);
            MatchSummary matchSummary = parseXml(xml, MatchSummary.class);
            log.info("Match summary: " + matchSummary);
            return matchSummary;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public VoidReasonsDescriptions getVoidReasons() {
        try {
            String xml = xmlFeedService.getVoidReasons();
            VoidReasonsDescriptions voidReasons = parseXml(xml, VoidReasonsDescriptions.class);
            log.info("Void reason: " + voidReasons);
            return voidReasons;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public MatchTimeline getMatchTimeline(String language, String prefix, String type, String id) {
        try {
            String xml = xmlFeedService.getOnGoingSportEventInfo(language, prefix, type, id);
            MatchTimeline matchTimeline = parseXml(xml, MatchTimeline.class);
            log.info("Match timeline: " + matchTimeline);
            return matchTimeline;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public TournamentInfo getTournamentDetails(String language, String prefix, String type, String id) {
        try {
            String xml = xmlFeedService.getTournamentDetail(language, prefix, type, id);
            TournamentInfo tournamentInfo = parseXml(xml, TournamentInfo.class);
            log.info("Tournament info: " + tournamentInfo);
            return tournamentInfo;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public BetstopReasonsDescriptions getBetStopReasons() {
        try {
            String xml = xmlFeedService.getBetStopReasons();
            BetstopReasonsDescriptions betstopReasonsDescriptions = parseXml(xml, BetstopReasonsDescriptions.class);
            log.info("Bet stop reasons: " + betstopReasonsDescriptions);
            return betstopReasonsDescriptions;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public BettingStatusDescriptions getBetStatusDescriptions() {
        try {
            String xml = xmlFeedService.getBetStatusDescriptions();
            BettingStatusDescriptions bettingStatusDescriptions = parseXml(xml, BettingStatusDescriptions.class);
            log.info("Bet status descriptions: " + bettingStatusDescriptions);
            return bettingStatusDescriptions;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public PlayerProfile getPlayerProfile(String language, String prefix, String type, String id) {
        try {
            String xml = xmlFeedService.getPlayerProfile(language, prefix, type, id);
            PlayerProfile playerProfile = parseXml(xml, PlayerProfile.class);
            log.info("Player profile: " + playerProfile);
            return playerProfile;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public VenueSummary getVenueProfile(String prefix, String type, String id) {
        try {
            String xml = xmlFeedService.getVenueProfile(prefix, type, id);
            VenueSummary venueSummary = parseXml(xml, VenueSummary.class);
            log.info("Venue summary: " + venueSummary);
            return venueSummary;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public VariantDescriptions getVariantDescriptions(String language) {
        try {
            String xml = xmlFeedService.getVariantDescriptions(language);
            VariantDescriptions variantDescriptions = parseXml(xml, VariantDescriptions.class);
            log.info("Variant descriptions: " + variantDescriptions);
            return variantDescriptions;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }

    public MarketDescriptions getMarketVariantDescriptions(String language, String marketId, String prefix, String type, String id) {
        try {
            String xml = xmlFeedService.getMarketVariantDescriptions(language, marketId, prefix, type, id);
            MarketDescriptions marketVariantDescriptions = parseXml(xml, MarketDescriptions.class);
            log.info("Market variant descriptions: " + marketVariantDescriptions);
            return marketVariantDescriptions;
        } catch (Exception ex) {
            log.error("Error processing schedule XML feed", ex);
            throw new RuntimeException("Error processing schedule XML feed", ex);
        }
    }
}

