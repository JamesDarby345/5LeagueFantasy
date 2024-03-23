package com.example.LeagueFantasy.step_definitions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.LeagueFantasy.controller.PlayerToTeamController;
import com.example.LeagueFantasy.dto.ForwardResponseDto;
import com.example.LeagueFantasy.dto.KeepersResponseDto;
import com.example.LeagueFantasy.dto.PlayerToTeamRequestDto;
import com.example.LeagueFantasy.dto.PlayerToTeamResponseDto;
import com.example.LeagueFantasy.entity.EuropeanLeague;
import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.entity.Forward;
import com.example.LeagueFantasy.entity.Goalkeeper;
import com.example.LeagueFantasy.entity.PlayerToTeam;
import com.example.LeagueFantasy.entity.UserTeam;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.FantasyManagerRepository;
import com.example.LeagueFantasy.repository.ForwardRepository;
import com.example.LeagueFantasy.repository.GoalkeeperRepository;
import com.example.LeagueFantasy.repository.PlayerToTeamRepository;
import com.example.LeagueFantasy.repository.UserTeamRepository;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import org.junit.Assert;

@SpringBootTest
public class AddPlayerToTeamStepDefinitions {

    @Autowired 
    private UserTeamRepository userTeamRepository;

    @Autowired
    private ForwardRepository forwardRepository;

    @Autowired
    private GoalkeeperRepository goalkeeperRepository;

    @Autowired
    private PlayerToTeamRepository playerToTeamRepository;

    @Autowired
    private PlayerToTeamController playerToTeamController;

    @Autowired
    private FantasyManagerRepository fantasyManagerRepository;

    private String existingForwardName;
    private String existingForwardTeam;
    private String existingForwardPosition;
    private String existingGoalkeeperName;
    private String existingGoalkeeperTeam;
    private String existingGoalkeeperPosition;
    private int userTeamId;
    private int forwardId;
    private int goalkeeperId;
    private PlayerToTeamRequestDto request;
    private ResponseEntity<PlayerToTeamResponseDto> addedPlayerToTeamResponse;

    // Normal Flow

    @Given("I have an existing user team with team name {string} with 1 forward named {string}") 
    public void i_have_an_existing_user_team_with_team_name_with_1_forward(String teamName, String existingForwardName) {

        FantasyManager existingUser = new FantasyManager("username", "name", "email", "password");
        fantasyManagerRepository.save(existingUser);

        UserTeam userTeam = new UserTeam(teamName, 0, true);
        userTeam.setFantasyManager(existingUser);
        userTeamRepository.save(userTeam);
        
        this.userTeamId = userTeamRepository.findByName(teamName).getId();

        Forward forward = new Forward(existingForwardName, "TeamName", "Striker", 0, EuropeanLeague.Bundesliga, 0, 0);
        forwardRepository.save(forward);
        this.existingForwardName = existingForwardName;
        this.existingForwardTeam = "TeamName";
        this.existingForwardPosition = "Striker";

        PlayerToTeam existingPlayer = new PlayerToTeam();
        existingPlayer.setForward(forward);
        existingPlayer.setUserTeam(userTeam);
        existingPlayer.setGoalkeeper(null);
        playerToTeamRepository.save(existingPlayer);
    }

    @When("I add a forward named {string} to the user team")
    public void when_i_add_a_forward_named_to_the_user_team(String newForwardName) {

        Forward forward = new Forward(newForwardName, "OtherTeam", "Right Midfield", 0, EuropeanLeague.Bundesliga, 0, 0);
        forwardRepository.save(forward);
        this.forwardId = forwardRepository.findByNameContainingIgnoreCase(newForwardName).get(0).getPlayerId();
        this.goalkeeperId = -1;

        request = new PlayerToTeamRequestDto(this.userTeamId, this.forwardId, this.goalkeeperId);

        addedPlayerToTeamResponse = playerToTeamController.addForwardToTeam(request); 
    }

