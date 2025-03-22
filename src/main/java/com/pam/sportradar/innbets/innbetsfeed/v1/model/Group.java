package com.pam.sportradar.innbets.innbetsfeed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Group {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "competitor")
    private List<Competitor> competitors;

    public List<Competitor> getCompetitors() {
        return competitors;
    }
    public void setCompetitors(List<Competitor> competitors) {
        this.competitors = competitors;
    }
}

