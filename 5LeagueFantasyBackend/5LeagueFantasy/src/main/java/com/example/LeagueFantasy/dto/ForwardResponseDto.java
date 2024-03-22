package com.example.LeagueFantasy.dto;

import com.example.LeagueFantasy.entity.EuropeanLeague;

public class ForwardResponseDto {
  private String name;
  private String team;
  private EuropeanLeague europeanLeague;
  private String position;
  private int goals;
  private int assists;

  private final int gamesPlayed;

  public ForwardResponseDto(
      int id,
      String name,
      String team,
      EuropeanLeague europeanLeague,
      String position,
      int goals,
      int assists,
      int gamesPlayed) {
    this.name = name;
    this.team = team;
    this.europeanLeague = europeanLeague;
    this.position = position;
    this.goals = goals;
    this.assists = assists;
    this.gamesPlayed = gamesPlayed;
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

  public int getGamesPlayed() {
    return this.gamesPlayed;
  }


  public int getAssists() {
    return assists;
  }

  public void setAssists(int assists) {
    this.assists = assists;
  }
}
