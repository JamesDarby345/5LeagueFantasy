package com.example.LeagueFantasy.service;

import com.example.LeagueFantasy.entity.League;
import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.List;

@Service
public class LeagueService {

      @Autowired
      private LeagueRepository leagueRepository;


      @Transactional(readOnly = true)
      public List<League> getAllLeagues() {
            List<League> leagues = new ArrayList<>();
            leagueRepository.findAll().forEach(leagues::add);
            return leagues;
      }

      @Transactional(readOnly = true)
      public League getLeagueByName(String name) {
            return leagueRepository.findByName(name);
      }

      @Transactional(readOnly = true)
      public List<League> getLeaguesByOwner(FantasyManager manager) {
            return leagueRepository.findByLeagueOwner(manager);
      }

      @Transactional
      public void deleteLeague(int id) {
            leagueRepository.deleteById(id);
      }

}
