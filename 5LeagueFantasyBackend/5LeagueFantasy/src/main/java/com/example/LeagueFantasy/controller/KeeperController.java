package com.example.LeagueFantasy.controller;

import com.example.LeagueFantasy.Entity.FantasyManager;
import com.example.LeagueFantasy.Entity.Keeper;
import com.example.LeagueFantasy.dto.FantasyManagerResponseDto;
import com.example.LeagueFantasy.dto.KeeperResponseDto;
import com.example.LeagueFantasy.service.keeperService;
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

    @GetMapping("/players/position/{position}")
    public ResponseEntity<List<KeeperResponseDto>> getByPositionKeeper(@PathVariable String position) {

        List<Keeper> retrievedKeepers = keeperService.getByPositionKeeper(position);
        List<KeeperResponseDto> retrievedKeepersResponse = new ArrayList<KeeperResponseDto>();

        for(Keeper keeper: retrievedKeepers) {
            KeeperResponseDto currentKeeperResponse = new KeeperResponseDto(
                    keeper.getName(),
                    keeper.getTeam(),
                    keeper.getEuropeanLeague(),
                    keeper.getPosition(),
                    keeper.getGoals(),
                    keeper.getAssists(),
                    keeper.getSaves(),
                    keeper.getCleanSheets(),
            );
            retrievedKeepersResponse.add(currentKeeperResponse);
        }

        return new ResponseEntity<>(retrievedKeepersResponse, HttpStatus.OK);
    }

}