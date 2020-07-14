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
import io.restassured.RestAssured;

public class VeryMe {
	public static String veryMeOffersResoureURL;
	public static void VeryMe(String baseURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		veryMeOffersResoureURL = ResourceUrls.veryMeOffersResoureURL;

		String response = given().queryParam("latitude", "0").queryParam("longitude", "0")
				.queryParam("locationStatus", "denied").header("Segment", Segment.segment)
				.header("Subscription-Type", Segment.subscriptionType).header("JWT", SoftToken.backendJwtSoftToken)
				.headers(ReUsableMethods.generalHeaders(subscription)).when().get(veryMeOffersResoureURL).then()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println("VeryMe response is \n" + response);
		System.out.println("************************************");


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(response, MVA10APIS.VERY_ME.getName());

	}
}
