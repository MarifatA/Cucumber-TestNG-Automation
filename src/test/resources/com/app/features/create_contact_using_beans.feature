Feature: Creating contacts using beans

  Scenario: Create contact
    Given I logged in into suiteCRM
    When I save a new contact:
      | firstName | lastName | department | officePhone  | cellPhone    | email                 |
      | Chypa     | Baatyr   | IT         | 433-322-4323 | 454-545-3423 | chypabaatyr@gmail.com |
    Then I should see contact information for "Chypa Baatyr"

  @create_contactBeans
  Scenario Outline: Create way more contacts
    Given I logged in into suiteCRM
    When I save a new contact:
      | firstName    | lastName    | department   | officePhone    | cellPhone    | email   |
      | <first-name> | <last-name> | <department> | <office-phone> | <cell-phone> | <email> |
    Then I should see contact information for "<first-name> <last-name>"

    Examples: 
      | first-name | last-name | department | office-phone | cellp-hone   | email                  |
      | Admiral    | Kunkkal   | NAVY       | 321-232-2321 | 344-232-3123 | admin@gmail.com        |
      | Barmak     | Baatyr    | AyilCamp   | 433-322-4323 | 454-545-3423 | barmakbaatyr@gmail.com |
