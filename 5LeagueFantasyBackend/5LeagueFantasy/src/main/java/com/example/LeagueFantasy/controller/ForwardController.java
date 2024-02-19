package com.example.LeagueFantasy.controller;

import com.example.LeagueFantasy.Entity.FantasyManager;
import com.example.LeagueFantasy.Entity.Forward;
import com.example.LeagueFantasy.dto.FantasyManagerResponseDto;
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

    @GetMapping("/forwards/name/{name}")
    public ResponseEntity<List<ForwardResponseDto>> getForwardsByName(@PathVariable String name) {

        List<Forward> retrievedForwards = forwardService.getForwardsByName(name);
        List<ForwardResponseDto> retrievedForwardsResponse = new ArrayList<ForwardResponseDto>();

        for(Forward forward: retrievedForwards) {
            ForwardResponseDto currentForwardResponse = new ForwardResponseDto(
                    forward.getName(),
                    forward.getTeam(),
                    forward.getEuropeanLeague(),
                    forward.getPosition(),
                    forward.getGoals(),
                    forward.getAssists()
            );
            retrievedForwardsResponse.add(currentForwardResponse);
        }

        return new ResponseEntity<>(retrievedForwardsResponse, HttpStatus.OK);
    }

}
