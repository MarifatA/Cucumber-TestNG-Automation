Feature: Creating tasks

  @regression @functional
  Scenario: Create a task
    Given I logged in into suiteCRM
    When I clicked on create taks
    And Set subject as "Automate Scenarios"
    And Set status as "Not Started"
    And Start date is todays date
    And Due date is 5 days after todays date
    And Set priority "Medium" priority
    And Set description as "Automate test cases using Cucumber and Selenium"
    And Save the task
    Then Task details page should be displayed
    And Data should match with created task data
