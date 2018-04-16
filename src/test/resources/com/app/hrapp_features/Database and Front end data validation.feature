Feature: Hr Application Database and UI verification

  Background: 
    Given I am on DeptEmpPage

  Scenario: Department data UI and Database verification
    When I search for deaprtment id 10
    And I query database with sql "select department_name,manager_id,location_id from departments where department_id=10"
    Then UI data and Database data must match

  Scenario Outline: First name and lastname search by email-UI vs DB verification
    When I search for email "<email>" to see firstname and lastname
    And I query database with sql "select first_name,last_name from employees where email='<email>'"
    Then UI data and Database data must match

    Examples: 
      | email   |
      | JWHALEN |
      | HBAER   |
      | JRUSSEL |

  @HRAppDB
  Scenario Outline: Verify number of employees for departments-UI vs DB verification
    When I search for deaprtment id <departmentID> and get number of employees
    And I query database with sql "select count(*)  as employees_count from employees where department_id=<departmentID>"
    Then UI data and Database data must match

    Examples: 
      | departmentID |
      |           50 |
      |           20 |
      |           30 |

 