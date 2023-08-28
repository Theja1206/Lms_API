@Batch_Module @Deletebatch_byID
Feature: DELETE REQUEST in Batch Module 


@Delete_validBatchID
Scenario: Check if user able to delete a batch with valid batch Id

Given User Delete batch using DELETE HTTPS Request
When User calls "DeleteBatch" endpoint with valid batch Id
Then User receives Status Code <200>with success message in response