package com.example.LeagueFantasy.dto;

import com.example.LeagueFantasy.entity.FantasyManager;

public class UserTeamResponseDto {

    private final int id;
    private final String name;
    private final int points;
    private final Boolean isActive;
    private final FantasyManager fantasyManager;
    private final int numberOfKeepers;
    private final int numberOfForwards;


    public UserTeamResponseDto(int id, String name, int points, Boolean isActive, FantasyManager fantasyManager, int numberOfKeepers, int numberOfForwards) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.isActive = isActive;
        this.fantasyManager = fantasyManager;
        this.numberOfKeepers = numberOfKeepers;
        this.numberOfForwards = numberOfForwards;
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

    public int getNumberOfKeepers() {
        return this.numberOfKeepers;
    }

    public int getNumberOfForwards() {
        return this.numberOfForwards;
    }
}