    @Then("the user team should have 2 players total")
    public void the_user_team_should_have_2_players_total() {
        List<ForwardResponseDto> forwards = playerToTeamController.getForwardsByTeam(this.userTeamId).getBody();
        KeepersResponseDto keeper = playerToTeamController.getGoalkeeperByTeam(this.userTeamId).getBody();

        Assert.assertTrue(forwards.size() == 2);
        Assert.assertNull(keeper);
        Assert.assertEquals(existingForwardName, forwards.get(0).getName());
        Assert.assertEquals(existingForwardTeam, forwards.get(0).getTeam());
        Assert.assertEquals(existingForwardPosition, forwards.get(0).getPosition());

        Assert.assertEquals("Eden Hazard", forwards.get(1).getName());
        Assert.assertEquals("OtherTeam", forwards.get(1).getTeam());
        Assert.assertEquals("Right Midfield", forwards.get(1).getPosition());
    }

    @When("I add a goalkeeper named {string} to the user team")
    public void when_i_add_a_goalkeeper_named_to_the_user_team(String newGoalkeeperName) {

        Goalkeeper goalkeeper = new Goalkeeper(newGoalkeeperName, "Montreal Canadians", "Goalie", 0, EuropeanLeague.Bundesliga, 0, 0, 10, 2);
        goalkeeperRepository.save(goalkeeper);
        this.forwardId = -1;
        this.goalkeeperId = goalkeeperRepository.findByName(newGoalkeeperName).get(0).getPlayerId();

        request = new PlayerToTeamRequestDto(this.userTeamId, this.forwardId, this.goalkeeperId);

        addedPlayerToTeamResponse = playerToTeamController.addGoalkeeperToTeam(request); 
    }

    @Then("the user team should have 2 players total, with one goalkeeper and one forward")
    public void the_user_team_should_have_2_players_total_with_one_goalie_and_one_forward() {
        List<ForwardResponseDto> forwards = playerToTeamController.getForwardsByTeam(this.userTeamId).getBody();
        KeepersResponseDto keeper = playerToTeamController.getGoalkeeperByTeam(this.userTeamId).getBody();

        Assert.assertTrue(forwards.size() == 1);
        Assert.assertNotNull(keeper);
        Assert.assertEquals(existingForwardName, forwards.get(0).getName());
        Assert.assertEquals(existingForwardTeam, forwards.get(0).getTeam());
        Assert.assertEquals(existingForwardPosition, forwards.get(0).getPosition());

        Assert.assertEquals("Carey Price", keeper.getName());
        Assert.assertEquals("Montreal Canadians", keeper.getTeam());
        Assert.assertEquals("Goalie", keeper.getPosition());
    }

    @Given("I have an existing user team with team name {string} with 1 goalkeeper named {string}")
    public void i_have_an_existing_user_team_with_team_name_with_1_goalkeeper(String teamName, String existingGoalkeeperName) {

        FantasyManager existingUser = new FantasyManager("username", "name", "email", "password");
        fantasyManagerRepository.save(existingUser);

        UserTeam userTeam = new UserTeam(teamName, 0, true);
        userTeam.setFantasyManager(existingUser);
        userTeamRepository.save(userTeam);
        
        this.userTeamId = userTeamRepository.findByName(teamName).getId();

        Goalkeeper goalkeeper = new Goalkeeper(existingGoalkeeperName, "TeamName", "Goalie", 0, EuropeanLeague.Bundesliga, 0, 0, 10, 2);
        goalkeeperRepository.save(goalkeeper);
        this.existingGoalkeeperName = existingGoalkeeperName;
        this.existingGoalkeeperTeam = "TeamName";
        this.existingGoalkeeperPosition = "Goalie";

        request = new PlayerToTeamRequestDto(this.userTeamId, -1, goalkeeperRepository.findByName(existingGoalkeeperName).get(0).getPlayerId());
        ResponseEntity<PlayerToTeamResponseDto> existingGoalie = playerToTeamController.addGoalkeeperToTeam(request);
    }

