Feature: Login to existing account

    Scenario: Successful login (Normal Flow)
        Given a user with the username "abhiprav" and password "password123" exists
        When I enter the valid username "abhiprav"
        And the correct, corresponding password "password123"
        And I click on the login button
        Then I am redirected to the main dashboard for the associated account

    Scenario: Unsuccessful login due to incorrect password (Alternate Flow)
        Given a user with the username "abhiprav" and password "password123" exists
        When I enter the valid username "abhiprav"
        And the incorrect password "password987654321"
        And I click on the login button with an incorrect provided password
        Then I am informed that password is incorrect

    Scenario: Non-existing username unsuccessful login (Error Flow)
        Given a user with the username "oopmaLoompa" does not exist
        When I enter the valid username "oopmaLoompa"
        And the correct, corresponding password "chocolateFactory123"
        And I click on the login button with a non-existent username
        Then I am informed that username does not exist

