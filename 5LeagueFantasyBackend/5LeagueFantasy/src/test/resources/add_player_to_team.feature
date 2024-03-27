Feature: Add Player to User Team

    Scenario: Successfully add a forward to an existing user team (Normal Flow)
        Given I have an existing user team with team name "MyAwesomeTeam" with 1 forward named "Joe Fresh"
        When I add a forward named "Eden Hazard" to the user team
        Then the user team should have 2 players total

    Scenario: Successfully add a goalkeeper to an existing user team (Normal Flow)
        Given I have an existing user team with team name "MyAwesomeTeam" with 1 forward named "Joe Fresh"
        When I add a goalkeeper named "Carey Price" to the user team
        Then the user team should have 2 players total, with one goalkeeper and one forward

    Scenario: Attempt to add a goalkeeper to a team already with one (Alternate Flow)
        Given I have an existing user team with team name "MyAwesomeTeam" with 1 goalkeeper named "Lebron James"
        When I add another goalkeeper to the user team
        Then the attempt should not be successful and theres should only be 1 goalkeeper on the team

    Scenario: Attempt to add a forward to a team already with five  (Alternate Flow)
        Given I have an existing user team with team name "MyAwesomeTeam" with 5 forwards
        When I add another forward to the user team
        Then the attempt should not be successful and there should still be 5 forwards on the team

    Scenario: Forward already exists on team (Error Flow)
        Given I have an existing user team with team name "MyAwesomeTeam" with 1 forward named "Sidney Crosby"
        When I add a forward named "Sidney Crosby" to the user team already with that player
        Then the attempt should not be successful and there should still be 1 forward on the team
