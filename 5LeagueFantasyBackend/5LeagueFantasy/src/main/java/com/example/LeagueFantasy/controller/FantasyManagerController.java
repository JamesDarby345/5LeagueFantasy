package com.example.LeagueFantasy.controller;

import com.example.LeagueFantasy.dto.FantasyManagerRequestDto;
import com.example.LeagueFantasy.dto.FantasyManagerResponseDto;
import com.example.LeagueFantasy.entity.EuropeanLeague;
import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.Forward;
import com.example.LeagueFantasy.entity.Goalkeeper;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.ForwardRepository;
import com.example.LeagueFantasy.repository.GoalkeeperRepository;
import com.example.LeagueFantasy.service.FantasyManagerService;
import com.example.LeagueFantasy.util.DataLoader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FantasyManagerController {

  @Autowired ForwardRepository forwardRepository;
  @Autowired GoalkeeperRepository goalkeeperRepository;
  @Autowired private FantasyManagerService fantasyManagerService;

  @GetMapping("/")
  public ResponseEntity<String> loadPlayers() {
    List<DataLoader.Root> roots = DataLoader.loadData();
    for (DataLoader.Root root : roots) {
      if (root == null) {
        return ResponseEntity.status(503).body("Internal Server Error");
      }

      for (DataLoader.PlayerInfo playerInfo : root.response) {
        for (DataLoader.Statistic stt : playerInfo.statistics) {
          if (stt.games.position != null
              && EuropeanLeague.fromString((stt.league.name)) != EuropeanLeague.UNKNOWN) {
            if (stt.games.position.equals("Goalkeeper")) {
              Goalkeeper entityPlayer =
                  new Goalkeeper(
                      playerInfo.player.name,
                      stt.team.name,
                      stt.games.position,
                      stt.games.appearences,
                      EuropeanLeague.fromString((stt.league.name)),
                      stt.goals.total,
                      stt.goals.saves,
                      stt.goals.conceded,
                      stt.games.appearences);
              goalkeeperRepository.save(entityPlayer);
            } else {
              Forward entityPlayer =
                  new Forward(
                      playerInfo.player.name,
                      stt.team.name,
                      stt.games.position,
                      stt.games.appearences,
                      EuropeanLeague.fromString((stt.league.name)),
                      stt.goals.total,
                      stt.goals.assists);
              forwardRepository.save(entityPlayer);
            }
          }
        }
      }
    }
    return ResponseEntity.status(200).body("All good");
  }

  @PostMapping("/managers/newManager")
  public ResponseEntity<FantasyManagerResponseDto> createManager(
      @RequestBody FantasyManagerRequestDto fantasyManagerRequest) {

    FantasyManager userToCreate =
        new FantasyManager(
            fantasyManagerRequest.getUsername(),
            fantasyManagerRequest.getName(),
            fantasyManagerRequest.getEmail(),
            fantasyManagerRequest.getPassword());

    try {
      FantasyManager createdUser = fantasyManagerService.createFantasyManager(userToCreate);
      FantasyManagerResponseDto createdUserResponse =
              new FantasyManagerResponseDto(
                      createdUser.getUsername(),
                      createdUser.getName(),
                      createdUser.getEmail(),
                      createdUser.getPassword(),
                      createdUser.getLeague());

      return new ResponseEntity<>(createdUserResponse, HttpStatus.CREATED);
      }
    catch (FiveLeagueFantasyException exception) {
      exception.printStackTrace();
      return new ResponseEntity <> (null, HttpStatus.BAD_REQUEST);
    }
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/managers/login/{username}/{password}")
  public ResponseEntity<FantasyManagerResponseDto> loginManager(
      @PathVariable String username, @PathVariable String password) {

    FantasyManager retrievedUser = fantasyManagerService.loginFantasyManager(username, password);

    FantasyManagerResponseDto retrievedUserResponse =
        new FantasyManagerResponseDto(
            retrievedUser.getUsername(),
            retrievedUser.getName(),
            retrievedUser.getEmail(),
            retrievedUser.getPassword(),
            retrievedUser.getLeague());

    return new ResponseEntity<FantasyManagerResponseDto>(retrievedUserResponse, HttpStatus.OK);
  }

  @GetMapping("/managers/username/{username}")
  public ResponseEntity<FantasyManagerResponseDto> getManagerByUsername(
      @PathVariable String username) {

    FantasyManager retrievedUser = fantasyManagerService.getFantasyManagerByUsername(username);

    FantasyManagerResponseDto retrievedUserResponse =
        new FantasyManagerResponseDto(
            retrievedUser.getUsername(),
            retrievedUser.getName(),
            retrievedUser.getEmail(),
            retrievedUser.getPassword(),
            retrievedUser.getLeague());

    return new ResponseEntity<FantasyManagerResponseDto>(retrievedUserResponse, HttpStatus.OK);
  }

  @GetMapping("/managers/email/{email}")
  public ResponseEntity<FantasyManagerResponseDto> getManagerByEmail(@PathVariable String email) {

    FantasyManager retrievedUser = fantasyManagerService.getFantasyManagerByEmail(email);

    FantasyManagerResponseDto retrievedUserResponse =
        new FantasyManagerResponseDto(
            retrievedUser.getUsername(),
            retrievedUser.getName(),
            retrievedUser.getEmail(),
            retrievedUser.getPassword(),
            retrievedUser.getLeague());

    return new ResponseEntity<FantasyManagerResponseDto>(retrievedUserResponse, HttpStatus.OK);
  }

  @GetMapping("/managers/name/{name}")
  public ResponseEntity<List<FantasyManagerResponseDto>> getManagerByName(
      @PathVariable String name) {

    List<FantasyManager> retrievedUser = fantasyManagerService.getFantasyManagersByName(name);
    List<FantasyManagerResponseDto> retrievedUsersResponse =
        new ArrayList<FantasyManagerResponseDto>();

    for (FantasyManager user : retrievedUser) {
      FantasyManagerResponseDto currentUserResponse =
          new FantasyManagerResponseDto(
              user.getUsername(),
              user.getName(),
              user.getEmail(),
              user.getPassword(),
              user.getLeague());
      retrievedUsersResponse.add(currentUserResponse);
    }

    return new ResponseEntity<List<FantasyManagerResponseDto>>(
        retrievedUsersResponse, HttpStatus.OK);
  }

  @GetMapping("/managers/league/id/{id}")
  public ResponseEntity<List<FantasyManagerResponseDto>> getManagersByLeague(@PathVariable int id) {

    List<FantasyManager> usersByLeague = fantasyManagerService.getFantasyManagersByLeague(id);
    List<FantasyManagerResponseDto> usersByLeagueResponse =
        new ArrayList<FantasyManagerResponseDto>();

    for (FantasyManager user : usersByLeague) {
      FantasyManagerResponseDto currentUserResponse =
          new FantasyManagerResponseDto(
              user.getUsername(),
              user.getName(),
              user.getEmail(),
              user.getPassword(),
              user.getLeague());

      usersByLeagueResponse.add(currentUserResponse);
    }

    return new ResponseEntity<List<FantasyManagerResponseDto>>(
        usersByLeagueResponse, HttpStatus.OK);
  }

  @GetMapping("/managers/league/name/{name}")
  public ResponseEntity<List<FantasyManagerResponseDto>> getManagersByLeague(
      @PathVariable String name) {

    List<FantasyManager> usersByLeague = fantasyManagerService.getFantasyManagersByLeague(name);
    List<FantasyManagerResponseDto> usersByLeagueResponse =
        new ArrayList<FantasyManagerResponseDto>();

    for (FantasyManager user : usersByLeague) {
      FantasyManagerResponseDto currentUserResponse =
          new FantasyManagerResponseDto(
              user.getUsername(),
              user.getName(),
              user.getEmail(),
              user.getPassword(),
              user.getLeague());

      usersByLeagueResponse.add(currentUserResponse);
    }

    return new ResponseEntity<List<FantasyManagerResponseDto>>(
        usersByLeagueResponse, HttpStatus.OK);
  }

  @PutMapping("/managers/username/{username}/{newName}")
  public ResponseEntity<FantasyManagerResponseDto> updateManagerName(
      @PathVariable String username, @PathVariable String newName) {

    FantasyManager userToUpdate = fantasyManagerService.updateFantasyManagerName(username, newName);

    FantasyManagerResponseDto updatedUserResponse =
        new FantasyManagerResponseDto(
            userToUpdate.getUsername(),
            userToUpdate.getName(),
            userToUpdate.getEmail(),
            userToUpdate.getPassword(),
            userToUpdate.getLeague());

    return new ResponseEntity<FantasyManagerResponseDto>(updatedUserResponse, HttpStatus.OK);
  }

  @PutMapping("/managers/league/{username}/{newEmail}")
  public ResponseEntity<FantasyManagerResponseDto> updateManagerEmail(
      @PathVariable String username, @PathVariable String newEmail) {

    FantasyManager userToUpdate =
        fantasyManagerService.updateFantasyManagerEmail(username, newEmail);

    FantasyManagerResponseDto updatedUserResponse =
        new FantasyManagerResponseDto(
            userToUpdate.getUsername(),
            userToUpdate.getName(),
            userToUpdate.getEmail(),
            userToUpdate.getPassword(),
            userToUpdate.getLeague());

    return new ResponseEntity<FantasyManagerResponseDto>(updatedUserResponse, HttpStatus.OK);
  }

  @PutMapping("/managers/password/{username}/{newPassword}")
  public ResponseEntity<FantasyManagerResponseDto> updateManagerPassword(
      @PathVariable String username, @PathVariable String newPassword) {

    FantasyManager userToUpdate =
        fantasyManagerService.updateFantasyManagerPassword(username, newPassword);

    FantasyManagerResponseDto updatedUserResponse =
        new FantasyManagerResponseDto(
            userToUpdate.getUsername(),
            userToUpdate.getName(),
            userToUpdate.getEmail(),
            userToUpdate.getPassword(),
            userToUpdate.getLeague());

    return new ResponseEntity<FantasyManagerResponseDto>(updatedUserResponse, HttpStatus.OK);
  }
}
