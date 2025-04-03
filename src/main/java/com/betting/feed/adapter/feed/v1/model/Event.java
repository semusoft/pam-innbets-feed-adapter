package com.betting.feed.adapter.feed.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private String id;

    @JacksonXmlProperty(isAttribute = true, localName = "type")
    private String type;

    @JacksonXmlProperty(isAttribute = true, localName = "match_time")
    private String matchTime;

    @JacksonXmlProperty(isAttribute = true, localName = "team")
    private String team;

    @JacksonXmlProperty(localName = "goal_scorer")
    private Player goalScorer;

    @JacksonXmlProperty(localName = "assist")
    private Assist assist;

    @JacksonXmlProperty(localName = "player")
    private Player player;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMatchTime() {
        return matchTime;
    }
    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public Player getGoalScorer() {
        return goalScorer;
    }
    public void setGoalScorer(Player goalScorer) {
        this.goalScorer = goalScorer;
    }
    public Assist getAssist() {
        return assist;
    }
    public void setAssist(Assist assist) {
        this.assist = assist;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}
