package com.lmshackathon.stepdefiniton;


import static org.testng.Assert.assertTrue;

import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testrun.Readconfig;

public class GetBatchesByID {
	Readconfig reader;
	RequestSpecification request;
	Response response;
	
	@Given("User sends request with base url")
public void user_sends_request_with_base_url() {
   reader=new Readconfig();
   
		
		RestAssured.baseURI=reader.Baseurl();
	request=RestAssured.given();
}


@Then("The content type of response header should be application\\/json to get all BatchID")
public void the_content_type_of_response_header_should_be_application_json_to_get_all_batch_id() {
	reader=new Readconfig();
	response=request.get(reader.EndpointBatchID());
	response.then().assertThat().header("Content-Type",reader.contenttype());
}

@When("User send a request with valid endpoint to get batchID")
public void user_send_a_request_with_valid_endpoint_to_get_batch_id() {
	reader=new Readconfig();
	response=request.get(reader.epbatchbyid());
	System.out.println("validendpoint");
}

@Then("User should receive response with success status code for Batchid")
public void user_should_receive_response_with_success_status_code_for_batchid() {
	reader=new Readconfig();
	
    response.then().assertThat().statusCode(reader.successcode());
    System.out.println("ststuscode");
}

@Then("Validate Get batch by ID Schema in json format")
public void validate_get_batch_by_id_schema_in_json_format() {
	String responsebody=response.getBody().asString();
	if( responsebody.contains("batchStatus")) {
		assertTrue(true);
		System.out.println( responsebody);
		System.out.println("schema");
	}	
}

@Then("The status line should beHTTP\\/{double} 200for valid batchid")
public void the_status_line_should_be_http_200for_valid_batchid(Double double1) {
	reader=new Readconfig();
	
	response.then().assertThat().statusLine(reader.successsl());
	System.out.println("http");
			
	
}

@When("User send a invalid request with valid endpoint")
public void user_send_a_invalid_request_with_valid_endpoint() {
	reader=new Readconfig();
	response=request.post(reader.invalidreqbatchid());
}

@Then("Verify response with status code '{int} Method Not Allowed")
public void verify_response_with_status_code_method_not_allowed(Integer int1) {
	reader=new Readconfig();
	response=request.get(reader.invalidreqbatchid());
    response.then().assertThat().statusCode(reader.notallowedsc());
}

@Then("The response body should contain error message")
public void the_response_body_should_contain_error_message() {
	String responsebody=response.getBody().asString();
	if(responsebody.contains("Method Not Allowed")){
	assertTrue(true);
	System.out.println("responsebody");
	}
}

@Then("The status line should be HTTP\\/{double} {int} for invalid request")
public void the_status_line_should_be_http_for_invalid_request(Double double1, Integer int1) {
	reader=new Readconfig();
	response.then().assertThat().statusLine(reader.invalidreqbatchsl());
}

@When("User send a invalid endpoint with valid request")
public void user_send_a_invalid_endpoint_with_valid_request() {
	reader=new Readconfig();

	response=request.get(reader.invalidendbatchid());
}

@Then("Verify response with status code '{int} Bad Request")
public void verify_response_with_status_code_bad_request(Integer int1) {
	reader=new Readconfig();
	response=request.get(reader.invalidendbatchid());
    response.then().assertThat().statusCode(reader.invalidendbatchssc());
}

@Then("The response body should contain {string}")
public void the_response_body_should_contain(String string) {
	String responsebody=response.getBody().asString();
	if(responsebody.contains("ENTITY_DOES_NOT_EXIST")){
	assertTrue(true);
	System.out.println("responsebody");
	}
}

@Then("The status line should be HTTP\\/{double} {int} for invalid endpoint")
public void the_status_line_should_be_http_for_invalid_endpoint(Double double1, Integer int1) {
	reader=new Readconfig();
	response.then().assertThat().statusLine(reader.invalidendbatchsscline());
}
}


}
