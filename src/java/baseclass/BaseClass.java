package BaseClass;

import static org.testng.Assert.assertEquals;

import org.hamcrest.Matcher;
import org.testng.annotations.BeforeClass;
import Utilities.ReadConfigFile;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static io.restassured.RestAssured.*;

public class BaseClass {

	ReadConfigFile readConfigFile = new ReadConfigFile();
	public String baseurl = readConfigFile.getApplicationURL();
	public String programpath = readConfigFile.getProgramPath();
	public String batchpath = readConfigFile.getBatchPath();
	public String excelpath = readConfigFile.getExcelPath();
	
	//read the data from the method in ReadConfigFile class
		public static RequestSpecification HttpRequest;
		public static Response response;
		public static  ValidatableResponse json;
		
	public void createRequest() {

		HttpRequest = given().
						baseUri(baseurl)
						.auth().basic()
						.header("content-Type","application/json");
	}

	public void call(String path, Method method) {
		response = HttpRequest.request(method, path);
	}

	public void validateStatus(int status) {

		assertEquals(response.getStatusCode(), status);
	}

	public void validateStatusLine(String statusLine) {

		assertEquals(response.getStatusLine(), statusLine);

	}

	public void validateContentType(String contentType) {

		assertEquals(response.getContentType(), contentType);
	}

	public void validateSchema(String schemaPath) {
		response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
	}
}
