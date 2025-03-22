package com.pam.sportradar.innbets.innbetsfeed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Mapping {

    @JacksonXmlProperty(isAttribute = true, localName = "product_id")
    private String productId;

    @JacksonXmlProperty(isAttribute = true, localName = "product_ids")
    private String productIds;

    @JacksonXmlProperty(isAttribute = true, localName = "sport_id")
    private String sportId;

    @JacksonXmlProperty(isAttribute = true, localName = "market_id")
    private String marketId;

    @JacksonXmlProperty(isAttribute = true, localName = "product_market_id")
    private String productMarketId;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "mapping_outcome")
    private List<MappingOutcome> mappingOutcomes;

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductIds() {
        return productIds;
    }
    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }
    public String getSportId() {
        return sportId;
    }
    public void setSportId(String sportId) {
        this.sportId = sportId;
    }
    public String getMarketId() {
        return marketId;
    }
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }
    public String getProductMarketId() {
        return productMarketId;
    }
    public void setProductMarketId(String productMarketId) {
        this.productMarketId = productMarketId;
    }
    public List<MappingOutcome> getMappingOutcomes() {
        return mappingOutcomes;
    }
    public void setMappingOutcomes(List<MappingOutcome> mappingOutcomes) {
        this.mappingOutcomes = mappingOutcomes;
    }
}
