Feature: Create a new user team 

    Scenario: Successfully create a new user team (Normal Flow)
        Given a user with the username "kdach" exists when creating a new user team
        When I enter a valid user team name "MyNewTeamWeek1"
        And set it to active
        And I click on the create team button
        Then the user team with name "MyNewTeamWeek1" should be created
        
     Scenario: Name of new user team is empty (Alternate Flow)
        Given a user with the username "kdach" exists when creating a new user team
        When I enter an empty user team name ""
        And set it to active
        And I click on the create team button to try and create an invalid team
        Then the user team with an empty name should not be created

    Scenario: Manager already has another team that must be set to inactive (Alternate Flow)
        Given a user with the username "kdach" exists when creating a new user team
        And the user has already created a team with the name "ExistingTeam" and status active
        When I enter a valid user team name "NewTeamNewWeek"
        And set it to active
        And I click on the create team button to create another team
        Then the user team with name "ExistingTeam" should be updated to inactive

    Scenario: User requesting new user team does not exist (Error Flow)
        Given a user with the username "rorymcilroy" does not exist when creating a new user team
        When I enter a valid user team name "RorysTeams5"
        And set it to active
        And I click on the create team button to try and create an invalid team
        Then the user team with invalid manager should not be created

