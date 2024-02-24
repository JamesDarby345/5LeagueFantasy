package com.example.LeagueFantasy.step_definitions;

import com.example.LeagueFantasy.controller.FantasyManagerController;
import com.example.LeagueFantasy.dto.FantasyManagerRequestDto;
import com.example.LeagueFantasy.dto.FantasyManagerResponseDto;
import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.FantasyManagerRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class CreateAccountStepDefinitions {

  @Autowired FantasyManagerRepository fantasyManagerRepository;
  @Autowired private FantasyManagerController fantasyManagerController;
  private String username;
  private String name;
  private String email;
  private String password;
  private FantasyManagerRequestDto request;
  private ResponseEntity<FantasyManagerResponseDto> createdAccount;

  // Step Definition for Normal Flow

  @Given("no user with the username {string} exists")
  public void no_user_with_the_username_username_exists(String username) {
    Assert.assertThrows(
        FiveLeagueFantasyException.class,
        () -> {
          fantasyManagerController.getManagerByUsername(username);
        });
  }

  @When("I enter a valid username {string}")
  public void i_enter_a_valid_username_username(String username) {
    this.username = username;
  }

  @And("I enter a valid name {string}")
  public void i_enter_a_valid_name_name(String name) {
    this.name = name;
  }

  @And("I enter a valid email {string}")
  public void i_enter_a_valid_email_email(String email) {
    this.email = email;
  }

  @And("I enter a valid password {string}")
  public void i_enter_a_valid_password_password(String password) {
    this.password = password;
  }

  @And("I click on the create account button")
  public void and_i_click_on_the_create_account_button() {
    request = new FantasyManagerRequestDto(this.username, this.name, this.email, this.password);
    createdAccount = fantasyManagerController.createManager(request);
  }

  @Then("the user with username {string} should be created")
  public void the_user_with_username_username_should_be_created(String username) {
    Assert.assertEquals(
        fantasyManagerController.getManagerByUsername(this.username).getBody().getUsername(),
        this.username);
    Assert.assertEquals(
        fantasyManagerController.getManagerByUsername(this.username).getBody().getName(),
        this.name);
    Assert.assertEquals(
        fantasyManagerController.getManagerByUsername(this.username).getBody().getEmail(),
        this.email);
    Assert.assertEquals(
        fantasyManagerController.getManagerByUsername(this.username).getBody().getPassword(),
        this.password);
  }

  // Step Definition for Alternate Flow (Existing Username)

  @Given("a user with the username {string} exists")
  public void a_user_with_the_username_username_exists(String username) {
    FantasyManager existingUser = new FantasyManager(username, "name", "email", "password");
    fantasyManagerRepository.save(existingUser);
    Assert.assertEquals(
        fantasyManagerController.getManagerByUsername(username).getBody().getUsername(), username);
  }

  @When("I enter a valid but existing username {string}")
  public void i_enter_an_existing_username_username(String username) {
    this.username = username;
  }

  @And("I click on the create account button to create a new account with an existing username")
  public void
      i_click_on_the_create_account_button_to_create_a_new_account_with_an_existing_username() {
    request = new FantasyManagerRequestDto(this.username, this.name, this.email, this.password);
    Assert.assertThrows(
        FiveLeagueFantasyException.class,
        () -> {
          fantasyManagerController.createManager(request);
        });
  }

  @Then("the user with username {string} should not be created")
  public void the_user_with_username_username_should_not_be_created(String username) {
    Assert.assertEquals(
        fantasyManagerController.getManagerByUsername(this.username).getBody().getUsername(),
        this.username);
    Assert.assertEquals(
        "name", fantasyManagerController.getManagerByUsername(this.username).getBody().getName());
    Assert.assertEquals(
        "email", fantasyManagerController.getManagerByUsername(this.username).getBody().getEmail());
    Assert.assertEquals(
        "password",
        fantasyManagerController.getManagerByUsername(this.username).getBody().getPassword());
  }

  // Step Definition for Alternate Flow (Existing Username)

  @Given("a user with the email {string} exists")
  public void a_user_with_the_email_email_exists(String email) {
    FantasyManager existingUser = new FantasyManager("username", "name", email, "password");
    fantasyManagerRepository.save(existingUser);
    Assert.assertEquals(
        fantasyManagerController.getManagerByEmail(email).getBody().getEmail(), email);
  }

  @And("I enter a valid but existing email {string}")
  public void i_enter_an_existing_email_email(String email) {
    this.email = email;
  }

  @And("I click on the create account button to create a new account with an existing email")
  public void
      i_click_on_the_create_account_button_to_create_a_new_account_with_an_existing_email() {
    request = new FantasyManagerRequestDto(this.username, this.name, this.email, this.password);
    Assert.assertThrows(
        FiveLeagueFantasyException.class,
        () -> {
          fantasyManagerController.createManager(request);
        });
  }

  @Then("the user with email {string} should not be created")
  public void the_user_with_email_email_should_not_be_created(String email) {
    Assert.assertEquals(
        fantasyManagerController.getManagerByEmail(this.email).getBody().getEmail(), this.email);
    Assert.assertEquals(
        "username", fantasyManagerController.getManagerByEmail(this.email).getBody().getUsername());
    Assert.assertEquals(
        "name", fantasyManagerController.getManagerByEmail(this.email).getBody().getName());
    Assert.assertEquals(
        "password", fantasyManagerController.getManagerByEmail(this.email).getBody().getPassword());
  }

  // Step Definition for Error Flow (Invalid Password)

  @And("I click on the create account button to create a new account with an invalid password")
  public void
      i_click_on_the_create_account_button_to_create_a_new_account_with_an_invalid_password() {
    request = new FantasyManagerRequestDto(this.username, this.name, this.email, this.password);
    Assert.assertThrows(
        FiveLeagueFantasyException.class,
        () -> {
          fantasyManagerController.createManager(request);
        });
  }

  @Then("the user with username {string} should not be created due to invalid information.")
  public void the_user_with_username_username_should_not_be_created_due_to_invalid_information(
      String username) {
    Assert.assertThrows(
        FiveLeagueFantasyException.class,
        () -> {
          fantasyManagerController.getManagerByUsername(this.username);
        });
  }
}
