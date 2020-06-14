package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.apitesting.listners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import io.restassured.RestAssured;

public class VeryMe {
	@Parameters({ "baseURL", "veryMeOffersResoureURL", "subscription" })
	@Test
	public void VeryMe(String baseURL, String veryMeOffersResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().queryParam("latitude", "0").queryParam("longitude", "0")
				.queryParam("locationStatus", "denied").header("Segment", Segment.segment)
				.header("Subscription-Type", Segment.subscriptionType).header("JWT", SoftToken.backendJwtSoftToken)
				.headers(ReUsableMethods.generalHeaders(subscription)).when().get(veryMeOffersResoureURL).then().log()
				.all().assertThat().statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
}
