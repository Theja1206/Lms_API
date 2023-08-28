@Program_Module @DeleteProgram_byName
Feature: User checks DELET request endpoint in Program Module 

Background: User already created a program Id

@Delete_validProgName
Scenario: Check if user able to delete a program with valid Program Name

Given User Delete program HTTPS Request
When User calls "DeletebyProgName" endpoint by "Valid Program Name"
Then User receives Status Code <200> with message