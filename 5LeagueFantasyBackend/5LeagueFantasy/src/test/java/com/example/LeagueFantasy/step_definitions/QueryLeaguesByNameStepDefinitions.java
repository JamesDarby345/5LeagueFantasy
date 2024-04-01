package com.example.LeagueFantasy.step_definitions;

import com.example.LeagueFantasy.controller.LeagueController;
import com.example.LeagueFantasy.dto.LeagueResponseDto;
import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.League;
import com.example.LeagueFantasy.repository.LeagueRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
public class QueryLeaguesByNameStepDefinitions {
    @Autowired
    private LeagueController leagueController;

    @Autowired
    private LeagueRepository leagueRepository;

    private List<LeagueResponseDto> queriedLeagues;

    @Given("that there is a league name {string} in the system")
    public void givenLeaguesExist(String leagueName){
        League league = new League();
        league.setName(leagueName);
        league.setLeagueOwner(new FantasyManager());
        leagueRepository.save(league);
    }

    @When("a user requests to retrieve leagues by name {string}")
    public void whenIQuery(String query) {

        ResponseEntity<List<LeagueResponseDto>> responseEntity =
                leagueController.getLeagueByName(query);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            queriedLeagues = responseEntity.getBody();
        } else {
            throw new RuntimeException(
                    "Failed to retrieve players. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    @Then("the system should return a list of leagues with the given {string}")
    public void thenListIncludesPlayer(String expectedLeague) {
        Assert.assertTrue(
                "Player list should include " + expectedLeague,
                queriedLeagues.stream().anyMatch(player -> player.getName().equals(expectedLeague)));
    }
}
