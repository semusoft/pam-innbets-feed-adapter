package com.pam.sportradar.innbets.sportsfeed.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TournamentInfo {

    @JacksonXmlProperty(isAttribute = true, localName = "generated_at")
    private String generatedAt;

    @JacksonXmlProperty(localName = "tournament")
    private Tournament tournament;

    @JacksonXmlProperty(localName = "season")
    private Season season;

    @JacksonXmlProperty(localName = "round")
    private Round round;

    @JacksonXmlProperty(localName = "coverage_info")
    private CoverageInfo coverageInfo;

    @JacksonXmlProperty(localName = "groups")
    private Groups groups;

    // This element exists in the XML but is empty; you can map it if needed.
    @JacksonXmlProperty(localName = "competitors")
    private Competitors competitors;

    // Getters and Setters
    public String getGeneratedAt() {
        return generatedAt;
    }
    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }
    public Tournament getTournament() {
        return tournament;
    }
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    public Season getSeason() {
        return season;
    }
    public void setSeason(Season season) {
        this.season = season;
    }
    public Round getRound() {
        return round;
    }
    public void setRound(Round round) {
        this.round = round;
    }
    public CoverageInfo getCoverageInfo() {
        return coverageInfo;
    }
    public void setCoverageInfo(CoverageInfo coverageInfo) {
        this.coverageInfo = coverageInfo;
    }
    public Groups getGroups() {
        return groups;
    }
    public void setGroups(Groups groups) {
        this.groups = groups;
    }
    public Competitors getCompetitors() {
        return competitors;
    }
    public void setCompetitors(Competitors competitors) {
        this.competitors = competitors;
    }
}
