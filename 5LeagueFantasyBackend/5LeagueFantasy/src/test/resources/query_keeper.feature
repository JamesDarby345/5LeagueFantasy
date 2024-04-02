Feature: Query Goalkeepers by Clean Sheets or Name

    In order to find top-performing goalkeepers or specific goalkeepers for my team
    As a user of the Goalkeeper Stats system
    I want to be able to search for goalkeepers based on their number of clean sheets or by their name

    Scenario: Querying goalkeepers by an exact number of clean sheets
        Given a goalkeeper named "Peter Save" has "10" clean sheets in the system
        When I query goalkeepers by clean sheets "10"
        Then I am shown a list including "Peter Save"

    Scenario Outline: Querying goalkeepers by a range of clean sheets
        Given goalkeepers "John Jones" and "Bill Murray" with varying numbers of clean sheets in the system
        When I query goalkeepers by clean sheets with a range '10' and '30'
        Then I am shown a list of goalkeepers within that range


    Scenario: Querying a goalkeeper by name
        Given a goalkeeper named "Peter Save" exists in the system
        When I query goalkeepers by name "Peter Save"
        Then I am shown "Peter Save" with his details including clean sheets


