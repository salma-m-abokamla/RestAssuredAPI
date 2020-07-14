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

public class HanSolo{
	public static String hanSoloresponce;
	public static String mspHansoloToken;
	public static String hansoloResoureURL;

	public static void  HanSolo(String baseURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		hansoloResoureURL = ResourceUrls.hansoloResoureURL;

		hanSoloresponce = given().headers(ReUsableMethods.generalHeaders(subscription)).when().get(hansoloResoureURL)
				.then().assertThat().statusCode(200).extract().response().asString();

		System.out.println("HanSolo response is \n" + hanSoloresponce);
		System.out.println("************************************");

		JsonPath js = ReUsableMethods.rawToJson(hanSoloresponce);
		mspHansoloToken = js.getString("mspHansoloToken");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + hanSoloresponce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloresponce, MVA10APIS.HANS_SOLO.getName());

	}
}
