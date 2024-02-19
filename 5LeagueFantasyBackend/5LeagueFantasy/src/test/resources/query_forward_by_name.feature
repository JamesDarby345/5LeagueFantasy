Feature: Query forward by name

  Scenario: Exact player name
    Given that there is a player named "Lionel Messi" in the system
    When I query "Lionel Messi"
    Then I am shown a list of players that includes "Lionel Messi"

  Scenario: Partial match
    Given that there are players named "Harry Kane" and "Harry Maguire" in the system
    When I query "Harry"
    Then I am shown a list of players that includes "Harry Kane" and "Harry Maguire"

  Scenario: Player not found
    Given that the list of players in the system does not include any player whose name contains "Yassine Mimet"
    When I query "Yassine Mimet"
    Then I am shown a prompt that indicates that no results were found
