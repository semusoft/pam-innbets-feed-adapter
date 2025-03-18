package com.pam.sportradar.innbets.sportsfeed.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SportEvents {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "sport_event")
    private List<SportEvent> sportEvents;

    public List<SportEvent> getSportEvents() {
        return sportEvents;
    }
    public void setSportEvents(List<SportEvent> sportEvents) {
        this.sportEvents = sportEvents;
    }
}

