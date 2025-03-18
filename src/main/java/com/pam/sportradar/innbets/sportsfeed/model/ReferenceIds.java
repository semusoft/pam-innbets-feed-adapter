package com.pam.sportradar.innbets.sportsfeed.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferenceIds {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "reference_id")
    private List<ReferenceId> referenceIds;

    public List<ReferenceId> getReferenceIds() {
        return referenceIds;
    }
    public void setReferenceIds(List<ReferenceId> referenceIds) {
        this.referenceIds = referenceIds;
    }
}
