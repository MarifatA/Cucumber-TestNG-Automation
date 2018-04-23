@smoke
Feature: User Interface for SuiteCRM

  Scenario: CRM Name and Modules
    Given I logged in into suiteCRM
    Then CRM name should be SuiteCRM
    And Moduls should be displayed
    Then I logout from app

  Scenario: 
    Given I logged in into suiteCRM
    And duplicated contact "John Doe" exists
    When I open contact "John Doe"
    And remove duplicates for this contact
    Then there should be only 1 "John Doe" in the contacts page
