package com.example.LeagueFantasy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.LeagueFantasy.Entity.Player;
import com.example.LeagueFantasy.Entity.Forward;
import com.example.LeagueFantasy.Entity.Goalkeeper;
import com.example.LeagueFantasy.repository.ForwardRepository;
import com.example.LeagueFantasy.repository.GoalkeeperRepository;


@Service
public class PlayerService {
	@Autowired
	private ForwardRepository forwardRepo;
	@Autowired
	private GoalkeeperRepository goalkeeperRepo;
	
	@Transactional
	public List<Player> getPlayersByName(String name) {
		ArrayList<Player> players = new ArrayList<Player>();
		System.out.println(name);
		List<Forward> forwards = forwardRepo.findByName(name);
		List<Goalkeeper> goalkeepers = goalkeeperRepo.findByName(name);
		for (Forward f: forwards) {
			players.add(f);
		}
		for (Goalkeeper g: goalkeepers) {
			players.add(g);
		}
		return players;
	}
}
