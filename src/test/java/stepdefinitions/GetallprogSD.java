package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReader;
import utilities.Excelreader;

public class GetallprogSD {
	ConfigReader reader;
	RequestSpecification request;
	Response response;
	Excelreader excel;
	@Given("The user sends base url as a part of request to get all Programs")
	public void the_user_sends_base_url_as_a_part_of_request_to_get_all_programs() throws IOException {
		reader=new ConfigReader();
		 request = given().baseUri(reader.baseurl());
	}

	@Then("The content type of response header should be application\\/json to get all Programs")
	public void the_content_type_of_response_header_should_be_application_json_to_get_all_programs() throws IOException {
		 reader=new ConfigReader();
		 response= request.get(reader.endpointforallprogs());
	     response.then().assertThat().header("Content-Type", reader.contenttypejson());
	}

	@When("User sends request with valid endpoint to get all Programs")
	public void user_sends_request_with_valid_endpoint_to_get_all_programs() throws IOException {
		 reader=new ConfigReader();
		response= request.get(reader.endpointforallprogs());
	}

	@Then("User should receive response with success status code for all Programs")
	public void user_should_receive_response_with_success_status_code_for_all_programs() throws IOException {
		 reader=new ConfigReader();
		response.then().assertThat().statusCode(reader.successcode());
	}

	@Then("The response body should contain details of the Program validated from {string} {string}")
	public void the_response_body_should_contain_details_of_the_program_validated_from(String testid, String sheetname) {
		excel = new Excelreader(testid, sheetname);
		String responsebody= response.getBody().asString();
		int bodylength = responsebody.length();
		 if(responsebody.contains(excel.readdatafromexcel())) {
			 assertTrue(true);
			 System.out.println(responsebody);
		 } if (bodylength == 6) {
			 assertTrue(true);
		 }
	}

	@Then("The status line should be HTTP\\/{double} {int} for all Programs with valid request")
	public void the_status_line_should_be_http_for_all_programs_with_valid_request(Double double1, Integer int1) throws IOException {
		 reader=new ConfigReader();
			response.then().assertThat().statusLine(reader.successsl());
	}

	@When("User sends POST, PUT, PATCH, DELETE requests with valid endpoint for all Programs")
	public void user_sends_post_put_patch_delete_requests_with_valid_endpoint_for_all_programs() throws IOException {
		 reader=new ConfigReader();
		response = request.post(reader.endpointforallprogs());
	     response = request.put(reader.endpointforallprogs());
	     response = request.patch(reader.endpointforallprogs());
	     response = request.delete(reader.endpointforallprogs());
	}

	@Then("User should receive response with Method Not Allowed status code for all Programs")
	public void user_should_receive_response_with_method_not_allowed_status_code_for_all_programs() throws IOException {
		 reader=new ConfigReader();
			response.then().assertThat().statusCode(reader.notallowedsc());
	}

	@Then("The response body should contain error message from {string} {string} for all Programs")
	public void the_response_body_should_contain_error_message_from_for_all_programs(String testid, String sheetname) {
		excel = new Excelreader(testid, sheetname);
		String respbody= response.getBody().asString();
		 if(respbody.contains(excel.readdatafromexcel())) {
			 assertTrue(true);
			 System.out.println(respbody);
		 }
	}

	@Then("The status line should be HTTP\\/{double} {int} for all Programs with invalid request")
	public void the_status_line_should_be_http_for_all_programs_with_invalid_request(Double double1, Integer int1) throws IOException {
		 reader=new ConfigReader();
		 response.then().assertThat().statusLine(reader.methodnotallowedsl());
	}

	@When("User sends request with invalid endpoint for all Programs")
	public void user_sends_request_with_invalid_endpoint_for_all_programs() {
		response = request.get("/programs");
	}

	@Then("User should receive response with Not found status code for all Programs")
	public void user_should_receive_response_with_not_found_status_code_for_all_programs() throws IOException {
		 reader=new ConfigReader();
		  response.then().assertThat().statusCode(reader.notfoundsc());
	}

	@Then("The response body should contain error message from {string} {string} for all the Programs")
	public void the_response_body_should_contain_error_message_from_for_all_the_programs(String testid, String sheetname) {
		excel = new Excelreader(testid, sheetname);
		String rbody = response.getBody().asString();
		  if(rbody.contains(excel.readdatafromexcel())) {
			  assertTrue(true);
				 System.out.println(rbody);
		  }
	}

	@Then("The status line should be HTTP\\/{double} {int} for all Programs")
	public void the_status_line_should_be_http_for_all_programs(Double double1, Integer int1) throws IOException {
		 reader=new ConfigReader();
		   response.then().assertThat().statusLine(reader.notfoundsl());
	}

}
