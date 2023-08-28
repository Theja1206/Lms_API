@Program_Module @Put_ProgramName
Feature: User checks PUT request endpoint in Program Module 

Background: User already created a program Id

@Put_validProgramName
Scenario: Check if user able to update a program with valid Program Name

Given User updates program using PUT HTTPS Request with "Valid Program Name"
When User calls "PutRequest" endpoint
Then User receives Status Code with updated program


@Put_InvalidProgramName
Scenario: Check if user able to update a program with invalidProgramName
Given User updates program using PUT HTTPS Request with "invalid Program Name" 
When User calls "PutRequest" endpoint
Then User receive HTTP Status code with error message