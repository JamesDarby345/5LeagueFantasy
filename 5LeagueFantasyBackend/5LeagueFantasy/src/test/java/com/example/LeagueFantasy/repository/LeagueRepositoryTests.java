package com.example.LeagueFantasy.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.League;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LeagueRepositoryTests {
  @Autowired private LeagueRepository leagueRepository;

  @Autowired private FantasyManagerRepository fantasyManagerRepository;

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
  }
}
