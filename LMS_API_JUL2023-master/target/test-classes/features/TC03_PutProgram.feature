@Program_Module @PutProgram_Module
Feature: User checks PUT request endpoint in Program Module 

Background: User already created a program Id

@Put_validProgramID
Scenario: Check if user able to update a program with valid details
Given User updates Put Request with "valid request body" 
When User calls "PutRequestEndpoint" with "PUT" HTTP Request "Valid ProgramID"
Then User receives Status Code with updated program Name

@Put_exisitingProgramName
Scenario: Check if user able to update a program with exisiting Program Name
Given User updates Put Request with "request body" and "exisiting Program Name"
When User calls "PutRequestEndpoint" with "PUT" HTTP Request after creating "ProgramID"
Then User receive HTTP Status code and error message

@Put_InvalidProgramID
Scenario: Check if user able to update a program with invalidProgramID
Given User updates Put Request with "invalid Program ID" 
When User calls "PutRequestEndpoint" with "PUT" HTTP Request "invalid program ID"
Then User receive HTTP Status code with error message

@Put_withoutProgramName
Scenario: Check if user able to update a program without program Name
Given User updates Put Request with "valid request body"
When User calls "PutRequestEndpoint" with "without Program Name" HTTP Request "Valid ProgramID"
Then User receive HTTP Status code and error message 

@Put_withoutProgramStatus
Scenario: Check if user able to update a program without Program status
Given User updates Put Request with "valid request body" 
When User calls "PutRequestEndpoint" with "without Program Status" HTTP Request "Valid ProgramID"
Then User receive HTTP Status code and error message

