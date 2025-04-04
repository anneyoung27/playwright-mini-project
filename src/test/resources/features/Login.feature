Feature: WebDriverUniversity - Login Page

  Background:
    Given I navigate to the webdriveruniversity homepage
    When I click the login button to navigate to the login page

  Scenario Outline: Login with valid and invalid credentials
    When I enter a valid username <username>
    And I enter a valid password <password>
    And I click the login button
    Then I should be presented with an alert box which contains text <alertText>

    Examples:
      | username  | password     | alertText            |
      | webdriver | webdriver123 | validation succeeded |
      | webdriver | Password123  | validation failed    |