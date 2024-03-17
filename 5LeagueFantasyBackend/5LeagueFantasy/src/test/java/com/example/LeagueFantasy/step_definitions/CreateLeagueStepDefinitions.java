package com.example.LeagueFantasy.step_definitions;

import com.example.LeagueFantasy.controller.LeagueController;
import com.example.LeagueFantasy.dto.LeagueRequestDto;
import com.example.LeagueFantasy.dto.LeagueResponseDto;
import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.League;
import com.example.LeagueFantasy.repository.FantasyManagerRepository;
import com.example.LeagueFantasy.repository.LeagueRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;
import java.util.Objects;

@SpringBootTest
public class CreateLeagueStepDefinitions {
      @Autowired
      private LeagueRepository leagueRepository;

      @Autowired
      private LeagueController leagueController;

      @Autowired
      private FantasyManagerRepository fantasyManagerRepository;
      private LeagueRequestDto request;
      private ResponseEntity<?> response;

      private FantasyManager mockManager;

      private FantasyManager manager;

      // Step Definitions for Normal Flow

      @Given("the user is authenticated and on the Create League page")
      public void the_user_is_authenticated_and_on_the_create_league_page() {
            mockManager = new FantasyManager();
            mockManager.setUsername("username");
            mockManager.setPassword("password");
            mockManager.setEmail("email@example.com");
            mockManager.setName("User's name");
            // ... set other required fields ...
            fantasyManagerRepository.save(mockManager);
            Authentication authentication = new UsernamePasswordAuthenticationToken(mockManager.getUsername(), mockManager.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
      }

      @When("the user enters {string} as the league name")
      public void the_user_enters_as_the_league_name(String leagueName) {
            request = new LeagueRequestDto();
            request.setName(leagueName);
      }

      @And("the user submits the league creation form")
      public void the_user_submits_the_league_creation_form() {

            // Mimic the actual user by calling the leagueController with the test request
            response = leagueController.createLeague(request);
      }

      @Then("the user should receive a confirmation message {string}")
      public void the_user_should_receive_a_confirmation_message(String expectedMessage) {
            Assert.assertNotNull(response);
            Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
            LeagueResponseDto responseBody = (LeagueResponseDto) response.getBody();
            Assert.assertNotNull(responseBody);
            Assert.assertEquals(expectedMessage, responseBody.getMessage());
      }

      // Step Definitions for Alternate Flow

      @And("the league name {string} already exists")
      public void the_league_name_already_exists(String leagueName) {
            Assert.assertNotNull(mockManager); // Ensure mockManager is not null
            Assert.assertNotNull(mockManager.getName()); // Ensure mockManager is persisted

            League existingLeague = new League();
            existingLeague.setName(leagueName);
            existingLeague.setLeagueOwner(mockManager);
            leagueRepository.save(existingLeague); // Use saveAndFlush here as well
      }

      @Then("the user should see an error message {string}")
      public void the_user_should_see_an_error_message(String expectedErrorMessage) {
            Assert.assertNotNull(response);
            Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

            // Handle both possible response types
            if (response.getBody() instanceof LeagueResponseDto) {
                  LeagueResponseDto responseBody = (LeagueResponseDto) response.getBody();
                  Assert.assertEquals(expectedErrorMessage, responseBody.getMessage());
            } else if (response.getBody() instanceof Map) {
                  Map<String, Object> errorResponse = (Map<String, Object>) response.getBody();
                  String actualErrorMessage = (String) errorResponse.get("error");
                  Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
            } else {
                  Assert.fail("Unexpected response body type: " + response.getBody().getClass().getName());
            }
      }

      // Error Flow: Preventing a user from joining multiple leagues
      @Given("a user {string} is already a member of {string}")
      public void a_user_is_already_a_member_of(String username, String leagueName) {
            // Create a FantasyManager
            FantasyManager existingUser = new FantasyManager();
            existingUser.setUsername(username);
            existingUser.setName("Test Manager");
            existingUser.setPassword("password");
            existingUser.setEmail("test@gmail.com");
            fantasyManagerRepository.save(existingUser);

            // Create the existing league
            League existingLeague = new League();
            existingLeague.setName(leagueName);
            existingLeague.setLeagueOwner(existingUser);

            // Save the existing league
            existingLeague = leagueRepository.save(existingLeague);

            // Associate the league with the FantasyManager
            existingUser.setLeague(existingLeague);

            // Save the FantasyManager to ensure it's not transient
            existingUser = fantasyManagerRepository.save(existingUser);

            // Ensure that the association exists between the FantasyManager and the existing league
            Assert.assertNotNull(existingUser.getLeague());
      }

      @When("a user {string} tries to create a new league {string}")
      public void a_user_tries_to_create_a_new_league(String username, String newLeagueName) {
            FantasyManager existingUser = fantasyManagerRepository.findByUsername(username);

            // Ensure that the existing user is associated with a league
            League existingLeague = existingUser.getLeague();
            Assert.assertNotNull(existingLeague); // Ensure that the user is associated with a league

            // Setup authentication for the user
            Authentication authentication = new UsernamePasswordAuthenticationToken(existingUser.getUsername(), existingUser.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Attempt to create a new league
            request = new LeagueRequestDto();
            request.setName(newLeagueName);
            response = leagueController.createLeague(request);
      }

      @Then("{string} should see an error message saying {string}")
      public void user_should_see_an_error_message_saying(String username, String expectedErrorMessage) {
            Assert.assertNotNull(response);
            Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

            // Handle both possible response types
            if (response.getBody() instanceof LeagueResponseDto) {
                  LeagueResponseDto responseBody = (LeagueResponseDto) response.getBody();
                  Assert.assertEquals(expectedErrorMessage, responseBody.getMessage());
            } else if (response.getBody() instanceof Map) {
                  Map<String, Object> errorResponse = (Map<String, Object>) response.getBody();
                  String actualErrorMessage = (String) errorResponse.get("error");
                  Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
            } else {
                  Assert.fail("Unexpected response body type: " + response.getBody().getClass().getName());
            }
      }

      @After
      public void tearDown() {
            // Dissociate fantasy managers from the league
            for (FantasyManager manager : fantasyManagerRepository.findAll()) {
                  if (manager.getLeague() != null) {
                        manager.setLeague(null);
                        fantasyManagerRepository.save(manager); // Save to update the association
                  }
            }

            // Clear the database after each scenario
            leagueRepository.deleteAll();
            fantasyManagerRepository.deleteAll();

            // Clear the security context
            SecurityContextHolder.clearContext();
      }


}
