package com.example.LeagueFantasy.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.LeagueFantasy.Entity.EuropeanLeague;
import com.example.LeagueFantasy.Entity.Forward;
import com.example.LeagueFantasy.Entity.UserTeam;

import java.util.List;

public interface ForwardRepository extends CrudRepository<Forward, Integer> {

    Forward findById(int id);

    List<Forward> findByNameContainingIgnoreCase(String name);

    List<Forward> findByTeam(String teamName);

    List<Forward> findByPosition(String position);

    List<Forward> findByEuropeanLeague(EuropeanLeague europeanLeague);

    List<Forward> findAll();
}
