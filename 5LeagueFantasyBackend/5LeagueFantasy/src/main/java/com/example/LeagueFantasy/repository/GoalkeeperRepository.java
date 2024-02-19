package com.example.LeagueFantasy.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.LeagueFantasy.Entity.EuropeanLeague;
import com.example.LeagueFantasy.Entity.Goalkeeper;
import com.example.LeagueFantasy.Entity.UserTeam;

import java.util.List;

public interface GoalkeeperRepository extends CrudRepository<Goalkeeper, Integer> {

    Goalkeeper findById(int id);

    List<Goalkeeper> findByName(String name);

    List<Goalkeeper> findByTeam(String teamName);

    List<Goalkeeper> findByEuropeanLeague(EuropeanLeague europeanLeague);

    List<Goalkeeper> findAll();

    List<Goalkeeper> findByPosition(String position);
}
