package commonclass;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReader;

public class CommonMthods {
	public ConfigReader reader;
	public RequestSpecification request;
   public Response response;
   CommonMthods mthd;
	public String geturl() throws IOException {
	reader=new ConfigReader();
	String url = reader.baseurl();
	return url;
	
	}
	
  public String endpointforallbatches() throws IOException {
	reader=new ConfigReader();
	String endpoint = reader.endpointforallbatches();
	return endpoint;
	
	}
  public String endpointforallprograms() throws IOException {
		reader=new ConfigReader();
		String endpoint = reader.endpointforallprogs();
		return endpoint;
		
		}
  public String invalidprogramendpoint() throws IOException {
		reader=new ConfigReader();
		String endpoint = reader.invalidprogramendpoint();
		return endpoint;
		
		}
  public String batchbynameendpoint() throws IOException {
		reader=new ConfigReader();
		String endpoint = reader.endpointforbatchbyname();
		return endpoint;
		
		}
  public String getinvalidurl() throws IOException {
		reader=new ConfigReader();
		String url = reader.invalidbaseurl();
		return url;
		
		}

  
  public ValidatableResponse checkcontentforallbatches() throws IOException {
	  mthd = new CommonMthods();
		reader=new ConfigReader();
		request =given().baseUri(mthd.getinvalidurl());
		response= request.get(reader.endpointforallbatches());
		ValidatableResponse result=response.then().assertThat().header("Content-Type", reader.contenttypetxt());
		return result;
  }
  
  public ValidatableResponse checkcontentforbatchbyname() throws IOException {
	  mthd = new CommonMthods();
		reader=new ConfigReader();
		request =given().baseUri(mthd.getinvalidurl());
		response= request.get(reader.endpointforbatchbyname());
		ValidatableResponse result=response.then().assertThat().header("Content-Type", reader.contenttypetxt());
		return result;
  }
  
  public ValidatableResponse checkcontentforallprograms() throws IOException {
	  mthd = new CommonMthods();
		reader=new ConfigReader();
		request =given().baseUri(mthd.getinvalidurl());
		response= request.get(reader.endpointforallprogs());
		ValidatableResponse result=response.then().assertThat().header("Content-Type", reader.contenttypetxt());
		return result;
  }
  
 
  
}




