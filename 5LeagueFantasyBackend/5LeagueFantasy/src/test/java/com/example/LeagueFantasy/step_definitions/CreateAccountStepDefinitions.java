package com.example.LeagueFantasy.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.example.LeagueFantasy.Entity.FantasyManager;
import com.example.LeagueFantasy.controller.FantasyManagerController;
import com.example.LeagueFantasy.dto.FantasyManagerRequestDto;
import com.example.LeagueFantasy.dto.FantasyManagerResponseDto;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.FantasyManagerRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

@SpringBootTest
public class CreateAccountStepDefinitions {

    @Autowired
    private FantasyManagerController fantasyManagerController;

    @Autowired
    FantasyManagerRepository fantasyManagerRepository;

    private String username;
    private String name;
    private String email;
    private String password;
    private FantasyManagerRequestDto request;
    private ResponseEntity<FantasyManagerResponseDto> createdAccount;

    // Step Definition for Normal Flow

    @Given("no user with the username {string} exists")
    public void no_user_with_the_username_username_exists(String username) {
        Assert.assertThrows(FiveLeagueFantasyException.class, () ->
         {fantasyManagerController.getManagerByUsername(username);
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
        Assert.assertTrue(fantasyManagerController.getManagerByUsername(this.username).getBody().getUsername().equals(this.username));
        Assert.assertTrue(fantasyManagerController.getManagerByUsername(this.username).getBody().getName().equals(this.name));
        Assert.assertTrue(fantasyManagerController.getManagerByUsername(this.username).getBody().getEmail().equals(this.email));
        Assert.assertTrue(fantasyManagerController.getManagerByUsername(this.username).getBody().getPassword().equals(this.password));
    }
    
    // Step Definition for Alternate Flow (Existing Username)

    @Given("a user with the username {string} exists")
    public void a_user_with_the_username_username_exists(String username) {
        FantasyManager existingUser = new FantasyManager(username, "name", "email", "password");
        fantasyManagerRepository.save(existingUser);
        Assert.assertTrue(fantasyManagerController.getManagerByUsername(username).getBody().getUsername().equals(username));
    }

    @When("I enter a valid but existing username {string}")
    public void i_enter_an_existing_username_username(String username) {
        this.username = username;
    }

    @And("I click on the create account button to create a new account with an existing username")
    public void i_click_on_the_create_account_button_to_create_a_new_account_with_an_existing_username() {
        request = new FantasyManagerRequestDto(this.username, this.name, this.email, this.password);
        Assert.assertThrows(FiveLeagueFantasyException.class, () ->
            {fantasyManagerController.createManager(request);
        });
    }

    @Then("the user with username {string} should not be created")
    public void the_user_with_username_username_should_not_be_created(String username) {
        Assert.assertTrue(fantasyManagerController.getManagerByUsername(this.username).getBody().getUsername().equals(this.username));
        Assert.assertTrue(fantasyManagerController.getManagerByUsername(this.username).getBody().getName().equals("name"));
        Assert.assertTrue(fantasyManagerController.getManagerByUsername(this.username).getBody().getEmail().equals("email"));
        Assert.assertTrue(fantasyManagerController.getManagerByUsername(this.username).getBody().getPassword().equals("password"));
    }

    // Step Definition for Alternate Flow (Existing Username)

    @Given("a user with the email {string} exists")
    public void a_user_with_the_email_email_exists(String email) {
        FantasyManager existingUser = new FantasyManager("username", "name", email, "password");
        fantasyManagerRepository.save(existingUser);
        Assert.assertTrue(fantasyManagerController.getManagerByEmail(email).getBody().getEmail().equals(email));
    }

    @And("I enter a valid but existing email {string}")
    public void i_enter_an_existing_email_email(String email) {
        this.email = email;
    }

   @And("I click on the create account button to create a new account with an existing email")
   public void i_click_on_the_create_account_button_to_create_a_new_account_with_an_existing_email() {
       request = new FantasyManagerRequestDto(this.username, this.name, this.email, this.password);
       Assert.assertThrows(FiveLeagueFantasyException.class, () ->
           {fantasyManagerController.createManager(request);
       });
   }

   @Then("the user with email {string} should not be created")
    public void the_user_with_email_email_should_not_be_created(String email) {
         Assert.assertTrue(fantasyManagerController.getManagerByEmail(this.email).getBody().getEmail().equals(this.email));
         Assert.assertTrue(fantasyManagerController.getManagerByEmail(this.email).getBody().getUsername().equals("username"));
         Assert.assertTrue(fantasyManagerController.getManagerByEmail(this.email).getBody().getName().equals("name"));
         Assert.assertTrue(fantasyManagerController.getManagerByEmail(this.email).getBody().getPassword().equals("password"));
    }

    // Step Definition for Error Flow (Invalid Password)
    
    @And("I click on the create account button to create a new account with an invalid password")
    public void i_click_on_the_create_account_button_to_create_a_new_account_with_an_invalid_password() {
        request = new FantasyManagerRequestDto(this.username, this.name, this.email, this.password);
        Assert.assertThrows(FiveLeagueFantasyException.class, () ->
            {fantasyManagerController.createManager(request);
        });
    }

    @Then("the user with username {string} should not be created due to invalid information.")
    public void the_user_with_username_username_should_not_be_created_due_to_invalid_information(String username) {
        Assert.assertThrows(FiveLeagueFantasyException.class, () ->
            {fantasyManagerController.getManagerByUsername(this.username);
        });
    }
}
