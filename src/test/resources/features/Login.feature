@login
Feature: Test login user
  This is to test that user can login to the application.

  Scenario: To test for functionality of the subscribe option
    Given User visits the website
    And User clicks login on the website
    And User enters email
    And User clicks continue
    And User enters password
    And clicks login
    Then User is shown My Account Button