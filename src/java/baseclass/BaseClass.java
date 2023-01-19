package com.nn.api.base;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.nn.api.utils.ReadConfigFile;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	// read the data from the method in ReadConfigFile class

	ReadConfigFile readConfigFile = new ReadConfigFile();
	public String baseUrl = readConfigFile.getApplicationURL();
	public String baseprogramEndpoint = readConfigFile.getProgramPath();
	public String basebatchesEndpoint = readConfigFile.getBatchesPath();
	public String baseprogramExcelPath = readConfigFile.getprogramExcelPath();
	public String basebatchesExcelpath = readConfigFile.getbatchesExcelPath();
	public String excelFilePath = readConfigFile.excelFilePath();
	public int badrequestStatusCode = readConfigFile.badrequestStatusCode();
	private final int createrequestStatusCode = readConfigFile.createrequestStatusCode();
	private final int invalidRequestStatusCode = readConfigFile.invalidRequestStatusCode();
	private final int invalidEndpointStatusCode = readConfigFile.invalidEndpointStatusCode();
	protected final String statusLine = readConfigFile.statusLine();
	public static RequestSpecification httpRequest =null; 
	public static io.restassured.response.Response response =null; 
	public static ValidatableResponse json =null;
	public static int rownum;
	public static int colnum;
	public static String programId;
	public static String programName;
	public static String programDescription;
	public static String programStatus;
	public static String creationTime;
	public static String lastModTime;
	protected Map<String, Response> responseMap = new HashMap<String, Response>(); // create Hashmap object for storing response
	Map<String, String[]> dataMap = new HashMap<String, String[]>();
	public Logger logger=LogManager.getLogger(BaseClass.class);
	
	public void readBatchesDataForStatusCode201() throws Exception {
		
		//generating log
		logger.info("Read data from Excel");
		rownum = com.nn.api.utils.ExcelUtils.getRowCount(basebatchesExcelpath, "batchdata");
		colnum = com.nn.api.utils.ExcelUtils.getCellCount(basebatchesExcelpath, "batchdata", 1);

		System.out.println("row count:" + rownum);
		System.out.println("col count:" + colnum);

		String[][] batchData = new String[rownum][colnum];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) {

				batchData[i - 1][j] = com.nn.api.utils.ExcelUtils.getCellData(basebatchesExcelpath, "batchdata", i, j); 

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
			String batchId = ((ResponseBodyExtractionOptions) response).jsonPath().getString("batchId");
			responseMap.put(batchId,(Response) response);// use hashmap to store response
			dataMap.put(batchId, row);// use another hashmap datamap to read all values by row*/

		}
	}

	public void readBatchesDataForStatusCode400() throws Exception {

		rownum = com.nn.api.utils.ExcelUtils.getRowCount(basebatchesExcelpath, "batcherrordata");
		colnum = com.nn.api.utils.ExcelUtils.getCellCount(basebatchesExcelpath, "batcherrordata", 1);

		System.out.println("row count:" + rownum);
		System.out.println("col count:" + colnum);

		String[][] batchData = new String[rownum][colnum];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) {

				batchData[i - 1][j] = com.nn.api.utils.ExcelUtils.getCellData(basebatchesExcelpath, "batcherrordata", i, j); 

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
			String batchId = response.jsonPath().getString("batchId");
			((RestAssured) responseMap.put(batchId, (Response) response)).put(batchId, response);// use hashmap to store response
			dataMap.put(batchId, row);// use another hashmap datamap to read all values by row*/

		}
	}
	
	public void readProgramDataForStatusCode201() throws Exception{

		rownum = com.nn.api.utils.ExcelUtils.getRowCount(baseprogramExcelPath, "programdata");
		colnum = com.nn.api.utils.ExcelUtils.getCellCount(baseprogramExcelPath, "programdata", 1);

		System.out.println("row count:" + rownum);
		System.out.println("col count:" + colnum);

		String[][] progamData = new String[rownum][colnum];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) {

				progamData[i - 1][j] = com.nn.api.utils.ExcelUtils.getCellData(baseprogramExcelPath, "programdata", i,j); // i =1, j=0 --> first cell value

			}
		}
	
		JSONObject params = new JSONObject();
		for (String[] row : progamData) {
			
			params.put("programName", row[0]);
			params.put("programDescription", row[1]);
			params.put("programStatus", row[2]);
			params.put("creationTime", row[3]);
			params.put("lastModTime", row[4]);

			httpRequest = given().auth().none().contentType("application/json");
			httpRequest.body(params.toJSONString());
			response = httpRequest.request(Method.POST, baseprogramEndpoint);
			/* Get the User ID from the response */
			if(!(row[5].equals("401") || row[5].equals("404"))) {
				String programId = response.jsonPath().getString("skill_id");
				responseMap.put(programId, (Response) response);
				dataMap.put(programId, row);
			}
			String programId = response.jsonPath().getString("programId");
			responseMap.put(programId, (Response) response);
			dataMap.put(programId, row);
			String errorCode = response.jsonPath().getString("error");
			String errorMessage = response.jsonPath().getString("message");
		}
	}

		public void readProgramDataForStatus400() throws Exception{

			rownum = com.nn.api.utils.ExcelUtils.getRowCount(baseprogramExcelPath, "programerrordata");
			colnum = com.nn.api.utils.ExcelUtils.getCellCount(baseprogramExcelPath, "programerrordata", 1);

			System.out.println("row count:" + rownum);
			System.out.println("col count:" + colnum);

			String[][] progamData = new String[rownum][colnum];

			for (int i = 1; i <= rownum; i++) {
				for (int j = 0; j < colnum; j++) {

					progamData[i - 1][j] = com.nn.api.utils.ExcelUtils.getCellData(baseprogramExcelPath, "programerrordata", i,
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
				if((row[5].equals("400"))) { 
				String errorCode = response.jsonPath().getString("error");
				String errorMessage = response.jsonPath().getString("message");
				}	

		}
		}
		public void readProgramDataForInvalidCreationTime() throws Exception{

			rownum = com.nn.api.utils.ExcelUtils.getRowCount(baseprogramExcelPath, "programerrordata");
			colnum = com.nn.api.utils.ExcelUtils.getCellCount(baseprogramExcelPath, "programerrordata", 1);

			System.out.println("row count:" + rownum);
			System.out.println("col count:" + colnum);

			String[][] progamData = new String[rownum][colnum];

			for (int i = 1; i <= rownum; i++) {
				for (int j = 0; j < colnum; j++) {

					progamData[i - 1][j] = com.nn.api.utils.ExcelUtils.getCellData(baseprogramExcelPath, "programerrordata", i,
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
				if((row[6].equals("Invalid Creation Time"))) { 
				String errorCode = response.jsonPath().getString("error");
				String errorMessage = response.jsonPath().getString("message");
				}	

		}
		}
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	






