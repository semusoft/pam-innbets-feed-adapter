package com.pam.sportradar.innbets.sportsfeed.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Market {

    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private String id;

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;

    @JacksonXmlProperty(isAttribute = true, localName = "groups")
    private String groups;

    @JacksonXmlProperty(isAttribute = true, localName = "variant")
    private String variant;

    @JacksonXmlElementWrapper(localName = "outcomes")
    @JacksonXmlProperty(localName = "outcome")
    private List<Outcome> outcomes;

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGroups() {
        return groups;
    }
    public void setGroups(String groups) {
        this.groups = groups;
    }
    public String getVariant() {
        return variant;
    }
    public void setVariant(String variant) {
        this.variant = variant;
    }
    public List<Outcome> getOutcomes() {
        return outcomes;
    }
    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }
}
