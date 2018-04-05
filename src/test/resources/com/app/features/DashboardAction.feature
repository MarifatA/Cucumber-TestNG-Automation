Feature: Note on Dashboard

  @smoke
  Scenario: Post a note on Dasgboard
    Given I logged in into suiteCRM
    When I post "Hello, Crazy Hackers team!"
    Then Post "Hello, Crazy Hackers team!" should be displayed
    Then I logout from app

  Scenario: Post a another note on Dasgboard
    Given I logged in into suiteCRM
    When I post "Hello World!"
    Then Post "Hello World!" should be displayed
    Then I logout from app
