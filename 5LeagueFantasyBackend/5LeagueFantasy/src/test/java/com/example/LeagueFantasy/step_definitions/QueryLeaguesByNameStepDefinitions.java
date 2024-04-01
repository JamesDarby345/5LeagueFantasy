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
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@SpringBootTest
public class QueryLeaguesByNameStepDefinitions {
    @Autowired
    private LeagueController leagueController;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private FantasyManagerRepository fantasyManagerRepository;

    private List<LeagueResponseDto> queriedLeagues;

    private FantasyManager mockManager;



    @Given("the user is authenticated and on the Retrieve League page")
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

    @And("that there is a league name {string} in the system")
    public void givenLeaguesExist(String leagueName){
        LeagueRequestDto leagueRequestDto = new LeagueRequestDto();
        leagueRequestDto.setName(leagueName);
        leagueController.createLeague(leagueRequestDto);
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
