@Program_Module @PostProgram_Module
Feature: TC01_Post_Program
  Check if user able to create a program with valid details

  Background:
    User sets Authoization to No  Auth

 
   @Post_validProgram
  Scenario: Check if user able to create a program with valid details
  
    Given User creates "POST" Request with "request body"
    When User calls "PostRequestEndpoint" with "POST" HTTP Request
    Then User receives Status Code with response body and verify programId using "GetProgramByID" request method

    @Post_withProgID
  Scenario: Check if user able to create a program with prog_ID
  
    Given User creates "POST" Request with programID in Request Body
    When User calls "PostRequestEndpoint" with "POST" HTTP Request
    Then User receives failed Status Code 
    