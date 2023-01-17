package com.lmshackathon.stepdefiniton;


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

public class CreateBatch {
	
	public static RequestSpecification httpRequest;
	public static Response response;

	// already provided in base class
	public static String responsebody;
	public static String batchId;
	public static String batchName;
	public static String batchDescription;
	public static String batchNoOfClasses;
	public static String programId;

	Map<String, Response> responseMap = new HashMap<String, Response>(); // create Hashmap object for storing response
	Map<String, String[]> dataMap = new HashMap<String, String[]>();// create another Hashmap object for getting the key
																	// for rows-->hash map set
	String[][] batchData;

	@When("User sends request  body to create Batch.")
	public void user_sends_request_body_to_create_batch() throws Exception {
		rownum = com.nn.api.utils.ExcelUtils.getRowCount(basebatchesExcelpath, "batchdata");

		colnum = com.nn.api.utils.ExcelUtils.getCellCount(basebatchesExcelpath, "batchdata", 1);

		System.out.println("row count:" + rownum);
		System.out.println("col count:" + colnum);

		String[][] batchData = new String[rownum][colnum];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) {

				batchData[i - 1][j] = com.nn.api.utils.ExcelUtils.getCellData(basebatchesExcelpath, "batchdata", i, j); // i
																														// =1,
																														// j=0
																														// -->
																														// first
																														// cell
																														// value

			}
		}

		JSONObject params = new JSONObject();
		for (String[] row : batchData) {
			params.put("batchName", row[0]);
			params.put("batchDescription", row[1]);
			params.put("batchStatus", row[2]);
			params.put("batchNoOfClasses", row[3]);
			params.put("programId", row[4]);

			httpRequest = given().auth().none().contentType("application/json");// request
			httpRequest.body(params.toJSONString());
			response = httpRequest.request(Method.POST, basebatchesEndpoint);// response
			response.then().log().all();

			String batchId = response.jsonPath().getString("batchId");
			responseMap.put(batchId, response);// use hashmap to store response
			dataMap.put(batchId, row);// use another hashmap datamap to read all values by row*/

		}

	}

	@Then("Validate response body for batchName,batchDescription,batchStatus")
	public void validate_response_body_for_batch_name_batch_description_batch_status() {
		// response body validation
		System.out.println("=========Responsebody in POST Request=========");
		// Response body validation
		Assert.assertTrue(responsebody != null);// check response body not equal to null
		String responsebody = response.getBody().asString();
		System.out.println("Response body:" + responsebody);

		System.out.println("==========Validate batch Id==================");
		String batchId = response.jsonPath().getString("batchId");
		Assert.assertEquals(responsebody.contains(batchId), true);

		System.out.println("==========Validate batch Name==================");
		String batchName = response.jsonPath().getString("batchName");
		Assert.assertEquals(responsebody.contains(batchName), true);

		System.out.println("==========Validate batch Description==================");
		String batchDescription = response.jsonPath().getString("batchDescription");
		Assert.assertEquals(responsebody.contains(batchDescription), true);

		System.out.println("==========Validate batch no of classes==================");
		String batchNoOfClasses = response.jsonPath().getString("batchNoOfClasses");
		Assert.assertEquals(responsebody.contains(batchNoOfClasses), true);

		System.out.println("==========Validate program id==================");
		String programId = response.jsonPath().getString("programId");
		Assert.assertEquals(responsebody.contains(programId), true);

		System.out.println("==========Validate program name==================");
		String programName = response.jsonPath().getString("programName");
		Assert.assertEquals(responsebody.contains(programName), true);

		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
	}

	@Then("Verify batch response body validation with error code {int} and error message {string}.")
	public void verify_batchresponse_body_validation_with_error_code_and_error_message(Integer errorcode,
			String errormessage) {

		String responsebody = response.getBody().asString();
		System.out.println("Response body:" + responsebody);
		Assert.assertEquals(responsebody.contains("status"), true);
		String errorMessage = response.jsonPath().getString("Message");
		Assert.assertEquals(responsebody.contains("Message"), true);
	}

	@Then("Validate response body with status and error {string}")
	public void Validate_response_body_with_status_and_error(String error) {
		String responsebody = response.getBody().asString();
		System.out.println("Response body:" + responsebody);
		Assert.assertEquals(responsebody.contains("status"), true);
		String errorMessage = response.jsonPath().getString("error");
		Assert.assertEquals(responsebody.contains("error"), true);
	}

	@Then("Validate batch response status<{int}>.")
	public void validate_batch_response_status(Integer statuscode) {

		System.out.println("=================Status Code Validation=============");

		assertEquals(response.getStatusCode(), statuscode);

	}

	@Then("Validate batch response headers should be application json format")
	public void validate_batch_response_headers_should_be_application_json_format() {
		// fetch all headers
		Headers allheaders = response.headers();
		for (Header header : allheaders) {
			System.out.println(header.getName() + " " + header.getValue());
		}

		// logger.info("Validate the Status Line");
		for (String batchId : responseMap.keySet()) {

			Response response = responseMap.get(batchId);
			assertEquals(response.getStatusLine(), "application/json");
		}
	}

	@Then("Verify response body schema in batch {string} in json format")
	public void verify_response_body_schema_in_batch_in_json_format(String schema) {
		System.out.println("=========SCHEMA VALIDATION ========");
		// logger.info("Validate Json Schema");
		for (String batchId : responseMap.keySet()) {
			Response response = responseMap.get(batchId);
			response.then().assertThat().body(matchesJsonSchemaInClasspath(schema));
		}
		System.out.println("=======SCHEMA VALIDATION SUCCESSFULL=========");
	}

	@Then("Verify the batch values are present in DB")
	public void verify_the_batch_values_are_present_in_db() {
		System.out.println("============DB Validation=============");
		String ResponseDBCheck = given().auth().none().when().get().getBody().asString();

		for (String batchIdfromDB : dataMap.keySet()) {

			String[] row = dataMap.get(batchIdfromDB);

			response = httpRequest.request(Method.GET,
					"https://lms-backend-service.herokuapp.com/lms/allPrograms" + "/" + batchIdfromDB);
			response.then().log().all();

			// assert the value from DB and excel sheet
			assertEquals(batchName, row[0]);
			assertEquals(batchDescription, row[1]);
			// assertEquals(batchStatus, row[2]);
			assertEquals(batchNoOfClasses, row[3]);
			assertEquals(programId, row[4]);
		}
		System.out.println("=======DB VALIDATION SUCCESSFULL=========");

	}

	@Then("Validate batch response body with {string} and sucess message {string}.")
	public void validate_batch_response_body_with_and_sucess_message(String message, String successmessage) {

		String responsebody = response.getBody().asString();
		System.out.println("Response body:" + responsebody);
		Assert.assertEquals(responsebody.contains(message), true);

		String errorMessage = response.jsonPath().getString("sucess");
		Assert.assertEquals(responsebody.contains(successmessage), true);
	}

	@When("User sends request  without request body")
	public void user_sends_request_without_request_body() {

		httpRequest = given().auth().none().contentType("application/json");// request
		response = httpRequest.request(Method.POST, basebatchesEndpoint);// response
		response.then().log().all();
	}

	@When("User able to save batch without required fields entity")
	public void user_able_to_save_batch_without_required_fields_entity() throws Exception {
		rownum = com.nn.api.utils.ExcelUtils.getRowCount(basebatchesExcelpath, "batcherrordata");

		colnum = com.nn.api.utils.ExcelUtils.getCellCount(basebatchesExcelpath, "batcherrordata", 1);

		System.out.println("row count:" + rownum);
		System.out.println("col count:" + colnum);

		String[][] batchData = new String[rownum][colnum];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) {

				batchData[i - 1][j] = com.nn.api.utils.ExcelUtils.getCellData(basebatchesExcelpath, "batcherrordata", i,
						j); // i =1, j=0 --> first cell value

			}
		}

		JSONObject params = new JSONObject();
		for (String[] row : batchData) {
			params.put("batchName", row[0]);
			params.put("batchDescription", row[1]);
			params.put("batchStatus", row[2]);
			params.put("batchNoOfClasses", row[3]);
			params.put("programId", row[4]);

			httpRequest = given().auth().none().contentType("application/json");// request
			httpRequest.body(params.toJSONString());
			response = httpRequest.request(Method.POST, basebatchesEndpoint);// response
			response.then().log().all();

			String batchId = response.jsonPath().getString("batchId");
			responseMap.put(batchId, response);// use hashmap to store response
			dataMap.put(batchId, row);// use another hashmap datamap to read all values by row*/

		}

	}

	@When("User sends request  body with batch as null value to create batch")
	public void user_sends_request_body_with_batch_as_null_value_to_create_batch() throws Exception {
		rownum = com.nn.api.utils.ExcelUtils.getRowCount(basebatchesExcelpath, "batcherrordata");

		colnum = com.nn.api.utils.ExcelUtils.getCellCount(basebatchesExcelpath, "batcherrordata", 1);

		System.out.println("row count:" + rownum);
		System.out.println("col count:" + colnum);

		String[][] batchData = new String[rownum][colnum];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) {

				batchData[i - 1][j] = com.nn.api.utils.ExcelUtils.getCellData(basebatchesExcelpath, "batcherrordata", i,
						j); // i =1, j=0 --> first cell value

			}
		}

		JSONObject params = new JSONObject();
		for (String[] row : batchData) {
			params.put("batchName", row[0]);
			params.put("batchDescription", row[1]);
			params.put("batchStatus", row[2]);
			params.put("batchNoOfClasses", row[3]);
			params.put("programId", row[4]);

			httpRequest = given().auth().none().contentType("application/json");// request
			httpRequest.body(params.toJSONString());
			response = httpRequest.request(Method.POST, basebatchesEndpoint);// response
			response.then().log().all();

			String batchId = response.jsonPath().getString("batchId");
			responseMap.put(batchId, response);// use hashmap to store response
			dataMap.put(batchId, row);// use another hashmap datamap to read all values by row*/

		}

	}
}
