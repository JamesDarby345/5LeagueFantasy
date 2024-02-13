package com.example.LeagueFantasy.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.LeagueFantasy.Entity.FantasyManager;
import com.example.LeagueFantasy.Entity.League;
import java.util.List;

public interface FantasyManagerRepository extends CrudRepository<FantasyManager, Integer> {

    // Use this as unique id for each user
    FantasyManager findByUsername(String username);
    List<FantasyManager> findByName(String name);
    FantasyManager findByEmail(String email);
    List<FantasyManager> findByLeague(League league);
    List<FantasyManager> findAll();  
}
