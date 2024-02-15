package com.example.LeagueFantasy.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FantasyManagerRepositoryTests {
    @Autowired
    private FantasyManagerRepository fantasyManagerRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase(){
        fantasyManagerRepository.deleteAll();
    }
    
}
