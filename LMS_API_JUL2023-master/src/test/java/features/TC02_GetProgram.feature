@Program_Module @GetProgram_Module
Feature: User checks GET Request in Program Module

  Background:
    User sets Authoization to No  Auth
    Given User creates GET Request for LMS API endpoint

@Get_AllProgram
Scenario: Check if user able to retrieve all programs with valid LMS API
    
    When User calls "GetAllPrograms" Https Request for "All Program" 
    Then User receive HTTP Status code and response body

@Get_ProgramID
Scenario: Check if User able to retrieve program with valid ProgramID

    When User calls "GetProgramByID" Https Request for "ProgramID"  
    Then User receive HTTP Status code and response body


@Get_InvalidProgram
Scenario: Check if user able to retrieve program with invalid program ID

    When User calls "GetProgramByID" Https Request for "invalid ProgramID"  
    Then User receive HTTP Status code and error message
    

    