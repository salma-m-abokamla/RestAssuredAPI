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

public class SoftToken extends BaseClass {
	public static String backendJwtSoftToken;
	
	@Parameters({ "baseURL", "softTokenResoureURL", "subscription" })
	@Test
	public void SoftToken(String baseURL, String softTokenResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Msp-Hansolo-Token", HanSolo.mspHansoloToken).when().get(softTokenResoureURL).then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath js = ReUsableMethods.rawToJson(responce);
		backendJwtSoftToken = js.getString("backendJwtSoftToken");
		System.out.println("backendJwtSoftToken is: " + backendJwtSoftToken);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
}
