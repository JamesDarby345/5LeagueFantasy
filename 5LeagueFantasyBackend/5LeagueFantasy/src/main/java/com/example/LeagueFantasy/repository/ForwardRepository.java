package com.example.LeagueFantasy.repository;

import com.example.LeagueFantasy.entity.EuropeanLeague;
import com.example.LeagueFantasy.entity.Forward;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ForwardRepository extends CrudRepository<Forward, Integer> {

    Forward findById(int id);

    List<Forward> findByName(String name);

    List<Forward> findByTeam(String teamName);

    List<Forward> findByPosition(String position);

    List<Forward> findByEuropeanLeague(EuropeanLeague europeanLeague);

    List<Forward> findAll();
}
