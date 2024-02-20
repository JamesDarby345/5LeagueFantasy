package com.example.LeagueFantasy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Forward extends Player {
  @Column(name = "goals", nullable = false)
  private int goals;

  @Column(name = "assists", nullable = false)
  private int assists;

  @Column(name = "position", nullable = false)
  private String position;

  @OneToMany
  @JoinColumn(name = "forwardToTeam")
  private List<PlayerToTeam> forwardToTeam;

  public Forward(
      String name,
      String team,
      String position,
      int gamesPlayed,
      EuropeanLeague europeanLeague,
      int goals,
      int assists) {
    super(name, team, gamesPlayed, europeanLeague);
    this.goals = goals;
    this.assists = assists;
    this.position = position;
  }

  public Forward() {
    super();
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

  public String getPosition() {
    return this.position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public List<PlayerToTeam> getForwardToTeam() {
    return forwardToTeam;
  }

  public void setForwardToTeam(List<PlayerToTeam> playersToTeam) {
    this.forwardToTeam = playersToTeam;
  }
}
