package com.example.LeagueFantasy.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.LeagueFantasy.Entity.EuropeanLeague;
import com.example.LeagueFantasy.Entity.Forward;

@SpringBootTest
public class ForwardRepositoryTests {
    @Autowired
    private ForwardRepository forwardRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase(){
        forwardRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadForward(){
        // Create forward object
        Forward forward = new Forward();
        String name = "Erling Haaland";
        String position = "CF";
        String team = "Manchester City";
        int gamesPlayed = 14;
        EuropeanLeague europeanLeague = EuropeanLeague.PremierLeague;
        int goals = 13;
        int assists = 3;

        forward.setName(name);
        forward.setPosition(position);
        forward.setTeam(team);
        forward.setGamesPlayed(gamesPlayed);
        forward.setEuropeanLeague(europeanLeague);
        forward.setGoals(goals);
        forward.setAssists(assists);

        // Save to repository and get ID
        forwardRepository.save(forward);
        int id = forward.getPlayerId();
        forward = forwardRepository.findById(id);

        // Assert correctness
        assertNotNull(forward);
        assertEquals(name, forward.getName());
        assertEquals(position, forward.getPosition());
        assertEquals(gamesPlayed, forward.getGamesPlayed());
        assertEquals(europeanLeague, forward.getEuropeanLeague());
        assertEquals(goals, forward.getGoals());
        assertEquals(assists, forward.getAssists());
    }
    
}
