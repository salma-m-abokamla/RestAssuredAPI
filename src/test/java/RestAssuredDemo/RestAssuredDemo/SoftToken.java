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

public class SoftToken {
	public static String backendJwtSoftToken;
	public static String softTokenResoureURL;
	public static void SoftToken(String baseURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		softTokenResoureURL = ResourceUrls.softTokenResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Msp-Hansolo-Token", HanSolo.mspHansoloToken).when().get(softTokenResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("SoftToken response is \n" + response);
		System.out.println("************************************");

		JsonPath js = ReUsableMethods.rawToJson(response);
		backendJwtSoftToken = js.getString("backendJwtSoftToken");
		//	System.out.println("backendJwtSoftToken is: " + backendJwtSoftToken);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(response, MVA10APIS.SOFT_TOKEN.getName());

	}
}
