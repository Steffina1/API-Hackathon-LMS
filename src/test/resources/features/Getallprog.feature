
@Getallprograms
Feature: Test the Program using GET
Background: User enters the base url to get all Programs
Given The user sends base url as a part of request to get all Programs
Then  The content type of response header should be application/json to get all Programs

  @testcase1programs
  Scenario Outline: Test if user is able to get all Programs when sending valid request
    When  User sends request with valid endpoint to get all Programs 
    Then User should receive response with success status code for all Programs
    And The response body should contain details of the Program validated from "<testid>" "<sheetname>"
    And The status line should be HTTP/1.1 200 for all Programs with valid request
     Examples: 
      | testid| sheetname| 
      |12     |API      |
  @testcase2programs
  Scenario Outline: Test if user is able to get all Programs when sending invalid request
    When  User sends POST, PUT, PATCH, DELETE requests with valid endpoint for all Programs
    Then User should receive response with Method Not Allowed status code for all Programs 
    And The response body should contain error message from "<testid>" "<sheetname>" for all Programs
    And  The status line should be HTTP/1.1 405 for all Programs with invalid request

   Examples: 
      | testid| sheetname| 
      | 9     |     API |
    
      
 @testcase3programs
  Scenario Outline: Test if user is able to get all Programs when sending invalid endpoint
    When  User sends request with invalid endpoint for all Programs
    Then User should receive response with Not found status code for all Programs
    And The response body should contain error message from "<testid>" "<sheetname>" for all the Programs
    And  The status line should be HTTP/1.1 404 for all Programs
    Examples: 
      | testid| sheetname| 
      | 11     |     API |
      