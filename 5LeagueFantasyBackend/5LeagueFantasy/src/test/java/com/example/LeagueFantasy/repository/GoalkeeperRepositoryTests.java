package com.example.LeagueFantasy.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.LeagueFantasy.Entity.EuropeanLeague;
import com.example.LeagueFantasy.Entity.Goalkeeper;

public class GoalkeeperRepositoryTests {
    @Autowired
    private GoalkeeperRepository goalkeeperRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase(){
        goalkeeperRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadGoalkeeper(){
        // Create goalkeeper object
        Goalkeeper goalkeeper = new Goalkeeper();
        String name = "Mike Maignan";
        String position = "GK";
        String team = "AC Milan";
        int gamesPlayed = 25;
        EuropeanLeague europeanLeague = EuropeanLeague.SerieA;
        int saves = 55;
        int cleanSheets = 4;

        goalkeeper.setName(name);
        goalkeeper.setTeam(team);
        goalkeeper.setGamesPlayed(gamesPlayed);
        goalkeeper.setEuropeanLeague(europeanLeague);
        goalkeeper.setSaves(saves);
        goalkeeper.setCleanSheets(cleanSheets);

        // Save to repository and get ID
        goalkeeperRepository.save(goalkeeper);
        int id = goalkeeper.getPlayerId();
        goalkeeper = goalkeeperRepository.findById(id);

        // Assert Correctness
        assertNotNull(goalkeeper);
        assertEquals(name, goalkeeper.getName());
        assertEquals(gamesPlayed, goalkeeper.getGamesPlayed());
        assertEquals(europeanLeague, goalkeeper.getEuropeanLeague());
        assertEquals(saves, goalkeeper.getSaves());
        assertEquals(cleanSheets, goalkeeper.getCleanSheets());
    }
}
