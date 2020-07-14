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

public class AppConfig {

	public static String appConfigResoureURL;

	public static void  AppConfig(String baseURL,String subscription) {

		appConfigResoureURL = ResourceUrls.appConfigResoureURL;
		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription)).when().get(appConfigResoureURL)
				.then().assertThat().statusCode(200).extract().response().asString();

		System.out.println("************************************");
		System.out.println( "AppConfig response is \n" + response);
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(response, MVA10APIS.APP_CONFIG.getName());

	}
}
