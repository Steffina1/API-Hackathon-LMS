#@getBatchbyid
#Feature: Test the Batch using ID
#Background: User enter the baseurl to get all program
#Given:  User sends request with its endpoint to get Batch ID
#Then: The content type of response header should be application/json to get all BatchID


@getprogbyid
Feature: To test the program by ID using Get Method

  Scenario: User able to Get a Program by ID with valid request
    Given User sends valid base url to get Program by Id
    When User sends request with its endpoint to get Program ID
    Then User should receive response with status code '200 ok'
    And Validate response body  to get Program by ID
    And Validate Get Program by ID Schema in json format
    And Verify status line should be HTTP/1.1 200


 @getprogbyid
  Scenario: User able to Get a Program by ID with invalid request
    Given User sends the url with invalid request
    When User sends the request with its invalid request
    Then Validate response with status code '405' Method not allowed
    And Validate response body with error message 'Method Not Allowed'
    And Verify status line should be HTTP/1.1 405
    
@getprogbyid
  Scenario: User able to Get a Program by ID with invalid endpoint
  Given User sends the request to get Program ID
  When User sends a request body with invalid endpoint
  Then Verify response with status code '400 Bad Request' 
  And Validate the response body with error code message 'ENTITY_DOES_NOT_EXIST'
  
  
  
