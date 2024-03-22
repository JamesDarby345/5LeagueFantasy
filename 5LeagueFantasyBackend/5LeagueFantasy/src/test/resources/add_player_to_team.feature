Feature: Add Player to User Team

    Scenario: Successfully add a forward to an existing user team
        Given I have an existing user team with team name "MyAwesomeTeam" with 1 forward named "Joe Fresh"
        When I add a forward named "Eden Hazard" to the user team
        Then the user team should have 2 players total

    Scenario: Successfully add a goalkeeper to an existing user team
        Given I have an existing user team with team name "MyAwesomeTeam" with 1 forward named "Joe Fresh"
        When I add a goalkeeper named "Carey Price" to the user team
        Then the user team should have 2 players total, with one goalkeeper and one forward

    Scenario: Attempt to add a goalkeeper to a team already with one
        Given I have an existing user team with team name "MyAwesomeTeam" with 1 goalkeeper named "Lebron James"
        When I add another goalkeeper to the user team
        Then the attempt should not be successful and theres should only be 1 goalkeeper on the team

    