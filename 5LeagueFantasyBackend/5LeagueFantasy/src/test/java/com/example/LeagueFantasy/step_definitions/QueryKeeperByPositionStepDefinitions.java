package com.example.LeagueFantasy.step_definitions;

import com.example.LeagueFantasy.Entity.EuropeanLeague;
import com.example.LeagueFantasy.Entity.Goalkeeper;
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

import java.util.List;

@SpringBootTest
public class QueryKeeperByPositionStepDefinitions {
    @Autowired
    private KeeperController keeperController;

    @Autowired
    private GoalkeeperRepository goalkeeperRepository;

    private List<KeepersResponseDto> queriedKeepers;

    @Given("that there is a keeper {string} in the system")
    public void givenKeeperExists(String playerName) {
        Goalkeeper player = new Goalkeeper();
        player.setName(playerName);
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

    @SuppressWarnings("deprecation")
    @When("I query keeper {string}")
    public void whenQuery(String query) {

        ResponseEntity<List<KeepersResponseDto>> responseEntity = keeperController.getByPositionKeeper(query);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            queriedKeepers = responseEntity.getBody();
        } else {
            throw new RuntimeException(
                    "Failed to retrieve keeper. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    @Then("I am shown a list of all goalkeepers")
    public void thenListIncludesAllGoalkeepers() {
        assertNotNull("Queried players should not be null", queriedKeepers);

    }

}
