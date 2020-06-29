package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class HanSolo extends BaseClass{
	public static String hanSoloresponce;
	public static String mspHansoloToken;
	
	@Parameters({ "baseURL", "hansoloResoureURL", "subscription" })
	@Test
	public void  HanSolo(String baseURL, String hansoloResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		hanSoloresponce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription)).when()
				.get(hansoloResoureURL).then().log().all().assertThat().statusCode(200).extract().response().asString();
	
		JsonPath js = ReUsableMethods.rawToJson(hanSoloresponce);
		mspHansoloToken = js.getString("mspHansoloToken");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + hanSoloresponce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
}
