# Created by wasifsomji at 2024-02-19
Feature: # Enter feature name here
  # Enter feature description here

  Scenario: Sort Players by Descending Order of Assists
  Given there exists in the system players "John Doe" with 5 assists and "Jane Smith" with 4 assists
  When I sort players by descending order of assists
  Then I am shown a list that has "John Doe" ranked higher than "Jane Smith"

  Scenario: Sort Players by Ascending Order of Assists
    Given there exists in the system players "John Doe" with 5 assists and "Jane Smith" with 4 assists
    When I sort players by ascending order of assists
    Then I am shown a list that has "Jane Smith" ranked higher than "John Doe"

  Scenario: No players in the system
    Given no player is in the system
    When I query by ascending order of assists
    Then I am shown a prompt indicating no results were found
    # Enter steps here