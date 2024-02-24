package com.example.LeagueFantasy.dto;

import com.example.LeagueFantasy.entity.League;

public class FantasyManagerResponseDto {

  private final String username;
  private final String name;
  private final String email;
  private final String password;
  private final League league;

  public FantasyManagerResponseDto(
      String username, String name, String email, String password, League league) {
    this.username = username;
    this.name = name;
    this.email = email;
    this.password = password;
    this.league = league;
  }

  public String getUsername() {
    return this.username;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public League getLeague() {
    return this.league;
  }
}
