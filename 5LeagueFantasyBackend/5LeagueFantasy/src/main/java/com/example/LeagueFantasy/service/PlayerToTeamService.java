package com.example.LeagueFantasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.LeagueFantasy.entity.Forward;
import com.example.LeagueFantasy.entity.Goalkeeper;
import com.example.LeagueFantasy.entity.Player;
import com.example.LeagueFantasy.entity.PlayerToTeam;
import com.example.LeagueFantasy.entity.UserTeam;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.ForwardRepository;
import com.example.LeagueFantasy.repository.GoalkeeperRepository;
import com.example.LeagueFantasy.repository.PlayerToTeamRepository;
import com.example.LeagueFantasy.repository.UserTeamRepository;

import jakarta.transaction.Transactional;

@Service
public class PlayerToTeamService {

    @Autowired
    private PlayerToTeamRepository playerToTeamRepository;

    @Autowired
    private ForwardRepository forwardRepository;

    @Autowired
    private GoalkeeperRepository goalkeeperRepository;

    @Autowired
    private UserTeamRepository userTeamRepository;

    @Transactional
    public PlayerToTeam addForwardToTeamRequest(int forwardId, int goalkeeperId, int userTeamId) {

        Forward chosenForward = forwardRepository.findById(forwardId);
        UserTeam userTeamToAddTo = userTeamRepository.findById(userTeamId);

        if (chosenForward == null) {
            throw new FiveLeagueFantasyException("Forward does not exist", HttpStatus.BAD_REQUEST);
        } else if (userTeamToAddTo == null) {
            throw new FiveLeagueFantasyException("User team does not exist", HttpStatus.BAD_REQUEST);
        }

        int currentNumberOfForwards = userTeamToAddTo.getNumberOfForwards();

        if (currentNumberOfForwards == 5) {
            throw new FiveLeagueFantasyException("User team already has 5 forwards", HttpStatus.BAD_REQUEST);
        }

        PlayerToTeam playerAlreadyAddedToUserTeam = playerToTeamRepository.findByUserTeamAndForward(userTeamToAddTo, chosenForward);

        if (playerAlreadyAddedToUserTeam != null) {
            throw new FiveLeagueFantasyException("Player already added to team", HttpStatus.BAD_REQUEST);
        }

        PlayerToTeam playerToTeam = new PlayerToTeam();
        playerToTeam.setGoalkeeper(null);
        playerToTeam.setForward(chosenForward);
        playerToTeam.setUserTeam(userTeamToAddTo);
        userTeamToAddTo.setNumberOfForwards(currentNumberOfForwards + 1);
        userTeamRepository.save(userTeamToAddTo);
        return playerToTeamRepository.save(playerToTeam);
    }

    @Transactional
    public PlayerToTeam addGoalkeeperToTeamRequest(int forwardId, int goalkeeperId, int userTeamId) {

        Goalkeeper chosenGoalkeeper = goalkeeperRepository.findById(goalkeeperId);
        UserTeam userTeamToAddTo = userTeamRepository.findById(userTeamId);

        if (userTeamToAddTo == null) {
            throw new FiveLeagueFantasyException("User team does not exist", HttpStatus.BAD_REQUEST);
        } else if (chosenGoalkeeper == null) {
            throw new FiveLeagueFantasyException("Goalkeeper does not exist", HttpStatus.BAD_REQUEST);
        }

        int currentNumberOfGoalkeepers = userTeamToAddTo.getNumberOfKeepers();

        if (currentNumberOfGoalkeepers == 1) {
            throw new FiveLeagueFantasyException("User team already has a goalkeeper", HttpStatus.BAD_REQUEST);
        }

        PlayerToTeam goalkeeperAlreadyAddedToUserTeam = playerToTeamRepository.findByUserTeamAndGoalkeeper(userTeamToAddTo, chosenGoalkeeper);

        if (goalkeeperAlreadyAddedToUserTeam != null) {
            throw new FiveLeagueFantasyException("Goalkeeper already added to team", HttpStatus.BAD_REQUEST);
        }

        PlayerToTeam  playerToTeam = new PlayerToTeam();
        playerToTeam.setGoalkeeper(chosenGoalkeeper);
        playerToTeam.setForward(null);
        playerToTeam.setUserTeam(userTeamToAddTo);

        userTeamToAddTo.setNumberOfKeepers(1);
        userTeamRepository.save(userTeamToAddTo);
        
        return playerToTeamRepository.save(playerToTeam);
    }

    @Transactional
    public List<PlayerToTeam> getPlayersByUserTeam(int userTeamId) {
        
        UserTeam userTeam = userTeamRepository.findById(userTeamId);

        if (userTeam == null) {
            throw new FiveLeagueFantasyException("User team does not exist", HttpStatus.BAD_REQUEST);
        }

        List<PlayerToTeam> playersOfUserTeam = playerToTeamRepository.findByUserTeam(userTeam);

        if (playersOfUserTeam == null) {
            throw new FiveLeagueFantasyException("User team has no players", HttpStatus.NOT_FOUND);
        }

        return playersOfUserTeam;
    }
}
