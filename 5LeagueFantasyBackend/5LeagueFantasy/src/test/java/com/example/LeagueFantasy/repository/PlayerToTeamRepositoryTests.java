package com.example.LeagueFantasy.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.UserTeam;

public class PlayerToTeamRepositoryTests {
    @Autowired
    private PlayerToTeamRepository playerToTeamRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        playerToTeamRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadPlayerToTeam(){
        // Create manager object
        FantasyManager fantasyManager = new FantasyManager();
        String username = "manager_man";
        String managerName = "John Manager";
        String email = "john.manager@gmail.com";
        String password = "football4321";

        fantasyManager.setUsername(username);
        fantasyManager.setName(managerName);
        fantasyManager.setEmail(email);
        fantasyManager.setPassword(password);

        // Create user team object
        UserTeam userTeam = new UserTeam();
        String name = "Testing FC";
        int points = 33;
        boolean isActive = true;

        userTeam.setName(name);
        userTeam.setPoints(points);
        userTeam.setActive(isActive);
        userTeam.setManager(fantasyManager);
    }
}