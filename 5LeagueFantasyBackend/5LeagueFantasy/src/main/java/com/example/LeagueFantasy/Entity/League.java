package com.example.LeagueFantasy.Entity;

import jakarta.persistence.*;

@Entity
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;

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
}
