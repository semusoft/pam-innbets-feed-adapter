package com.pam.sportradar.innbets.innbetsfeed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Market {
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private String id;
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;
    @JacksonXmlProperty(isAttribute = true, localName = "groups")
    private String groups;
    @JacksonXmlProperty(isAttribute = true, localName = "variant")
    private String variant;
    @JacksonXmlElementWrapper(localName = "outcomes")
    @JacksonXmlProperty(localName = "outcome")
    private List<Outcome> outcomes;
    @JacksonXmlElementWrapper(localName = "specifiers")
    @JacksonXmlProperty(localName = "specifier")
    private List<Specifier> specifiers;
}
