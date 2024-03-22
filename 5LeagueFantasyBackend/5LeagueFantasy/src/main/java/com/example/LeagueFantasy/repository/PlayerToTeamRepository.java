package com.example.LeagueFantasy.repository;

import com.example.LeagueFantasy.entity.Forward;
import com.example.LeagueFantasy.entity.Goalkeeper;
import com.example.LeagueFantasy.entity.PlayerToTeam;
import com.example.LeagueFantasy.entity.UserTeam;
import java.sql.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PlayerToTeamRepository extends CrudRepository<PlayerToTeam, Integer> {

  PlayerToTeam findById(int id);

  List<PlayerToTeam> findByUserTeam(UserTeam userTeam);

  List<PlayerToTeam> findAll();

  PlayerToTeam findByUserTeamAndForward(UserTeam userTeam, Forward forward);

  PlayerToTeam findByUserTeamAndGoalkeeper(UserTeam userTeam, Goalkeeper goalkeeper);

}
