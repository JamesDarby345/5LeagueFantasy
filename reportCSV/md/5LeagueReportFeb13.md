# Met Definition of Done Stories
### Name: Implement the data model
  - **Owner**: chrisovalantis.vatos@mail.mcgill.ca;yassine.mimet@mail.mcgill.ca
  - **Description**: Set up project tech stack and implement data model agreed upon on the meeting of 02/10/2024!
  - **Tasks**:
    - [X] Set up frontend with react and typescript
    - [X] Set up backend using spring initializer
    - [X] Implement data model using JPA annotations
### Name: Find the API's
  - **Owner**: abhigyan.praveen@mail.mcgill.ca
  - **Description**: Find a free API for each of the 5 leagues

Leagues Involved: England, Spain, Italy, Germany, France (top leagues).
  - **Tasks**:
    - No tasks specified
### Name: Create the Repositories
  - **Owner**: chrisovalantis.vatos@mail.mcgill.ca;yassine.mimet@mail.mcgill.ca
  - **Description**: Create the repositories using Crud interface. WIth the model complete, next step is to create the repository layer to make accessing data easier. Follow steps as per the following link: https://spring.io/guides/gs/accessing-data-jpa, and in particular the "Create Simple Queries" section.
  - **Tasks**:
    - [X] Create repository layer for each table/entity in the database.
    - [X] For each repository, focus on the main CRUD (Create, Read, Update, Delete) operations

# In Progress Stories
### Name: Query players by name
  - **Owner**: jerry.hou-liu@mail.mcgill.ca
  - **Description**: User story: As a user, I want to be able to search for players using their names so that I can view their information and add them to a team

Acceptance criteria: 
- Entering a player name or a portion thereof allows the user to see players that match the name entered
- Normal flow: Entering the exact name of a player causes all players with that name to be displayed
- Alternate flow: Entering a portion of a player's name causes all players whose names include the string to be displayed
- Error flow: Entering a string that is not an exact match or substring of the name of any player in the system causes the user to be notified that no results were found

