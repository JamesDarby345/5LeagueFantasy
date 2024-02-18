package com.example.LeagueFantasy.step_definitions;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;

@CucumberContextConfiguration
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CucumberTestContextConfiguration {
}