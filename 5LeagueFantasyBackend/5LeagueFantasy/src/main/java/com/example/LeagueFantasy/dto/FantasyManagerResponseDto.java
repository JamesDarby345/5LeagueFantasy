package com.example.LeagueFantasy.dto;

import com.example.LeagueFantasy.Entity.League;

public class FantasyManagerResponseDto {

    private String username;
    private String name;   
    private String email;  
    private String password;
    private League league;

    public FantasyManagerResponseDto(String username, String name, String email, String password, League league) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.league = league;
    }

    public String getUsername() {
        return this.username;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public League getLeague() {
        return this.league;
    }
    
}
