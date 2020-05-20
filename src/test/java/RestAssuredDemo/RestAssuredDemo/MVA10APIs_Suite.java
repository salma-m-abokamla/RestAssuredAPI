package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentTestManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class MVA10APIs_Suite extends BaseClass {
	public static String hanSoloresponce;
	public static String mspHansoloToken;
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
	@Parameters({ "baseURL", "appConfigResoureURL", "subscription" })
	@Test(priority = 1)
	public void  AppConfig(String baseURL, String appConfigResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription)).when()
				.get(appConfigResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "hansoloResoureURL", "subscription" })
	@Test(priority = 2)
	public static String HanSolo(String baseURL, String hansoloResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		hanSoloresponce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription)).when()
				.get(hansoloResoureURL).then().log().all().assertThat().statusCode(200).extract().response().asString();
	
		JsonPath js = ReUsableMethods.rawToJson(hanSoloresponce);
		mspHansoloToken = js.getString("mspHansoloToken");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + hanSoloresponce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
		return mspHansoloToken;

	}

	@Parameters({ "baseURL", "softTokenResoureURL", "subscription" })
	@Test(priority = 3)
	public void SoftToken(String baseURL, String softTokenResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Msp-Hansolo-Token", mspHansoloToken).when().get(softTokenResoureURL).then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath js = ReUsableMethods.rawToJson(responce);
		backendJwtSoftToken = js.getString("backendJwtSoftToken");
		System.out.println("backendJwtSoftToken is: " + backendJwtSoftToken);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "segmentResoureURL", "subscription" })
	@Test(priority = 4)
	public void Segment(String baseURL, String segmentResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("JWT", backendJwtSoftToken).when().get(segmentResoureURL).then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		JsonPath js = ReUsableMethods.rawToJson(responce);
		segment = js.getString("segment");
		System.out.println("Segment is :" + segment);
		JsonPath js1 = ReUsableMethods.rawToJson(responce);
		subscriptionType = js1.getString("subscriptionType.name");

		System.out.println("subscriptionType is :" + subscriptionType);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "subsConfigResoureURL", "subscription" })
	@Test(priority = 5)
	public void SubsConfigWithoutSegment(String baseURL, String subsConfigResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("JWT", backendJwtSoftToken).when().get(subsConfigResoureURL).then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "dashboardResoureURL", "subscription" })
	@Test(priority = 6)
	public void Dashboard(String baseURL, String dashboardResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(dashboardResoureURL).then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "subsConfigResoureURL", "subscription" })
	@Test(priority = 7)
	public void SubsConfigWithSegment(String baseURL, String subsConfigResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(subsConfigResoureURL).then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "veryMeOffersResoureURL", "subscription" })
	@Test(priority = 8)
	public void VeryMe(String baseURL, String veryMeOffersResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().queryParam("latitude", "0").queryParam("longitude", "0")
				.queryParam("locationStatus", "denied").header("Segment", segment)
				.header("Subscription-Type", subscriptionType).header("JWT", backendJwtSoftToken)
				.headers(ReUsableMethods.generalHeaders(subscription)).when().get(veryMeOffersResoureURL).then().log()
				.all().assertThat().statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "discoverResoureURL", "subscription" })
	@Test(priority = 9)
	public void Discover(String baseURL, String discoverResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(discoverResoureURL).then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "productsAndServicesResoureURL", "subscription" })
	@Test(priority = 10)
	public void ProductsAndServices(String baseURL, String productsAndServicesResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(productsAndServicesResoureURL).then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "billHistoryResoureURL", "subscription" })
	@Test(priority = 11)
	public void BillHistory(String baseURL, String billHistoryResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(billHistoryResoureURL).then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "planResoureURL", "subscription" })
	@Test(priority = 12)
	public void Plan(String baseURL, String planResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(planResoureURL).then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "usagesResoureURL", "subscription" })
	@Test(priority = 13)
	public void Usages(String baseURL, String usagesResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(usagesResoureURL).then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "extrasResoureURL", "subscription" })
	@Test(priority = 14)
	public void Extras(String baseURL, String extrasResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(extrasResoureURL).then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "currentChargesResoureURL", "subscription" })
	@Test(priority = 15)
	public void CurrentCharges(String baseURL, String currentChargesResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(currentChargesResoureURL).then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "additionalChargesResoureURL", "subscription" })
	@Test(priority = 16)
	public void AdditionalCharges(String baseURL, String additionalChargesResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(additionalChargesResoureURL).then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "upgradesResoureURL", "subscription" })
	@Test(priority = 17)
	public void Upgrades(String baseURL, String upgradesResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(upgradesResoureURL).then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "vovResoureURL", "subscription" })
	@Test(priority = 18)
	public void VOV(String baseURL, String vovResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(vovResoureURL).then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "passwordLoginResoureURL", "subscription", "userName" })
	@Test(priority = 19)
	public void PasswordLogin(String baseURL, String passwordLoginResoureURL, String subscription, String userName) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("Content-type", "application/json").header("JWT", backendJwtSoftToken)
				.body(payload.passwordLoginBody(userName)).when().post(passwordLoginResoureURL).then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath js1 = ReUsableMethods.rawToJson(responce);
		encryptedUserName = js1.getString("username");
		fullAccessToken = js1.getString("fullAccessToken");
		accountId = js1.getString("accountId");

		System.out.print("fullAccessToken:" + fullAccessToken);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "accountsResoureURL", "subscription" })
	@Test(priority = 20)
	public void GetAccounts(String baseURL, String accountsResoureURL, String subscription) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).header("Root-Full-Access-Token", fullAccessToken)
				.header("accountId", accountId).header("Full-Access-Token", fullAccessToken).when()
				.get(accountsResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
				.asString();
		JsonPath js1 = ReUsableMethods.rawToJson(responce);
		accountType = js1.getString("accounts[0].accountType");
		accountIdHash = js1.getString("accounts[0].accountIdHash");
		accountId = js1.getString("accounts[0].accountId");

		System.out.println("accountType" + accountType);
		System.out.println("accountIdHash" + accountIdHash);
		System.out.println("accountId" + accountId);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

}
