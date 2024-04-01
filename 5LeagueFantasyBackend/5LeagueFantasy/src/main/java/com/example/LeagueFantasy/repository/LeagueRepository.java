package com.example.LeagueFantasy.repository;

import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.League;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface LeagueRepository extends CrudRepository<League, Integer> {

  League findById(int id);

  List<League> findByNameContainingIgnoreCase(String name);

  List<League> findByLeagueOwner(FantasyManager leagueOwner);

      boolean existsByName(String name);
}
