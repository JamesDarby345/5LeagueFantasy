package com.example.LeagueFantasy.repository;

import com.example.LeagueFantasy.entity.EuropeanLeague;
import com.example.LeagueFantasy.entity.Goalkeeper;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface GoalkeeperRepository extends CrudRepository<Goalkeeper, Integer> {

  Goalkeeper findById(int id);

  List<Goalkeeper> findByName(String name);

  List<Goalkeeper> findByTeam(String teamName);

  List<Goalkeeper> findByEuropeanLeague(EuropeanLeague europeanLeague);

  List<Goalkeeper> findAll();

  List<Goalkeeper> findByPosition(String position);

  List<Goalkeeper> findByNameContainingIgnoreCase(String name);

  List<Goalkeeper> findByCleanSheets(int cleanSheets);

}
