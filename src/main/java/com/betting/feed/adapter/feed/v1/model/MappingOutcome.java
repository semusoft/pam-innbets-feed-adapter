package com.betting.feed.adapter.feed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MappingOutcome {

    @JacksonXmlProperty(isAttribute = true, localName = "outcome_id")
    private String outcomeId;

    @JacksonXmlProperty(isAttribute = true, localName = "product_outcome_id")
    private String productOutcomeId;

    @JacksonXmlProperty(isAttribute = true, localName = "product_outcome_name")
    private String productOutcomeName;

    // Getters and Setters
    public String getOutcomeId() {
        return outcomeId;
    }
    public void setOutcomeId(String outcomeId) {
        this.outcomeId = outcomeId;
    }
    public String getProductOutcomeId() {
        return productOutcomeId;
    }
    public void setProductOutcomeId(String productOutcomeId) {
        this.productOutcomeId = productOutcomeId;
    }
    public String getProductOutcomeName() {
        return productOutcomeName;
    }
    public void setProductOutcomeName(String productOutcomeName) {
        this.productOutcomeName = productOutcomeName;
    }
}

