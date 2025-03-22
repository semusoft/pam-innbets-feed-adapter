package com.pam.sportradar.innbets.innbetsfeed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fixture {

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

    @JacksonXmlProperty(isAttribute = true, localName = "start_time_confirmed")
    private boolean startTimeConfirmed;

    @JacksonXmlProperty(isAttribute = true, localName = "start_time")
    private String startTime;

    @JacksonXmlProperty(localName = "tournament_round")
    private TournamentRound tournamentRound;

    @JacksonXmlProperty(localName = "season")
    private Season season;

    @JacksonXmlProperty(localName = "tournament")
    private Tournament tournament;

    @JacksonXmlElementWrapper(localName = "competitors")
    @JacksonXmlProperty(localName = "competitor")
    private List<Competitor> competitors;

    @JacksonXmlProperty(localName = "venue")
    private Venue venue;

    @JacksonXmlProperty(localName = "extra_info")
    private ExtraInfo extraInfo;

    @JacksonXmlProperty(localName = "reference_ids")
    private ReferenceIds referenceIds;

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
    public boolean isStartTimeConfirmed() {
        return startTimeConfirmed;
    }
    public void setStartTimeConfirmed(boolean startTimeConfirmed) {
        this.startTimeConfirmed = startTimeConfirmed;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
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
    public List<Competitor> getCompetitors() {
        return competitors;
    }
    public void setCompetitors(List<Competitor> competitors) {
        this.competitors = competitors;
    }
    public Venue getVenue() {
        return venue;
    }
    public void setVenue(Venue venue) {
        this.venue = venue;
    }
    public ExtraInfo getExtraInfo() {
        return extraInfo;
    }
    public void setExtraInfo(ExtraInfo extraInfo) {
        this.extraInfo = extraInfo;
    }
    public ReferenceIds getReferenceIds() {
        return referenceIds;
    }
    public void setReferenceIds(ReferenceIds referenceIds) {
        this.referenceIds = referenceIds;
    }
}
