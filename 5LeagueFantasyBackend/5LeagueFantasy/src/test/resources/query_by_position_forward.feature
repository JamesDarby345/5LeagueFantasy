Feature: Query Players by Position

    In order to accurately find players for team selection
    As a user of the League Fantasy system
    I want to be able to search for players by their specific position or a partial match of their position

    Scenario: Normal flow - Exact player position
        Given that there is a position "Midfielder" in the system containing players
        When I query players by position "Midfielder"
        Then I am shown a list of players in that position that includes "Ngolo Kante"

    Scenario: Alternate flow - No matches
        Given that there are no players in the system playing as "Defender"
        When I query players by position "Defender"
        Then I am prompted that no results were found

    Scenario: Error flow - Position not found
        Given that there is no "Goalpost" position in the system
        When I query players by position "Goalpost"
        Then I am shown a no position found error
