Feature: CreditCard

  Scenario: New Credit card is created with £0 balance
    Given  I put a new Credit card with valid data
    Then the Credit Card  will be created
    And the balance is £0

  Scenario: New Credit card is created with £0 balance
    Given  I put a new Credit card with valid data
    Then the Credit Card  will be created
    And the balance is £0

  Scenario: Trying to create a new credit card but the card number is invalid
    Given I put a new credit card with invalid card number
    Then the put fails as a bad request

  Scenario: Get All Credit cards
    Given I request get all credit cards
    Then All the credit cards are listed