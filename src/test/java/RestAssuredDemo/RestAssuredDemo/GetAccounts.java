package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.apitesting.listners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GetAccounts {
	@Parameters({ "baseURL", "accountsResoureURL", "subscription" })
	@Test(priority = 20)
	public void GetAccounts(String baseURL, String accountsResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", Segment.segment).header("Subscription-Type", Segment.subscriptionType)
				.header("JWT", SoftToken.backendJwtSoftToken).header("Root-Full-Access-Token", PasswordLogin.fullAccessToken)
				.header("accountId", PasswordLogin.accountId).header("Full-Access-Token", PasswordLogin.fullAccessToken).when()
				.get(accountsResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
				.asString();
	

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
}
