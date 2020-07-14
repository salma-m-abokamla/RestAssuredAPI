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

public class Segment extends BaseClass {
	public static String segment;
	public static String subscriptionType;
	public static String segmentResoureURL;
	public static void Segment(String baseURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		segmentResoureURL = ResourceUrls.segmentResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("JWT", SoftToken.backendJwtSoftToken).when().get(segmentResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Segment response is \n" + response);
		System.out.println("************************************");


		JsonPath js = ReUsableMethods.rawToJson(response);
		segment = js.getString("segment");
		//System.out.println("Segment is :" + segment);
		JsonPath js1 = ReUsableMethods.rawToJson(response);
		subscriptionType = js1.getString("subscriptionType.name");

		//System.out.println("SubscriptionType is :" + subscriptionType);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(response, MVA10APIS.SEGMENT.getName());

	}
}
