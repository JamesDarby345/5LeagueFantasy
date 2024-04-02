package com.example.LeagueFantasy.step_definitions;

import com.example.LeagueFantasy.controller.FantasyManagerController;
import com.example.LeagueFantasy.dto.FantasyManagerResponseDto;
import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.League;
import com.example.LeagueFantasy.repository.FantasyManagerRepository;
import com.example.LeagueFantasy.repository.LeagueRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


@SpringBootTest
public class UpdateFantasyManagerLeagueStepDefinitions {

    @Autowired
    private FantasyManagerController fantasyManagerController;

    @Autowired
    private FantasyManagerRepository fantasyManagerRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    private FantasyManagerResponseDto updatedFantasyManager;


    @Given("a fantasy manager with username {string} exists in the system")
    public void givenManagerExist(String managerName){
        FantasyManager mockManager = new FantasyManager();
        mockManager.setUsername(managerName);
        mockManager.setPassword("password");
        mockManager.setEmail("email@example.com");
        mockManager.setName("User's name");
        fantasyManagerRepository.save(mockManager);
    }

    @And("a league with name {string} exists in the system")
    public void givenLeagueExist(String leagueName){
        FantasyManager mockManager = new FantasyManager();
        mockManager.setUsername(leagueName);
        mockManager.setPassword("password");
        mockManager.setEmail("email@example.com");
        mockManager.setName("User's name");
        fantasyManagerRepository.save(mockManager);
        League league = new League();
        league.setLeagueOwner(mockManager);
        league.setName(leagueName);
        leagueRepository.save(league);
    }

    @When("the user {string} requests to update their league to {string}")
    public void whenIQuery(String username, String leagueName) {

        ResponseEntity<FantasyManagerResponseDto> responseEntity =
                fantasyManagerController.updateManagerLeague(username, leagueName);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            updatedFantasyManager = responseEntity.getBody();
        } else {
            throw new RuntimeException(
                    "Failed to retrieve players. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    @Then("the fantasy manager should have the updated league {string}")
    public void thenManagerHasUpdatedLeague(String expectedLeague) {
        Assert.assertTrue(
                "Player list should include " + expectedLeague,
                updatedFantasyManager.getLeague().getName().equals(expectedLeague));
    }

}
