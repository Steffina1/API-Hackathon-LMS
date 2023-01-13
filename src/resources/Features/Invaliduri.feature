
@tag
Feature: Test the Program and Batch with invalid url
Background: Test Program and Batch with invalid base url
Given The user sends invalid base url as a part of request
Then User should receive response with service unavailable status code 
And The content type of response header should be "text/html; charset=utf-8"
And  The status line should be HTTP/1.1 503

  @tag1
  Scenario: Test if User is able to get all batches when sending invalid base url
    When User sends valid endpoint to get all the batches


  @tag1
  Scenario: Test if User is able to get requested batch by name when sending invalid base url
    When User sends valid endpoint to get requested batch by name

