package com.pam.sportradar.innbets.innbetsfeed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Variant {

    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private String id;

    @JacksonXmlElementWrapper(localName = "outcomes")
    @JacksonXmlProperty(localName = "outcome")
    private List<Outcome> outcomes;

    @JacksonXmlElementWrapper(localName = "mappings")
    @JacksonXmlProperty(localName = "mapping")
    private List<Mapping> mappings;

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<Outcome> getOutcomes() {
        return outcomes;
    }
    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }
    public List<Mapping> getMappings() {
        return mappings;
    }
    public void setMappings(List<Mapping> mappings) {
        this.mappings = mappings;
    }
}

