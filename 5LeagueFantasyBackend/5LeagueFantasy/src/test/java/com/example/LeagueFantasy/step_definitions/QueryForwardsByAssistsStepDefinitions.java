package com.example.LeagueFantasy.step_definitions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.LeagueFantasy.controller.ForwardController;
import com.example.LeagueFantasy.dto.ForwardResponseDto;
import com.example.LeagueFantasy.entity.EuropeanLeague;
import com.example.LeagueFantasy.entity.Forward;
import com.example.LeagueFantasy.repository.ForwardRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class QueryForwardsByAssistsStepDefinitions {

      @Autowired
      private ForwardController forwardController;

      @Autowired
      private ForwardRepository forwardRepository;

      private List<ForwardResponseDto> queriedPlayersAscending;

      private List<ForwardResponseDto> queriedPlayersDescending;

      private String queryResult;

      // empty repo
      @Autowired
      private ForwardRepository forwardRepository1;

      private ResponseEntity<List<ForwardResponseDto>> responseEntity;

      @Given("there exists in the system players {string} with {int} assists and {string} with {int} assists")
      public void GivenPlayerExists(String Player1Name, int player1assists, String Player2Name, int player2assists) {
            Forward player1 = new Forward();
            Forward player2 = new Forward();
            player1.setName(Player1Name);
            player1.setTeam("SampleTeam1"); // Set a sample team name
            player1.setGamesPlayed(1); // Set a default value for gamesPlayed
            player1.setEuropeanLeague(EuropeanLeague.PremierLeague); // Set a default league
            player1.setGoals(1); // Set a default value for goals
            player1.setAssists(player1assists); // Set a default value for assists
            player1.setPosition("ST"); // Set a default position

            player2.setName(Player2Name);
            player2.setTeam("SampleTeam2"); // Set a sample team name
            player2.setGamesPlayed(1); // Set a default value for gamesPlayed
            player2.setEuropeanLeague(EuropeanLeague.PremierLeague); // Set a default league
            player2.setGoals(2); // Set a default value for goals
            player2.setAssists(player2assists); // Set a default value for assists
            player2.setPosition("ST"); // Set a default position

            forwardRepository.save(player1);
            forwardRepository.save(player2);
      }

      @When("I sort players by descending order of assists")
      public void whenISortPlayersByDescendingGoals() {
            ResponseEntity<List<ForwardResponseDto>> response = forwardController.getForwardsSortedByDescendingAssists();
            queriedPlayersDescending = response.getBody();
      }

      @Then("I am shown a descending assists list that has {string} ranked higher than {string}")
      public void thenIAmShownAListWithCorrectOrder3(String player1Name, String player2Name) {
            assertNotNull(queriedPlayersDescending, "The queried players list should not be null.");
            assertTrue(queriedPlayersDescending.size() >= 2, "The list must contain at least two players for comparison.");

            // Find the indices of the two players in the list
            int player1Index = -1, player2Index = -1;
            for (int i = 0; i < queriedPlayersDescending.size(); i++) {
                  if (queriedPlayersDescending.get(i).getName().equals(player1Name)) {
                        player1Index = i;
                  } else if (queriedPlayersDescending.get(i).getName().equals(player2Name)) {
                        player2Index = i;
                  }
            }

            assertTrue(player1Index != -1 && player2Index != -1, "Both players should be found in the list.");

            // Verify that player1 is ranked higher (has a lower index) than player2 in the list
            assertTrue(player1Index < player2Index, player1Name + " should be ranked higher than " + player2Name);
      }

      @When("I sort players by ascending order of assists")
      public void whenISortPlayersByAscendingGoals() {
            ResponseEntity<List<ForwardResponseDto>> response = forwardController.getForwardsSortedByAscendingAssists();
            queriedPlayersAscending = response.getBody();
      }

      @Then("I am shown a ascending assists list that has {string} ranked higher than {string}")
      public void thenIAmShownAListWithCorrectOrder4(String player1Name, String player2Name) {
            assertNotNull(queriedPlayersAscending, "The queried players list should not be null.");
            assertTrue(queriedPlayersAscending.size() >= 2, "The list must contain at least two players for comparison.");

            // Find the indices of the two players in the list
            int player1Index = -1, player2Index = -1;
            for (int i = 0; i < queriedPlayersAscending.size(); i++) {
                  if (queriedPlayersAscending.get(i).getName().equals(player1Name)) {
                        player1Index = i;
                  } else if (queriedPlayersAscending.get(i).getName().equals(player2Name)) {
                        player2Index = i;
                  }
            }

            assertTrue(player1Index != -1 && player2Index != -1, "Both players should be found in the list.");

            // Verify that player1 is ranked lower (has a higher index) than player2 in the list
            assertTrue(player1Index < player2Index, player1Name + " should be ranked higher than " + player2Name);
      }


      @Given("no player is in the system assists")
      public void noPlayerIsInTheSystem() {
            forwardRepository.deleteAll();
      }

      @When("I query by ascending order of assists")
      public void iQueryByAscendingOrderOfGoals() {
            responseEntity = forwardController.getForwardsSortedByAscendingAssists();
      }


      @Then("I am shown a prompt indicating no results for ascending assists were found")
      public void iAmShownAPromptIndicatingNoResultsWereFound() {
            assertNotNull(responseEntity, "The response entity should not be null.");
            // Assuming the controller returns an empty list for no results
            assertTrue(Objects.requireNonNull(responseEntity.getBody()).isEmpty(), "The list should be empty indicating no results were found.");
      }
}
