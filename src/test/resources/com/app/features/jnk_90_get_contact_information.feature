Feature: Get detailed contact information
      Agile Story:
      As a user, I should be able to access the contact's user personal information,
       so that I have detailed information about that person
@get_contact_info 
Scenario: 
      Given I logged in into suiteCRM
      And I open contact "John Doe"
      Then contact name should be "John Doe"
      And contact email should be "johnDoe@example.org"        
