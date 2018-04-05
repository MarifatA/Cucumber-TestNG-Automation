Feature: Creating contacts

  Scenario: Create contact using CREATE page
    Given I logged in into suiteCRM
    And I open the create contact page
    And I enter first name "Askar" and last name "Akaev"
    And I enter the phone number "202-000-0000"
    And I enter the department "IT"
    When I click save button
    Then I should see contact information for "Askar Akaev"

  Scenario: Create contact using CREATE page
    Given I logged in into suiteCRM
    And I open the create contact page
    And I enter first name "Sarah" and last name "Connor"
    And I enter the phone number "202-000-0000"
    And I enter the department "IT"
    When I click save button
    Then I should see contact information for "Sarah Connor"

  @create_contact
  Scenario Outline: Create multiple contacts
    Given I logged in into suiteCRM
    And I open the create contact page
    And I enter first name "<firstname>" and last name "<lastname>"
    And I enter the phone number "<phonenumber>"
    And I enter the department "department"
    When I click save button
    Then I should see contact information for "<firstname> <lastname>"

    Examples: 
      | firstname | lastname | phonenumber | department |
      | Sakish    | Bopush   | 98779687878 | IT         |
      | Scott     | Smith    |    13123124 | HR         |
      | James     | Kelly    |  3412232332 | Sales      |
      | Brad      | Meat     | 23123213223 | Logistic   |
      | Rhiannon  | Morrison | 1-917-4613  | aa         |
      | Zeus      | Wright   | 1-328-4518  | ing        |
      | Samantha  | Maxwell  | 1-678-3953  | ss         |
      | Neil      | Sampson  | 1-826-6519  | ting       |
      | Teagan    | Larsen   | 1-247-2016  | dd         |
      | Kermit    | Goff     | 1-732-3727  | ing        |
      | Dennis    | Hoffman  | 1-602-5630  | ting       |
      | Quon      | Frank    | 1-191-8707  | sdf        |
      | Mason     | Mendez   | 1-710-5803  | sd         |
      | Herrod    | Berry    | 1-966-9204  | ing        |
      | Inez      | Slater   | 1-442-3808  | df         |
      | Todd      | Lyons    | 1-537-7100  | df         |
      | Brendan   | Meyer    | 1-1280-7814 | df         |
