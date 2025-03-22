package com.pam.sportradar.innbets.innbetsfeed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Round {

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
