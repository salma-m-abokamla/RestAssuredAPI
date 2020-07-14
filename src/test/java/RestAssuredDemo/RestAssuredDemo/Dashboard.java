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

public class Dashboard {
	public static String dashboardResoureURL;
	public static void Dashboard(String baseURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		dashboardResoureURL = ResourceUrls.dashboardResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", Segment.segment).header("Subscription-Type", Segment.subscriptionType)
				.header("JWT", SoftToken.backendJwtSoftToken).when().get(dashboardResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Dashboard response is \n" + response);
		System.out.println("************************************");


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(response, MVA10APIS.DASHBOARD.getName());

	}}
