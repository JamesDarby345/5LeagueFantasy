package com.example.LeagueFantasy.repository;

import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.UserTeam;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface UserTeamRepository extends CrudRepository<UserTeam, Integer> {

  UserTeam findById(int id);

  List<UserTeam> findByName(String name);

  List<UserTeam> findByWeekStartDate(Date weekStartDate);

  List<UserTeam> findByFantasyManager(FantasyManager manager);

  List<UserTeam> findAll();
}
