package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import io.restassured.RestAssured;

public class AppConfig extends BaseClass {
	@SuppressWarnings("deprecation")
	@Parameters({ "baseURL", "appConfigResoureURL", "subscription" })
	@Test
	public void  AppConfig(String baseURL, String appConfigResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription)).when()
				.get(appConfigResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
}
