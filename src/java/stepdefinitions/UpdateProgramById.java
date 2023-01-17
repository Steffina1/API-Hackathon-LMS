package com.lmshackathon.stepdefiniton;


  
  
package com.lmshackathon.stepdefiniton;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class UpdateProgramById {
	@Given("User sends request with valid id to update Program id")
	public void user_sends_request_with_valid_id_to_update_program_id() {
		RestAssured.baseURI="https://lms-backend-service.herokuapp.com/lms";

	}

	@When("User sends request body with a valid Json Schema to update Program id \\(path)")
	public void user_sends_request_body_with_a_valid_json_schema_to_update_program_id_path() {
		rownum = com.nn.api.utils.ExcelUtils.getRowCount(baseprogramExcelPath, "programdata");
		colnum = com.nn.api.utils.ExcelUtils.getCellCount(baseprogramExcelPath, "programdata", 1);

		System.out.println("row count:"+rownum);
		System.out.println("col count:"+colnum);

		String[][] progamData = new String[rownum][colnum];

		for(int i=1;i<=rownum;i++)
		{
			for(int j=0; j<colnum; j++) {

				progamData[i-1][j] = com.nn.api.utils.ExcelUtils.getCellData(baseprogramExcelPath, "programdata", i, j); //i =1, j=0 --> first cell value

			}
		}
		
		
		JSONObject params = new JSONObject();
		for(String[] row : progamData) {
			params.put("programName", row[0]);
			params.put("programDescription", row[1]);
			params.put("programStatus", row[2]);
			params.put("creationTime", row[3]);
			params.put("lastModTime", row[4]);
			params.put("programId", row[5]);
			httpRequest = given()
							.auth()
								.none()
							.contentType("application/json");//request
			httpRequest.body(params.toJSONString());
			response = httpRequest.request(Method.PUT,"/putprogram/programId"  );//response
			response.then().log().all();

			String programId = response.jsonPath().getString("programId");
			responseMap.put(programId, response);//use hashmap to store response
			dataMap.put(programId, row);//use another hashmap datamap to read all values by row*/

		}
		
	}

	}

	@When("Verify response headers should be application\\/json format")
	public void verify_response_headers_should_be_application_json_format() {
		fetch all headers
		Headers allheaders = response.headers();
		for (Header header : allheaders) {
			System.out.println(header.getName() + " " + header.getValue());
		}

		// logger.info("Validate the Status Line");
		for (String programId : responseMap.keySet()) {

			Response response = responseMap.get(programId);
			assertEquals(response.getStatusLine(), "application/json");

		}
	}

	@Then("User should receive response with status code OK")
	public void user_should_receive_response_with_status_code_ok() {
		System.out.println("=================Status Code Validation=============");
		int statusCode = response.getStatusCode();
		System.out.println("Status code is:"+statusCode);
		Assert.assertEquals(statusCode,200);
	}

	@Then("Validate programName,programDescription,programStatus")
	public void validate_program_name_program_description_program_status() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Verify response body schema in json format.")
	public void verify_response_body_schema_in_json_format() {
		System.out.println("=============Json responsebody validation============");
		//logger.info("Validate Json Schema");
		
		String responseBody = response.getBody().asString().replaceAll("NaN", "\"10 hrs\"");
		System.out.println("Replaced response body :"+responseBody);
		MatcherAssert.assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(Schema)));
		System.out.println("============Schema successfully validated in PUT Method=============");
	}


	@Then("Verify the values are present in DB")
	public void verify_the_values_are_present_in_db() {
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
		fetch all headers
		Headers allheaders = response.headers();
		for (Header header : allheaders) {
			System.out.println(header.getName() + " " + header.getValue());
		}

		// logger.info("Validate the Status Line");
		for (String programId : responseMap.keySet()) {

			Response response = responseMap.get(programId);
			assertEquals(response.getStatusLine(), "application/json");
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

	@Given("User sends a invalid request to update Program id")
	public void user_sends_a_invalid_request_to_update_program_id() {
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
		fetch all headers
		Headers allheaders = response.headers();
		for (Header header : allheaders) {
			System.out.println(header.getName() + " " + header.getValue());
		}

		// logger.info("Validate the Status Line");
		for (String programId : responseMap.keySet()) {

			Response response = responseMap.get(programId);
			assertEquals(response.getStatusLine(), "application/json");
	}

	@Given("User sends request with invalid endpoint")
	public void user_sends_request_with_invalid_endpoint() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("Validate response body with error message {string}.")
	public void validate_response_body_with_error_message(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends request body with valid Program id")
	public void user_sends_request_body_with_valid_program_id() {
		fetch all headers
		Headers allheaders = response.headers();
		for (Header header : allheaders) {
			System.out.println(header.getName() + " " + header.getValue());
		}

		// logger.info("Validate the Status Line");
		for (String programId : responseMap.keySet()) {

			Response response = responseMap.get(programId);
			assertEquals(response.getStatusLine(), "application/json");
	}

	@Given("User sends request with its endpoint to update Program id")
	public void user_sends_request_with_its_endpoint_to_update_program_id() {
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

	@Given("Validate {string}")
	public void validate(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends request body to update multiple Programs through external file")
	public void user_sends_request_body_to_update_multiple_programs_through_external_file() {
		fetch all headers
		Headers allheaders = response.headers();
		for (Header header : allheaders) {
			System.out.println(header.getName() + " " + header.getValue());
		}

		// logger.info("Validate the Status Line");
		for (String programId : responseMap.keySet()) {

			Response response = responseMap.get(programId);
			assertEquals(response.getStatusLine(), "application/json");
	}

	@When("Check creation time and last modification time should be the current time")
	public void check_creation_time_and_last_modification_time_should_be_the_current_time() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User sends request with already existing data to update Program id")
	public void user_sends_request_with_already_existing_data_to_update_program_id() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("Verify response body validation")
	public void verify_response_body_validation() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends the request body with existing Program id")
	public void user_sends_the_request_body_with_existing_program_id() {
		fetch all headers
		Headers allheaders = response.headers();
		for (Header header : allheaders) {
			System.out.println(header.getName() + " " + header.getValue());
		}

		// logger.info("Validate the Status Line");
		for (String programId : responseMap.keySet()) {

			Response response = responseMap.get(programId);
			assertEquals(response.getStatusLine(), "application/json");
	}

	@Given("User sends request with non existing data to update Program id")
	public void user_sends_request_with_non_existing_data_to_update_program_id() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("Verify response body validation with error code")
	public void verify_response_body_validation_with_error_code() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends the request body with non existing Program Id")
	public void user_sends_the_request_body_with_non_existing_program_id() {
		fetch all headers
		Headers allheaders = response.headers();
		for (Header header : allheaders) {
			System.out.println(header.getName() + " " + header.getValue());
		}

		// logger.info("Validate the Status Line");
		for (String programId : responseMap.keySet()) {

			Response response = responseMap.get(programId);
			assertEquals(response.getStatusLine(), "application/json");
	}

	@Given("User sends empty input fields to update Program id")
	public void user_sends_empty_input_fields_to_update_program_id() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends request body with empty input data to update Id")
	public void user_sends_request_body_with_empty_input_data_to_update_id() {
		fetch all headers
		Headers allheaders = response.headers();
		for (Header header : allheaders) {
			System.out.println(header.getName() + " " + header.getValue());
		}

		// logger.info("Validate the Status Line");
		for (String programId : responseMap.keySet()) {

			Response response = responseMap.get(programId);
			assertEquals(response.getStatusLine(), "application/json");
	}


 
 

  
  
  




}
