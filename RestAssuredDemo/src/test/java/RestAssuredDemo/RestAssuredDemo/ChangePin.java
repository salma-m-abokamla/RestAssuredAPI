package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;

public class ChangePin extends BaseClass {
	@Parameters({ "baseURL", "changePinResoureURL", "subscription", "userName" })

	@Test
	public void ChangePin(String baseURL, String changePinResoureURL, String subscription, String userName) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", Segment.segment).header("Subscription-Type", Segment.subscriptionType)
				.header("Content-type", "application/json").header("JWT", SoftToken.backendJwtSoftToken)
				.header("Parent-Subscription", subscription)
				.header("Parent-Subscription-Type", Segment.subscriptionType)
				.header("Full-Access-Token", PasswordLogin.fullAccessToken).body(payload.changePinBody(userName)).when()
				.post(changePinResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
}
