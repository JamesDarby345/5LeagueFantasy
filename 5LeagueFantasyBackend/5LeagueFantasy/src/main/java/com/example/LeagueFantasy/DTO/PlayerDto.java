package com.example.LeagueFantasy.DTO;

import com.example.LeagueFantasy.Entity.EuropeanLeague;

public class PlayerDto {
	private String name;
	private String team;
	private int gamesPlayed;
	private EuropeanLeague europeanLeague;
	
	public PlayerDto(String name, String team, int gamesPlayed, EuropeanLeague europeanLeague) {
		this.name = name;
		this.team = team;
		this.gamesPlayed = gamesPlayed;
		this.europeanLeague = europeanLeague;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	
	public EuropeanLeague getEuropeanLeague() {
		return europeanLeague;
	}
	public void setEuropeanLeague(EuropeanLeague europeanLeague) {
		this.europeanLeague = europeanLeague;
	}
}
