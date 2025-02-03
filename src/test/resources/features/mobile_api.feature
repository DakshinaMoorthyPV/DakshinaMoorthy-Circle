#Author: Dakshina Moorthy
@API @MobileValidation
Feature: Mobile API Validation

  @SmokeTest
  Scenario: Validating Mobile API Response
    Given I have a mobile API
    When I validate the response
    Then the status code should be 200
    Then the name of the 5th id should be "Samsung Galaxy Z Fold2"

