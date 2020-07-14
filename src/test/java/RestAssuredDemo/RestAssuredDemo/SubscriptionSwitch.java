package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import com.apitesting.enums.MVA10APIS;
import com.apitesting.uploader.VSTSFileUploader;
import files.ResourceUrls;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class SubscriptionSwitch extends BaseClass {
	public static String subscriptionSwitchResoureURL;

  public static void SubscriptionSwitch(String baseURL, String subscription)  {
	  ReUsableMethods.generateExtentReport();
	  RestAssured.baseURI = baseURL;
	  subscriptionSwitchResoureURL = ResourceUrls.subscriptionSwitchResoureURL;

	  String response = given().headers(ReUsableMethods.generalHeaders(subscription))
			  .header("Full-Access-Token", PasswordLogin.fullAccessToken).header("JWT", SoftToken.backendJwtSoftToken)
			  .header("Parent-Subscription", subscription).header("accountIdHash", GetAccounts.accountIdHash)
			  .header("Parent-Subscription-Type", Segment.subscriptionType).header("Segment", Segment.segment)
			  .header("accountId", GetAccounts.accountId).when().header("Subscription-Type", Segment.subscriptionType)
			  .header("msisdnHash", ReUsableMethod.returnMsisdnHashValue(SubscriptionsList.subsListsresponse, subscription))
			  .header("Root-Full-Access-Token", PasswordLogin.fullAccessToken).header("msisdn", subscription)

			  .get(subscriptionSwitchResoureURL).then().assertThat().statusCode(200).extract().response()
			  .asString();

	  System.out.println("SubscriptionSwitch response is \n" + response);
	  System.out.println("************************************");


	  ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
	  ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	  VSTSFileUploader.addResponseContentToUploadableFile(response, MVA10APIS.SUBSCRIPTION_SWITCH.getName());
  
  
  
  }
}
