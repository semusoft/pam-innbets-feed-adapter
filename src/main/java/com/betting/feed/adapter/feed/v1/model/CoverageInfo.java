package com.betting.feed.adapter.feed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoverageInfo {

    @JacksonXmlProperty(isAttribute = true, localName = "live_coverage")
    private String liveCoverage;

    public String getLiveCoverage() {
        return liveCoverage;
    }
    public void setLiveCoverage(String liveCoverage) {
        this.liveCoverage = liveCoverage;
    }
}
