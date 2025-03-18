package com.pam.sportradar.innbets.sportsfeed.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TournamentSchedule {

    @JacksonXmlProperty(isAttribute = true, localName = "generated_at")
    private String generatedAt;

    @JacksonXmlProperty(localName = "tournament")
    private Tournament tournament;

    @JacksonXmlProperty(localName = "sport_events")
    private SportEvents sportEvents;

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
    public SportEvents getSportEvents() {
        return sportEvents;
    }
    public void setSportEvents(SportEvents sportEvents) {
        this.sportEvents = sportEvents;
    }
}
