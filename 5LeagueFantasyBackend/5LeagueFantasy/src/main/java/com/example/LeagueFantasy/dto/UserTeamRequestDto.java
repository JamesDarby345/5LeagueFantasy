package com.example.LeagueFantasy.dto;

import org.springframework.lang.NonNull;

public class UserTeamRequestDto {

    @NonNull
    private String name;

    @NonNull
    private int points;
    
    @NonNull
    private Boolean isActive;

    @NonNull
    private String fantasyManagerUsername;

    public UserTeamRequestDto(String name, int points, Boolean isActive, String fantasyManagerUsername) {
        this.name = name;
        this.points = points;
        this.isActive = isActive;
        this.fantasyManagerUsername = fantasyManagerUsername;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getfantasyManagerUsername() {
        return this.fantasyManagerUsername;
    }

    public void setfantasyManagerUsername(String fantasyManagerUsername) {
        this.fantasyManagerUsername = fantasyManagerUsername;
    }
}
