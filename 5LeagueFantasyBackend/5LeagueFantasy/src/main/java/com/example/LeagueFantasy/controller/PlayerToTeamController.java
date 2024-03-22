package com.example.LeagueFantasy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.LeagueFantasy.dto.ForwardResponseDto;
import com.example.LeagueFantasy.dto.KeepersResponseDto;
import com.example.LeagueFantasy.dto.PlayerToTeamRequestDto;
import com.example.LeagueFantasy.dto.PlayerToTeamResponseDto;
import com.example.LeagueFantasy.entity.EuropeanLeague;
import com.example.LeagueFantasy.entity.Forward;
import com.example.LeagueFantasy.entity.Goalkeeper;
import com.example.LeagueFantasy.entity.PlayerToTeam;
import com.example.LeagueFantasy.repository.ForwardRepository;
import com.example.LeagueFantasy.repository.GoalkeeperRepository;
import com.example.LeagueFantasy.service.PlayerToTeamService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PlayerToTeamController {

    @Autowired
    private PlayerToTeamService playerToTeamService;

    @Autowired
    private ForwardRepository forwardRepository;

    @Autowired
    private GoalkeeperRepository goalkeeperRepository;

    @PostMapping("/userTeam/addForwardToTeam")
    public ResponseEntity<PlayerToTeamResponseDto> addForwardToTeam(@RequestBody PlayerToTeamRequestDto playerToTeamRequest) {
        PlayerToTeam createdPlayerToTeamForward = playerToTeamService.addForwardToTeamRequest(
            playerToTeamRequest.getForwardId(), 
            playerToTeamRequest.getGoalkeeperId(),
            playerToTeamRequest.getUserTeamId());

        PlayerToTeamResponseDto createdPlayerToTeamResponse = new PlayerToTeamResponseDto(
            createdPlayerToTeamForward.getId(),
            createdPlayerToTeamForward.getUserTeam().getId(),
            createdPlayerToTeamForward.getForward().getPlayerId(),
            playerToTeamRequest.getGoalkeeperId());

        return new ResponseEntity<PlayerToTeamResponseDto>(createdPlayerToTeamResponse, HttpStatus.CREATED);
    }

    @PostMapping("/userTeam/addGoalkeeperToTeam")
    public ResponseEntity<PlayerToTeamResponseDto> addGoalkeeperToTeam(@RequestBody PlayerToTeamRequestDto playerToTeamRequest) {
        PlayerToTeam createdGoalkeeperToTeamForward = playerToTeamService.addGoalkeeperToTeamRequest(
            playerToTeamRequest.getForwardId(), 
            playerToTeamRequest.getGoalkeeperId(),
            playerToTeamRequest.getUserTeamId());

        PlayerToTeamResponseDto createdPlayerToTeamResponse = new PlayerToTeamResponseDto(
            createdGoalkeeperToTeamForward.getId(),
            createdGoalkeeperToTeamForward.getUserTeam().getId(),
            playerToTeamRequest.getForwardId(),
            createdGoalkeeperToTeamForward.getGoalkeeper().getPlayerId());

        return new ResponseEntity<PlayerToTeamResponseDto>(createdPlayerToTeamResponse, HttpStatus.CREATED);
    }

    @GetMapping("/userTeam/getFowardsByTeam/{userTeamId}")
    public ResponseEntity<List<ForwardResponseDto>> getForwardsByTeam(@PathVariable int userTeamId) {

        List<PlayerToTeam> players = playerToTeamService.getPlayersByUserTeam(userTeamId);
        List<ForwardResponseDto> fowardsResponse = new ArrayList<ForwardResponseDto>();
        ForwardResponseDto currentFwdResponse;

        for (PlayerToTeam player : players) {

            if (player.getForward() != null) {
                Forward chosenFwd = forwardRepository.findById(player.getForward().getPlayerId());
                currentFwdResponse = new ForwardResponseDto(
                    chosenFwd.getPlayerId(), 
                    chosenFwd.getName(), 
                    chosenFwd.getTeam(), 
                    chosenFwd.getEuropeanLeague(),
                    chosenFwd.getPosition(),
                    chosenFwd.getGoals(),
                    chosenFwd.getAssists(),
                    chosenFwd.getGamesPlayed());
                    fowardsResponse.add(currentFwdResponse);
            } 
        }

        return new ResponseEntity<List<ForwardResponseDto>>(fowardsResponse,  HttpStatus.OK);    
    }

    @GetMapping("/userTeam/getGoalkeeperByTeam/{userTeamId}")
    public ResponseEntity<KeepersResponseDto> getGoalkeeperByTeam(@PathVariable int userTeamId) {

        List<PlayerToTeam> players = playerToTeamService.getPlayersByUserTeam(userTeamId);
        KeepersResponseDto keeperResponse = null;

        for (PlayerToTeam player : players) {

            if (player.getGoalkeeper() != null) {
                Goalkeeper chosenGk = goalkeeperRepository.findById(player.getGoalkeeper().getPlayerId());
                keeperResponse = new KeepersResponseDto(
                    chosenGk.getPlayerId(),  
                    chosenGk.getName(), 
                    chosenGk.getTeam(),
                    chosenGk.getEuropeanLeague(),
                    chosenGk.getPosition(),
                    chosenGk.getGamesPlayed(),
                    chosenGk.getGoals(),
                    chosenGk.getAssists(),
                    chosenGk.getSaves(),
                    chosenGk.getCleanSheets());
            } 
        }

        return new ResponseEntity<KeepersResponseDto>(keeperResponse,  HttpStatus.OK);    
    }
}
