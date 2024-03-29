package com.example.LeagueFantasy.controller;

import com.example.LeagueFantasy.dto.ForwardResponseDto;
import com.example.LeagueFantasy.entity.Forward;
import com.example.LeagueFantasy.service.ForwardService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ForwardController {

    @Autowired
    private ForwardService forwardService;

    @GetMapping("/forwards/all")
    public ResponseEntity<List<ForwardResponseDto>> getAllForwards() {
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
    public ResponseEntity<List<ForwardResponseDto>> getForwardsByEuropeanLeague(
            @PathVariable String europeanLeague) {

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

    @GetMapping("/forwards/sortedByDescendingGoals")
    public ResponseEntity<List<ForwardResponseDto>> getForwardsSortedByDescendingGoals() {
        List<Forward> retrievedForwards = forwardService.getForwardsByDescendingGoals();
        if (retrievedForwards == null) {
            retrievedForwards = new ArrayList<>();
        }
        List<ForwardResponseDto> descendingGoalsResponse = new ArrayList<>();
        return getListResponseEntity(retrievedForwards, descendingGoalsResponse);
    }

    @GetMapping("/forwards/sortedByDescendingAssists")
    public ResponseEntity<List<ForwardResponseDto>> getForwardsSortedByDescendingAssists() {
        List<Forward> retrievedForwards = forwardService.getForwardsByDescendingAssists();
        if (retrievedForwards == null) {
            retrievedForwards = new ArrayList<>();
        }
        List<ForwardResponseDto> descendingAssistsResponse = new ArrayList<>();
        return getListResponseEntity(retrievedForwards, descendingAssistsResponse);
    }

    @GetMapping("/forwards/sortedByAscendingGoals")
    public ResponseEntity<List<ForwardResponseDto>> getForwardsSortedByAscendingGoals() {
        List<Forward> retrievedForwards = forwardService.getForwardsByAscendingGoals();
        if (retrievedForwards == null) {
            retrievedForwards = new ArrayList<>();
        }
        List<ForwardResponseDto> ascendingGoalsResponse = new ArrayList<>();
        return getListResponseEntity(retrievedForwards, ascendingGoalsResponse);
    }

    @GetMapping("/forwards/sortedByAscendingAssists")
    public ResponseEntity<List<ForwardResponseDto>> getForwardsSortedByAscendingAssists() {
        List<Forward> retrievedForwards = forwardService.getForwardsByAscendingAssists();
        if (retrievedForwards == null) {
            retrievedForwards = new ArrayList<>();
        }
        List<ForwardResponseDto> ascendingAssistsResponse = new ArrayList<>();
        return getListResponseEntity(retrievedForwards, ascendingAssistsResponse);
    }

    @GetMapping("/forwards/position/{position}")
    public ResponseEntity<List<ForwardResponseDto>> getForwardByPosition(@PathVariable String position) {

        List<Forward> retrievedForwards = forwardService.getForwardByPosition((position));
        List<ForwardResponseDto> retrievedForwardsResponse = new ArrayList<>();

        return getListResponseEntity(retrievedForwards, retrievedForwardsResponse);
    }

    private ResponseEntity<List<ForwardResponseDto>> getListResponseEntity(List<Forward> retrievedForwards,
            List<ForwardResponseDto> retrievedForwardsResponse) {
        for (Forward forward : retrievedForwards) {
            ForwardResponseDto currentForwardResponse = new ForwardResponseDto(
                    forward.getPlayerId(),
                    forward.getName(),
                    forward.getTeam(),
                    forward.getEuropeanLeague(),
                    forward.getPosition(),
                    forward.getGoals(),
                    forward.getAssists(),
                    forward.getGamesPlayed());
            retrievedForwardsResponse.add(currentForwardResponse);
        }

        return new ResponseEntity<>(retrievedForwardsResponse, HttpStatus.OK);
    }
}
