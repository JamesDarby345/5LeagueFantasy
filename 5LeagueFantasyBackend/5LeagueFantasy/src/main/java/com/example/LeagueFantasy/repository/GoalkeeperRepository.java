package com.example.LeagueFantasy.repository;

import com.example.LeagueFantasy.entity.EuropeanLeague;
import com.example.LeagueFantasy.entity.Goalkeeper;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GoalkeeperRepository extends CrudRepository<Goalkeeper, Integer> {

  Goalkeeper findById(int id);

  List<Goalkeeper> findByName(String name);

  List<Goalkeeper> findByTeam(String teamName);

  List<Goalkeeper> findByEuropeanLeague(EuropeanLeague europeanLeague);

  List<Goalkeeper> findAll();
}
