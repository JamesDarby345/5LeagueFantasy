package com.example.LeagueFantasy.Entity;

import jakarta.persistence.*;

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

    public League() {

    }

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
