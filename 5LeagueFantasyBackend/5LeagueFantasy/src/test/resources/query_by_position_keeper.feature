Feature: Query Goalkeepers by Position

    In order to find goalkeepers for team selection
    As a user of the League Fantasy system
    I want to be able to search for goalkeepers by their position

    Scenario Outline: Querying for a specific goalkeeper in the system
        Given that there is a keeper "<keeper_name>" in the system
        When I query keeper "<query>"
        Then I am shown a list of all goalkeepers

        Examples:
            | keeper_name       | query      |
            | Lev Yashine       | Goalkeeper |
            | Mike Maignan      | Goalkeeper |
            | Kepa Arrizabalaga | Goalkeeper |
