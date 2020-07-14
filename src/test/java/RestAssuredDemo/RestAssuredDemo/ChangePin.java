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
import files.payload;
import io.restassured.RestAssured;

public class ChangePin extends BaseClass {
	public static String changePinResourceURL;

	public static void ChangePin(String baseURL, String subscription, String userName) {
		changePinResourceURL= ResourceUrls.changePinResoureURL;
		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", Segment.segment).header("Subscription-Type", Segment.subscriptionType)
				.header("Content-type", "application/json").header("JWT", SoftToken.backendJwtSoftToken)
				.header("Parent-Subscription", subscription).header("Parent-Subscription-Type", Segment.subscriptionType)
				.header("Full-Access-Token", PasswordLogin.fullAccessToken).body(payload.changePinBody(userName)).when()
				.post(changePinResourceURL).then().assertThat().statusCode(200).extract().response()
				.asString();

		System.out.println("ChangePin response is \n" + response  );


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
		VSTSFileUploader.addResponseContentToUploadableFile(response, MVA10APIS.CHANGE_PIN.getName());

	}
}
