package com.example.LeagueFantasy.service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.UserTeam;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.FantasyManagerRepository;
import com.example.LeagueFantasy.repository.UserTeamRepository;

import jakarta.transaction.Transactional;

@Service
public class UserTeamService {

    @Autowired 
    private FantasyManagerRepository fantasyManagerRepository;

    @Autowired
    private UserTeamRepository userTeamRepository;

    @Transactional
    public UserTeam createUserTeam(UserTeam userTeam, String fantasyManagerUsername) throws  FiveLeagueFantasyException {

        if(userTeam.getName().equals("") || userTeam.getName() == null) {
            throw new FiveLeagueFantasyException("Name of team can't be empty.", HttpStatus.BAD_REQUEST);
        } else if (userTeamRepository.findByName(fantasyManagerUsername) != null) {
            throw new FiveLeagueFantasyException("Team with that name already exists.", HttpStatus.BAD_REQUEST);
        }

        FantasyManager fantasyManager = fantasyManagerRepository.findByUsername(fantasyManagerUsername);

        if(fantasyManager == null) {
            throw new FiveLeagueFantasyException("Associated fantasy manager does not exist.", HttpStatus.BAD_REQUEST);
        }

        // If user wants to set current new team to active, set other ones to inactive
        if(userTeam.getActive() == true) {
            List<UserTeam> allTeamsOwnedByManager = userTeamRepository.findByFantasyManager(fantasyManager);

            for(UserTeam team: allTeamsOwnedByManager) {
                team.setActive(false);
            }
        }

        userTeam.setPoints(0);
        userTeam.setNumberOfForwards(0);
        userTeam.setNumberOfKeepers(0);
        userTeam.setManager(fantasyManager);
        LocalDate startOfThisWeek = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        userTeam.setWeekStartDate(Date.valueOf(startOfThisWeek));
        return userTeamRepository.save(userTeam);
    }

    @Transactional
    public List<UserTeam> getUserTeamsByManager(String fantasyManagerUsername) {


        FantasyManager fantasyManager = fantasyManagerRepository.findByUsername(fantasyManagerUsername);

        if(fantasyManager == null) {
            throw new FiveLeagueFantasyException("Associated fantasy manager does not exist.", HttpStatus.BAD_REQUEST);
        }

        List<UserTeam> allTeamsOwnedByManager = userTeamRepository.findByFantasyManager(fantasyManager);

        if (allTeamsOwnedByManager == null) {
            throw new FiveLeagueFantasyException("User has not created any teams.", HttpStatus.NOT_FOUND);
        }
        return allTeamsOwnedByManager;
    }
}
