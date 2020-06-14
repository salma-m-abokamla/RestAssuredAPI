package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentTestManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import files.ReadExcelDataSheet;
import files.ResourceUrls;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UFLAPIs_Suite extends BaseClass {

	public String backendJwtSoftToken;
	public String segment;
	public String subscriptionType;
	public String fullAccessToken;
	public String accountId;
	public String encryptedUserName;
	public String accountType;
	public String accountIdHash;
	public String registeredNumberId;
    public String AcessTokenL3;
    public String authTokenL2;
    public String authTokenL3;
    public String msisdnHash;
    public static String registeredNumbersResoureURL;
    public static String accessTokenL3;
    
	@Test(priority = 1 , dataProvider = "Authentication")
	public void RegisteredNumbers(String baseURL, String subscription,String userName) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		registeredNumbersResoureURL=ResourceUrls.registeredNumbersResoureURL;
		
		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Parent-Subscription", subscription).header("Content-type", "application/json")
				.body(payload.passwordLoginBody(userName)).when().post(registeredNumbersResoureURL).then().log().all()
				.assertThat().statusCode(200).extract().response().asString();
		JsonPath js1 = ReUsableMethods.rawToJson(responce);
		registeredNumberId = js1.getString("numbers[0].id");
		authTokenL2=js1.getString("authToken");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
	@Parameters({ "baseURL", "sendOTACResoureURL", "subscription" })
	@Test(priority = 2)
	public void SendOTAC(String baseURL, String sendOTACResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Parent-Subscription", subscription)
				.header("registeredNumberId",registeredNumberId )
				.header("authTokenL2", authTokenL2)
				.when().get(sendOTACResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
	@Parameters({ "baseURL", "accountsResoureURL", "subscription" })
	@Test(priority = 3)
	public void GetAccounts(String baseURL, String accountsResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Parent-Subscription", subscription)
				.header("registeredNumberId",registeredNumberId )
				.header("authTokenL2", authTokenL2)
				.header("otac", "GIBKK")
				.when()
				.get(accountsResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
				.asString();
		JsonPath js1 = ReUsableMethods.rawToJson(responce);
		accountType = js1.getString("accounts[0].accountType");
		accountIdHash = js1.getString("accounts[0].accountIdHash");
		accountId = js1.getString("accounts[0].accountId");
		authTokenL3=js1.getString("authTokenL3");
		
		System.out.println("accountType" + accountType);
		System.out.println("accountIdHash" + accountIdHash);
		System.out.println("accountId" + accountId);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "accountsResoureURL", "subscription" })
	@Test(priority = 4)
	public void GetSubscriptionsList(String baseURL, String accountsResoureURL, String subscription)
			throws MalformedURLException {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("authTokenL3", authTokenL3).header("Parent-Subscription", subscription)
				.header("accountIdHash", accountIdHash).header("accountType", accountType)
				.when()
				.get(accountsResoureURL.concat("/{accountId}").concat("/subscriptions"), accountId).then().log().all()
				.assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1 = ReUsableMethods.rawToJson(responce);
		msisdnHash=js1.getString("subscriptions[0].msisdnHash");
		subscriptionType=js1.getString("subscriptions[0].subscriptionType.name");
		segment=js1.getString("subscriptions[0].segment");
		
		System.out.println("msisdnHash: "+msisdnHash);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

   @Parameters({ "baseURL", "upfrontLoginResoureURL", "subscription"})

	@Test(priority = 5)
	public void UpfrontLogin(String baseURL, String upfrontLoginResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("authTokenL3", authTokenL3).header("Parent-Subscription", subscription)
				.header("accountIdHash", accountIdHash)
				.header("accountId", accountId)
				.header("subscriptionId", subscription)
				.header("subscriptionIdHash",msisdnHash )
				.body("").when().post(upfrontLoginResoureURL).then().log().all().assertThat().statusCode(200).extract()
				.response().asString();
		JsonPath js1 = ReUsableMethods.rawToJson(responce);
		accessTokenL3=js1.getString("accessTokenL3");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
   @Parameters({ "baseURL", "changePinResoureURL", "subscription", "userName" })

	@Test(priority = 6)
	public void ChangePin(String baseURL, String changePinResoureURL, String subscription, String userName) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Parent-Subscription", subscription)
				.header("Access-Token-L3",accessTokenL3)
				.header("Segment",segment)
				.header("Subscription-Type",subscriptionType)
				.header("Content-type", "application/json")
				.body(payload.changePinBody(userName)).when()
				.post(changePinResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
   @DataProvider(name = "Authentication") // supplying data for a test method.
	public Object[][] getAuthentication() throws IOException {
		Object data[][]=ReadExcelDataSheet.getDataSheet();
		return data;
				
		

}
}
