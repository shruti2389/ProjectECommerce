@shoppingCart
Feature: Test checkout option
  This is to test the functionality of the checkout option

  Scenario: To test for functionality of the checkout option
    Given User is on the home page
    When adds an item to the cart
    And proceeds to checkout
    And confirms the address
    And confirms the delivery option
    And clears the cart