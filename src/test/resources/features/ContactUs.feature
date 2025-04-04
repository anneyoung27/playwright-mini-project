Feature: WebDriverUniversity.com - Contact Us Page

  Background:
    Given I navigate to the webdriveruniversity homepage
    When I click on the contact us button

  Scenario: Valid contact us form
    And I type a first name
    And I type a last name
    And I type an email address
    And I type a comment
    And I click on the submit button
    Then I should be presented with a successful contact us submission message

   Scenario: Invalid contact us form
     And I type a comment
     And I click on the submit button
     Then I should be presented error inline message