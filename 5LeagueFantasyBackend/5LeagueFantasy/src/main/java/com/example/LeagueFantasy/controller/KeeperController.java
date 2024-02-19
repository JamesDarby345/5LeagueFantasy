package com.example.LeagueFantasy.controller;

import com.example.LeagueFantasy.Entity.FantasyManager;
import com.example.LeagueFantasy.Entity.Goalkeeper;
import com.example.LeagueFantasy.dto.FantasyManagerResponseDto;
import com.example.LeagueFantasy.dto.KeepersResponseDto;
import com.example.LeagueFantasy.service.KeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KeeperController {

    @Autowired
    private KeeperService keeperService;

    @GetMapping("/players/{position}")
    public ResponseEntity<List<KeepersResponseDto>> getByPositionKeeper(@PathVariable String position) {

        List<Goalkeeper> retrievedKeepers = keeperService.getKeeperByPosition(position);
        List<KeepersResponseDto> retrievedKeepersResponse = new ArrayList<KeepersResponseDto>();

        for (Goalkeeper keeper : retrievedKeepers) {
            KeepersResponseDto currentKeeperResponse = new KeepersResponseDto(
                    keeper.getName(),
                    keeper.getTeam(),
                    keeper.getEuropeanLeague(),
                    keeper.getPosition(),
                    keeper.getGamesPlayed(),
                    keeper.getGoals(),
                    keeper.getAssists(),
                    keeper.getSaves(),
                    keeper.getCleanSheets());
            retrievedKeepersResponse.add(currentKeeperResponse);
        }

        return new ResponseEntity<>(retrievedKeepersResponse, HttpStatus.OK);
    }

}