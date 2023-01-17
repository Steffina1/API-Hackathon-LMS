@tag
Feature: Validate Post Request to Create Program

  Background: 

  #Base uri
  #Given User sends request with its endpoint.
  #Then User should receive response with status code
  @tag1
  Scenario: User able to create Program
    Given User sends request with its endpoint
    When User sends a request body with valid endpoint to create Program.
    Then Validate response status <201>.
    And Validate response body for programName,programDescription,programStatus
    And Validate response headers should be application json format
    And Verify response body schema '"./src/test/resources/configuration/JsonSchema_Program/postRequest/Post_ValidRequest.json' in json format.
    And Verify the values are present in DB

  @tag1
  Scenario: User able to create program with invalid base URL
    Given User sends request with its invalid base URL
    Then Validate response status <503>.

  @tag1
  Scenario: User able to create program with Invalid Request
    Given User sends request with its endpoint
    When User sends a invalid request to create Program.
    And Validate response status <405>.
    And Validate response body with error code <405> and error message "Method Not Allowed"
    And Validate response headers should be application json format
    And Verify response body schema 'schema' in json format.

  @tag1
  Scenario: User able to create batch with Invalid End Point already existing endpoint
    Given User sends request with its endpoint
    When User sends request with invalid endpoint
    And Validate response status <405>.
    And Validate response body with error code <405> and error message "Method Not Allowed"
    And Validate response headers should be application json format

  @tag1
  Scenario: User able to save program with Invalid End Point
    Given User sends request with its endpoint
    When User sends request with invalid endpoint
    Then Validate response status <404>.
    And Validate response headers should be application json format
    And Verify response body schema 'schema' in json format.

  @tag1
  Scenario Outline: User able to save program without program name,programstatus,creation time,last mod time entity
    When User able to save program without required fields entity
    Then Validate response status <400>.
    And Validate response body with error code 'VALIDATION_ERROR' and error message
    And Validate response headers should be application json format
    And Verify response body schema 'schema' in json format.

  @tag1
  Scenario: User able to save program without program description entity.
    When User able to save program without required fields entity
    Then Validate response status <201>.
    And Validate response body for programName,programDescription,programStatus
    And Validate response headers should be application json format
    And Verify response body schema 'schema' in json format.
    And Verify the values are present in DB

  @tag1
  Scenario: User able to save program without program status entity.
    Given User sends request with its endpoint
    When User able to save program without required fields entity
    Then Validate response status <400>.
    And Validate response body with error code 'VALIDATION_ERROR' and error message
    And Validate response headers should be application json format
    And Verify response body schema 'schema' in json format.

  @tag1
  Scenario: Validate User able to save program without Creation Time.
    Given User sends request with its endpoint
    When User able to save program without required fields entity
    Then Validate response status <400>.
    And Validate response headers should be application json format
    And Verify response body schema 'schema' in json format.

  @tag1
  Scenario: User able to save program without last Modification Time
    Given User sends request with its endpoint
    When User able to save program without required fields entity
    Then Validate response status <400>.
    And Validate response headers should be application json format
    And Verify response body schema 'schema' in json format.

  @tag1
  Scenario: User able to save program with existing data.
    Given User sends request with its endpoint
    When User sends a request body with valid endpoint to create Program.
    Then User sends the request body with existing Program.
    Then Validate response status <400>.
    And Validate response body with error code 'VALIDATION_ERROR' and error message
    And Validate response headers should be application json format
    And Verify response body schema 'schema' in json format.

  @tag1
  Scenario: User able to save program with program name as blank.
    Given User sends request with its endpoint
    When User able to save program with null values
    Then Validate response status <201>.
    And Validate response body for programName,programDescription,programStatus
    And Validate response headers should be application json format
    And Verify response body schema 'schema' in json format.

  @tag1
  Scenario: User able to save program with Program Description as blank
    Given User sends request with its endpoint
    When User able to save program with null values
    Then Validate response status <400>.
    And Validate response body with error code 'VALIDATION_ERROR' and error message
    And Validate response headers should be application json format
    And Verify response body schema 'schema' in json format.

  @tag1
  Scenario: User able to save program with Program Status as blank.
    Given User sends request with its endpoint
    When User able to save program with null values
    Then Validate response status <400>.
    And Validate response body with error code 'VALIDATION_ERROR' and error message

  @tag1
  Scenario: User able to save program with invalid Program Status as random value
    Given User sends request with its endpoint
    When User sends request with Program status as random value to create Program
    Then User sends request body with Program status as random value
    And Validate response status <400>.
    And Validate response body with error code '400' and message as "Bad Request"
    And Validate response headers should be application json format
    And Verify response body schema 'schema' in json format.

  @tag1
  Scenario: User able to save program with Creation Time as blank.
    Given User sends request with its endpoint
    When User able to save program with null values
    Then Validate response status <400>.
    And Validate response body with error code '400' and message as "Bad Request"
    And Validate response headers should be application json format
    And Verify response body schema 'schema' in json format.

  @tag1
  Scenario: User able to save program with Last Mod Time as blank.
    Given User sends request with its endpoint
    When User able to save program with null values
    Then Validate response status <400>.
    And Validate response body with error code '400' and message as "Bad Request"
    And Validate response headers should be application json format

  @tag1
  Scenario: User able to save program with invalid data
    Given User sends request with its endpoint
    When User sends request with Creation Time as invalid format
    Then User sends request body with Creation Time as invalid format
    And Validate response status <400>.
    And Validate response body with error code '400' and message as "Bad Request"
    And Validate response headers should be application json format
