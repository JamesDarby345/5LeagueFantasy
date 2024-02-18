package com.example.LeagueFantasy.step_definitions;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.LeagueFantasy.Entity.FantasyManager;
import com.example.LeagueFantasy.controller.FantasyManagerController;
import com.example.LeagueFantasy.dto.FantasyManagerResponseDto;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.FantasyManagerRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest
public class LoginToAccountStepDefinitions {

    @Autowired
    private FantasyManagerController fantasyManagerController;

    @Autowired
    FantasyManagerRepository fantasyManagerRepository;

    private String username;
    private String password;
    private ResponseEntity<FantasyManagerResponseDto> loggedInAccount;
    FiveLeagueFantasyException fiveLeagueFantasyException;

    // Step Definition for Normal Flow

    @Given("a user with the username {string} and password {string} exists")
    public void i_have_an_account_with_the_username_username(String username, String password) {
        FantasyManager existingUser = new FantasyManager(username, "name", "email", password);
        fantasyManagerRepository.save(existingUser);
    }

    @When("I enter the valid username {string}")
    public void i_enter_a_correct_username_username(String username) {
        this.username = username;
    }

    @When("the correct, corresponding password {string}")
    public void i_enter_a_correct_password_password(String password) {
        this.password = password;
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        loggedInAccount = fantasyManagerController.loginManager(username, password);
    }

    @Then("I am redirected to the main dashboard for the associated account")
    public void i_am_redirected_to_the_main_dashboard_for_the_associated_account() {
        assert(loggedInAccount.getStatusCode() == HttpStatus.OK);
    }

    // Step Definition for Alternate Flow (Incorrect Password)

    @And("the incorrect password {string}")
    public void i_enter_an_incorrect_password_password(String password) {
        this.password = password;
    }

    @And("I click on the login button with an incorrect provided password")
    public void i_click_on_the_login_button_with_an_incorrect_provided_password() {
        fiveLeagueFantasyException = Assert.assertThrows(FiveLeagueFantasyException.class, () -> {
            fantasyManagerController.loginManager(this.username,this.password);
        });

        Assert.assertTrue(fiveLeagueFantasyException.getStatus() == HttpStatus.UNAUTHORIZED);
        Assert.assertTrue(fiveLeagueFantasyException.getMessage().equals("Incorrect password."));
    }

    @Then("I am informed that password is incorrect")
    public void i_am_informed_that_password_is_incorrect() {
        Assert.assertTrue(fiveLeagueFantasyException.getStatus() == HttpStatus.UNAUTHORIZED);
        Assert.assertTrue(fiveLeagueFantasyException.getMessage().equals("Incorrect password."));
    }

    // Step Definition for Alternate Flow (Non-existent Username)

    @Given("a user with the username {string} does not exist")
    public void a_user_with_the_username_username_does_not_exist(String username) {
        // This step definition does nothing. No need to save user to database
    }

    @And("I click on the login button with a non-existent username")
    public void i_click_on_the_login_button_with_a_non_existent_username() {
        fiveLeagueFantasyException = Assert.assertThrows(FiveLeagueFantasyException.class, () -> {
            fantasyManagerController.loginManager(this.username,this.password);
        });
    }

    @Then("I am informed that username does not exist")
    public void i_am_informed_that_username_does_not_exist() {
        Assert.assertTrue(fiveLeagueFantasyException.getStatus() == HttpStatus.NOT_FOUND);
        Assert.assertTrue(fiveLeagueFantasyException.getMessage().equals("User with username " + this.username + " does not exist."));
    }
}
