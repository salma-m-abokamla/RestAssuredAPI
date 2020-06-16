package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

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

	@Parameters({ "baseURL", "segmentResoureURL", "subscription" })
	@Test
	public void Segment(String baseURL, String segmentResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("JWT", SoftToken.backendJwtSoftToken).when().get(segmentResoureURL).then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath js = ReUsableMethods.rawToJson(responce);
		segment = js.getString("segment");
		System.out.println("Segment is :" + segment);
		JsonPath js1 = ReUsableMethods.rawToJson(responce);
		subscriptionType = js1.getString("subscriptionType.name");

		System.out.println("subscriptionType is :" + subscriptionType);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
}
