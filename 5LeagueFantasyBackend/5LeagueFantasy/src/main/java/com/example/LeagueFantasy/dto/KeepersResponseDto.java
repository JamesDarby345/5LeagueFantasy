package com.example.LeagueFantasy.dto;

import com.example.LeagueFantasy.Entity.EuropeanLeague;

public class KeepersResponseDto {
    private String name;
    private String team;
    private EuropeanLeague europeanLeague;
    private String position;
    private int goals;
    private int assists;
    private int saves;
    private int cleanSheets;

    public KeepersResponseDto(String name, String team, EuropeanLeague europeanLeague, String position, int gamesPlayed,
            int goals, int assists,
            int saves, int cleanSheets) {
        this.name = name;
        this.team = team;
        this.europeanLeague = europeanLeague;
        this.position = position;
        this.goals = goals;
        this.assists = assists;
        this.saves = saves;
        this.cleanSheets = cleanSheets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public EuropeanLeague getEuropeanLeague() {
        return europeanLeague;
    }

    public void setEuropeanLeague(EuropeanLeague europeanLeague) {
        this.europeanLeague = europeanLeague;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getSaves() {
        return saves;
    }

    public void setSaves(int saves) {
        this.saves = saves;
    }

    public int getCleanSheets() {
        return cleanSheets;
    }

    public void setCleanSheets(int cleanSheets) {
        this.cleanSheets = cleanSheets;
    }
}