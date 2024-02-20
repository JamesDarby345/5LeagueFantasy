package com.example.LeagueFantasy.repository;

import com.example.LeagueFantasy.entity.EuropeanLeague;
import com.example.LeagueFantasy.entity.Forward;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ForwardRepository extends CrudRepository<Forward, Integer> {

  Forward findById(int id);

  List<Forward> findByNameContainingIgnoreCase(String name);

  List<Forward> findByTeam(String teamName);

  List<Forward> findByPosition(String position);

  List<Forward> findByEuropeanLeague(EuropeanLeague europeanLeague);

  List<Forward> findByGoals(int goals);

  List<Forward> findByAssists(int assists);

  List<Forward> findAllByOrderByGoalsDesc();

  List<Forward> findAllByOrderByAssistsDesc();

  List<Forward> findAllByOrderByGoalsAsc();

  List<Forward> findAllByOrderByAssistsAsc();

  List<Forward> findAll();
}
