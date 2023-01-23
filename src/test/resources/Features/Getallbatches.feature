
@getallbatches
Feature: Test the Batch using GET
Background: User enters the base url to get all batches
Given The user sends base url as a part of request to get all batches
Then The content type of response header should be application/json to get all batches 

  @test1getallbatches
  Scenario Outline: Test if user is able to get all batches when sending valid request
    When User sends request with valid endpoint to get all batches
    Then User should receive response with success status code for all batches
    And The response body should contain details of the batch from "<testid>" "<sheetname>" for all batches
    And The status line should be HTTP/1.1 200 for all batches
     Examples: 
     |testid | sheetname | 
     |8      |API        |

  @test2getallbatches
  Scenario Outline: Test if user is able to get all batches when sending invalid request
    When User sends PUT, PATCH, DELETE requests with valid endpoint for all batches
    Then User should receive response with Method Not Allowed status code for all batches 
    And The response body should contain error message from "<testid>" "<sheetname>" for all batches
    And The status line should be HTTP/1.1 405 for all batches with invalid request
    When User sends POST request with valid endpoint for all batches
    Then The response body should contain unsupp error message from "<testid>" "<sheetname>" for all batches
    And User should receive response with Method Unsupported Media Type status code for all the batches
    And The status line should be HTTP/1.1 415 for all the batches with invalid request
    
    Examples: 
     |testid | sheetname | 
     |9      |API        |
    |10      |API        |
      
@test3getallbatches
  Scenario Outline: Test if user is able to get all batches when sending invalid endpoint
    When User sends request with invalid endpoint for all batches
    Then User should receive response with Not found status code for all batches 
    And The response body should contain error message from "<testid>" "<sheetname>" for all batches with invalid endpoint
    And The status line should be HTTP/1.1 404 for all batches with invalid endpoint

        Examples: 
     |testid | sheetname | 
     |11     |API        |
     
 
       
   