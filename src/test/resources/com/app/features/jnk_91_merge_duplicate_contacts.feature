Feature: Merging duplicate contacts
   Agile Story
  As a user, I should be able to merge duplicate contacts,
  so that we can prevent future errors.
  @remove_duplicate
Scenario: 
   Given I logged in into suiteCRM
   And duplicated contact "John Doe" exists
   When I open contact "John Doe"
   And remove duplicates for this contact
   Then there should be only 1 "John Doe" in the contacts page