Gherkin:
```
# Normal flow: Exact player name
Given that there is a player named "John Doe" in the system
When I query "John Doe"
Then I am shown a list of players that includes "John Doe"

# Alternate flow: Partial match
Given that there is are players named "John Doe" and "John Roe" in the system
When I query "John"
Then I am shown a list of players that includes "John Doe" and "John Roe"

# Error flow: Player not found
Given that the list of players in the system does not include any player whose name contains "John Doe"
When I query "John Doe"
Then I am shown a prompt that indicates that no results were found
````
  - **Tasks**:
    - [ ] Backend: Create API that returns a list of players when queried with a player name
    - [ ] Frontend: Create webpage that is able to display players that meet certain querying criteria
    - [ ] Frontend: Add search box to the player display webpage that allows the user to use the query-by-name API
    - [ ] Testing: Create Gherkin acceptance tests for query-by-name functionality
### Name: Test Repository Layer
  - **Owner**: rashya.anggaraksa@mail.mcgill.ca
  - **Description**: Test the repository layer to ensure the correct functionality of the needed search queries using JUnit.
  - **Tasks**:
    - [ ] Test League repo
    - [ ] Test goalkeeper repo
    - [ ] Test PlayerToTeam repo
    - [ ] Test Forward repo
    - [ ] Test UserTeam repo
    - [ ] Test FantasyManager repo
### Name: Delete Account
  - **Owner**: tran.t.phan@mail.mcgill.ca
  - **Description**: User story: As a user, I want to be able to delete an account so that I can permanently remove my personal information.

Acceptance Criteria:
Normal Flow: The user deletes it’s own account
User is asked if they confirm their account deletion after delete button
User is asked to confirm their identity through their password or through a verification link sent to their email.
User enter the password
User presented message indicating successful account deletion
User is redirected to the login page

Error Flow: The user entered wrong password, and deletion process canceled 
User entered incorrect password
System inform user about wrong password
System cancel account deletion

Gherkin:
Feature: Delete Account

Background: 
Given the user is has an account
And the user pressed delete button

Scenario: The user deletes it’s own account. (Normal flow)
Given the deletion confirmation email is sent to the user
When the user confirms deletion in the email
And the user enter password
Then the account is deleted

Scenario:  The user entered wrong password, and deletion process canceled (Error Flow)
Given the deletion confirmation email is sent to the user
When the user confirms deletion in the email
And the user enter  wrong password
Then the system show wrong password message
And  the system cancels the deletion
  - **Tasks**:
    - [ ] Implement backend logic that allows a user to completely remove account from system. Design and develop frontend UI with confirmation that allows user to trigger existing  api endpoint and delete a existing account.
    - [ ] Deploy the account deletion feature to the production environment.
    - [ ] Document the account deletion process
    - [ ] Create API endpoint that allows complete account deletion.
    - [ ] Write Gherkin acceptance tests for delete account functionality
### Name: Login as User
  - **Owner**: abhigyan.praveen@mail.mcgill.ca
  - **Description**: Login as a User

User Story: As a user, I want to be able to login so that I can access the 5 League Fantasy dashboard.

Acceptance Criteria:
Normal Flow: 
Login requires a correct, unique username and a correct password
Successful login attempts redirect to main dashboard



Error Flow: 
Unsuccessful login attempts inform the user the username or password was incorrect and allow the user to re-enter them, while maintaining the entered username in the username field

Gherkin: **link to feature file in the future**
Feature: Login to User Account

Scenario: Successful login (Normal Flow)
Given I enter a correct username 
And the correct, corresponding password
When I login
Then I am redirected to the main dashboard for the associated account

Scenario: Incorrect Password Login (Error Flow)
Given I enter a correct username
And an incorrect password
When I login
Then I am informed that the username or password was incorrect 
And I can re-enter them
And the submitted username is still in the username field

Scenario: Non-existing username unsuccessful login (Error Flow)
Given I enter an  incorrect/non-existent username
And a  password
When I login
Then I am informed that the username or password was incorrect 
And I can re-enter them
  - **Tasks**:
    - [ ] Design and develop frontend UI that allows user to trigger api endpoint and login to their existing account
    - [ ] Implement backend logic/endpoint to allow users to login
    - [ ] Write Gherkin acceptance tests for login functionality
### Name: Query players by position
  - **Owner**: alikasanovitch@gmail.com
  - **Description**: Acceptance Criteria:

As a user, I want to be able to search for players based on their positions so that I can easily find players suitable for specific roles.
Entering a player's position allows the user to see players that match the position entered.
Normal flow: Entering the exact position of a player causes all players with that position to be displayed.
Alternate flow: Entering a portion of a player's position causes all players whose positions include the string to be displayed.
Error flow: Entering a string that is not an exact match or substring of any player's position in the system causes the user to be notified that no results were found.
Gherkin
# Normal flow: Exact player position
Given that there is a player named "John Doe" playing as "Goalkeeper" in the system
When I query players by position "Goalkeeper"
Then I am shown a list of players that includes "John Doe"

# Alternate flow: Partial match
Given that there are players named "John Doe" playing as "Goalkeeper" and "Jane Smith" playing as "Midfielder" in the system
When I query players by position "Goal"
Then I am shown a list of players that includes "John Doe" and "Jane Smith"

# Error flow: Position not found
Given that there are no players in the system playing as "Defender"
When I query players by position "Defender"
Then I am shown a prompt that indicates that no results were found
  - **Tasks**:
    - [ ] Testing: Create Gherkin acceptance tests for query-by-position functionalilty
    - [ ] Backend: Implement API call that returns a list of players when queried with a certain position
    - [ ] Frontend: Extend the UI that displays all the players that meet the query criteria
### Name: Query players by goals scored
  - **Owner**: wasifsomji@gmail.com
  - **Description**: Acceptance Criteria:
Entering a certain number of goals scored allows the user to see players that have scored that amount of goals.

Normal flow: Entering the exact number of goals scored causes all players who have scored that exact amount of goals to be outputted.
Alternate flow: Entering a range or upper/lower bound will output all the players whose number of goals fit in that range.
Error flow: Entering a number of goals that is not an exact match to anyone’s number of goals causes the user to be notified that no results were found.

Gherkin:
Normal flow: Exact number of goals
Given a player named "John Doe" has scored "5" goals in the system
When I query players by goals scored "5"
Then I am shown a list including "John Doe"

Alternate flow: Range of goals
Given players "John Doe" with "5" goals and "Jane Smith" with "4" goals
When I query players by goals scored ">3"
Then I am shown a list including both "John Doe" and "Jane Smith"

Error flow: No matching goals
Given no player has scored "10" goals
When I query by goals scored "10", 
Then I am shown a prompt indicating no results were found
  - **Tasks**:
    - [ ] Design and develop frontend UI that allows user to trigger api endpoint and get/query the list of players
    - [ ] Implement backend logic/endpoint that allows a user to get/query players by number of goals
    - [ ] Write Gherkin acceptance tests for getting/querying players functionality
### Name: Set points for players from game actions
  - **Owner**: guessous.haroun12@gmail.com
  - **Description**: 15. Set points for players from game actions
Acceptance Criteria:
As a user, I want to be able to set points for players based on their game actions so that their performance can be accurately reflected.
Entering the player's name and the points earned allows the user to update the player's points.
Normal flow: Entering the exact name of a player and the points earned updates the player's points accordingly.
Error flow: Entering a string that is not an exact match of any player's name in the system causes the user to be notified that the player was not found.
Gherkin : 
# Normal flow: Exact player name
Given that there is a player named "John Doe" in the system
When I set 10 points for player "John Doe"
Then the points for player "John Doe" are updated to 10

# Error flow: Player not found
Given that the list of players in the system does not include any player named "John Doe"
When I set 10 points for player "John Doe"
Then I am shown a prompt that indicates that the player was not found
  - **Tasks**:
    - No tasks specified
### Name: Join a league by search
  - **Owner**: yassine.mimet@mail.mcgill.ca
  - **Description**: User Story: As a user, I want to be able to join a 5 league fantasy by searching for the league.
Acceptance criteria:
Joining a league requires a valid invitation link, or a valid league name
Normal  flow: User sends a request to join to the owner of the league after searching for the league name
Error flow: User unable to join (no league found)
Gherkin:
Scenario 1: Joining a league by league name search
Given the user searches for a league by its name
When the user sends a join request to the owner
	Then the request should be sent to the owner 
And the user’s status for this league should be pending

Scenario 2: Unable to join the league
Given the user searches for a non-existent league name
Then the user should receive an error message indicating no league was found
  - **Tasks**:
    - [ ] Implement backend logic/endpoint that allows a user to join a league by search
    - [ ] Design and develop frontend UI that allows user to trigger api endpoint and allow the user to join a league by search
    - [ ] Write Gherkin acceptance tests for join league by search
### Name: Create Account
  - **Owner**: chrisovalantis.vatos@mail.mcgill.ca
  - **Description**: **User Story** : As a user, I want to be able to create an account, so that I can use the 5 League Fantasy web app.

**Acceptance Criteria:** 
Normal Flow:  User creates an account successfully
- User provides valid and non-registered email address and password
- User presented message indicating successful account creation
- User is redirected to the login page


Alternate Flow: User email already registered to an account
- Account creation with a registered email prompts user that account with provided email already exists
- User is asked if they’d like to be redirected to login page
- If user opts to be redirected, they are redirected to the login page
- If the user opts not to be redirected, they can provide another email to create an account with

Error Flow: User enters invalid information
- User is presented with clear instructions on how to correct any invalid input information
- User can correct input based on error message and try account creation again

**Gherkin:**
Feature: Create User Account

Background: 
Given the user is on the Create Account page
And the email field requires a valid email
And the username field requires at least seven characters
And the password field requires at least one capital letter, one number, and one special character

Scenario: User creates an account successfully (Normal Flow)
Given the user provides an email not already in the system 
When the user provides a valid username and a valid password
And requests for a new account to be made 
Then the system shall successfully create the user's account
And the system shall redirect the user to the login page

Scenario: User email already registered to an account (Alternate Flow)
Given there is an existing user with the provided email
When the user enters the pre-existing email
And provides a valid username and a valid password
And requests for a new account to be made 
Then the user should see a message indicating that an account with the provided email already exists

Scenario: User enters invalid information (Error Flow)
Given the user provides an email not already in the system 
When the user provides a password with no special characters
And provides a valid username
And requests for a new account to be made 
Then no new account should be created 
And the user should see an error message indicating the password must be revised
  - **Tasks**:
    - [ ] Write Gherkin acceptance tests for create account functionality
    - [ ] Design and develop frontend UI that allows user to trigger api endpoint and create a new non-existing account
    - [ ] Implement backend logic/endpoint that allows a user to create a new account with valid information

# Sprint Backlog (Refined) Stories
### Name: Edit account information
  - **Owner**: tran.t.phan@mail.mcgill.ca
  - **Description**: User story: As a user, I want to be able to edit my account information so that I can manage personal information

Acceptance Criteria:
Normal Flow: The user changes their username
The username is successfully changed
The user is shown the confirmation window

Alternate Flow: The user changes the email address
The system sends the confirmation email to the new email address
The user confirms the email change through the new email address
The system notifies the user about the successful email change through the old email


Alternate Flow: The user changes the password
The user provides an old password
The user is prompted to  enter the new password twice
The system notifies the user about the successful password change through email


Error Flow: The user attempted to change email to already registered email
The system notifies the user that the email is already in use


Error Flow: The user attempted to change the username to an already existent name
The system notifies the user that the username is already in use


Error Flow: The user attempted to change to change to non-compliant password
The user provides an old password
The user is prompted to enter the new password twice
The system notifies the user about the non-compliant password
  - **Tasks**:
    - [ ] Design and develop frontend UI with an endpoint that allows user to trigger api endpoint and edit a password/username/email of an existing account. Create a  frontend UI confirmation dialog to prevent accidental account edit
    - [ ] Write Gherkin acceptance tests for account edit functionality
    - [ ] Document the account edit process
    - [ ] Deploy the account edit feature to the production environment.
    - [ ] Implement backend logic that allows a user to edit  password/username/email account in the system.
### Name: Join a league by invitation
  - **Owner**: yassine.mimet@mail.mcgill.ca
  - **Description**: User Story: As a user, I want to be able to join a 5 league fantasy by invitation or by searching for the league.

Acceptance criteria:
Joining a league requires a valid invitation link

Normal Flow: Joining a league by invitation 
Valid invite sent to user
User joins a league after accepting the invitation

Alternate Flow: Declining a league invitation
User sends a request to join to the owner of the league after searching for the league name
Owner of the league receives the notification of user who wants to join
Owner sends the invite

Error Flow: Unable to join the league
Invitation link is expired
User is unable to join 

Gherkin:
Feature: Join a league by invitation

Scenario: Joining a league by invitation (Normal Flow)
Given a valid invitation link is provided
When the user accepts the invite
Then the user should be a member of the league
And the user should be redirected to the main page

Scenario: Declining a league invitation (Alternate Flow)
Given a valid invitation link is provided
When the user declines the invite
Then the user should be redirected to the main page
	
Scenario: Unable to join the league (Error Flow)
Given the user attempts to join using an expired invitation link
Then the user should receive an error page indicating that the link has expired
  - **Tasks**:
    - [ ] Write Gherkin acceptance tests for join league by invitation
    - [ ] Implement backend logic/endpoint that allows a user to join a league by invitation
    - [ ] Design and develop frontend UI that allows user to trigger api endpoint and allow the user to joina league by invitation
### Name: Query players by assists
  - **Owner**: alikasanovitch@gmail.com
  - **Description**: nan
  - **Tasks**:
    - [ ] Backend: Implement API call that returns a list of players when queried with a number of assists
    - [ ] Testing: Create Gherkin acceptance tests for query-by-assists functionalilty
    - [ ] Frontend: Extend the UI that displays all the players that meet the query criteria
### Name: Query players by saves (gk only)
  - **Owner**: alikasanovitch@gmail.com
  - **Description**: nan
  - **Tasks**:
    - [ ] Frontend: Extend the UI that displays all the goalkeepers that meet the query criteria
    - [ ] Testing: Create Gherkin acceptance tests for query-by-saves functionalilty
    - [ ] Backend: Implement API call that returns a list of players when queried with a number of saves
### Name: Add Player To Team
  - **Owner**: rashya.anggaraksa@mail.mcgill.ca
  - **Description**: User Story: As a user, I want to be able to add a player to my fantasy team for the matchweek, so that I can customize my lineup.

Acceptance Criteria
The player being added should be eligible (not injured, suspended, or on international duty) to play in the upcoming matchweek.
Normal Flow: Adding player by name.
An existing league user adds an available player by searching for their name, and adding the player to their team.
Alternate Flow: Adding a suspended player
Existing league user attempts to add an available player by querying by position, then adding them to their team.
Error Flow: Adding a suspended player
An existing league user attempts to add a player who is suspended to their team.

Gherkin:
Feature: Add Player to a Team

Scenario: Adding player by name (Normal Flow)
Given I am signed in as user
When I add a player to my team
Then I should receive a confirmation message on the app
And I should see the player in my team

Scenario: Adding a suspended player (Error Flow)
Given I am signed in as user
When I add a player to my team
But the player is suspended for the current matchday
Then I should receive an error message notifying of the suspension
And I should be prompted to add another player to my team
  - **Tasks**:
    - No tasks specified
### Name: Remove Player From Team
  - **Owner**: chrisovalantis.vatos@mail.mcgill.ca
  - **Description**: User Story: As a user, I want to be able to remove a player from my fantasy team, so that I can customize my lineup. 

Acceptance Criteria: 
Normal Flow: User successfully removes player from team
The selected player is successfully removed from the user's fantasy team
The user receives a confirmation message indicating the successful removal of the player
Alternate Flow: User cannot remove player since it will mean dropping below the minimum number of players needed
The system identifies that removing the selected player will result in the team failing to meet the minimum required number of players
The user receives a message indicating they cannot remove the player as it would violate the minimum player requirement
The user is prompted to either cancel the removal or choose a replacement player before proceeding

Gherkin:
Feature: Remove Player From Team

Background: 
Given the user is on the Team Management page

Scenario: User successfully removes player from team (Normal Flow)
When the user selects the player they want to remove
And confirms the removal request
Then the selected player should be successfully removed from the user’s team
And the user should see a message indicating successful removal of the selected player

Scenario: User cannot remove player since it will mean dropping below the minimum number of players needed (Alternate Flow)
Given the user’s team currently has the minimum number of players
When the user selects the player they want to remove
And confirms the removal request
Then the system shall identify that removing the selected player will result in the user’s team falling below the minimum required number of players
And the selected player should not be removed from the user’s team
And the user should see a message indicating they cannot remove the player due to minimum player requirements
And the system shall prompt the user to either cancel the removal or choose a replacement player before proceeding
  - **Tasks**:
    - [ ] Write Gherkin acceptance tests for the functionality of removing a player
    - [ ] Implement backend logic/endpoint that allows a user to remove a player from their current team
    - [ ] On the "My Team" page, add frontend functionality to trigger api endpoint and allow a user to delete a player from their team
### Name: Query players by assists
  - **Owner**: wasifsomji@gmail.com
  - **Description**: Acceptance Criteria:
Entering a certain number of assists allows the user to see players that have delivered that number of assists.
Normal flow: Entering the exact number of assists causes all players who have delivered that exact amount of assists to be outputted.
Alternate flow: Entering a range or upper/lower bound will output all the players whose number of assists fit in that range.
Error flow: Entering a number of assists that is not an exact match to anyone’s number of assists causes the user to be notified that no results were found.

Gherkin
Normal flow: Exact number of goals
Given a player named "John Doe" has delivered "5" assists in the system
When I query players by assists "5"
Then I am shown a list including "John Doe"

Alternate flow: Range of goals
Given players "John Doe" with "5" assists and "Jane Smith" with "3" assists
When I query players by goals scored ">4"
Then I am shown a list including “John Doe”

Error flow: No matching goals
Given no player has delivered "10" assists
When I query by assists "10", then I am shown a prompt indicating no results were found.
  - **Tasks**:
    - [ ] Implement backend logic/endpoint that allows a user to get/query players by number of assists
    - [ ] Design and develop frontend UI that allows user to trigger api endpoint and get/query the list of player(s)
    - [ ] Write Gherkin acceptance tests for querying/getting players
### Name: Leave a league
  - **Owner**: yassine.mimet@mail.mcgill.ca
  - **Description**: User Story: As a user, I want to be able to leave a 5 league fantasy

Acceptance criteria:
Normal flow: User leaves the league by clicking on the leave league button.

Gherkin:
Scenario: User leaves league
Given the user is a member of a league
When the user clicks on the "Leave League" button
Then the user should be successfully removed from the league
And the user should receive a confirmation message of leaving the league
  - **Tasks**:
    - [ ] Write Gherkin acceptance tests for leave league
    - [ ] Implement backend logic/endpoint that allows a user to leave a league
    - [ ] Design and develop frontend UI that allows user to trigger api endpoint and allow the user to leave a league
### Name: Query players by league
  - **Owner**: jerry.hou-liu@mail.mcgill.ca
  - **Description**: User story: As a user, I want to be able to search for players using the league that they belong to so that I can view their information and add them to a team

Acceptance criteria:
- Selecting a league allows the user to see all players in the league
- Normal flow: Selecting a league causes all players in the league to be displayed

Gherkin: 
```gherkin
# Normal flow: Display all players in league
Given that there is a league containing players
When I select the league
Then I am shown a list of all players in the league
```
  - **Tasks**:
    - [ ] Frontend: Extend the query-by-name webpage to also support querying by league
    -  add a drop-down that allows for filtering
    - [ ] Testing: Create Gherkin acceptance tests for query-by-league functionality
    - [ ] Backend: Extend the query-by-name API to also support query-by-league
### Name: View League Leaderboard
  - **Owner**: rashya.anggaraksa@mail.mcgill.ca
  - **Description**: User Story: As a user, I want to be able to view the leaderboard for the league my team is in.

Acceptance Criteria
The league name must be valid, and the user must be a member of that league.
Normal Flow: An existing league member views their league’s leaderboard.
Alternate Flow: A league member attempts to view the leaderboard, but they are not logged in. The user then logs in and views the league leaderboard.
Error Flow: A league member attempts to view the leaderboard for an invalid league (no league found, user is not a member of that league).

Gherkin
Scenario
Given I am signed in as user
When I view the league’s leaderboard
Then the leaderboard for the league I am a part of should be displayed

**Scenario**
Given I am signed in as user
When I view the league’s leaderboard
But the league’s name does not exist
Then I should receive an error message
And I should receive a prompt to enter another league’s name
  - **Tasks**:
    - No tasks specified

