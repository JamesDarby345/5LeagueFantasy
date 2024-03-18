package com.example.LeagueFantasy.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.UserTeam;

@SpringBootTest
public class UserTeamRepositoryTests {

    @Autowired private FantasyManagerRepository fantasyManagerRepository;
    @Autowired private UserTeamRepository userTeamRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        userTeamRepository.deleteAll();
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

        // Save and reload from repository
        fantasyManagerRepository.save(fantasyManager);
        fantasyManager = fantasyManagerRepository.findByUsername(username);
    
        // Assert correctness
        assertNotNull(fantasyManager);
        assertEquals(username, fantasyManager.getUsername());
        assertEquals(password, fantasyManager.getPassword());
        assertEquals(email, fantasyManager.getEmail());
        assertEquals(managerName, fantasyManager.getName());

        // Create user team object
        UserTeam userTeam = new UserTeam();
        String name = "Testing FC";
        int points = 33;
        long millis = System.currentTimeMillis();
        Date weekStartDate = new Date(millis);
        boolean isActive = true;

        userTeam.setName(name);
        userTeam.setPoints(points);
        userTeam.setWeekStartDate(weekStartDate);
        userTeam.setActive(isActive);
        userTeam.setManager(fantasyManager);

        // Save to repository and get ID
        userTeamRepository.save(userTeam);
        int id = userTeam.getUserTeamId();
        userTeam = userTeamRepository.findById(id);

        // Assert correctness
        assertNotNull(userTeam);
        assertEquals(name, userTeam.getName());
        assertEquals(points, userTeam.getPoints());
        assertEquals(weekStartDate.toLocalDate(), userTeam.getWeekStartDate().toLocalDate());
        assertEquals(isActive, userTeam.getActive());
        assertEquals(fantasyManager.getName(), userTeam.getManager().getName());
    }
}