@tag
Feature: Validate POST request to Create Batch.

  @tag1
  Scenario: User able to create batch with existing program
    Given User sends request with its endpoint
    When User sends request  body to create Batch.
    Then Validate batch response status<201>.
    And Validate response body for batchName,batchDescription,batchStatus
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format
    And Verify the batch values are present in DB

  @tag1
  Scenario: User able to create batch with active status with not available program
    Given User sends request with its endpoint
    When User sends request  body to create Batch.
    Then Validate batch response status<400>.
    And Validate response body with error code 'ENTITY_DOES_NOT_EXIST' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Batch_error_status_schema.json' in json format

  @tag1
  Scenario: User able to create batch with inactive status to  non existing program
    Given User sends request with its endpoint
    When User sends request  body to create Batch.
    Then Validate batch response status<400>.
    And Validate response body with error code 'ENTITY_DOES_NOT_EXIST' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format

  @tag1
  Scenario: User able to create existing active batch with non existing program
    Given User sends request with its endpoint
    When User sends request  body to create Batch.
    Then Validate batch response status<400>.
    And Validate response body with error code 'ENTITY_DOES_NOT_EXIST' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format

  @tag1
  Scenario: User able to create existing inactive batch with non existing program
    Given User sends request with its endpoint
    When User sends request  body to create Batch.
    Then Validate batch response status<400>.
    And Validate response body with error code 'ENTITY_DOES_NOT_EXIST' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format

  @tag1
  Scenario: User able to create existing active batch with non existing program
    Given User sends request with its endpoint
    When User sends request  body to create Batch.
    Then Validate batch response status<400>.
    And Validate response body with error code 'ENTITY_DOES_NOT_EXIST' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format

  @tag1
  Scenario: User able to create active batch with Inactive status
    Given User sends request with its endpoint
    When User sends request  body to create Batch.
    Then Validate batch response status<400>.
    And Validate response body with error code 'ENTITY_DOES_NOT_EXIST' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format

  @tag1
  Scenario: User able to create batch with invalid program entity which does not exists
    Given User sends request with its endpoint
    When User sends request  body to create Batch.
    Then Validate batch response status<400>.
    And Validate response body with status and error 'ENTITY_DOES_NOT_EXIST'
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format

  @tag1
  Scenario: User able to create batch with invalid data
    Given User sends request with its endpoint
    When User sends request  body to create Batch.
    Then Validate batch response status<400>.
    And Validate response body with error code 'ENTITY_DOES_NOT_EXIST' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format

  @tag1
  Scenario: User able to create same batch with an existing program
    Given User sends request with its endpoint
    When User sends request  body to create Batch.
    Then Validate batch response status<400>.
    And Validate batch response body with 'message' and sucess message 'false'.
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format

  @tag1
  Scenario: User able to create batch with multiple programs.
    Given User sends request with its endpoint
    When User sends request  body to create Batch.
    Then Validate batch response status<201>.
    And Validate response body for batchName,batchDescription,batchStatus
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format

  @tag1
  Scenario: User able to create multiple batch with same program
    Given User sends request with its endpoint
    When User sends request  body to create Batch.
    Then Validate batch response status<201>.
    And Validate response body for batchName,batchDescription,batchStatus
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format
    And Verify the batch values are present in DB

  @tag1
  Scenario: User able to create batch without request body
    Given User sends request with its endpoint
    When User sends request  without request body
    Then Validate batch response status<415>.
    And Validate response body with status and error 'Unsupported Media Type'
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format
    And Verify the batch values are present in DB

  @tag1
  Scenario: User able to create program with invalid base URL
    Given User sends request with its invalid base URL
    Then Validate batch response status<503>.

  @tag1
  Scenario: User able to create batch with Invalid Request
    Given User sends request with its endpoint
    When User sends request  body to create Batch.
    Then Validate batch response status<405>.
    And Validate response body with status and error "Method Not Allowed"
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format

  @tag1
  Scenario: User able to save batch with Invalid End Point
    Given User sends request with its endpoint
    When User sends request  body to create Batch.
    Then Validate batch response status<404>.
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format

  @tag1
  Scenario: User able to create batch with Invalid End Point already existing endpoint
    Given User sends request with its endpoint
    When User able to save batch without required fields entity
    And Validate batch response status<405>.
    And Validate response body with status and error "Method Not Allowed"
    And Validate batch response headers should be application json format

  @tag1
  Scenario: User able to create batch with Invalid Json Schema with incorrect entity name
    Given User sends request with its endpoint
    When User able to save batch without required fields entity
    And Validate batch response status<400>.
    And Validate response body with error code 'VALIDATION_ERROR' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Batch_error_status_schema.json' in json format

  @tag1
  Scenario: User able to create batch without either one of the batch details.
    Given User sends request with its endpoint
    When User able to save batch without required fields entity
    Then Validate batch response status<400>.
    And Validate response body with error code 'VALIDATION_ERROR' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Batch_error_status_schema.json' in json format

  @tag1
  Scenario: User able to create batch without batch description
    Given User sends request with its endpoint
    When User able to save batch without required fields entity
    Then Validate batch response status<201>.
    And Validate response body for batchName,batchDescription,batchStatus
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format

  @tag1
  Scenario: User able to create batch without batch status
    Given User sends request with its endpoint
    When User able to save batch without required fields entity
    Then Validate batch response status<400>.
    And Validate response body with error code 'VALIDATION_ERROR' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Batch_error_status_schema.json' in json format

  @tag1
  Scenario: User able to create batch without  batch No of Classes
    Given User sends request with its endpoint
    When User able to save batch without required fields entity
    Then Validate batch response status<400>.
    And Validate response body with error code 'VALIDATION_ERROR' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Batch_error_status_schema.json' in json format

  @tag1
  Scenario: User able to create batch without program.
    Given User sends request with its endpoint
    When User able to save batch without required fields entity
    Then Validate batch response status<400>.
    And Validate response body with error code 'VALIDATION_ERROR' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Batch_error_status_schema.json' in json format

  @tag1
  Scenario: User able to create batch with batch name as blank
    Given User sends request with its endpoint
    When User sends request  body with batch as null value to create batch
    Then Validate batch response status<400>.
    And Validate response body with error code 'VALIDATION_ERROR' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Batch_error_status_schema.json' in json format

  @tag1
  Scenario: User able to create batch with batch description as blank
    Given User sends request with its endpoint
    When User sends request  body with batch as null value to create batch
    Then Validate batch response status<201>.
    And Validate response body for batchName,batchDescription,batchStatus
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Post_Batch_Schema.json' in json format

  @tag1
  Scenario: User able to create batch with batch status as blank
    Given User sends request with its endpoint
    When User sends request  body with batch as null value to create batch
    Then Validate batch response status<400>.
    And Validate response body with error code 'VALIDATION_ERROR' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Batch_error_status_schema.json' in json format

  @tag1
  Scenario: User able to create batch with batch status as random blank
    Given User sends request with its endpoint
    When User sends request  body with batch as null value to create batch
    Then Validate batch response status<400>.
    And Validate response body with error code 'VALIDATION_ERROR' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Batch_error_status_schema.json' in json format

  @tag1
  Scenario: User able to create batch with batch no of classes as blank
    Given User sends request with its endpoint
    When User sends request  body with batch as null value to create batch
    Then Validate batch response status<400>.
    And Validate response body with error code 'VALIDATION_ERROR' and error message
    And Validate batch response headers should be application json format
    And Verify response body schema in batch './src/test/resources/configuration/JsonSchema_Program/PostRequest/Batch_error_status_schema.json' in json format