    @When("I add another goalkeeper to the user team")
    public void when_i_add_another_goalkeeper_to_the_user_team() {

        Goalkeeper goalkeeper = new Goalkeeper("Paper Towel", "Kitchen", "Goalie", 0, EuropeanLeague.Bundesliga, 0, 0, 10, 2);
        goalkeeperRepository.save(goalkeeper);
        this.forwardId = -1;
        this.goalkeeperId = goalkeeperRepository.findByName("Carey Price").get(0).getPlayerId();

        request = new PlayerToTeamRequestDto(this.userTeamId, this.forwardId, this.goalkeeperId);

        FiveLeagueFantasyException fiveLeagueFantasyException = Assert.assertThrows(FiveLeagueFantasyException.class, 
            () -> addedPlayerToTeamResponse = playerToTeamController.addGoalkeeperToTeam(request)
        ); 
        Assert.assertSame(fiveLeagueFantasyException.getStatus(), HttpStatus.BAD_REQUEST);
        Assert.assertEquals("User team already has a goalkeeper", fiveLeagueFantasyException.getMessage());
    }

    @Then("the attempt should not be successful and theres should only be 1 goalkeeper on the team")
    public void then_the_attempt_should_not_be_successful_and_theres_should_only_be_1_goalkeeper_on_the_team() {
        List<ForwardResponseDto> forwards = playerToTeamController.getForwardsByTeam(this.userTeamId).getBody();
        KeepersResponseDto keeper = playerToTeamController.getGoalkeeperByTeam(this.userTeamId).getBody();

        Assert.assertTrue(forwards.size() == 0);
        Assert.assertNotNull(keeper);
        Assert.assertEquals(1, userTeamRepository.findById(this.userTeamId).getNumberOfKeepers());
        Assert.assertEquals(existingGoalkeeperName, keeper.getName());
        Assert.assertEquals(existingGoalkeeperTeam, keeper.getTeam());
        Assert.assertEquals(existingGoalkeeperPosition, keeper.getPosition());
    }

    @Given("I have an existing user team with team name {string} with 5 forwards")
    public void i_have_an_existing_user_team_with_team_name_with_5_forwards(String teamName) {

        FantasyManager existingUser = new FantasyManager("username", "name", "email", "password");
        fantasyManagerRepository.save(existingUser);

        UserTeam userTeam = new UserTeam(teamName, 0, true);
        userTeam.setFantasyManager(existingUser);
        userTeam.setNumberOfForwards(0);
        userTeam.setNumberOfKeepers(0);
        userTeamRepository.save(userTeam);
        
        this.userTeamId = userTeamRepository.findByName(teamName).getId();

        Forward forward1 = new Forward("F1", "ManCity", "Striker", 0, EuropeanLeague.Bundesliga, 0, 0);
        forwardRepository.save(forward1);
        Forward forward2 = new Forward("F2", "ManCity", "RW", 0, EuropeanLeague.Bundesliga, 0, 0);
        forwardRepository.save(forward2);
        Forward forward3 = new Forward("F3", "ManCity", "LW", 0, EuropeanLeague.Bundesliga, 0, 0);
        forwardRepository.save(forward3);
        Forward forward4 = new Forward("F4", "ManCity", "Mid", 0, EuropeanLeague.Bundesliga, 0, 0);
        forwardRepository.save(forward4);
        Forward forward5 = new Forward("F5", "ManCity", "Def", 0, EuropeanLeague.Bundesliga, 0, 0);
        forwardRepository.save(forward5);

        request = new PlayerToTeamRequestDto(this.userTeamId, forward1.getPlayerId(), -1);
        ResponseEntity<PlayerToTeamResponseDto> p1 = playerToTeamController.addForwardToTeam(request);

        request = new PlayerToTeamRequestDto(this.userTeamId, forward2.getPlayerId(), -1);
        ResponseEntity<PlayerToTeamResponseDto> p2 = playerToTeamController.addForwardToTeam(request);

        request = new PlayerToTeamRequestDto(this.userTeamId, forward3.getPlayerId(), -1);
        ResponseEntity<PlayerToTeamResponseDto> p3 = playerToTeamController.addForwardToTeam(request);

        request = new PlayerToTeamRequestDto(this.userTeamId, forward4.getPlayerId(), -1);
        ResponseEntity<PlayerToTeamResponseDto> p4 = playerToTeamController.addForwardToTeam(request);

        request = new PlayerToTeamRequestDto(this.userTeamId, forward5.getPlayerId(), -1);
        ResponseEntity<PlayerToTeamResponseDto> p5 = playerToTeamController.addForwardToTeam(request);

        List<ForwardResponseDto> forwards = playerToTeamController.getForwardsByTeam(this.userTeamId).getBody();

        Assert.assertEquals(forwards.size(), 5);
        Assert.assertEquals(forwards.get(0).getName(), "F1");
        Assert.assertEquals(forwards.get(1).getName(), "F2");
        Assert.assertEquals(forwards.get(2).getName(), "F3");
        Assert.assertEquals(forwards.get(3).getName(), "F4");
        Assert.assertEquals(forwards.get(4).getName(), "F5");
        Assert.assertNotNull(p1);
        Assert.assertNotNull(p2);
        Assert.assertNotNull(p3);
        Assert.assertNotNull(p4);
        Assert.assertNotNull(p5);
    }

