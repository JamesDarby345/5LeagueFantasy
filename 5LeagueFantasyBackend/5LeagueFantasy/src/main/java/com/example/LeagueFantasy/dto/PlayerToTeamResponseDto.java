package com.example.LeagueFantasy.dto;

public class PlayerToTeamResponseDto {

    private int playerToTeamId;
    private int userTeamId;
    private int forwardId;
    private int goalkeeperId;

    public PlayerToTeamResponseDto(int playerToTeamId, int userTeamId, int forwardId, int goalkeeperId) {
        this.playerToTeamId = playerToTeamId;
        this.userTeamId = userTeamId;
        this.forwardId = forwardId;
        this.goalkeeperId = goalkeeperId;
    }

    public PlayerToTeamResponseDto() {
    }

    public int getPlayerToTeamId() {
        return this.playerToTeamId;
    }

    public int getUserTeamId() {
        return this.userTeamId;
    }

    public int getForwardId() {
        return this.forwardId;
    }

    public int getGoalkeeperId() {
        return this.goalkeeperId;
    }
}
