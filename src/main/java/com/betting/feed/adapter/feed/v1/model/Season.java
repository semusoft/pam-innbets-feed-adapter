package com.betting.feed.adapter.feed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Season {
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private String id;

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;

    @JacksonXmlProperty(isAttribute = true, localName = "start_date")
    private String startDate;

    @JacksonXmlProperty(isAttribute = true, localName = "end_date")
    private String endDate;

    @JacksonXmlProperty(isAttribute = true, localName = "year")
    private String year;

    @JacksonXmlProperty(isAttribute = true, localName = "tournament_id")
    private String tournamentId;

    // Getters and setters
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
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getTournamentId() {
        return tournamentId;
    }
    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }
}
