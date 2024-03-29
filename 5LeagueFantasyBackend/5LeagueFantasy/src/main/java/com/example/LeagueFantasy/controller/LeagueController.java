package com.example.LeagueFantasy.controller;

import com.example.LeagueFantasy.dto.LeagueRequestDto;
import com.example.LeagueFantasy.dto.LeagueResponseDto;
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
                  String username = (authentication != null) ? authentication.getName() : "anonymous"; // Fallback to "anonymous" or similar
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



}
