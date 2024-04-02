Feature: Updating Fantasy Manager League

  Scenario Outline: Updating fantasy manager's league
    Given a fantasy manager with username "<username>" exists in the system
    And a league with name "<leagueName>" exists in the system
    When the user "<username>" requests to update their league to "<leagueName>"
    Then the fantasy manager should have the updated league "<leagueName>"

    Examples:
      | username          | leagueName     |
      | user123           | Oiiiii         |
      | PepGuardiola      | The bois       |
      | MegaMindAncelotti | The bois       |
