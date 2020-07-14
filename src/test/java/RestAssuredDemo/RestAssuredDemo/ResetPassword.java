package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import com.apitesting.enums.MVA10APIS;
import com.apitesting.uploader.VSTSFileUploader;
import files.ResourceUrls;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.apitesting.listners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;

public class ResetPassword {
public static String  restPasswordResourceURL;
  public static void RestPassword(String baseURL, String subscription, String userName){
	  restPasswordResourceURL= ResourceUrls.resetPasswordResoureURL;
	  ReUsableMethods.generateExtentReport();
	  RestAssured.baseURI = baseURL;

	  String response = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
			  .header("JWT", SoftToken.backendJwtSoftToken).header("Parent-Subscription", subscription)
			  .header("Parent-Subscription-Type", Segment.subscriptionType).header("Segment", Segment.segment)
			  .header("Subscription-Type", Segment.subscriptionType).header("Content-type", "application/json")
			  .body(payload.resetPasswordBody(userName)).when().post(restPasswordResourceURL).then().log().all()
			  .assertThat().statusCode(200).extract().response().asString();

	  System.out.println("RestPassword response is \n" + response  );


	  ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
	  ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	  VSTSFileUploader.addResponseContentToUploadableFile(response, MVA10APIS.RESET_PASSWORD.getName());


  }
}
