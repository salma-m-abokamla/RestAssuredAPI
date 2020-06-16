package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.apitesting.listners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class PasswordLogin {
	public static String encryptedUserName;
	public static String fullAccessToken;
	public static String accountId;
	@Parameters({ "baseURL", "passwordLoginResoureURL", "subscription", "userName" })
	@Test(priority = 19)
	public void PasswordLogin(String baseURL, String passwordLoginResoureURL, String subscription, String userName) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", Segment.segment).header("Subscription-Type", Segment.subscriptionType)
				.header("Content-type", "application/json").header("JWT", SoftToken.backendJwtSoftToken)
				.body(payload.passwordLoginBody(userName)).when().post(passwordLoginResoureURL).then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath js1 = ReUsableMethods.rawToJson(responce);
		encryptedUserName = js1.getString("username");
		fullAccessToken = js1.getString("fullAccessToken");
		accountId = js1.getString("accountId");

		System.out.print("fullAccessToken:" + fullAccessToken);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
}
