package com.pam.sportradar.innbets.sportsfeed.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VariantDescriptions {

    @JacksonXmlProperty(isAttribute = true, localName = "response_code")
    private String responseCode;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "variant")
    private List<Variant> variants;

    // Getters and Setters
    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    public List<Variant> getVariants() {
        return variants;
    }
    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }
}
