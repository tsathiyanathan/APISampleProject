package LoginPage;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


@SuppressWarnings("deprecation")
public class LoginRestAPI {

@SuppressWarnings("unchecked")
@Test
  public void f() {
	  
	  RestAssured.baseURI ="https://gateway.healthrecoverysolutions.com";

	  RequestSpecification request = RestAssured.given();
	   
	   request.header("Content-Type", "application/json");
	   
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("type","credentials");
		requestParams.put("username","test");
		requestParams.put("password","test");
		requestParams.put("source","ClinicianConnect 2");
		
		JSONObject dataparams = new JSONObject();
		dataparams.put("data", requestParams);
		
	    request.body(dataparams.toJSONString());

	  // Post the request and check the response

	  Response response = request.post("/v1/tokens");

	  String responsebody=response.asString();
	
      System.out.println("Response Body is :"+ responsebody);
	  
	  int statusCode = response.getStatusCode();
	  
	  Assert.assertEquals(statusCode, 422);
	 
	  
	  String contentType = response.header("Content-Type");
	  	  
	  Assert.assertEquals(contentType, "application/json");
	 
	  String serverType =  response.header("Server");
	  
	  Assert.assertEquals(serverType, "nginx");
	
	
	    
	
	  
  }
}
