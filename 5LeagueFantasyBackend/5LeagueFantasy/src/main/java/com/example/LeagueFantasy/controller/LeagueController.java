package com.example.LeagueFantasy.controller;

import com.example.LeagueFantasy.dto.ForwardResponseDto;
import com.example.LeagueFantasy.dto.LeagueRequestDto;
import com.example.LeagueFantasy.dto.LeagueResponseDto;
import com.example.LeagueFantasy.entity.Forward;
import com.example.LeagueFantasy.entity.League;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.service.LeagueService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/league") // This maps all methods to start with /league
public class LeagueController {

      @Autowired
      private LeagueService leagueService;

      @PostMapping("/new")
      public ResponseEntity<?> createLeague(@RequestBody LeagueRequestDto request) {
            try {
                  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                  String username = (authentication != null) ? authentication.getName() : "anonymous"; // Fallback to
                                                                                                       // "anonymous" or
                                                                                                       // similar
                  LeagueResponseDto response = leagueService.createLeague(request, username);
                  return new ResponseEntity<>(response, HttpStatus.CREATED);
            } catch (FiveLeagueFantasyException e) {
                  Map<String, Object> errorResponse = new HashMap<>();
                  errorResponse.put("error", e.getMessage());
                  errorResponse.put("status", HttpStatus.BAD_REQUEST);
                  return ResponseEntity
                              .status(HttpStatus.BAD_REQUEST)
                              .body(errorResponse);
            }
      }

<<<<<<< HEAD
=======
      @GetMapping("/{name}")
      public ResponseEntity<List<LeagueResponseDto>> getLeagueByName(@PathVariable String name){
            List<League> retrievedLeagues = leagueService.getLeagueByName(name);
            List<LeagueResponseDto> retrievedLeaguesResponse = new ArrayList<LeagueResponseDto>();

            return getListResponseEntity(retrievedLeagues, retrievedLeaguesResponse);
      }

      private ResponseEntity<List<LeagueResponseDto>> getListResponseEntity(List<League> retrievedLeagues,
                                                                             List<LeagueResponseDto> retrievedLeaguesResponse) {
            for (League league : retrievedLeagues) {
                  LeagueResponseDto currentLeagueResponse = new LeagueResponseDto(
                          league.getId(),
                          league.getName(),
                          league.getLeagueOwner().getName());
                  retrievedLeaguesResponse.add(currentLeagueResponse);
            }

            return new ResponseEntity<>(retrievedLeaguesResponse, HttpStatus.OK);
      }

>>>>>>> 4bc56d752c05fc08ff066726e6d7b07f7e275709
}
