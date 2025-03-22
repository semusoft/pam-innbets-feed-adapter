package com.pam.sportradar.innbets.innbetsfeed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketDescriptions {

    @JacksonXmlProperty(isAttribute = true, localName = "response_code")
    private String responseCode;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "market")
    private List<Market> markets;

    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    public List<Market> getMarkets() {
        return markets;
    }
    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }
}
