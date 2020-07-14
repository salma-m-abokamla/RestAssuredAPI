package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import com.apitesting.enums.MVA10APIS;
import com.apitesting.uploader.VSTSFileUploader;
import files.ResourceUrls;

import com.apitesting.listners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import io.restassured.RestAssured;

public class AdditionalCharges {
	public static String additionalChargesResoureURL;
	public static void AdditionalCharges(String baseURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		additionalChargesResoureURL = ResourceUrls.additionalChargesResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", Segment.segment).header("Subscription-Type", Segment.subscriptionType)
				.header("JWT", SoftToken.backendJwtSoftToken).when().get(additionalChargesResoureURL).then()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println("AdditionalCharges response is \n" + response);
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(response, MVA10APIS.ADDITIONAL_CHARGES.getName());

	}
}
