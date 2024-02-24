package com.example.LeagueFantasy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class League {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "leagueOwner", nullable = false)
  private FantasyManager leagueOwner;

  public League(String name) {
    this.name = name;
  }

  public League() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public FantasyManager getLeagueOwner() {
    return this.leagueOwner;
  }

  public void setLeagueOwner(FantasyManager leagueOwner) {
    this.leagueOwner = leagueOwner;
  }
}
