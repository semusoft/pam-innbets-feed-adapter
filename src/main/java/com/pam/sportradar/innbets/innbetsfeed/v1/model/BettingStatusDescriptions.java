package com.pam.sportradar.innbets.innbetsfeed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BettingStatusDescriptions {

    @JacksonXmlProperty(isAttribute = true, localName = "response_code")
    private String responseCode;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "betting_status")
    private List<BettingStatus> bettingStatus;

    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    public List<BettingStatus> getBettingStatus() {
        return bettingStatus;
    }
    public void setBettingStatus(List<BettingStatus> bettingStatus) {
        this.bettingStatus = bettingStatus;
    }
}
