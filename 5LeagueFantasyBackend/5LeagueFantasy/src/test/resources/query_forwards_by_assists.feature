# Created by wasifsomji at 2024-02-19
Feature: Sort forwards by assists
  # Enter feature description here

  Scenario: Sort Players by Descending Order of Assists
  Given there exists in the system players "John Doe" with 5 assists and "Jane Smith" with 4 assists
  When I sort players by descending order of assists
  Then I am shown a descending assists list that has "John Doe" ranked higher than "Jane Smith"

  Scenario: Sort Players by Ascending Order of Assists
    Given there exists in the system players "John Doe" with 5 assists and "Jane Smith" with 4 assists
    When I sort players by ascending order of assists
    Then I am shown a ascending assists list that has "Jane Smith" ranked higher than "John Doe"

  Scenario: No players in the system
    Given no player is in the system assists
    When I query by ascending order of assists
    Then I am shown a prompt indicating no results for ascending assists were found
    # Enter steps here