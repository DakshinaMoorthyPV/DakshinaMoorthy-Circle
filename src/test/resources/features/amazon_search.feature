#Author: Dakshina Moorthy
@Regression @AmazonSearch
Feature: Amazon Product Search

  @SmokeTest
  Scenario: Successfully searching for a product on Amazon
    Given I am on the Amazon homepage
    When I enter "laptop" into the search bar
    And I click on the search button
    Then I should see a list of products related to "laptop"
    And each product listed should have a name and price displayed.
