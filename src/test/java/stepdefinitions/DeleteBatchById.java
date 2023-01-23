package com.lmshackathon.stepdefiniton;


  

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteBatchById {
 RequestSpecification httprequest;
 Response response;
 
 @Given("User sends base url to delete <{int}>.")
 public void user_sends_base_url_to_delete(Integer BatchId) {
  RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms/";
  RestAssured.basePath="/batches/{id}";
  httprequest=RestAssured.given().pathParam("id", BatchId);
 }

 @When("User sends request to delete with existing batch ID.")
 public void user_sends_request_to_delete_with_existing_batch_id() {
  response=httprequest.delete();
  System.out.println(response.asPrettyString());
    
 }

 @Then("status code should be {string}.")
 public void status_code_should_be(String string) {
  int StatusCode = response.getStatusCode();
     Assert.assertEquals(StatusCode, 200);
    
 }

 @Then("Verify content type header should be application\\/json.")
 public void verify_content_type_header_should_be_application_json() {
  String contentType=response.getHeader("Content-Type");
  Assert.assertEquals(contentType, "application/json");
 }

 @Then("Verify if status line is {string}.")
 public void verify_if_status_line_is(String string) {
  String statusline = response.getStatusLine();
     Assert.assertEquals("HTTP/1.1 200 ", statusline);
 }

 @Given("User sends a invalid request to delete a Batch ID.")
 public void user_sends_a_invalid_request_to_delete_a_batch_id() {
  RestAssured.baseURI="https://lms-backend-service.herokuapp.com/lms/";
  RestAssured.basePath="/batche/{id}";
  httprequest=RestAssured.given().pathParam("id", 3270);
    
 }
 @Given("User sends a invalid request to delete a  <{int}>.")
 public void user_sends_a_invalid_request_to_delete_a(Integer BatchId) {
  RestAssured.baseURI="https://lms-backend-service.herokuapp.com/lms/";
  RestAssured.basePath="/batche/{id}";
  httprequest=RestAssured.given().pathParam("id", BatchId);
 }

 @When("User sends a request body with invalid endpoint.")
 public void user_sends_a_request_body_with_invalid_endpoint() {
  response=httprequest.delete();
  System.out.println(response.asPrettyString());
 }

 @Then("Verify status code is {string}.")
 public void verify_status_code_is(String string) {
  int StatusCode = response.getStatusCode();
     Assert.assertEquals(StatusCode, 404);
 }

 @Then("Verify content type header {string}.")
 public void verify_content_type_header(String string) {
  String contentType=response.getHeader("Content-Type");
  Assert.assertEquals(contentType, "application/json");
 }

 @Given("User send base URL to delete batch with invalid Batch ID")
 public void user_send_base_url_to_delete_batch_with_invalid_batch_id() {
  RestAssured.baseURI="https://lms-backend-service.herokuapp.com/lms/";
  RestAssured.basePath="/batches/{id}";
  httprequest=RestAssured.given().pathParam("id", 3270);
 }
 @Given("User send base URL to delete batch with invalid <{int}>.")
 public void user_send_base_url_to_delete_batch_with_invalid(Integer BatchId) {
  RestAssured.baseURI="https://lms-backend-service.herokuapp.com/lms/";
  RestAssured.basePath="/batches/{id}";
  httprequest=RestAssured.given().pathParam("id", BatchId);;
 }

 @When("User send valid request with invalid Batch ID and its endpoint.")
 public void user_send_valid_request_with_invalid_batch_id_and_its_endpoint() {
  response=httprequest.delete();
  System.out.println(response.asPrettyString());
 }

 @Then("the  status code should be {string}.")
 public void the_status_code_should_be(String string) {
  int StatusCode = response.getStatusCode();
     Assert.assertEquals(StatusCode, 400);
 }

 @Given("User sends invalid base url to delete a Batch ID.")
 public void user_sends_invalid_base_url_to_delete_a_batch_id() {
  RestAssured.baseURI="https://lms-backend-service.herokuapp.com/lms/";
     httprequest=RestAssured.given();
 }
@When("User sends invalid base url with valid endpoint.")
 public void user_sends_invalid_base_url_with_valid_endpoint() {
  response=httprequest.get("https://lms-backend.herokuapp.com/lms/");
  System.out.println("Invalid url");
 }

 @Then("Verify  status code {string}.")
 public void verify_status_code(String string) {
  int StatusCode = response.getStatusCode();
     Assert.assertEquals(StatusCode,503);
 }
 @Given("User sends a base URL with batch ID to delete <{int}>.")
 public void user_sends_a_base_url_with_batch_id_to_delete(Integer batchid) {
  RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms/";
  RestAssured.basePath="/deletebyprogid/{programId}";
  httprequest=RestAssured.given().pathParam("programId", batchid);
 }

 @When("User sends a request with invalid method to delete batch ID.")
 public void user_sends_a_request_with_invalid_method_to_delete_batch_id() {
  response=httprequest.get();
  System.out.println(response.asPrettyString());
 }

 @Then("Verify if the response  status code is {string}")
 public void verify_if_the_response_status_code_is(String string) {
  int StatusCode = response.getStatusCode();
     Assert.assertEquals(StatusCode, 405);
 }
  

}

}
