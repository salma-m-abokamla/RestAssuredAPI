package RestAssuredDemo.RestAssuredDemo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import files.payload;

public class Basics{
@Test
public void test() {
		// TODO Auto-generated method stub
		// Validate if Add Place API is working as expected

		// given -- all input details
		// when -- submit the API - resource , http method
		// Then - validate the response
		// Add place -> update place with new address -> Get Place to validate if new
		// address is present in response

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String responce = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.AddPlace()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)").extract().response()
				.asString();

		System.out.println("Add Place responce \n"+responce+"\n\n");
		/*JsonPath js = new JsonPath(responce); // for parsing Json
		String placeId = js.getString("place_id"); // it needs path param which refer to its location on json file from
													// parent to child
		System.out.println(placeId);
		
	//Update Place
		String newAddress="Summer Walk , Africa";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
         .body("{\r\n" + 
         		"\"place_id\":\""+placeId+"\",\r\n" + 
         		"\"address\":\""+newAddress+"\",\r\n" + 
         		"\"key\":\"qaclick123\"\r\n" + 
         		"}\r\n" + 
         		"")
         .when().put("maps/api/place/update/json")
         .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
	//Get Place
	String getPlaceResponse=given().log().all().queryParam("key", "qaclick123")
        .queryParam("place_id", placeId)  
        .when().get("maps/api/place/get/json")
        .then().assertThat().log().all().statusCode(200).extract().response().asString();
	    JsonPath js1 = new JsonPath(getPlaceResponse);
	  String newAddressResponce=js1.getString("address");
	  System.out.println(newAddressResponce);
	
	}*/
	}}
