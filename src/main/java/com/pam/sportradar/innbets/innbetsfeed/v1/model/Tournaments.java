package com.pam.sportradar.innbets.innbetsfeed.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tournaments {

    @JacksonXmlProperty(localName = "tournament")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Tournament> tournament;

    public List<Tournament> getTournaments() {
        return tournament;
    }

    public void setTournament(List<Tournament> tournament) {
        this.tournament = tournament;
    }
}
