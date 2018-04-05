Feature: SugarCRM menu options

  @table
  Scenario: Verify Collaboration menu options
    Given I logged in into suiteCRM
    When I hover over the Collaboration menu
    Then following menu options should be visible for Collaboration:
      | Home      |
      | Emails    |
      | Documents |
      | Projects  |

  Scenario: Verify Support menu options
    Given I logged in into suiteCRM
    When I hover over the Support menu
    Then following menu options should be visible for Support:
      | Home     |
      | Accounts |
      | Contacts |
      | Cases    |

  Scenario: Verify Sales menu options
    Given I logged in into suiteCRM
    When I hover over the Sales menu
    Then following menu options should be visible for Sales:
      | Home     |
      | Accounts |
      | Contacts |
      | Cases    |
