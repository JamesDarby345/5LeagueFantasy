package com.example.LeagueFantasy.repository;

import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.League;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FantasyManagerRepository extends CrudRepository<FantasyManager, Integer> {

  // Use this as unique id for each user
  FantasyManager findByUsername(String username);

  List<FantasyManager> findByName(String name);

  FantasyManager findByEmail(String email);

  List<FantasyManager> findByLeague(League league);

  List<FantasyManager> findAll();
}
