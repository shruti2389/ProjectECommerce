@zip
Feature: Test Zipcode user
  This is to test that user enters a valid zipcode to proceed further

  Scenario: To test for functionality of the zipcode option
    Given User visits the website
    When User enters a valid ZipCode
    And User clicks on find
    And User enters credentials into prompted login
    Then User is taken to the home page