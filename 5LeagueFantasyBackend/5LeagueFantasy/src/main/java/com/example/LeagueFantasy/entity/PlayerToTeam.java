package com.example.LeagueFantasy.entity;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PlayerToTeam {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "userTeam", nullable = false)
  private UserTeam userTeam;

  @ManyToOne
  @JoinColumn(name = "forward", nullable = true)
  private Forward forward;

  @ManyToOne
  @JoinColumn(name = "goalkeeper", nullable = true)
  private Goalkeeper goalkeeper;

  @JoinColumn(name = "dateAdded", nullable = false)
  private Date dateAdded;
  
  public PlayerToTeam() {
	  dateAdded = Date.valueOf(LocalDate.now());
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public UserTeam getUserTeam() {
    return userTeam;
  }

  public void setUserTeam(UserTeam userTeam) {
    this.userTeam = userTeam;
  }

  public Forward getForward() {
    return this.forward;
  }

  public void setForward(Forward forward) {
    this.forward = forward;
  }

  public Goalkeeper getGoalkeeper() {
    return this.goalkeeper;
  }

  public void setGoalkeeper(Goalkeeper goalkeeper) {
    this.goalkeeper = goalkeeper;
  }
}
