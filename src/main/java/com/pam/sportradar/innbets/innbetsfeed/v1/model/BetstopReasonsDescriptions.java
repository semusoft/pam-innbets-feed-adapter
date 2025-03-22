package com.pam.sportradar.innbets.innbetsfeed.v1.model;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BetstopReasonsDescriptions {

    @JacksonXmlProperty(isAttribute = true, localName = "response_code")
    private String responseCode;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "betstop_reason")
    private List<BetstopReason> betstopReason;

    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    public List<BetstopReason> getBetstopReason() {
        return betstopReason;
    }
    public void setBetstopReason(List<BetstopReason> betstopReason) {
        this.betstopReason = betstopReason;
    }
}

