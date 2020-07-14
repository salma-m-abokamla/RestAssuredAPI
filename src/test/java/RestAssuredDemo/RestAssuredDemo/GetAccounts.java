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
import io.restassured.path.json.JsonPath;

public class GetAccounts {
	public static String accountsResoureURL;
	public static String accountType;
	public static String accountIdHash;
	public static String accountId;
	public static void GetAccounts(String baseURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		accountsResoureURL = ResourceUrls.accountsResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", Segment.segment).header("Subscription-Type", Segment.subscriptionType)
				.header("JWT", SoftToken.backendJwtSoftToken).header("Root-Full-Access-Token", PasswordLogin.fullAccessToken)
				.header("accountId", PasswordLogin.accountId).header("Full-Access-Token", PasswordLogin.fullAccessToken).when()
				.get(accountsResoureURL).then().assertThat().statusCode(200).extract().response()
				.asString();

		System.out.println("GetAccounts response is \n" + response);
		System.out.println("************************************");

		JsonPath js1 = ReUsableMethods.rawToJson(response);
		accountType = js1.getString("accounts[0].accountType");
		accountIdHash = js1.getString("accounts[0].accountIdHash");
		accountId = js1.getString("accounts[0].accountId");

		System.out.println("accountType" + accountType);
		System.out.println("accountIdHash" + accountIdHash);
		System.out.println("accountId" + accountId);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(response, MVA10APIS.GET_ACCOUNTS.getName());

	}
}
