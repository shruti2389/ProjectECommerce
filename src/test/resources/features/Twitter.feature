@twitter
Feature: Test twitter icon
  This is to test the user is routed to the twitter by clicking on twitter icon

  Scenario: To validate an icon on the home page
    Given User visits the website
    And User scrolls down to the bottom of the page
    When User clicks on twitter icon
    Then User is routed to the twitter website
