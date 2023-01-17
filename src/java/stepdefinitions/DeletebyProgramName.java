package com.lmshackathon.stepdefiniton;
  import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeletebyProgramName {
  



 RequestSpecification httprequest;
 Response response;
 
 @Given("User sends base URL to delete {string}.")
 public void user_sends_base_url_to_delete(String pgmID) {
  
  RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms/";
  RestAssured.basePath="/deletebyprogname/{programName}";
  httprequest=RestAssured.given().pathParam("programName", pgmID);
 }

 @When("User sends request with valid program name.")
 public void user_sends_request_with_valid_program_name() {
  response=httprequest.delete();
  System.out.println(response.asPrettyString());
     
 }

 @Then("User status code {string}.")
 public void user_status_code(String string) {
  int StatusCode = response.getStatusCode();
     Assert.assertEquals(StatusCode, 200);
 }

 @Then("content type header should be application\\/json")
 public void content_type_header_should_be_application_json() {
  String contentType=response.getHeader("Content-Type");
 Assert.assertEquals(contentType, "application/json");
 }
 @Given("User able to delete a Program Name with invalid method with valid {string}.")
 public void user_able_to_delete_a_program_name_with_invalid_method_with_valid(String pgmName) {
  RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms/";
  RestAssured.basePath="/deletebyprogname/{programName}";
  httprequest=RestAssured.given().pathParam("programName", pgmName);
 }

 @When("User sends a invalid request to delete a Program Name.")
 public void user_sends_a_invalid_request_to_delete_a_program_name() {
  response=httprequest.post();
  System.out.println(response.asPrettyString());
 }

 @Then("Verify response with status code {string}")
 public void verify_response_with_status_code(String string) {
  int StatusCode = response.getStatusCode();
     Assert.assertEquals(StatusCode, 405);
 }

 @Given("User sends the request to to delete a Program Name with invalid end point and with valid {string}..")
 public void user_sends_the_request_to_to_delete_a_program_name_with_invalid_end_point_and_with_valid(String pgmname) {
  RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms/";
  RestAssured.basePath="/deletebyprog/{programName}";
  httprequest=RestAssured.given().pathParam("programName", pgmname);
 }

 @When("User sends a request with invalid end point.")
 public void user_sends_a_request_with_invalid_end_point() {
  response=httprequest.delete();
  System.out.println(response.asPrettyString());
 }

 @Then("User checks response if status code is {string}")
 public void user_checks_response_if_status_code_is(String string) {
  int StatusCode = response.getStatusCode();
  Assert.assertEquals(StatusCode, 404);
 }

 @Given("User sends invalid base url to delete a Program Name.")
 public void user_sends_invalid_base_url_to_delete_a_program_name() {
  RestAssured.baseURI="https://lms-backend-service.herokuapp.com/lms/";
  httprequest=RestAssured.given();
 }

 @When("User sends invalid base url with valid end point")
 public void user_sends_invalid_base_url_with_valid_end_point() {
  response=httprequest.get("https://lms-backend.herokuapp.com/lms/");
  System.out.println("Invalid url");
 }

 @Then("status code should be {string}")
 public void status_code_should_be(String string) {
  int StatusCode = response.getStatusCode();
     Assert.assertEquals(StatusCode,503);
 }


@Given("User sends the request with invalid {string}.")
public void user_sends_the_request_with_invalid(String pgmname) {
 RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms/";
 RestAssured.basePath="/deletebyprogname/{programName}";
 httprequest=RestAssured.given().pathParam("programName", pgmname);
}
@When("User sends valid request with invalid Program Name and its endpoint")
 public void user_sends_valid_request_with_invalid_program_name_and_its_endpoint() {
  response=httprequest.delete();
  System.out.println(response.asPrettyString());
 }
 @Then("Verify response with {string}")
 public void verify_response_with(String string) {
  int StatusCode = response.getStatusCode();
     Assert.assertEquals(StatusCode, 400);
 }




}

}
