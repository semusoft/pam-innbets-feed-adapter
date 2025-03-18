package com.pam.sportradar.innbets.sportsfeed.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FixturesFixture {

    @JacksonXmlProperty(isAttribute = true, localName = "generated_at")
    private String generatedAt;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "fixture")
    private List<Fixture> fixtures;

    // Getters and Setters
    public String getGeneratedAt() {
        return generatedAt;
    }
    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }
    public List<Fixture> getFixtures() {
        return fixtures;
    }
    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }
}
