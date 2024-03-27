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

import com.example.LeagueFantasy.dto.UserTeamRequestDto;
import com.example.LeagueFantasy.dto.UserTeamResponseDto;
import com.example.LeagueFantasy.entity.UserTeam;
import com.example.LeagueFantasy.service.UserTeamService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserTeamController {

    @Autowired 
    private UserTeamService userTeamService;

    @PostMapping("/userTeam/newUserTeam/") 
    public ResponseEntity<UserTeamResponseDto> createNewUserTeam(@RequestBody UserTeamRequestDto userTeamRequest) {
        
        UserTeam userTeamToCreate = new UserTeam();
        userTeamToCreate.setName(userTeamRequest.getName());
        userTeamToCreate.setActive(userTeamRequest.getSetAsActive());

        UserTeam createdUserTeam = userTeamService.createUserTeam(userTeamToCreate, userTeamRequest.getFantasyManagerUsername());

        UserTeamResponseDto createdUserTeamResponse = new UserTeamResponseDto(
            createdUserTeam.getUserTeamId(),
            createdUserTeam.getName(),
            createdUserTeam.getPoints(), 
            createdUserTeam.getActive(), 
            createdUserTeam.getManager(),
            createdUserTeam.getNumberOfKeepers(),
            createdUserTeam.getNumberOfForwards());

        return new ResponseEntity<UserTeamResponseDto>(createdUserTeamResponse, HttpStatus.CREATED);
    }

    @GetMapping("/userTeam/getUserTeams/{fantasyManagerUsername}")
    public ResponseEntity<List<UserTeamResponseDto>> getTeamsByManager(@PathVariable String fantasyManagerUsername) {

        List<UserTeam> userTeams = userTeamService.getUserTeamsByManager(fantasyManagerUsername);
        List<UserTeamResponseDto> userTeamsResponse = new ArrayList<UserTeamResponseDto>();

        for (UserTeam userTeam : userTeams) {
            UserTeamResponseDto currentUserResponse =
            new UserTeamResponseDto(
                userTeam.getUserTeamId(),
                userTeam.getName(),
                userTeam.getPoints(),
                userTeam.getActive(),
                userTeam.getManager(),
                userTeam.getNumberOfKeepers(),
                userTeam.getNumberOfForwards());

                userTeamsResponse.add(currentUserResponse);
        }

        return new ResponseEntity<List<UserTeamResponseDto>>(userTeamsResponse, HttpStatus.OK);
    }
}
