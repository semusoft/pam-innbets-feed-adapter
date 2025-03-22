package com.pam.sportradar.innbets.innbetsfeed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SportEventStatus {

    @JacksonXmlProperty(isAttribute = true, localName = "status")
    private String status;

    @JacksonXmlProperty(isAttribute = true, localName = "match_status")
    private String matchStatus;

    @JacksonXmlProperty(isAttribute = true, localName = "status_code")
    private String statusCode;

    @JacksonXmlProperty(isAttribute = true, localName = "match_status_code")
    private String matchStatusCode;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMatchStatus() {
        return matchStatus;
    }
    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getMatchStatusCode() {
        return matchStatusCode;
    }
    public void setMatchStatusCode(String matchStatusCode) {
        this.matchStatusCode = matchStatusCode;
    }
}

