package com.example.LeagueFantasy.step_definitions;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.example.LeagueFantasy.controller.FantasyManagerController;
import com.example.LeagueFantasy.controller.UserTeamController;
import com.example.LeagueFantasy.dto.UserTeamRequestDto;
import com.example.LeagueFantasy.dto.UserTeamResponseDto;
import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.FantasyManagerRepository;
import com.example.LeagueFantasy.repository.UserTeamRepository;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest
public class CreateUserTeamStepDefinitions {

    @Autowired 
    private FantasyManagerRepository fantasyManagerRepository;
    
    @Autowired 
    private FantasyManagerController fantasyManagerController;
    
    @Autowired
    private UserTeamRepository userTeamRepository;
    
    @Autowired
    private UserTeamController userTeamController;

    private String teamName;
    private boolean setAsActive;
    private String fantasyManagerUsername;
    private UserTeamRequestDto request;
    private ResponseEntity<UserTeamResponseDto> createdTeam;

    // Step Definition for Normal Flow
    @Given("a user with the username {string} exists when creating a new user team")
    public void a_user_with_the_username_username_exists(String username) {
        FantasyManager existingUser = new FantasyManager(username, "Kirby", "kdach@gmail.com", "password");
        fantasyManagerRepository.save(existingUser);
        this.fantasyManagerUsername = username;
        Assert.assertEquals(
            fantasyManagerController.getManagerByUsername(username).getBody().getUsername(), username);
   
    }

    @When("I enter a valid user team name {string}")
    public void i_enter_a_valid_user_team_name_name(String teamName) {
        this.teamName = teamName;
    }

    @And("set it to active")
    public void set_it_to_active() {
        this.setAsActive = true;
    }

    @And("I click on the create team button")
    public void i_click_on_the_create_team_button() {
        request = new UserTeamRequestDto(this.teamName, this.setAsActive, this.fantasyManagerUsername);
        createdTeam = userTeamController.createNewUserTeam(request);
    }

    @Then("the user team with name {string} should be created")
    public void the_user_team_with_name_name_should_be_created(String teamName) {
        Assert.assertEquals(userTeamController.getTeamsByManager(this.fantasyManagerUsername).getBody().get(0).getName(), teamName);
        Assert.assertEquals(userTeamController.getTeamsByManager(this.fantasyManagerUsername).getBody().get(0).getIsActive(), this.setAsActive);
        Assert.assertEquals(userTeamController.getTeamsByManager(this.fantasyManagerUsername).getBody().get(0).getFantasyManager().getUsername(), this.fantasyManagerUsername);
    }


    // Step Defintion for Alternate Flow
    @When("I enter an empty user team name {string}")
    public void i_enter_an_empty_user_team_name_name(String teamName) {
        this.teamName = teamName;
    }

    @And("I click on the create team button to try and create an invalid team")
    public void i_click_on_the_create_team_button_to_try_and_create_an_invalid_team() {
        request = new UserTeamRequestDto(this.teamName, this.setAsActive, this.fantasyManagerUsername);
        Assert.assertThrows(
        FiveLeagueFantasyException.class,
        () -> {
            userTeamController.createNewUserTeam(request);
        });
    }

    @Then("the user team with an empty name should not be created")
    public void the_user_team_with_an_empty_name_should_not_be_created() {
        Assert.assertEquals(userTeamController.getTeamsByManager(fantasyManagerUsername).getBody().size(), 0);
    }

    // Step Definitions for Alternate Flow
    @And("the user has already created a team with the name {string} and status active")
    public void the_user_has_already_created_a_team_with_the_name_name(String teamName) {
        UserTeamRequestDto request = new UserTeamRequestDto(teamName, true, this.fantasyManagerUsername);
        userTeamController.createNewUserTeam(request);
        Assert.assertEquals(userTeamController.getTeamsByManager(this.fantasyManagerUsername).getBody().get(0).getName(), teamName);
        Assert.assertEquals(userTeamController.getTeamsByManager(this.fantasyManagerUsername).getBody().get(0).getIsActive(), true);
        Assert.assertEquals(userTeamController.getTeamsByManager(this.fantasyManagerUsername).getBody().get(0).getFantasyManager().getUsername(), this.fantasyManagerUsername);
    }

    @When("When I enter a valid user team name {string}") 
    public void when_i_enter_a_valid_user_team_name_name(String teamName) {
        this.teamName = teamName;
    }

    @And("I click on the create team button to create another team")
    public void i_click_on_the_create_team_button_to_create_another_team() {
        request = new UserTeamRequestDto(this.teamName, this.setAsActive, this.fantasyManagerUsername);
        createdTeam = userTeamController.createNewUserTeam(request);
    }

    @Then("the user team with name {string} should be updated to inactive")
    public void then_the_user_team_with_name_name_should_be_updated_to_inactive(String teamName) {
        Assert.assertEquals(userTeamController.getTeamsByManager(this.fantasyManagerUsername).getBody().get(1).getName(), teamName);
        Assert.assertEquals(userTeamController.getTeamsByManager(this.fantasyManagerUsername).getBody().get(1).getIsActive(), false);
        Assert.assertEquals(userTeamController.getTeamsByManager(this.fantasyManagerUsername).getBody().get(1).getFantasyManager().getUsername(), this.fantasyManagerUsername);

        Assert.assertEquals(userTeamController.getTeamsByManager(this.fantasyManagerUsername).getBody().get(0).getName(), this.teamName);
        Assert.assertEquals(userTeamController.getTeamsByManager(this.fantasyManagerUsername).getBody().get(0).getIsActive(), this.setAsActive);
        Assert.assertEquals(userTeamController.getTeamsByManager(this.fantasyManagerUsername).getBody().get(0).getFantasyManager().getUsername(), this.fantasyManagerUsername);
    }


    // Step Definitions for Error Flow
    @Given("a user with the username {string} does not exist when creating a new user team")
    public void a_user_with_the_username_username_does_not_exist(String username) {
        Assert.assertNull(fantasyManagerRepository.findByUsername(username));
        this.fantasyManagerUsername = username;
    }

    @Then("the user team with invalid manager should not be created")
    public void the_user_team_with_invalid_manager_should_not_be_created() {
        Assert.assertNull(userTeamRepository.findByName(this.teamName));
    }
}
