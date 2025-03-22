package com.pam.sportradar.innbets.innbetsfeed.v1.model;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

public class Competitors {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "competitor")
    private List<Competitor> competitor;

    // Getters and Setters
    public List<Competitor> getCompetitor() {
        return competitor;
    }
    public void setCompetitor(List<Competitor> competitor) {
        this.competitor = competitor;
    }
}
