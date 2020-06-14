package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import java.net.MalformedURLException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.apitesting.listners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class SubscriptionsList {
	public static String msisdnHash;
	@Parameters({ "baseURL", "accountsResoureURL", "subscription" })
	@Test(priority = 21)
	public void GetSubscriptionsList(String baseURL, String accountsResoureURL, String subscription)
			throws MalformedURLException {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Full-Access-Token", PasswordLogin.fullAccessToken)
				.header("JWT", SoftToken.backendJwtSoftToken)
				.header("Parent-Subscription", subscription)
				.header("accountIdHash", GetAccounts.accountIdHash)
				.header("accountType", GetAccounts.accountType)
				.header("Parent-Subscription-Type", Segment.subscriptionType)
				.header("Segment", Segment.segment)
				.header("Subscription-Type", Segment.subscriptionType)	
				.header("Root-Full-Access-Token", PasswordLogin.fullAccessToken)
				.when()
				.get(accountsResoureURL.concat("/{accountId}").concat("/subscriptions"), GetAccounts.accountId).then().log().all()
				.assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1 = ReUsableMethods.rawToJson(responce);
		msisdnHash = js1.getString("subscriptions.msisdnHash");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
}
