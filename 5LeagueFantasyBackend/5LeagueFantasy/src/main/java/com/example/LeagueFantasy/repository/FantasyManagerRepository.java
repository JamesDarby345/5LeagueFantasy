package com.example.LeagueFantasy.repository;

import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.League;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface FantasyManagerRepository extends CrudRepository<FantasyManager, Integer> {

  // Use this as unique id for each user
  FantasyManager findByUsername(String username);

  List<FantasyManager> findByName(String name);

  FantasyManager findByEmail(String email);

  List<FantasyManager> findByLeague(League league);

  List<FantasyManager> findAll();
}
