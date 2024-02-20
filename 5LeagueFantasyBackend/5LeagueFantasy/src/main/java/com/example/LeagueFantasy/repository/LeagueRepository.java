package com.example.LeagueFantasy.repository;

import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.League;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeagueRepository extends CrudRepository<League, Integer> {

  League findById(int id);

  League findByName(String name);

  List<League> findByLeagueOwner(FantasyManager leagueOwner);
}
