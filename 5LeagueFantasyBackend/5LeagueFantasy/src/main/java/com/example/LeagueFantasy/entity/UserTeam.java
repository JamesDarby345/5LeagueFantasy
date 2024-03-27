package com.example.LeagueFantasy.entity;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

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

  @Column(name = "numberOfKeepers", nullable = false)
  private int numberOfKeepers;
  
  @Column(name = "weekStartDate", nullable = false)
  private Date weekStartDate;
  
  @Column(name = "numberOfForwards", nullable = false)
  private int numberOfForwards;


  public UserTeam(String name, int points, Boolean isActive) {
    this.name = name;
    this.points = points;
    this.isActive = isActive;
    LocalDate startOfThisWeek = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
    this.weekStartDate = Date.valueOf(startOfThisWeek);
    this.numberOfForwards = 0;
    this.numberOfKeepers = 0;
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

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public Date getWeekStartDate() {
	 return this.weekStartDate;
  }
  
  public void setWeekStartDate(Date weekStartDate) {
	 this.weekStartDate = weekStartDate;
  }

  public Boolean isIsActive() {
    return this.isActive;
  }

  public Boolean getIsActive() {
    return this.isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public FantasyManager getFantasyManager() {
    return this.fantasyManager;
  }

  public void setFantasyManager(FantasyManager fantasyManager) {
    this.fantasyManager = fantasyManager;
  }

  public int getNumberOfKeepers() {
    return this.numberOfKeepers;
  }

  public void setNumberOfKeepers(int numberOfKeepers) {
    this.numberOfKeepers = numberOfKeepers;
  }

  public int getNumberOfForwards() {
    return this.numberOfForwards;
  }

  public void setNumberOfForwards(int numberOfForwards) {
    this.numberOfForwards = numberOfForwards;
  }
}
