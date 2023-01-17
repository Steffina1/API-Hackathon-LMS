package stepDefinitions;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;

import com.nn.api.base.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateProgram extends BaseClass {

	public static RequestSpecification httpRequest;
	public static Response response;

	// already provided in base class
	public static String responsebody;
	public static String programId;
	public static String programName;
	public static String programDescription;
	public static String programStatus;
	public static String creationTime;
	public static String lastModTime;

	Map<String, Response> responseMap = new HashMap<String, Response>(); // create Hashmap object for storing response
	Map<String, String[]> dataMap = new HashMap<String, String[]>();// create another Hashmap object for getting the key
																	// for rows-->hash map set
	String[][] progamData;

	@Given("User sends request with its endpoint")
	public void user_sends_request_with_its_endpoint() {

		RestAssured.baseURI = baseUrl;
	}

	@When("User sends a request body with valid endpoint to create Program.")
	public void user_sends_a_request_body_with_valid_endpoint_to_create_program() throws Exception {

		
		rownum = com.nn.api.utils.ExcelUtils.getRowCount(baseprogramExcelPath, "programdata");

		colnum = com.nn.api.utils.ExcelUtils.getCellCount(baseprogramExcelPath, "programdata", 1);

		System.out.println("row count:" + rownum);
		System.out.println("col count:" + colnum);

		String[][] progamData = new String[rownum][colnum];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) {

				progamData[i - 1][j] = com.nn.api.utils.ExcelUtils.getCellData(baseprogramExcelPath, "programdata", i,
						j); // i =1, j=0 --> first cell value

			}
		}
	
		JSONObject params = new JSONObject();
		for (String[] row : progamData) {
			params.put("programName", row[0]);
			params.put("programDescription", row[1]);
			params.put("programStatus", row[2]);
			params.put("creationTime", row[3]);
			params.put("lastModTime", row[4]);

			httpRequest = given().auth().none().contentType("application/json");// request
			httpRequest.body(params.toJSONString());
			response = httpRequest.request(Method.POST, baseprogramEndpoint);// response
			response.then().log().all();

			String programId = response.jsonPath().getString("programId");
			responseMap.put(programId, response);// use hashmap to store response
			dataMap.put(programId, row);// use another hashmap datamap to read all values by row*/

		}

	}

	@Then("Validate response status <{int}>.")
	public void validate_response_status(Integer statuscode) {

		System.out.println("=================Status Code Validation=============");
		for (String programId : responseMap.keySet()) {
			
			Response response = responseMap.get(programId);
			assertEquals(response.getStatusCode(), statuscode);

		}
	}

	@Then("Validate response body for programName,programDescription,programStatus")
	public void validate_response_body_for_program_name_program_description_program_status() {

		// response body validation
		System.out.println("=========Responsebody in POST Request=========");
		// Response body validation
		Assert.assertTrue(responsebody != null);// check response body not equal to null
		String responsebody = response.getBody().asString();
		System.out.println("Response body:" + responsebody);

		System.out.println("==========Validate Program Id==================");
		String programId = response.jsonPath().getString("programId");
		Assert.assertEquals(responsebody.contains(programId), true);

		System.out.println("==========Validate Program Name==================");
		String programName = response.jsonPath().getString("programName");
		Assert.assertEquals(responsebody.contains(programName), true);

		System.out.println("==========Validate Program Description==================");
		String programDescription = response.jsonPath().getString("programDescription");
		Assert.assertEquals(responsebody.contains(programDescription), true);

		System.out.println("==========Validate Program Status==================");
		String programStatus = response.jsonPath().getString("programStatus");
		Assert.assertEquals(responsebody.contains(programStatus), true);

		System.out.println("==========Validate Creation Time==================");
		String creationTime = response.jsonPath().getString("creationTime");
		Assert.assertEquals(responsebody.contains(creationTime), true);

		System.out.println("==========Validate last modification Time==================");
		String lastModTime = response.jsonPath().getString("lastModTime");
		Assert.assertEquals(responsebody.contains(lastModTime), true);
		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
	}

	@Then("Validate response headers should be application json format")
	public void validate_response_headers_should_be_application_json_format() {

		// fetch all headers
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

	@Then("Verify response body schema {string} in json format.")
	public void verify_response_body_in_json_format(String Schema) {

		System.out.println("=========SCHEMA VALIDATION ========");
		// logger.info("Validate Json Schema");
		for (String programId : responseMap.keySet()) {
			Response response = responseMap.get(programId);
			response.then().assertThat().body(matchesJsonSchemaInClasspath(Schema));
		}
		System.out.println("=======SCHEMA VALIDATION SUCCESSFULL=========");
	}

	@Then("Verify the values are present in DB")
	public void verify_the_values_are_present_in_db() {
		System.out.println("============DB Validation=============");
		String ResponseDBCheck = given().auth().none().when().get().getBody().asString();

		for (String programIdfromDB : dataMap.keySet()) {

			String[] row = dataMap.get(programIdfromDB);

			response = httpRequest.request(Method.GET,
					"https://lms-backend-service.herokuapp.com/lms/allPrograms" + "/" + programIdfromDB);
			response.then().log().all();

			// assert the value from DB and excel sheet
			assertEquals(programName, row[0]);
			assertEquals(programDescription, row[1]);
			assertEquals(programStatus, row[2]);
			assertEquals(creationTime, row[3]);
			assertEquals(lastModTime, row[4]);
		}
		System.out.println("=======DB VALIDATION SUCCESSFULL=========");
	}

	@Given("User sends request with its invalid base URL")
	public void user_sends_request_with_its_invalid_base_url() {

		RestAssured.baseURI = "https://lms-backend.herokuapp.com/lms";
	}

	@When("User sends a invalid request to create Program.")
	public void user_sends_a_invalid_request_to_create_program() throws Exception {

		response = httpRequest.request(Method.GET, baseUrl + "/save");
		response.then().log().all();

	}

	@When("User sends request with invalid endpoint")
	public void user_sends_request_with_invalid_endpoint() throws Exception {

		response = httpRequest.request(Method.POST, baseUrl + "/save");// response
		response.then().log().all();

	}

	@Then("User sends a request with invalid endpoint which exists.")
	public void user_sends_a_request_body_with_invalid_endpoint_which_exists() {
		
		response = httpRequest.request(Method.POST, baseUrl + "/allPrograms");
		
	}

	@Then("User sends request body with invalid end point which does not exists.")
	public void user_sends_request_body_with_invalid_end_point_which_does_not_exists() {
		
		response = httpRequest.request(Method.POST, baseUrl + "/123");
	}

	
	
	@Then("User sends the request body with existing Program.")
	public void user_sends_the_request_body_with_existing_program() {

	}

	@When("User sends request with Program status as random value to create Program")
	public void user_sends_request_with_program_status_as_random_value_to_create_program() throws Exception {
		
		rownum = com.nn.api.utils.ExcelUtils.getRowCount(baseprogramExcelPath, "statuscode400");
		colnum = com.nn.api.utils.ExcelUtils.getCellCount(baseprogramExcelPath, "statuscode400", 1);

		System.out.println("row count:"+rownum);
		System.out.println("col count:"+colnum);

		String[][] progamData = new String[rownum][colnum];

		for(int i=1;i<=rownum;i++)
		{
			for(int j=0; j<colnum; j++) {

				progamData[i-1][j] = com.nn.api.utils.ExcelUtils.getCellData(baseprogramExcelPath, "statuscode400", i, j); //i =1, j=0 --> first cell value

			}
		}
		
		
		JSONObject params = new JSONObject();
		for(String[] row : progamData) {
			params.put("programName", row[0]);
			params.put("programDescription", row[1]);
			params.put("programStatus", row[2]);
			params.put("creationTime", row[3]);
			params.put("lastModTime", row[4]);
			
			httpRequest = given()
							.auth()
								.none()
							.contentType("application/json");//request
			httpRequest.body(params.toJSONString());
			response = httpRequest.request(Method.POST, "https://lms-backend-service.herokuapp.com/lms/saveprogram");//response
			response.then().log().all();

			String programId = response.jsonPath().getString("programId");
			responseMap.put(programId, response);//use hashmap to store response
			dataMap.put(programId, row);//use another hashmap datamap to read all values by row*/

		}

	}

	@When("User able to save program without required fields entity")
	public void user_able_to_save_program_without_required_fields_entity() throws Exception {

		readDatastatuscode400();
		JSONObject params = new JSONObject();
		for (String[] row : progamData) {
			params.put("programName", row[0]);
			params.put("programDescription", row[1]);
			params.put("programStatus", row[2]);
			params.put("creationTime", row[3]);
			params.put("lastModTime", row[4]);

			httpRequest = given().auth().none().contentType("application/json");// request
			httpRequest.body(params.toJSONString());
			response = httpRequest.request(Method.POST, baseprogramEndpoint);// response
			response.then().log().all();

			String programId = response.jsonPath().getString("programId");
			responseMap.put(programId, response);// use hashmap to store response
			dataMap.put(programId, row);// use another hashmap datamap to read all values by row*/

		}
	}

	@Then("User sends request body with Program status as random value")
	public void user_sends_request_body_with_program_status_as_random_value() throws Exception {
		readDatastatuscode400();
		JSONObject params = new JSONObject();
		for (String[] row : progamData) {
			params.put("programName", row[0]);
			params.put("programDescription", row[1]);
			params.put("programStatus", row[2]);
			params.put("creationTime", row[3]);
			params.put("lastModTime", row[4]);

			httpRequest = given().auth().none().contentType("application/json");// request
			httpRequest.body(params.toJSONString());
			response = httpRequest.request(Method.POST, baseprogramEndpoint);// response
			response.then().log().all();

			String programId = response.jsonPath().getString("programId");
			responseMap.put(programId, response);// use hashmap to store response
			dataMap.put(programId, row);// use another hashmap datamap to read all values by row*/
		}

	}

	@Then("Validate response body with error code {string} and message as {string}")
	public void validate_response_body_with_error_code_and_message_as(String string, String string2) {

	}

	@When("User sends request with Creation Time as invalid format")
	public void user_sends_request_with_creation_time_as_invalid_format() throws Exception {
		readDatastatuscode400();
		JSONObject params = new JSONObject();
		for (String[] row : progamData) {
			params.put("programName", row[0]);
			params.put("programDescription", row[1]);
			params.put("programStatus", row[2]);
			params.put("creationTime", row[3]);
			params.put("lastModTime", row[4]);

			httpRequest = given().auth().none().contentType("application/json");// request
			httpRequest.body(params.toJSONString());
			response = httpRequest.request(Method.POST, baseprogramEndpoint);// response
			response.then().log().all();

			String programId = response.jsonPath().getString("programId");
			responseMap.put(programId, response);// use hashmap to store response
			dataMap.put(programId, row);// use another hashmap datamap to read all values by row*/

		}
	}

	@Then("User sends request body with Creation Time as invalid format")
	public void user_sends_request_body_with_creation_time_as_invalid_format() throws Exception {
		readDatastatuscode400();
		JSONObject params = new JSONObject();
		for (String[] row : progamData) {
			params.put("programName", row[0]);
			params.put("programDescription", row[1]);
			params.put("programStatus", row[2]);
			params.put("creationTime", row[3]);
			params.put("lastModTime", row[4]);

			httpRequest = given().auth().none().contentType("application/json");// request
			httpRequest.body(params.toJSONString());
			response = httpRequest.request(Method.POST, baseprogramEndpoint);// response
			response.then().log().all();

			String programId = response.jsonPath().getString("programId");
			responseMap.put(programId, response);// use hashmap to store response
			dataMap.put(programId, row);// use another hashmap datamap to read all values by row*/

		}
	}

	@When("User able to save program with null values")
	public void user_able_to_save_program_with_null_values() throws Exception {
		readDatastatuscode400();
		JSONObject params = new JSONObject();
		for (String[] row : progamData) {
			params.put("programName", row[0]);
			params.put("programDescription", row[1]);
			params.put("programStatus", row[2]);
			params.put("creationTime", row[3]);
			params.put("lastModTime", row[4]);

			httpRequest = given().auth().none().contentType("application/json");// request
			httpRequest.body(params.toJSONString());
			response = httpRequest.request(Method.POST, baseprogramEndpoint);// response
			response.then().log().all();

			String programId = response.jsonPath().getString("programId");
			responseMap.put(programId, response);// use hashmap to store response
			dataMap.put(programId, row);// use another hashmap datamap to read all values by row*/

		}
	}

	@Then("Validate response body with error code {string} and error message")
	public void validate_response_body_with_error_code_and_error_message(String string) {

		String responsebody = response.getBody().asString();
		System.out.println("Response body:" + responsebody);
		Assert.assertEquals(responsebody.contains("status"), true);
		String errorMessage = response.jsonPath().getString("Message");
		Assert.assertEquals(responsebody.contains("Message"), true);
	}

	@Then("Validate response body with error code <{int}> and error message {string}")
	public void validate_response_body_with_error_code_and_message_as(Integer error, String status) {

		String responsebody = response.getBody().asString();
		System.out.println("Response body:" + responsebody);
		Assert.assertEquals(responsebody.contains("status"), true); // validate status
		Assert.assertEquals(responsebody.contains("error"), true); // validate error

	}

}
// =============================stop==========================================================/
