Feature: Query forwards by league
  Scenario: Display all forwards in league
    Given that there is a league "PremierLeague" containing players
    When I select the "PremierLeague"
    Then I am shown a list of all players in the league that includes "Haaland"
