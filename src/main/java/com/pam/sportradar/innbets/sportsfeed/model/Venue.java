package com.pam.sportradar.innbets.sportsfeed.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Venue {


    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private String id;

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;

    @JacksonXmlProperty(isAttribute = true, localName = "capacity")
    private String capacity;

    @JacksonXmlProperty(isAttribute = true, localName = "city_name")
    private String cityName;

    @JacksonXmlProperty(isAttribute = true, localName = "country_name")
    private String countryName;

    @JacksonXmlProperty(isAttribute = true, localName = "country_code")
    private String countryCode;

    @JacksonXmlProperty(isAttribute = true, localName = "map_coordinates")
    private String mapCoordinates;

    @JacksonXmlProperty(isAttribute = true, localName = "state")
    private String state;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCapacity() {
        return capacity;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getMapCoordinates() {
        return mapCoordinates;
    }
    public void setMapCoordinates(String mapCoordinates) {
        this.mapCoordinates = mapCoordinates;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
