package com.example.LeagueFantasy.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.LeagueFantasy.Entity.FantasyManager;
import com.example.LeagueFantasy.Entity.League;
import java.util.List;

public interface LeagueRepository extends CrudRepository<League, Integer> {

    League findById(int id);
    League findByName(String name);
    List<League> findByLeagueOwner(FantasyManager leagueOwner); 
}
