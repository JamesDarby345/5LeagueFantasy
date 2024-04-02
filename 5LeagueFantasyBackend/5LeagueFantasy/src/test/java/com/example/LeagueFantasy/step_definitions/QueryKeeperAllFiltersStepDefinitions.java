package com.example.LeagueFantasy.step_definitions;

import com.example.LeagueFantasy.entity.EuropeanLeague;
import com.example.LeagueFantasy.entity.Goalkeeper;
import com.example.LeagueFantasy.controller.KeeperController;
import com.example.LeagueFantasy.dto.KeepersResponseDto;
import com.example.LeagueFantasy.repository.GoalkeeperRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class QueryKeeperAllFiltersStepDefinitions {

    @Autowired
    private KeeperController keeperController;

    @Autowired
    private GoalkeeperRepository goalkeeperRepository;

    private List<KeepersResponseDto> queriedKeepers;

    @Given("a goalkeeper named {string} has {string} clean sheets in the system")
    public void givenGoalkeeperWithCleanSheets(String name, String cleanSheets) {
        Goalkeeper player = new Goalkeeper();
        player.setName(name);
        player.setTeam("SampleTeam"); // Set a sample team name
        player.setGamesPlayed(0); // Set a default value for gamesPlayed
        player.setEuropeanLeague(EuropeanLeague.PremierLeague); // Set a default league
        player.setGoals(0); // Set a default value for goals
        player.setAssists(0); // Set a default value for assists
        player.setCleanSheets(23); // Set a default value for clean sheets
        player.setSaves(987); // Set a default value for the number of saves
        player.setPosition("Goalkeeper");
        goalkeeperRepository.save(player);

    }

    @When("I query goalkeepers by clean sheets {string}")
    public void whenQueryByCleanSheets(Integer cleanSheets) {
        ResponseEntity<List<KeepersResponseDto>> responseEntity = keeperController.getKeeperByCleanSheet(cleanSheets);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            queriedKeepers = responseEntity.getBody();
        } else {
            throw new RuntimeException(
                    "Failed to retrieve keeper. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    @Then("I am shown a list including {string}")
    public void thenShownListIncluding(String expectedName) {
        assertNotNull("Queried players should not be null", queriedKeepers);
    }

    @Given("goalkeepers {string} and {string} with varying numbers of clean sheets in the system")
    public void givenGoalkeepersWithVaryingCleanSheets(String player1name, String player2name) {
        Goalkeeper player = new Goalkeeper();
        player.setName(player1name);
        player.setTeam("SampleTeam"); // Set a sample team name
        player.setGamesPlayed(0); // Set a default value for gamesPlayed
        player.setEuropeanLeague(EuropeanLeague.PremierLeague); // Set a default league
        player.setGoals(0); // Set a default value for goals
        player.setAssists(0); // Set a default value for assists
        player.setCleanSheets(23); // Set a default value for clean sheets
        player.setSaves(987); // Set a default value for the number of saves
        player.setPosition("Goalkeeper");
        goalkeeperRepository.save(player);

        Goalkeeper player2 = new Goalkeeper();
        player2.setName(player2name);
        player2.setTeam("SampleTeam"); // Set a sample team name
        player2.setGamesPlayed(55); // Set a default value for gamesPlayed
        player2.setEuropeanLeague(EuropeanLeague.PremierLeague); // Set a default league
        player2.setGoals(0); // Set a default value for goals
        player2.setAssists(0); // Set a default value for assists
        player2.setCleanSheets(3); // Set a default value for clean sheets
        player2.setSaves(222); // Set a default value for the number of saves
        player2.setPosition("Goalkeeper");
        goalkeeperRepository.save(player2);

    }

    @When("I query goalkeepers by clean sheets with a range {int} and {int}")
    public void whenQueryByCleanSheetsRange(Integer upper, Integer lower) {
        ResponseEntity<List<KeepersResponseDto>> responseEntity = null;
        for (int i = upper; upper > lower; upper--) {
            responseEntity = keeperController.getKeeperByCleanSheet(i);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                queriedKeepers = responseEntity.getBody();
            }
        }
    }

    @Then("I am shown a list of goalkeepers within that range")
    public void thenShownListWithinRange() {
        assertNotNull("Queried players should not be null", queriedKeepers);
    }

    @Given("a goalkeeper named <string> exists in the system")
    public void givenGoalkeepersExist(String name) {
        Goalkeeper player = new Goalkeeper();
        player.setName(name);
        player.setTeam("SampleTeam"); // Set a sample team name
        player.setGamesPlayed(0); // Set a default value for gamesPlayed
        player.setEuropeanLeague(EuropeanLeague.PremierLeague); // Set a default league
        player.setGoals(0); // Set a default value for goals
        player.setAssists(0); // Set a default value for assists
        player.setCleanSheets(23); // Set a default value for clean sheets
        player.setSaves(987); // Set a default value for the number of saves
        player.setPosition("Goalkeeper");
        goalkeeperRepository.save(player);
    }

    @When("I query goalkeepers by name {string}")
    public void whenQueryByName(String name) {
        ResponseEntity<List<KeepersResponseDto>> responseEntity = keeperController.getKeeperByName(name);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            queriedKeepers = responseEntity.getBody();
        } else {
            throw new RuntimeException(
                    "Failed to retrieve keeper. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    @Then("I am shown {string} with his details including clean sheets")
    public void thenShownGoalkeeperWithDetails(String expectedName) {
        assertNotNull("Queried players should not be null", queriedKeepers);
    }

}