
@Invaliduri
Feature: Test Batch and Program with invalid uri
Background: User enters invalid base url 
Given The user sends invalid base url 

  @getallprograms
  Scenario: Test if user is able to get all programs when sending invalid base url
   When User sends request with valid endpoint for getting all the programs
   Then User should receive response with service unavailable status code 
    And The response body should contain application error message for all programs
    And The content type of response header should be text/html for all programs
  @getallbatches
  Scenario: Test if user is able to get all batches when sending invalid base url
   When User sends request with valid endpoint for getting all the batches
   Then User should receive response with service unavailable status code 
   And The response body should contain application error message for all batches
    And The content type of response header should be text/html for all batches
   @getallbatchbyname
   Scenario: Test if user is able to get the batch by batch name when sending invalid base url
   When User sends request with valid endpoint for getting the requested batch detail
   Then User should receive response with service unavailable status code 
   And The response body should contain application error message for getting batch by name
   And The content type of response header should be text/html for getting batch by name
