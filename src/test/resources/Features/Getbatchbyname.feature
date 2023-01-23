
@getbatchbyname
Feature: Test the Batch by name using GET
 Background: User enters the base url to get batch by name
Given The user sends base url as a part of request to get batch by name
Then  The content type of response header should be application/json to get batch by name

  #@test1getbatchbyname
  #Scenario Outline: Test if user is able to get batch by name when sending valid request
    #When User sends request with valid endpoint to get batch by name
    #Then User should receive response with success status code for batch by name
    #And The response body should contain all the details of the requested batch from "<testid>" "<sheetname>"
    #And The status line should be HTTP/1.1 200 for batch by name
     #Examples: 
      #| testid| sheetname| 
      #|13     |API      |
#
  #@test2getbatchbyname
  #Scenario Outline: Test if user is able to get batch by name when sending invalid request
    #When User sends POST, PUT, PATCH, DELETE requests with valid endpoint for batch by name
    #Then  User should receive response with Method Not Allowed status code for batch by name 
    #And The response body should contain error message Method Not Allowed for batch by name from "<testid>" "<sheetname>"
    #And The status line should be HTTP/1.1 405 for metod not allowed batch by name
#
    #Examples: 
     #| testid| sheetname| 
      #|9     |API      |
  #@test3getbatchbyname
  #Scenario Outline:  Test if user is able to get batch by name when sending invalid endpoint
    #When User sends request with invalid endpoint for batch by name from "<testid>" "<sheetname>"
    #Then  User should receive response with Bad Request status code for batch by name
    #And The response body should contain error message ENTITY_DOES_NOT_EXIST for the batch by name
    #And  The status line should be HTTP/1.1 400 for the batch by name
     #Examples: 
     #| testid| sheetname| 
      #|6     |API      |
      
          @test4getallbatches
     Scenario: Test if JSON schema matches the response while sending valid request
       Then The JSON schema should match the response to get batch by name