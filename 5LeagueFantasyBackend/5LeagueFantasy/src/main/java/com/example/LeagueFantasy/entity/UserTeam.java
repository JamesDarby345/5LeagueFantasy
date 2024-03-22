package com.example.LeagueFantasy.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserTeam {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "points", nullable = false)
  private int points;

  @Column(name = "isActive", nullable = false)
  private Boolean isActive;

  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "manager", nullable = false)
  private FantasyManager fantasyManager;


  public UserTeam(String name, int points, Boolean isActive) {
    this.name = name;
    this.points = points;
    this.isActive = isActive;
  }

  public UserTeam() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public int getUserTeamId() {
    return this.id;
  }

  public void setUserTeamId(int id) {
    this.id = id;
  }

  public FantasyManager getManager() {
    return fantasyManager;
  }

  public void setManager(FantasyManager user) {
    this.fantasyManager = user;
  }
}
