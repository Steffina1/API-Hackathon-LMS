package com.lmshackathon.stepdefiniton;


  
  
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class UpdateProgramById {
 @Given("User sends request with valid id to update Program id")
 public void user_sends_request_with_valid_id_to_update_program_id() {
  RestAssured.baseURI="https://lms-backend-service.herokuapp.com/lms";

 }

 @When("User sends request body with a valid Json Schema to update Program id \\(path)")
 public void user_sends_request_body_with_a_valid_json_schema_to_update_program_id_path() {
  rownum = com.nn.api.utils.ExcelUtils.getRowCount(baseprogramExcelPath, "programdata");
  colnum = com.nn.api.utils.ExcelUtils.getCellCount(baseprogramExcelPath, "programdata", 1);

  System.out.println("row count:"+rownum);
  System.out.println("col count:"+colnum);

  String[][] progamData = new String[rownum][colnum];

  for(int i=1;i<=rownum;i++)
  {
   for(int j=0; j<colnum; j++) {

    progamData[i-1][j] = com.nn.api.utils.ExcelUtils.getCellData(baseprogramExcelPath, "programdata", i, j); //i =1, j=0 --> first cell value

   }
  }
  
  
  JSONObject params = new JSONObject();
  for(String[] row : progamData) {
   params.put("programName", row[0]);
   params.put("programDescription", row[1]);
   params.put("programStatus", row[2]);
   params.put("creationTime", row[3]);
   params.put("lastModTime", row[4]);
   params.put("programId", row[5]);
   httpRequest = given()
       .auth()
        .none()
       .contentType("application/json");//request
   httpRequest.body(params.toJSONString());
   response = httpRequest.request(Method.PUT,"/putprogram/programId"  );//response
   response.then().log().all();

   String programId = response.jsonPath().getString("programId");
   responseMap.put(programId, response);//use hashmap to store response
   dataMap.put(programId, row);//use another hashmap datamap to read all values by row*/

  }
  
 }

 }

 
 
 

  
  
  




}
