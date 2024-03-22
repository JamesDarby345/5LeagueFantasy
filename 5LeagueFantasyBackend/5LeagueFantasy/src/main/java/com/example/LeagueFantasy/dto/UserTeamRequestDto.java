package com.example.LeagueFantasy.dto;

import org.springframework.lang.NonNull;

public class UserTeamRequestDto {

    @NonNull
    private String name;

    @NonNull
    private boolean setAsActive;

    @NonNull
    private String fantasyManagerUsername;

    public UserTeamRequestDto(String name, boolean setAsActive, String fantasyManagerUsername) {
        this.name = name;
        this.setAsActive = setAsActive;
        this.fantasyManagerUsername = fantasyManagerUsername;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSetAsActive() {
        return this.setAsActive;
    }

    public void setSetAsActive(boolean setAsActive) {
        this.setAsActive = setAsActive;
    }

    public String getFantasyManagerUsername() {
        return this.fantasyManagerUsername;
    }

    public void setFantasyManagerUsername(String fantasyManagerUsername) {
        this.fantasyManagerUsername = fantasyManagerUsername;
    }
}
