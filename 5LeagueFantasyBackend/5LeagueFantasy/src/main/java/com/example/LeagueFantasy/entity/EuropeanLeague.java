package com.example.LeagueFantasy.entity;

public enum EuropeanLeague {
  Bundesliga("Bundesliga"),
  Ligue1("Ligue 1"),
  LaLiga("La Liga"),
  SerieA("Serie A"),
  PremierLeague("Premier League"),
  UNKNOWN("Unknown");

  private final String leagueName;

  EuropeanLeague(String leagueName) {
    this.leagueName = leagueName;
  }

  public static EuropeanLeague fromString(String leagueName) {
    for (EuropeanLeague league : values()) {
      if (league.getLeagueName().equalsIgnoreCase(leagueName)) {
        return league;
      }
    }
    return UNKNOWN; // Return UNKNOWN if no match is found
  }

  public String getLeagueName() {
    return leagueName;
  }
}
