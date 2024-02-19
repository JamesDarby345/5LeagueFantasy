package com.example.LeagueFantasy.step_definitions;

import com.example.LeagueFantasy.Entity.EuropeanLeague;
import com.example.LeagueFantasy.Entity.Forward;
import com.example.LeagueFantasy.Entity.Goalkeeper;
import com.example.LeagueFantasy.controller.ForwardController;
import com.example.LeagueFantasy.dto.ForwardResponseDto;
import com.example.LeagueFantasy.repository.ForwardRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class QueryAllForwardsStepDefinitions {
    @Autowired
    private ForwardController forwardController;

    @Autowired
    private ForwardRepository forwardRepository;

    private List<ForwardResponseDto> queriedPlayers;

    @Given("that there is a forward {string} in the system")
    public void givenKeeperExists(String playerName) {
        Forward player = new Forward();
        player.setName(playerName);
        player.setTeam("SampleTeam"); // Set a sample team name
        player.setGamesPlayed(0); // Set a default value for gamesPlayed
        player.setEuropeanLeague(EuropeanLeague.PremierLeague); // Set a default league
        player.setGoals(0); // Set a default value for goals
        player.setAssists(0); // Set a default value for assists
        player.setPosition("ST"); // Set a default position
        forwardRepository.save(player);
    }

    @When("I query all forward")
    public void whenIQueryAllForwards(){
        ResponseEntity<List<ForwardResponseDto>> responseEntity = forwardController.getAllForwards();

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            queriedPlayers = responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to retrieve players. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    @Then("I am shown a list of all forwards that includes {string}")
    public void thenIShownListOfForwards(String expectedForwardName) {
        boolean forwardFound = queriedPlayers.stream()
                .anyMatch(forward -> forward.getName().equals(expectedForwardName));

        assertTrue("Forward not found in the queried list", forwardFound);
    }

}
