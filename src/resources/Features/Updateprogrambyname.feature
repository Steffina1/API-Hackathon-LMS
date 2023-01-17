Feature: Title of your feature
  Validate PUT request to Update program name


  Scenario: User able to PUT a Program by ID with valid request
    Given User sends request to update a Program name
    And Validate "programName,programDescription,programStatus"
    When User sends request body with a valid endpoint to update Program name
    And Check creation time and last modification time should be the current time
    And Verify response headers should be application/json format
    Then User should receive response with status code '200 OK' 
    And Verify response body schema in json format
    And Verify the values are present in DB

  Scenario: User able to PUT program with invalid base URL
    Given User sends request with its invalid base URL
    And Verify response headers should be application/json format
    When User sends request body with invalid base url and its endpoint 
    And Verify response body schema in json format
    And Verify response headers should be application/json format
    Then Verify response with status code ' 503 Service unavailable'
    And Verify the values are present in DB
    
  Scenario: User able to PUT program with Invalid Request
    Given User sends a invalid request to update Program name
    And Verify response body validation with status '405' and error message 'Method Not Allowed' 
    When User sends request body with its endpoints 
    And Validate Post Schema in json format
    Then Verify response with status code '405 Method Not Allowed'
    
  Scenario: User able to PUT program with Invalid End Point
    Given User sends request with invalid endpoint
    And Validate response body with error message 'Not found'
    When User sends request body with valid Program name 
    And Verify response body schema in json format
    Then Verify response with status code '404 Not found' 
    And Verify the values are present in DB 
    
  Scenario: User able to PUT program with Invalid Json Schema
    Given User sends request with its endpoint to update Program name
    And Verify response headers should be application/json format
    When User sends request body with invalid Json Schema 
    And Verify response body schema in json format
    Then Verify response with status code '415 Unsupported Media Type' 
    And Verify the values are present in DB  
    
  Scenario: User able to PUT program with Multiple program through external file
    Given User sends request to update multiple Program through external file
    And Validate "programName,programDescription,programStatus"
    When User sends request body to update multiple Programs through external file
    And Check creation time and last modification time should be the current time
    And Validate Post Schema in json format
    Then Verify response with status code '200 OK' 
    And Check the DB validation  
    
  Scenario: User able to PUT program with existing data
    Given User sends request with already existing data to update Program name
    And Verify response body validation
    When User sends the request body with existing Program name 
    And Verify response body schema in json format
    Then Verify response with status code '200 OK
    And Verify the values are present in DB  
  
  Scenario: User able to PUT program with non existing data
    Given User sends request with non existing data to update Program name
    And Verify response body validation with error code
    When User sends the request body with non existing Program name
    And Verify response body schema in json format
    Then Verify response with status code '400 Bad Request'
    And Verify the values are present in DB 
    
   Scenario: User able to PUT program with program name as null
    Given User sends empty input fields to update Program name
    And Verify response body validation with error code
    When User sends request body with empty input data to update Program name
    And Verify response body schema in json format
    Then Verify response with status code '400 Bad Request'
    And Verify the values are present in DB
