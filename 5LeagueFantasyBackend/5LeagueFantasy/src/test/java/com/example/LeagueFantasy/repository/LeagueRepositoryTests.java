package com.example.LeagueFantasy.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.League;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LeagueRepositoryTests {

  @Autowired private FantasyManagerRepository fantasyManagerRepository;
  @Autowired private LeagueRepository leagueRepository;

  @BeforeEach
  @AfterEach
  public void clearDatabase() {
    leagueRepository.deleteAll();
    fantasyManagerRepository.deleteAll();
  }

  @Test
  public void testPersistAndLoadLeague() {
    // Create account
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

    // Create league
    League league = new League();
    String name = "Best League 001";
    league.setLeagueOwner(fantasyManager);
    league.setName(name);

    // Save to repository and get ID
    leagueRepository.save(league);
    int id = league.getId();
    league = leagueRepository.findById(id);

    // Assert correctness
    assertNotNull(league);
    assertEquals(name, league.getName());
    assertEquals(fantasyManager.getName(), league.getLeagueOwner().getName());
  }
}