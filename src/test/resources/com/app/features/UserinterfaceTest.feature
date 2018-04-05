@smoke_test
Feature: User Interface for SuiteCRM

  Scenario: CRM Name and Modules
    Given I logged in into suiteCRM
    Then CRM name should be SuiteCRM
    And Moduls should be displayed
    Then I logout from app
