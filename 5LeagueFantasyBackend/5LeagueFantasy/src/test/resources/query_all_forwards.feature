Feature: Query all forwards
  Scenario Outline: Querying for a specific forward in the system
    Given that there is a forward "<forward_name>" in the system
    When I query all forward
    Then I am shown a list of all forwards that includes "<forward_name>"

    Examples:
      | forward_name      |
      | Cristiano Ronaldo |
      | Lionel Messi      |
      | Robert Lewandowski |
