package com.example.LeagueFantasy.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.LeagueFantasy.Entity.PlayerToTeam;
import com.example.LeagueFantasy.Entity.UserTeam;

import java.sql.Date;
import java.util.List;

public interface PlayerToTeamRepository extends CrudRepository<PlayerToTeam, Integer> {
    
    PlayerToTeam findById(int id);
    List<PlayerToTeam> findByDateAdded(Date dateAdded);
    List<PlayerToTeam> findByUserTeam(UserTeam userTeam);
    List<PlayerToTeam> findAll();
}
