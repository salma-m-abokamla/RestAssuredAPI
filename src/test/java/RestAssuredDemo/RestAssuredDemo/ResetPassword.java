package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.apitesting.listners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;

public class ResetPassword {
	@Parameters({ "baseURL", "resetPasswordResoureURL", "subscription", "userName" })

  @Test
  public void RestPassword(String baseURL, String resetPasswordResoureURL, String subscription, String userName){
 
		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("JWT", SoftToken.backendJwtSoftToken)
				.header("Parent-Subscription", subscription)
				.header("Parent-Subscription-Type", Segment.subscriptionType)
				.header("Segment", Segment.segment)
				.header("Subscription-Type", Segment.subscriptionType)
				.header("Content-type", "application/json")
				.body(payload.resetPasswordBody(userName)).when()
				.post(resetPasswordResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
	
	
	}
}
