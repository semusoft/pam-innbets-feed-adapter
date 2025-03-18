package com.pam.sportradar.innbets.sportsfeed.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchSummary {

    @JacksonXmlProperty(isAttribute = true, localName = "generated_at")
    private String generatedAt;

    @JacksonXmlProperty(localName = "sport_event")
    private SportEvent sportEvent;

    @JacksonXmlProperty(localName = "sport_event_conditions")
    private SportEventConditions sportEventConditions;

    @JacksonXmlProperty(localName = "sport_event_status")
    private SportEventStatus sportEventStatus;

    // Getters and Setters
    public String getGeneratedAt() {
        return generatedAt;
    }
    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }
    public SportEvent getSportEvent() {
        return sportEvent;
    }
    public void setSportEvent(SportEvent sportEvent) {
        this.sportEvent = sportEvent;
    }
    public SportEventConditions getSportEventConditions() {
        return sportEventConditions;
    }
    public void setSportEventConditions(SportEventConditions sportEventConditions) {
        this.sportEventConditions = sportEventConditions;
    }
    public SportEventStatus getSportEventStatus() {
        return sportEventStatus;
    }
    public void setSportEventStatus(SportEventStatus sportEventStatus) {
        this.sportEventStatus = sportEventStatus;
    }
}
