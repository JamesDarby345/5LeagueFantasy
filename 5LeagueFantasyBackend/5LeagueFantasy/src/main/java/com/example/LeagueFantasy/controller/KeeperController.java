package com.example.LeagueFantasy.controller;

import com.example.LeagueFantasy.dto.KeepersResponseDto;
import com.example.LeagueFantasy.entity.Goalkeeper;
import com.example.LeagueFantasy.service.KeeperService;
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
public class KeeperController {

  @Autowired private KeeperService keeperService;

  @GetMapping("/players/{position}")
  public ResponseEntity<List<KeepersResponseDto>> getByPositionKeeper(
      @PathVariable String position) {

    List<Goalkeeper> retrievedKeepers = keeperService.getKeeperByPosition(position);
    List<KeepersResponseDto> retrievedKeepersResponse = new ArrayList<KeepersResponseDto>();

    for (Goalkeeper keeper : retrievedKeepers) {
      KeepersResponseDto currentKeeperResponse =
          new KeepersResponseDto(
            keeper.getPlayerId(),
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
