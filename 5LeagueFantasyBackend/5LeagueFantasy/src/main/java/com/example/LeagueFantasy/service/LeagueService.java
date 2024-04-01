package com.example.LeagueFantasy.service;

import com.example.LeagueFantasy.dto.LeagueRequestDto;
import com.example.LeagueFantasy.dto.LeagueResponseDto;
import com.example.LeagueFantasy.entity.League;
import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.FantasyManagerRepository;
import com.example.LeagueFantasy.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

      @Autowired
      private FantasyManagerRepository fantasyManagerRepository;


      @Transactional
      public List<League> getAllLeagues() {
            List<League> leagues = new ArrayList<>();
            leagueRepository.findAll().forEach(leagues::add);
            return leagues;
      }


      @Transactional
      public LeagueResponseDto createLeague(LeagueRequestDto request, String fantasyManagerUsername) {
            FantasyManager fantasyManager = fantasyManagerRepository.findByUsername(fantasyManagerUsername);

            if (fantasyManager == null) {
                  throw new FiveLeagueFantasyException("You are not logged in.", HttpStatus.BAD_REQUEST);
            }

            if (fantasyManager.getLeague() != null) {
                  throw new FiveLeagueFantasyException("You are already in a league.", HttpStatus.BAD_REQUEST);
            }

            if (leagueRepository.existsByName(request.getName())) {
                  throw new FiveLeagueFantasyException("League name already exists, please choose another name.", HttpStatus.BAD_REQUEST);
            }

            League league = new League(request.getName());
            league.setLeagueOwner(fantasyManager);

            league = leagueRepository.save(league);

            return new LeagueResponseDto(
                    league.getId(),
                    league.getName(),
                    fantasyManager.getUsername(), // Assuming the getUsername method exists
                    "League created successfully."
            );
      }


      @Transactional
      public List<League> getLeagueByName(String name) {
            if (name == null || name.isEmpty())
                  throw new FiveLeagueFantasyException("Name can't be null.", HttpStatus.BAD_REQUEST);
            return leagueRepository.findByNameContainingIgnoreCase(name);
      }

      @Transactional
      public List<League> getLeaguesByOwner(FantasyManager manager) {
            return leagueRepository.findByLeagueOwner(manager);
      }

      @Transactional
      public void deleteLeague(int id) {
            leagueRepository.deleteById(id);
      }

}
