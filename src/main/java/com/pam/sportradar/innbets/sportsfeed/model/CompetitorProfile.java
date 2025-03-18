package com.pam.sportradar.innbets.sportsfeed.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompetitorProfile {

    @JacksonXmlProperty(isAttribute = true, localName = "generated_at")
    private String generatedAt;

    @JacksonXmlProperty(localName = "competitor")
    private Competitor competitor;

    @JacksonXmlElementWrapper(localName = "players")
    @JacksonXmlProperty(localName = "player")
    private List<Player> players;

    // Getters and setters
    public String getGeneratedAt() {
        return generatedAt;
    }
    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }
    public Competitor getCompetitor() {
        return competitor;
    }
    public void setCompetitor(Competitor competitor) {
        this.competitor = competitor;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
