# Created by wasifsomji at 2024-03-17
Feature: Create a new league
  # Enter feature description here

  # Normal Flow: Successful creation of a new league
  Scenario: The user successfully creates a new league
    Given the user is authenticated and on the Create League page
    When the user enters "Summer Championship" as the league name
    And the user submits the league creation form
    Then the user should receive a confirmation message "League created successfully."

  # Alternate Flow: League creation with a name that already exists
  Scenario: Attempting to create a league with a name that already exists
    Given the user is authenticated and on the Create League page
    When the user enters "Summer Championship" as the league name
    And the league name "Summer Championship" already exists
    And the user submits the league creation form
    Then the user should see an error message "League name already exists, please choose another name."

#   Error Flow: League creation when already in a league
  Scenario: Prevent a user from joining multiple leagues
    Given a user "JohnDoe" is already a member of "Professional Europa League"
    When a user "JohnDoe" tries to create a new league "Professional Chess League"
    Then "JohnDoe" should see an error message saying "You are already in a league."
