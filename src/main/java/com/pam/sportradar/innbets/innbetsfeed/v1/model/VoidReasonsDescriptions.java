package com.pam.sportradar.innbets.innbetsfeed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VoidReasonsDescriptions {

    @JacksonXmlProperty(isAttribute = true, localName = "response_code")
    private String responseCode;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "void_reason")
    private List<VoidReason> voidReasons;

    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    public List<VoidReason> getVoidReasons() {
        return voidReasons;
    }
    public void setVoidReasons(List<VoidReason> voidReasons) {
        this.voidReasons = voidReasons;
    }
}
