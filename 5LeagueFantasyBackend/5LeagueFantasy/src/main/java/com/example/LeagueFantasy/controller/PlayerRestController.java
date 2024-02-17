package com.example.LeagueFantasy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.LeagueFantasy.service.PlayerService;
import com.example.LeagueFantasy.DTO.PlayerDto;
import com.example.LeagueFantasy.Entity.Player;
@CrossOrigin(origins = "*")
@RestController
public class PlayerRestController {
	@Autowired
	private PlayerService playerService;
	
	/**
	 * URL: /players/queryByName/{name}
	 */
	@GetMapping(value="/players/queryByName/{name}")
	public List<PlayerDto> getPlayersByName(@PathVariable("name") String name) {
		ArrayList<PlayerDto> players = new ArrayList<PlayerDto>();
		System.out.println(name);
		for (Player p: playerService.getPlayersByName(name)) {
			players.add(playerToPlayerDto(p));
		}
		return players;
	}
	
	private PlayerDto playerToPlayerDto(Player player) {
		return new PlayerDto(player.getName(), player.getTeam(), player.getGamesPlayed(), player.getEuropeanLeague());
	}
}
