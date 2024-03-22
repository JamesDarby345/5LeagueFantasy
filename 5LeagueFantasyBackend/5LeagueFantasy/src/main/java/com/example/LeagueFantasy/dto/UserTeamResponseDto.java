package com.example.LeagueFantasy.dto;

import com.example.LeagueFantasy.entity.FantasyManager;

public class UserTeamResponseDto {

    private final String fantasyManagerUsername;
    private final String name;
    private final int points;
    private final Boolean isActive;
    private final FantasyManager fantasyManager;


    public UserTeamResponseDto(String fantasyManagerUsername, String name, int points, Boolean isActive, FantasyManager fantasyManager) {
        this.fantasyManagerUsername = fantasyManagerUsername;
        this.name = name;
        this.points = points;
        this.isActive = isActive;
        this.fantasyManager = fantasyManager;
    }

    public String getfantasyManagerUsername() {
        return this.fantasyManagerUsername;
    }

    public String getName() {
        return this.name;
    }

    public int getPoints() {
        return this.points;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public FantasyManager getFantasyManager() {
        return this.fantasyManager;
    }
}
