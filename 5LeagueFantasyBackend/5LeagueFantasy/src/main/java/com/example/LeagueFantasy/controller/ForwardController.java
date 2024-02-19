package com.example.LeagueFantasy.controller;

import com.example.LeagueFantasy.Entity.EuropeanLeague;
import com.example.LeagueFantasy.Entity.Forward;
import com.example.LeagueFantasy.dto.ForwardResponseDto;
import com.example.LeagueFantasy.service.ForwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ForwardController {

    @Autowired
    private ForwardService forwardService;

    @GetMapping("/forwards/all")
    public ResponseEntity<List<ForwardResponseDto>> getAllForwards(){
        List<Forward> retrievedForwards = forwardService.getAllForwards();
        List<ForwardResponseDto> retrievedForwardsResponse = new ArrayList<ForwardResponseDto>();

        return getListResponseEntity(retrievedForwards, retrievedForwardsResponse);
    }

    @GetMapping("/forwards/name/{name}")
    public ResponseEntity<List<ForwardResponseDto>> getForwardsByName(@PathVariable String name) {

        List<Forward> retrievedForwards = forwardService.getForwardsByName(name);
        List<ForwardResponseDto> retrievedForwardsResponse = new ArrayList<ForwardResponseDto>();

        return getListResponseEntity(retrievedForwards, retrievedForwardsResponse);
    }

    @GetMapping("/forwards/europeanLeague/{europeanLeague}")
    public ResponseEntity<List<ForwardResponseDto>> getForwardsByEuropeanLeague(@PathVariable String europeanLeague) {

        List<Forward> retrievedForwards = forwardService.getForwardsByLeague((europeanLeague));
        List<ForwardResponseDto> retrievedForwardsResponse = new ArrayList<>();

        return getListResponseEntity(retrievedForwards, retrievedForwardsResponse);
    }
    @GetMapping("/forwards/goals/{goals}")
    public ResponseEntity<List<ForwardResponseDto>> getForwardsByGoals(@PathVariable int goals) {
        List<Forward> retrievedForwards = forwardService.getForwardsByGoals(goals);
        List<ForwardResponseDto> responseDtoList = new ArrayList<>();

        return getListResponseEntity(retrievedForwards, responseDtoList);
    }

    @GetMapping("/forwards/assists/{assists}")
    public ResponseEntity<List<ForwardResponseDto>> getForwardsByAssists(@PathVariable int assists) {
        List<Forward> retrievedForwards = forwardService.getForwardsByGoals(assists);
        List<ForwardResponseDto> responseDtoList = new ArrayList<>();

        return getListResponseEntity(retrievedForwards, responseDtoList);
    }
    @GetMapping("/forwards/sortedByGoals")
    public ResponseEntity<List<ForwardResponseDto>> getForwardsSortedByGoals() {
        List<Forward> retrievedForwards = forwardService.getForwardsByDescendingGoals();
        return getListResponseEntitySorted(retrievedForwards);
    }

    @GetMapping("/forwards/sortedByAssists")
    public ResponseEntity<List<ForwardResponseDto>> getForwardsSortedByAssists() {
        List<Forward> retrievedForwards = forwardService.getForwardsByDescendingAssists();
        return getListResponseEntitySorted(retrievedForwards);
    }

    private ResponseEntity<List<ForwardResponseDto>> getListResponseEntity(List<Forward> retrievedForwards, List<ForwardResponseDto> retrievedForwardsResponse) {
        for(Forward forward: retrievedForwards) {
            ForwardResponseDto currentForwardResponse = new ForwardResponseDto(
                    forward.getName(),
                    forward.getTeam(),
                    forward.getEuropeanLeague(),
                    forward.getPosition(),
                    forward.getGoals(),
                    forward.getAssists(),
                    forward.getGamesPlayed()
            );
            retrievedForwardsResponse.add(currentForwardResponse);
        }

        return new ResponseEntity<>(retrievedForwardsResponse, HttpStatus.OK);
    }

    private ResponseEntity<List<ForwardResponseDto>> getListResponseEntitySorted(List<Forward> retrievedForwards) {
        List<ForwardResponseDto> retrievedForwardsResponse = new ArrayList<>();
        for (Forward forward : retrievedForwards) {
            retrievedForwardsResponse.add(new ForwardResponseDto(
                    forward.getName(),
                    forward.getTeam(),
                    forward.getEuropeanLeague(),
                    forward.getPosition(),
                    forward.getGoals(),
                    forward.getAssists(),
                    forward.getGamesPlayed()
            ));
        }
        return new ResponseEntity<>(retrievedForwardsResponse, HttpStatus.OK);
    }



}
