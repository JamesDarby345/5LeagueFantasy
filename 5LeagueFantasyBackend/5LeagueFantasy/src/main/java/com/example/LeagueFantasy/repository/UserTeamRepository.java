package com.example.LeagueFantasy.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.LeagueFantasy.Entity.FantasyManager;
import com.example.LeagueFantasy.Entity.UserTeam;
import java.util.List;
import java.sql.Date;

public interface UserTeamRepository extends CrudRepository<UserTeam, Integer> {

    UserTeam findById(int id);
    List<UserTeam> findByName(String name);
    List<UserTeam> findByWeekStartDate(Date weekStartDate);
    List<UserTeam> findByFantasyManager(FantasyManager manager);
    List<UserTeam> findAll();

}
