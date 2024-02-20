package com.example.LeagueFantasy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Date;

@Entity
public class PlayerToTeam {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "dateAdded", nullable = false)
  private Date dateAdded;

  @ManyToOne
  @JoinColumn(name = "userTeam", nullable = false)
  private UserTeam userTeam;

  public PlayerToTeam(Date dateAdded) {
    this.dateAdded = dateAdded;
  }

  public PlayerToTeam() {}

  public Date getDateAdded() {
    return dateAdded;
  }

  public void setDateAdded(Date dateAdded) {
    this.dateAdded = dateAdded;
  }

  public UserTeam getUserTeam() {
    return userTeam;
  }

  public void setUserTeam(UserTeam userTeam) {
    this.userTeam = userTeam;
  }
}
