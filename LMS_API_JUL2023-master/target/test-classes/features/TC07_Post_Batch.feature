@Batch_Module @Post_Batch
Feature: Post Batch Request - Create new batch for the program
 
   @Post_Batch_validReqBody
  Scenario: Check if user able to create a batch with valid reqbody
  
    Given User creates batch using post request with "request body"
    When User calls "PostBatch"
    Then User receives Status Code with response body

    