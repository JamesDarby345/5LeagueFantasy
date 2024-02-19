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

import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
public class QueryForwardsByLeagueStepDefinition {
    @Autowired
    private ForwardController forwardController;

    @Autowired
    private ForwardRepository forwardRepository;

    private List<ForwardResponseDto> queriedPlayers;

    @Given("that there is a league {string} containing players")
    public void givenLeagueContainsPlayers(String leagueName) {
        // Create a Premier League
        EuropeanLeague premierLeague = EuropeanLeague.PremierLeague;

        // Create players and associate them with the Premier League
        Forward player1 = createPlayer("Haaland", premierLeague);
        Forward player2 = createPlayer("Player2", premierLeague);

        // Save players to the repository
        forwardRepository.save(player1);
        forwardRepository.save(player2);
    }


    @When("I select the {string}")
    public void whenISelectLeague(String selectedLeague) {
        ResponseEntity<List<ForwardResponseDto>> responseEntity = forwardController.getForwardsByEuropeanLeague(selectedLeague);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            queriedPlayers = responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to retrieve players by league. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    @Then("I am shown a list of all players in the league that includes {string}")
    public void thenListIncludesPlayer(String expectedPlayer) {
        assertNotNull("Queried players should not be null", queriedPlayers);
        Assert.assertTrue("Player list should include " + expectedPlayer,
                queriedPlayers.stream().anyMatch(player -> player.getName().equals(expectedPlayer)));
    }

    private Forward createPlayer(String playerName, EuropeanLeague league) {
        Forward player = new Forward();
        player.setName(playerName);
        player.setTeam("ManCity"); // Set a sample team name
        player.setGamesPlayed(0); // Set a default value for gamesPlayed
        player.setEuropeanLeague(league); // Set the EuropeanLeague for the player
        player.setGoals(0); // Set a default value for goals
        player.setAssists(0); // Set a default value for assists
        player.setPosition("ST"); // Set a default position
        return player;
    }

}
