package com.example.LeagueFantasy.dto;

import org.springframework.lang.NonNull;

public class PlayerToTeamRequestDto {

    @NonNull
    private int userTeamId;
    private int forwardId;
    private int goalkeeperId;

    public PlayerToTeamRequestDto(int userTeamId, int forwardId, int goalkeeperId) {
        this.userTeamId = userTeamId;
        this.forwardId = forwardId;
        this.goalkeeperId = goalkeeperId;
    }

    public PlayerToTeamRequestDto() {
    }
    
    public int getUserTeamId() {
        return this.userTeamId;
    }

    public void setUserTeamId(int userTeamId) {
        this.userTeamId = userTeamId;
    }

    public int getForwardId() {
        return this.forwardId;
    }

    public void setForwardId(int forwardId) {
        this.forwardId = forwardId;
    }

    public int getGoalkeeperId() {
        return this.goalkeeperId;
    }

    public void setGoalkeeperId(int goalkeeperId) {
        this.goalkeeperId = goalkeeperId;
    }


    
}
