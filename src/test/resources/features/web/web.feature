@web
Feature: Login sauce demo

  Scenario: when user login with valid username and password
    Given user open the login page
    When user input username "standard_user"
    And user input password "secret_sauce"
    And user click login button
    Then user should be redirected to inventory page

  Scenario: when user login with invalid password
    Given user open the login page
    When user input username "standard_user"
    And user input password "wrong_pass"
    And user click login button
    Then user should see error message

  Scenario: when user login with empty username
    Given user open the login page
    When user input username ""
    And user input password "secret_sauce"
    And user click login button
    Then user should see error message

  Scenario: when user login with empty password
    Given user open the login page
    When user input username "standard_user"
    And user input password ""
    And user click login button
    Then user should see error message