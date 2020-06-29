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

public class SubscriptionSwitch extends BaseClass {
	@Parameters({ "baseURL", "subscriptionSwitchResoureURL", "subscription" })

  @Test
  public void SubscriptionSwitch(String baseURL, String subscriptionSwitchResoureURL, String subscription)  {
	  ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Full-Access-Token", PasswordLogin.fullAccessToken)
				.header("JWT", SoftToken.backendJwtSoftToken)
				.header("Parent-Subscription", subscription)
				.header("accountIdHash", GetAccounts.accountIdHash)
				.header("Parent-Subscription-Type", Segment.subscriptionType)
				.header("Segment", Segment.segment)
				.header("accountId", GetAccounts.accountId).when()
				.header("Subscription-Type", Segment.subscriptionType)
				.header("msisdnHash", SubscriptionsList.msisdnHash)
				.header("Root-Full-Access-Token", PasswordLogin.fullAccessToken)
				.header("msisdn", subscription)
				.get(subscriptionSwitchResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
				.asString();
		

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
  
  
  
  }
}
