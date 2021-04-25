package LoginPage;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginRestAPI {

@Test
  public void f() {
	  
	  RestAssured.baseURI ="https://cc.healthrecoverysolutions.com/login";

	  RequestSpecification request = RestAssured.given();
	   
		request.header("Content-Type", "application/json");
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("type","credentials");
		requestParams.put("username","test");
		requestParams.put("password","test");
		requestParams.put("source","ClinicianConnect 2");

	  
       request.body(requestParams.toJSONString());

	  // Post the request and check the response

	  Response response = request.post("https://gateway.healthrecoverysolutions.com/v1/tokens/");

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