    @When("I add another forward to the user team")
    public void when_i_add_another_forward_to_the_user_team() {

        Forward forward = new Forward("F6", "ManCity", "Striker", 0, EuropeanLeague.Bundesliga, 0, 0);
        forwardRepository.save(forward);
        this.forwardId = forwardRepository.findByNameContainingIgnoreCase("F6").get(0).getPlayerId();
        this.goalkeeperId = -1;

        request = new PlayerToTeamRequestDto(this.userTeamId, this.forwardId, this.goalkeeperId);

        FiveLeagueFantasyException fiveLeagueFantasyException = Assert.assertThrows(FiveLeagueFantasyException.class, 
            () -> addedPlayerToTeamResponse = playerToTeamController.addForwardToTeam(request)
        ); 
        Assert.assertSame(fiveLeagueFantasyException.getStatus(), HttpStatus.BAD_REQUEST);
        Assert.assertEquals("User team already has 5 forwards", fiveLeagueFantasyException.getMessage());
    }
    
    @Then("the attempt should not be successful and there should still be 5 forwards on the team")
    public void then_the_attempt_should_not_be_successful_and_theres_should_only_be_5_forwards_on_the_team() {
        List<ForwardResponseDto> forwards = playerToTeamController.getForwardsByTeam(this.userTeamId).getBody();

        Assert.assertTrue(forwards.size() == 5);
        Assert.assertEquals(forwards.get(0).getName(), "F1");
        Assert.assertEquals(forwards.get(1).getName(), "F2");
        Assert.assertEquals(forwards.get(2).getName(), "F3");
        Assert.assertEquals(forwards.get(3).getName(), "F4");
        Assert.assertEquals(forwards.get(4).getName(), "F5");
    }


    @When("I add a forward named {string} to the user team already with that player")
    public void when_i_add_a_forward_named_to_the_user_team_already_with_that_player(String newForwardName) {

        this.forwardId = forwardRepository.findByNameContainingIgnoreCase(newForwardName).get(0).getPlayerId();
        this.goalkeeperId = -1;

        request = new PlayerToTeamRequestDto(this.userTeamId, this.forwardId, this.goalkeeperId);
        FiveLeagueFantasyException exception = Assert.assertThrows(
            FiveLeagueFantasyException.class, 
            () -> playerToTeamController.addForwardToTeam(request)
        ); 

        Assert.assertEquals("Player already added to team", exception.getMessage());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @Then("the attempt should not be successful and there should still be 1 forward on the team")
    public void then_the_attempt_should_not_be_successful_and_theres_should_only_be_1_forward_on_the_team() {
        List<ForwardResponseDto> forwards = playerToTeamController.getForwardsByTeam(this.userTeamId).getBody();

        Assert.assertTrue(forwards.size() == 1);
        Assert.assertEquals(forwards.get(0).getName(), this.existingForwardName);
        Assert.assertEquals(forwards.get(0).getTeam(), this.existingForwardTeam);
        Assert.assertEquals(forwards.get(0).getPosition(), this.existingForwardPosition);
    }
}
