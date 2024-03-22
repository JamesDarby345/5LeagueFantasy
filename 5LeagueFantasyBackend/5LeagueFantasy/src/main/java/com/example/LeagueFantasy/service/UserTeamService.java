package com.example.LeagueFantasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.UserTeam;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.FantasyManagerRepository;
import com.example.LeagueFantasy.repository.UserTeamRepository;

import jakarta.transaction.Transactional;

public class UserTeamService {

    @Autowired 
    private FantasyManagerRepository fantasyManagerRepository;

    @Autowired
    private UserTeamRepository userTeamRepository;

    @Transactional
    public UserTeam createUserTeam(UserTeam userTeam, String fantasyManagerUsername) throws  FiveLeagueFantasyException {

        if(userTeam.getName().equals("") || userTeam.getName() == null) {
            throw new FiveLeagueFantasyException("Name of team can't be empty.", HttpStatus.BAD_REQUEST);
        }

        FantasyManager fantasyManager = fantasyManagerRepository.findByUsername(fantasyManagerUsername);

        if(fantasyManager == null) {
            throw new FiveLeagueFantasyException("Associated fantast manager does not exist.", HttpStatus.BAD_REQUEST);
        }

        userTeam.setPoints(0);
        userTeam.setActive(true);
        userTeam.setManager(fantasyManager);
        
        return userTeamRepository.save(userTeam);
    }
    
}
