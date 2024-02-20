package com.example.LeagueFantasy.step_definitions;

import com.example.LeagueFantasy.entity.EuropeanLeague;
import com.example.LeagueFantasy.entity.Forward;
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

@SpringBootTest
public class QueryForwardsByNameStepDefinitions {
  @Autowired private ForwardController forwardController;

  @Autowired private ForwardRepository forwardRepository;

  private List<ForwardResponseDto> queriedPlayers;

  @Given("that there is a player named {string} in the system")
  public void givenPlayerExists(String playerName) {
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

  @Given("that there are players named {string} and {string} in the system")
  public void givenPlayersExist(String playerName1, String playerName2) {
    Forward player1 = new Forward();
    player1.setName(playerName1);
    player1.setTeam("SampleTeam"); // Set a sample team name
    player1.setGamesPlayed(0); // Set a default value for gamesPlayed
    player1.setEuropeanLeague(EuropeanLeague.PremierLeague); // Set a default league
    player1.setGoals(0); // Set a default value for goals
    player1.setAssists(0); // Set a default value for assists
    player1.setPosition("ST"); // Set a default position

    Forward player2 = new Forward();
    player2.setName(playerName2);
    player2.setName(playerName2);
    player2.setTeam("SampleTeam"); // Set a sample team name
    player2.setGamesPlayed(0); // Set a default value for gamesPlayed
    player2.setEuropeanLeague(EuropeanLeague.PremierLeague); // Set a default league
    player2.setGoals(0); // Set a default value for goals
    player2.setAssists(0); // Set a default value for assists
    player2.setPosition("ST"); // Set a default position

    forwardRepository.save(player1);
    forwardRepository.save(player2);
  }

  @When("I query {string}")
  public void whenIQuery(String query) {

    ResponseEntity<List<ForwardResponseDto>> responseEntity =
        forwardController.getForwardsByName(query);

    if (responseEntity.getStatusCode().is2xxSuccessful()) {
      queriedPlayers = responseEntity.getBody();
    } else {
      throw new RuntimeException(
          "Failed to retrieve players. Status code: " + responseEntity.getStatusCodeValue());
    }
  }

  @Then("I am shown a list of players that includes {string}")
  public void thenListIncludesPlayer(String expectedPlayer) {
    Assert.assertTrue(
        "Player list should include " + expectedPlayer,
        queriedPlayers.stream().anyMatch(player -> player.getName().equals(expectedPlayer)));
  }

  @Then("I am shown a list of players that includes {string} and {string}")
  public void thenListIncludesPlayers(String expectedPlayer1, String expectedPlayer2) {
    Assert.assertTrue(
        "Player list should include " + expectedPlayer1,
        queriedPlayers.stream().anyMatch(player -> player.getName().equals(expectedPlayer1)));

    Assert.assertTrue(
        "Player list should include " + expectedPlayer2,
        queriedPlayers.stream().anyMatch(player -> player.getName().equals(expectedPlayer2)));
  }

  @Given(
      "that the list of players in the system does not include any player whose name contains {string}")
  public void givenNoPlayerWithNameExists(String playerName) {}

  @Then("I am shown a prompt that indicates that no results were found")
  public void thenNoResultsFoundPrompt() {
    Assert.assertTrue("No results should be found", queriedPlayers.isEmpty());
  }
}
