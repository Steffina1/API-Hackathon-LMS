package com.lmshackathon.stepdefiniton;


  
  import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class UpdateProgramByName {
	public static RequestSpecification httpRequest;
	public static Response response;
	
	//already provided in base class
	public static String  responsebody;
	public static String programId;
	public static String programName;
	public static String programDescription;
	public static String programStatus;
	public static String creationTime;
	public static String lastModTime;
	public static String baseprogramExcelPath="excel"
	Map<String,Response> responseMap = new HashMap<String,Response>(); //create Hashmap object for storing response
	Map<String, String[]> dataMap = new HashMap<String, String[]>();//create another Hashmap object for getting the key for rows-->hash map set
 

	@Given("User sends request to update a Program name")
	public void user_sends_request_to_update_a_program_name() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Validate programName,programDescription,programStatus"
	public void Validate_programName_programDescription_programStatus {
		System.out.println("=========Responsebody in POST Request=========");
		//Response body validation
		Assert.assertTrue(responsebody!=null);//check response body not equal to null
		String responsebody = response.getBody().asString();
		System.out.println("Response body:"+responsebody);
		
		System.out.println("==========Validate Program Id==================");
		String programId = response.jsonPath().getString("programId");
		Assert.assertEquals(responsebody.contains(programId),true); 
		
		System.out.println("==========Validate Program Name==================");
		String programName = response.jsonPath().getString("programName");
		Assert.assertEquals(responsebody.contains(programName),true); 
		
		System.out.println("==========Validate Program Description==================");
		String programDescription = response.jsonPath().getString("programDescription");
		Assert.assertEquals(responsebody.contains(programDescription),true);
		
		System.out.println("==========Validate Program Status==================");
		String programStatus = response.jsonPath().getString("programStatus");
		Assert.assertEquals(responsebody.contains(programStatus),true);
		
		System.out.println("==========Validate Creation Time==================");
		String creationTime = response.jsonPath().getString("creationTime");
		Assert.assertEquals(responsebody.contains(creationTime),true);
		
		System.out.println("==========Validate last modification Time==================");
		String lastModTime = response.jsonPath().getString("lastModTime");
		Assert.assertEquals(responsebody.contains(lastModTime),true);
}
	}

	@When("User sends request body with a valid endpoint to update Program name")
	public void user_sends_request_body_with_a_valid_endpoint_to_update_program_name() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("Check creation time and last modification time should be the current time")
	public void check_creation_time_and_last_modification_time_should_be_the_current_time() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("Verify response headers should be application\\/json format")
	public void verify_response_headers_should_be_application_json_format() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User should receive response with status code {string}")
	public void user_should_receive_response_with_status_code(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Verify response body schema in json format")
	public void verify_response_body_schema_in_json_format() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Verify the values are present in DB")
	public void verify_the_values_are_present_in_db() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User sends request with its invalid base URL")
	public void user_sends_request_with_its_invalid_base_url() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("Verify response headers should be application\\/json format")
	public void verify_response_headers_should_be_application_json_format() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends request body with invalid base url and its endpoint")
	public void user_sends_request_body_with_invalid_base_url_and_its_endpoint() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("Verify response body schema in json format")
	public void verify_response_body_schema_in_json_format() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Verify response with status code {string}")
	public void verify_response_with_status_code(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User sends a invalid request to update Program name")
	public void user_sends_a_invalid_request_to_update_program_name() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("Verify response body validation with status {string} and error message {string}")
	public void verify_response_body_validation_with_status_and_error_message(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends request body with its endpoints")
	public void user_sends_request_body_with_its_endpoints() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("Validate Post Schema in json format")
	public void validate_post_schema_in_json_format() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User sends request with invalid endpoint")
	public void user_sends_request_with_invalid_endpoint() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("Validate response body with error message {string}")
	public void validate_response_body_with_error_message(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends request body with valid Program name")
	public void user_sends_request_body_with_valid_program_name() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User sends request with its endpoint to update Program name")
	public void user_sends_request_with_its_endpoint_to_update_program_name() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends request body with invalid Json Schema")
	public void user_sends_request_body_with_invalid_json_schema() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User sends request to update multiple Program through external file")
	public void user_sends_request_to_update_multiple_program_through_external_file() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends request body to update multiple Programs through external file")
	public void user_sends_request_body_to_update_multiple_programs_through_external_file() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Check the DB validation")
	public void check_the_db_validation() {
		System.out.println("============DB Validation=============");
		String ResponseDBCheck =given()
				.auth()
				.none()
				.when()
				.get()
				.getBody()
				.asString();

		for (String programIdfromDB : dataMap.keySet()) {

			String[] row = dataMap.get(programIdfromDB);

			response = httpRequest.request(Method.GET, "https://lms-backend-service.herokuapp.com/lms/allPrograms"+"/"+programIdfromDB);
			response.then().log().all();

			// assert the value from DB and excel sheet
			assertEquals(programName, row[0]); 
			assertEquals(programDescription, row[1]);
			assertEquals(programStatus, row[2]);
			assertEquals(creationTime, row[3]);
			assertEquals(lastModTime, row[4]);
			assertEquals(programId, row[5]);
		}
	}

	@Given("User sends request with already existing data to update Program name")
	public void user_sends_request_with_already_existing_data_to_update_program_name() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("Verify response body validation")
	public void verify_response_body_validation() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends the request body with existing Program name")
	public void user_sends_the_request_body_with_existing_program_name() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Verify response with status code '{int} OK")
	public void verify_response_with_status_code_ok(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User sends request with non existing data to update Program name")
	public void user_sends_request_with_non_existing_data_to_update_program_name() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("Verify response body validation with error code")
	public void verify_response_body_validation_with_error_code() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends the request body with non existing Program name")
	public void user_sends_the_request_body_with_non_existing_program_name() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User sends empty input fields to update Program name")
	public void user_sends_empty_input_fields_to_update_program_name() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends request body with empty input data to update Program name")
	public void user_sends_request_body_with_empty_input_data_to_update_program_name() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I want to write a step with name1")
	public void i_want_to_write_a_step_with_name1() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I check for the {int} in step")
	public void i_check_for_the_in_step(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I verify the success in step")
	public void i_verify_the_success_in_step() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I want to write a step with name2")
	public void i_want_to_write_a_step_with_name2() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I verify the Fail in step")
	public void i_verify_the_fail_in_step() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


}
