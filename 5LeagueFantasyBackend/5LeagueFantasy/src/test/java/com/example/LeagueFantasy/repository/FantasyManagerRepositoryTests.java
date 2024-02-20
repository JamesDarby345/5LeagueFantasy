package com.example.LeagueFantasy.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.LeagueFantasy.entity.FantasyManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FantasyManagerRepositoryTests {
  @Autowired private FantasyManagerRepository fantasyManagerRepository;

  @BeforeEach
  @AfterEach
  public void clearDatabase() {
    fantasyManagerRepository.deleteAll();
  }

  @Test
  public void testPersistAndLoadFantasyManagerAccount() {
    // Create manager object
    FantasyManager fantasyManager = new FantasyManager();
    String username = "manager_man";
    String name = "John Manager";
    String email = "john.manager@gmail.com";
    String password = "football4321";

    fantasyManager.setUsername(username);
    fantasyManager.setName(name);
    fantasyManager.setEmail(email);
    fantasyManager.setPassword(password);

    // Save and reload from repository
    fantasyManagerRepository.save(fantasyManager);
    fantasyManager = fantasyManagerRepository.findByEmail(email);

    // Assert correctness
    assertNotNull(fantasyManager);
    assertEquals(username, fantasyManager.getUsername());
    assertEquals(password, fantasyManager.getPassword());
  }
}
