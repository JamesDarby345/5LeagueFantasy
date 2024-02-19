package com.example.LeagueFantasy.dto;

import com.example.LeagueFantasy.Entity.EuropeanLeague;

public class ForwardResponseDto {
    private String name;
    private String team;
    private EuropeanLeague europeanLeague;
    private String position;
    private int goals;
    private int assists;

    public ForwardResponseDto(String name, String team, EuropeanLeague europeanLeague, String position, int goals, int assists) {
        this.name = name;
        this.team = team;
        this.europeanLeague = europeanLeague;
        this.position = position;
        this.goals = goals;
        this.assists = assists;
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
}
