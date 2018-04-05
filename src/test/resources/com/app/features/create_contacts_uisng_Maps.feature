Feature: Creating contact

  Scenario: Creating contacts using a map
    Given I logged in into suiteCRM
    When I create a new contact:
      | first_name   | Johnjon      |
      | last_name    | Smithbek     |
      | office_phone | 321-232-2132 |
      | cell_phone   | 513-231-5634 |
    Then I should see contact information for "Johnjon Smithbek"

  @create_contactUsinMap
  Scenario Outline: Creating contacts using a map
    Given I logged in into suiteCRM
    When I create a new contact:
      | first_name   | <firstName>   |
      | last_name    | <lastName>    |
      | cell_phone   | <cellPhone>   |
      | office_phone | <officePhone> |
    Then I should see contact information for "<firstName> <lastName>"

    Examples: 
      | firstName | lastName | cellPhone    | officePhone  |
      | Bonnie    | Garcia   | 343-343-3345 | 454-343-4565 |
      | Donaldbek | Trump    | 343-343-3345 | 454-343-4565 |
