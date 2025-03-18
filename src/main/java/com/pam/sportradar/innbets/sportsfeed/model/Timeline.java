package com.pam.sportradar.innbets.sportsfeed.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Timeline {

    // Since <timeline> directly contains multiple <event> elements without an extra wrapper
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "event")
    private List<Event> events;

    // Getters and Setters
    public List<Event> getEvents() {
        return events;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
