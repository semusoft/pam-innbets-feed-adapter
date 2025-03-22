package com.pam.sportradar.innbets.innbetsfeed.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SportEvent {

    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private String id;

    @JacksonXmlProperty(isAttribute = true, localName = "scheduled")
    private String scheduled;

    @JacksonXmlProperty(isAttribute = true, localName = "start_time_tbd")
    private boolean startTimeTbd;

    @JacksonXmlProperty(isAttribute = true, localName = "liveodds")
    private String liveodds;

    @JacksonXmlProperty(isAttribute = true, localName = "status")
    private String status;

    // Child elements
    @JacksonXmlProperty(localName = "tournament_round")
    private TournamentRound tournamentRound;

    @JacksonXmlProperty(localName = "season")
    private Season season;

    @JacksonXmlProperty(localName = "tournament")
    private Tournament tournament;

    @JacksonXmlProperty(localName = "competitors")
    private Competitors competitors;

    @JacksonXmlProperty(localName = "venue")
    private Venue venue;

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getScheduled() {
        return scheduled;
    }
    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }
    public boolean isStartTimeTbd() {
        return startTimeTbd;
    }
    public void setStartTimeTbd(boolean startTimeTbd) {
        this.startTimeTbd = startTimeTbd;
    }
    public String getLiveodds() {
        return liveodds;
    }
    public void setLiveodds(String liveodds) {
        this.liveodds = liveodds;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public TournamentRound getTournamentRound() {
        return tournamentRound;
    }
    public void setTournamentRound(TournamentRound tournamentRound) {
        this.tournamentRound = tournamentRound;
    }
    public Season getSeason() {
        return season;
    }
    public void setSeason(Season season) {
        this.season = season;
    }
    public Tournament getTournament() {
        return tournament;
    }
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    public Competitors getCompetitors() {
        return competitors;
    }
    public void setCompetitors(Competitors competitors) {
        this.competitors = competitors;
    }
    public Venue getVenue() {
        return venue;
    }
    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
