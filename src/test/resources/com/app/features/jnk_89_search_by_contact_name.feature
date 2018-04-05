Feature: Search by contact name
    Agile Story:
    As a user,
    I should be able to search for my contacts,
    So that I can access their information easily.

  @search_modul
  Scenario: Search contact name
    Given I logged in into suiteCRM
    When I search for "John Doe"
    Then link for user "John Doe" should be displayed
    And verify number of result 1 for "John Doe"
