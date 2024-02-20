# Created by wasifsomji at 2024-02-19
Feature: Sort forwards by goals
  # Enter feature description here

  Scenario: Sort Players by Descending Order of Goals
  Given there exists in the system players "John Doe" with 5 goals and "Jane Smith" with 4 goals
  When I sort players by descending order of goals
  Then I am shown a descending goals list that has "John Doe" ranked higher than "Jane Smith"

  Scenario: Sort Players By Ascending Order of Goals
    Given there exists in the system players "John Doe" with 5 goals and "Jane Smith" with 4 goals
    When I sort players by ascending order of goals
    Then I am shown a ascending goals list that has "Jane Smith" ranked higher than "John Doe"

  Scenario: No players in the system
  Given no player is in the system goals
  When I query by ascending order of goals
  Then I am shown a prompt indicating no results for ascending goals were found




    # add more test cases for querying