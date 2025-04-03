package com.betting.feed.adapter.feed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class TournamentSeasons {

    @JacksonXmlProperty(isAttribute = true, localName = "generated_at")
    private String generatedAt;
    @JacksonXmlProperty(localName = "tournament")
    private Tournament tournament;
    @JacksonXmlElementWrapper(localName = "seasons")
    @JacksonXmlProperty(localName = "season")
    private List<Season> seasons;

    // Getters and setters
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

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }
}
