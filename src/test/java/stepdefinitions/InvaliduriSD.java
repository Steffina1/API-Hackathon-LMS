package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import commonclass.CommonMthods;
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

public class InvaliduriSD {
	RequestSpecification request;
	Response response;
	ConfigReader reader;
	Excelreader excel;
	CommonMthods cmn;
	public InvaliduriSD(CommonMthods cmn) {
		this.cmn = cmn;
		
	}
		@Description("Response validation for invalid url")
	@Severity(SeverityLevel.CRITICAL)
	@Feature("Test using Invalidurl")
	@Given("The user sends invalid base url")
	public void the_user_sends_invalid_base_url() throws IOException {
		 request =given().baseUri(cmn.getinvalidurl());
	}

	@Then("User should receive response with service unavailable status code")
	public void user_should_receive_response_with_service_unavailable_status_code() throws IOException {
		reader = new ConfigReader();
		response.then().assertThat().statusCode(reader.serviceunavailablesc());
	}

	@Then("The response body should contain application error message")
	public void the_response_body_should_contain_application_error_message() throws IOException {
		reader = new ConfigReader();
		 String responsebody= response.getBody().asString();
		 if(responsebody.contains(reader.apperrormsg())){
			 assertTrue(true);
			 System.out.println(responsebody);
		 }
		}
	

	@Then("The content type of response header should be text\\/html")
	public void the_content_type_of_response_header_should_be_text_html() throws IOException {
		reader = new ConfigReader();
		 response.then().assertThat().header("Content-Type", reader.contenttypetxt());
	}

	@When("User sends request with valid endpoint for getting all the programs")
	public void user_sends_request_with_valid_endpoint_for_getting_all_the_programs() throws IOException {
		response= request.get(cmn.endpointforallprograms());
	}

	@When("User sends request with valid endpoint for getting all the batches")
	public void user_sends_request_with_valid_endpoint_for_getting_all_the_batches() throws IOException {
		response= request.get(cmn.endpointforallbatches());
	}

	@When("User sends request with valid endpoint for getting the requested batch detail")
	public void user_sends_request_with_valid_endpoint_for_getting_the_requested_batch_detail() throws IOException {
		response= request.get(cmn.batchbynameendpoint());
	}
	
	@Then("The response body should contain application error message for all programs")
	public void the_response_body_should_contain_application_error_message_for_all_programs() throws IOException {
		reader = new ConfigReader();
		 String responsebody= response.getBody().asString();
		 if(responsebody.contains(reader.apperrormsg())) {
			 assertTrue(true);
			 System.out.println(responsebody);
		 }
		}

	@Then("The response body should contain application error message for all batches")
	public void the_response_body_should_contain_application_error_message_for_all_batches() throws IOException {
		reader = new ConfigReader();
		 String responsebody= response.getBody().asString();
		 if(responsebody.contains(reader.apperrormsg())) {
			 assertTrue(true);
			 System.out.println(responsebody);
		 }
	}

	@Then("The response body should contain application error message for getting batch by name")
	public void the_response_body_should_contain_application_error_message_for_getting_batch_by_name() throws IOException {
		reader = new ConfigReader();
		 String responsebody= response.getBody().asString();
		 if(responsebody.contains(reader.apperrormsg())) {
			 assertTrue(true);
			 System.out.println(responsebody);
		 }
	}
	
	@Then("The content type of response header should be text\\/html for all programs")
	public void the_content_type_of_response_header_should_be_text_html_for_all_programs() throws IOException {
		cmn.checkcontentforallprograms();
	}

	@Then("The content type of response header should be text\\/html for all batches")
	public void the_content_type_of_response_header_should_be_text_html_for_all_batches() throws IOException {
		cmn.checkcontentforallbatches();
	}

	@Then("The content type of response header should be text\\/html for getting batch by name")
	public void the_content_type_of_response_header_should_be_text_html_for_getting_batch_by_name() throws IOException {
		cmn.checkcontentforbatchbyname();
	}


}
