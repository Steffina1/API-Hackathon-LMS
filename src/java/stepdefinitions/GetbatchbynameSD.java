package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReader;
import utilities.Excelreader;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import commonclass.CommonMthods;

public class GetbatchbynameSD {
	RequestSpecification request;
	Response response;
	ConfigReader reader;
	Excelreader excel;
	CommonMthods cmn;
	Logger logs = LogManager.getLogger(GetbatchbynameSD.class);
	public GetbatchbynameSD(CommonMthods cmn) {
		this.cmn=cmn;
	}
	@Description("Response validation for getting batch by name")
	@Severity(SeverityLevel.NORMAL)
	@Feature("Test batches by name using GET")
	@Given("The user sends base url as a part of request to get batch by name")
	public void the_user_sends_base_url_as_a_part_of_request_to_get_batch_by_name() throws IOException {
		logs.info("User sends base url to get batch by name");
		 request = given().baseUri(cmn.geturl());
	}

	@Then("The content type of response header should be application\\/json to get batch by name")
	public void the_content_type_of_response_header_should_be_application_json_to_get_batch_by_name() throws IOException {
		reader = new ConfigReader();
		 response= request.get(cmn.batchbynameendpoint());
		 response.then().assertThat().header("Content-Type", reader.contenttypejson());
	}

	@When("User sends request with valid endpoint to get batch by name")
	public void user_sends_request_with_valid_endpoint_to_get_batch_by_name() throws IOException {
		logs.info("User sends valid endpoint to get batch by name");
		 response= request.get(cmn.batchbynameendpoint());
		System.out.println(response.getBody().asString());
	}

	@Then("User should receive response with success status code for batch by name")
	public void user_should_receive_response_with_success_status_code_for_batch_by_name() throws IOException {
		reader = new ConfigReader();
		response.then().assertThat().statusCode(reader.successcode());
	}

	@Then("The response body should contain all the details of the requested batch from {string} {string}")
	public void the_response_body_should_contain_all_the_details_of_the_requested_batch_from(String testid, String sheetname) {
		excel = new Excelreader(testid, sheetname);
		String responsebody = response.getBody().asString();
		  if(responsebody.equalsIgnoreCase(excel.readdatafromexcel())) {
			  assertTrue(true);
		  }
		}

	@Then("The status line should be HTTP\\/{double} {int} for batch by name")
	public void the_status_line_should_be_http_for_batch_by_name(Double double1, Integer int1) throws IOException {
		reader = new ConfigReader();
		response.then().assertThat().statusLine(reader.successsl());
	}

	@When("User sends POST, PUT, PATCH, DELETE requests with valid endpoint for batch by name")
	public void user_sends_post_put_patch_delete_requests_with_valid_endpoint_for_batch_by_name() throws IOException {
		logs.info("User sends invalid request to get batch by name");
		 response= request.post(cmn.batchbynameendpoint());
		 response= request.put(cmn.batchbynameendpoint());
		 response= request.patch(cmn.batchbynameendpoint());
		 response= request.delete(cmn.batchbynameendpoint());
	}

	@Then("User should receive response with Method Not Allowed status code for batch by name")
	public void user_should_receive_response_with_method_not_allowed_status_code_for_batch_by_name() throws IOException {
		reader = new ConfigReader();
		response.then().assertThat().statusCode(reader.notallowedsc());
	}

	@Then("The response body should contain error message Method Not Allowed for batch by name from {string} {string}")
	public void the_response_body_should_contain_error_message_method_not_allowed_for_batch_by_name_from(String testid, String sheetname) {
		excel = new Excelreader(testid, sheetname);
		 String respbody= response.getBody().asString();
		 if(respbody.contains(excel.readdatafromexcel())) {
			 assertTrue(true);
			 System.out.println(respbody);
		 }
	}

	@Then("The status line should be HTTP\\/{double} {int} for metod not allowed batch by name")
	public void the_status_line_should_be_http_for_metod_not_allowed_batch_by_name(Double double1, Integer int1) throws IOException {
		reader = new ConfigReader();
		response.then().assertThat().statusLine(reader.methodnotallowedsl());
	}

	@When("User sends request with invalid endpoint for batch by name from {string} {string}")
	public void user_sends_request_with_invalid_endpoint_for_batch_by_name_from(String testid, String sheetname) {
		logs.info("User sends invalid endpoint to get batch by name");
		excel = new Excelreader(testid, sheetname);
		response = request.get(excel.readdatafromexcel());
	}

	@Then("User should receive response with Bad Request status code for batch by name")
	public void user_should_receive_response_with_bad_request_status_code_for_batch_by_name() throws IOException {
		reader = new ConfigReader();
		response.then().assertThat().statusCode(reader.badrequestsc());
	}

	@Then("The response body should contain error message ENTITY_DOES_NOT_EXIST for the batch by name")
	public void the_response_body_should_contain_error_message_entity_does_not_exist_for_the_batch_by_name() throws IOException {
		reader = new ConfigReader();
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("errorCode", reader.errorcodegetbatchbyname());
		maps.put("errorMessage", reader.errormsggetbatchbyname());
		  for(String x : maps.keySet()) {
			  if(maps.get(x).equalsIgnoreCase(reader.errorcodegetbatchbyname())) {
				  System.out.println("Error code matches");
			  } 
			  if(maps.get(x).equalsIgnoreCase(reader.errormsggetbatchbyname())) {
				  System.out.println("errorMessage matches");
			  }
		  }
	}

	@Then("The status line should be HTTP\\/{double} {int} for the batch by name")
	public void the_status_line_should_be_http_for_the_batch_by_name(Double double1, Integer int1) throws IOException {
		reader = new ConfigReader();
		response.then().assertThat().statusLine(reader.badrequestsl());
	}
	@Then("The JSON schema should match the response to get batch by name")
	public void the_json_schema_should_match_the_response_to_get_batch_by_name() throws IOException {
		response.then().assertThat().body(matchesJsonSchemaInClasspath("jsonschemalms.json")).statusCode(200);
	}

}
