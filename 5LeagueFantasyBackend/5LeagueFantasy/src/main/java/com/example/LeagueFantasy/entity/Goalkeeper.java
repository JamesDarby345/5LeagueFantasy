package com.example.LeagueFantasy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Goalkeeper extends Player {
  @Column(name = "saves", nullable = false)
  private int saves;

  @Column(name = "cleanSheets", nullable = false)
  private int cleanSheets;

  @Column(name = "goals", nullable = false)
  private int goals;

  @Column(name = "assists", nullable = false)
  private int assists;

  @Column(name = "position", nullable = false)
  private String position;

  @OneToMany
  @JoinColumn(name = "keeperToTeam")
  private List<PlayerToTeam> keeperToTeam;

  public Goalkeeper(
      String name,
      String team,
      String position,
      int gamesPlayed,
      EuropeanLeague europeanLeague,
      int goals,
      int assists,
      int saves,
      int cleanSheets) {
    super(name, team, gamesPlayed, europeanLeague);
    this.position = position;
    this.saves = saves;
    this.cleanSheets = cleanSheets;
    this.goals = goals;
    this.assists = assists;
  }

  public Goalkeeper() {
    super();
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

  public List<PlayerToTeam> getKeeperToTeam() {
    return keeperToTeam;
  }

  public void setKeeperToTeam(List<PlayerToTeam> playersToTeam) {
    this.keeperToTeam = playersToTeam;
  }
}
