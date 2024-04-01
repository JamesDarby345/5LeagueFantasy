Feature: Retrieving Leagues by Name

  Scenario Outline: Retrieving leagues by name
    Given that there is a league name "<name>" in the system
    When a user requests to retrieve leagues by name "<name>"
    Then the system should return a list of leagues with the given "<name>"

    Examples:
      | name     |
      | SUIIIIII |
      | The boys |
      | G's      |
