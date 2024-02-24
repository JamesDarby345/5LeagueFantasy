package com.example.LeagueFantasy.step_definitions;

import com.example.LeagueFantasy.Entity.EuropeanLeague;
import com.example.LeagueFantasy.Entity.Forward;
import com.example.LeagueFantasy.controller.ForwardController;
import com.example.LeagueFantasy.dto.ForwardResponseDto;
import com.example.LeagueFantasy.repository.ForwardRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
public class QueryForwardByPositionStepDefinitions {
    @Autowired
    private ForwardController forwardController;

    @Autowired
    private ForwardRepository forwardRepository;

    private List<ForwardResponseDto> queriedPlayers;

    @Given("that there is a position {string} in the system containing players")
    public void givenPositionContainsPlayer(String position) {

        // Create players
        Forward player1 = createPlayer("Ngolo Kante", position);
        Forward player2 = createPlayer("Yaya Toure", position);

        // Save players to the repository
        forwardRepository.save(player1);
        forwardRepository.save(player2);
    }

    @When("I query players by position {string}")
    public void whenISelectPosition(String selectedPosition) {
        ResponseEntity<List<ForwardResponseDto>> responseEntity = forwardController
                .getForwardByPosition(selectedPosition);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            queriedPlayers = responseEntity.getBody();
        } else {
            throw new RuntimeException(
                    "Failed to retrieve players by position. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    @Then("I am shown a list of players in that position that includes {string}")
    public void thenListIncludesPlayer(String expectedPlayer) {
        assertNotNull("Queried players should not be null", queriedPlayers);
        Assert.assertTrue("Player list should include " + expectedPlayer,
                queriedPlayers.stream().anyMatch(player -> player.getName().equals(expectedPlayer)));
    }

    @Given("that there are no players in the system playing as {string}")
    public void givenPositionDoesntContainPlayer(String position) {
        // Create players
        Forward player1 = createPlayer("Ngolo Kante", "Striker");
        Forward player2 = createPlayer("Yaya Toure", "Striker");

        // Save players to the repository
        forwardRepository.save(player1);
        forwardRepository.save(player2);
    }

    @When("I query players by valid position {string}")
    public void whenISelectPositionEmpty(String selectedPosition) {
        ResponseEntity<List<ForwardResponseDto>> responseEntity = forwardController
                .getForwardByPosition(selectedPosition);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            queriedPlayers = responseEntity.getBody();
        } else {
            throw new RuntimeException(
                    "Failed to retrieve players by position. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    @Then("I am prompted that no results were found")
    public void thenListDoesntIncludesPlayer() {
        assertNotNull("Queried players should not be null", queriedPlayers);
        assertTrue("Queried players list should be empty", queriedPlayers.isEmpty());
    }

    @Given("that there is no {string} position in the system")
    public void givenPositionDoesNotExist(String position) {

        // Create players
        Forward player1 = createPlayer("Ngolo Kante", "Midfielder");
        Forward player2 = createPlayer("Yaya Toure", "Midfielder");

        // Save players to the repository
        forwardRepository.save(player1);
        forwardRepository.save(player2);
    }

    @When("I query players by invalid position {string}")
    public void whenISelectIncorrectPosition(String selectedPosition) {
        ResponseEntity<List<ForwardResponseDto>> responseEntity = forwardController
                .getForwardByPosition(selectedPosition);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            queriedPlayers = responseEntity.getBody();
        } else {
            throw new RuntimeException(
                    "Failed to retrieve players by position. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    @Then("I am shown a no position found error")
    public void thenErrorNoPosition() {
        Assert.assertTrue("Position does not exist", queriedPlayers.isEmpty());

    }

    private Forward createPlayer(String playerName, String position) {
        Forward player = new Forward();
        EuropeanLeague premierLeague = EuropeanLeague.PremierLeague;
        player.setName(playerName);
        player.setTeam("ManCity"); // Set a sample team name
        player.setGamesPlayed(0); // Set a default value for gamesPlayed
        player.setEuropeanLeague(premierLeague); // Set the EuropeanLeague for the player
        player.setGoals(0); // Set a default value for goals
        player.setAssists(0); // Set a default value for assists
        player.setPosition(position); // Set given position
        return player;
    }

}
