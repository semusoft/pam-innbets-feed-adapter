package com.pam.sportradar.innbets.sportsfeed.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TournamentRound {

    @JacksonXmlProperty(isAttribute = true, localName = "type")
    private String type;

    @JacksonXmlProperty(isAttribute = true, localName = "number")
    private String number;

    @JacksonXmlProperty(isAttribute = true, localName = "betradar_id")
    private String betradarId;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getBetradarId() {
        return betradarId;
    }
    public void setBetradarId(String betradarId) {
        this.betradarId = betradarId;
    }
}
