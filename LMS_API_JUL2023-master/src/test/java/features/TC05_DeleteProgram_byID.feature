@Program_Module @DeleteProgram_byID
Feature: User checks DELET request endpoint in Program Module 

Background: User already created a program Id

@Delete_validProgID
Scenario: Check if user able to delete a program with valid Program Id

Given User Delete program using DELETE HTTPS Request
When User calls "DeleteProgram" endpoint with "Valid Program Id"
Then User receives Status Code with success message