package com.example.LeagueFantasy.dto;

import com.example.LeagueFantasy.entity.FantasyManager;

public class UserTeamResponseDto {

    private final int id;
    private final String name;
    private final int points;
    private final Boolean isActive;
    private final FantasyManager fantasyManager;


    public UserTeamResponseDto(int id, String name, int points, Boolean isActive, FantasyManager fantasyManager) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.isActive = isActive;
        this.fantasyManager = fantasyManager;
    }

    public int getId() {
        return this.id;
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
