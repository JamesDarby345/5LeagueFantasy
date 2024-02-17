Feature: Create a new user account

    Scenario: Successfully create a new account (Normal Flow)
        Given no user with the username "cvatos" exists
        When I enter a valid username "cvatos"
        And I enter a valid name "Chris Vatos"
        And I enter a valid email "test@gmail.com"
        And I enter a valid password "test1235678"
        And I click on the create account button
        Then the user with username "cvatos" should be created

    Scenario: Username already registered to an account (Alternate Flow)
        Given a user with the username "abhiprav" exists
        When I enter a valid but existing username "abhiprav"
        And I enter a valid name "Abhi Praveen"
        And I enter a valid email "newEmail@gmail.com"
        And I enter a valid password "newPassword"
        And I click on the create account button to create a new account with an existing username
        Then the user with username "abhiprav" should not be created

    Scenario: Email already registered to an account (Alternate Flow)
        Given a user with the email "tom@gmail.com" exists
        When I enter a valid username "tomJones"
        And I enter a valid name "Tom Jones"
        And I enter a valid but existing email "tom@gmail.com"
        And I enter a valid password "otherPassword"
        And I click on the create account button to create a new account with an existing email
        Then the user with email "tom@gmail.com" should not be created

    Scenario: Provided password less than 8 characters (Error Flow)
        Given no user with the username "jerrySoccer" exists
        When I enter a valid username "jerrySoccer"
        And I enter a valid name "Jerry Seinfeld"
        And I enter a valid email "jerry@gmail.com"
        And I enter a valid password "invalid"
        And I click on the create account button to create a new account with an invalid password
        Then the user with username "jerrySoccer" should not be created due to invalid information.
