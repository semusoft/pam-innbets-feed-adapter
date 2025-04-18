package com.betting.feed.adapter.feed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JacksonXmlRootElement(
        localName = "tournament_info",
        namespace = "http://schemas.sportradar.com/sportsapi/v1/unified")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TournamentInfo {

    @JacksonXmlProperty(isAttribute = true, localName = "generated_at")
    private String generatedAt;

    @JacksonXmlProperty(localName = "tournament")
    private Tournament tournament;

    @JacksonXmlProperty(localName = "season")
    private Season season;

    @JacksonXmlProperty(localName = "round")
    private Round round;

    @JacksonXmlProperty(localName = "coverage_info")
    private CoverageInfo coverageInfo;

    @JacksonXmlProperty(localName = "groups")
    private Groups groups;

    @JacksonXmlProperty(localName = "competitors")
    private Competitors competitors;
}
