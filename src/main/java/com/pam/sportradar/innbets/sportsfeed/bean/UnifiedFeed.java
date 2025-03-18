package com.pam.sportradar.innbets.sportsfeed.bean;

import com.pam.sportradar.innbets.sportsfeed.model.*;
import java.util.List;

public class UnifiedFeed {
    private List<Sport> sportList;
    private List<Category> categoryList;
    private List<Tournament> tournamentList;
    private List<Market> marketList;
    private List<BetstopReason> betStopReasonList;
    private List<BettingStatus> bettingStatusList;
    private List<VoidReason> voidReasonList;
    public UnifiedFeed() {}

    public List<Sport> getSportList() {
        return sportList;
    }

    public void setSportList(List<Sport> sportList) {
        this.sportList = sportList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Tournament> getTournamentList() {
        return tournamentList;
    }

    public void setTournamentList(List<Tournament> tournamentList) {
        this.tournamentList = tournamentList;
    }

    public List<Market> getMarketList() {
        return marketList;
    }

    public void setMarketList(List<Market> marketList) {
        this.marketList = marketList;
    }

    public List<BetstopReason> getBetStopReasonList() {
        return betStopReasonList;
    }

    public void setBetStopReasonList(List<BetstopReason> betStopReasonList) {
        this.betStopReasonList = betStopReasonList;
    }

    public List<BettingStatus> getBettingStatusList() {
        return bettingStatusList;
    }

    public void setBettingStatusList(List<BettingStatus> bettingStatusList) {
        this.bettingStatusList = bettingStatusList;
    }

    public List<VoidReason> getVoidReasonList() {
        return voidReasonList;
    }

    public void setVoidReasonList(List<VoidReason> voidReasonList) {
        this.voidReasonList = voidReasonList;
    }
}

