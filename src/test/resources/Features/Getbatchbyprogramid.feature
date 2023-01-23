@getbatchbyprogID
Feature: To test the Batch  by program  ID using Get Method.

 Background: User enter the baseurl to get all batchprogid
 Given  User sends request with base url forbatch by progid
 Then The content type of response header should be application/json to get for batchprogID 

 @getbatchbyprogID
  Scenario: Test if user able to get a Batch by progID when sending valid request
  
    When User send a request with valid endpoint to get batchprogID
    Then  User should receive response with success status code for Batchprogid
    And Validate Get batch by batchprogID Schema in json format
    And The status line should beHTTP/1.1 200for valid batchprogid

  @getbatchbyprogID
    Scenario:  Test if user is able to get batch Id when sending invalid request
    
    When User send a invalid request with valid endpoint batchprogid
    Then Verify response with status code 'Method Not Allowed' batchprogid
    And Validate Get batch by batchprogID Schema in json format
    And The status line should beHTTP/1.1 405for valid batchprogid
   
   @getbatchbyprogID
     Scenario: Test if user is able to getbatchbyprogID when sending invalid endpoint
     
     When User send a invalid endpoint with valid request for batchprogId
     Then Verify response with status code 'Bad Request' batchprogid
     And   Validate Get batch by batchprogID Schema in json format
     And  The status line should be HTTP/1.1 400 for invalid endpoint batchprogid
