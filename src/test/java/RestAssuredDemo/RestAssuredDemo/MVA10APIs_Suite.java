package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;

import java.util.List;
import java.util.logging.Logger;
import java.io.PrintStream;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentTestManager;

import com.relevantcodes.extentreports.LogStatus;

import files.CofigFileReader;
import files.ReUsableMethods;
import files.ResourceUrls;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class MVA10APIs_Suite extends BaseClass {
	public static String softTokenResoureURL;
	public static String appConfigResoureURL;
	public static String hansoloResoureUR;
	public static String segmentResoureURL;
	public static String dashboardResoureURL;
	public static String subsConfigResoureURL;
	public static String veryMeOffersResoureURL;
	public static String discoverResoureURL;
	public static String productsAndServicesResoureURL;
	public static String billHistoryResoureURL;
	public static String planResoureURL;
	public static String usagesResoureURL;
	public static String extrasResoureURL;
	public static String changePinResoureURL;
	public static String currentChargesResoureURL;
	public static String additionalChargesResoureURL;
	public static String resetPasswordResoureURL;
	public static String upgradesResoureURL;
	public static String subscriptionSwitchResoureURL;
	public static String vovResoureURL;
	public static String passwordLoginResoureURL;
	public static String accountsResoureURL;
	public static String accountListsResoureURL;
	public static String registeredNumbersResoureURL;
	public static String sendOTACResoureURL;
	public static String upfrontLoginResoureURL;

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
	public String msisdnHash;
	public String subsListsResponce;
	public String baseURL;
	public String subscription;
	public String userName;
	
	@BeforeSuite
	public void beforeSuite() {
		CofigFileReader.setDeploymentEnv();
		baseURL=CofigFileReader.getBaseUrl();
		subscription=CofigFileReader.getSubscriprion();
		userName=CofigFileReader.getUserName();
		
			}
	
	@Test (priority = 1)
	public void AppConfig( ) {

		appConfigResoureURL = ResourceUrls.appConfigResoureURL;
		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription)).when().get(appConfigResoureURL)
				.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("************************************");
		System.out.println( "AppConfig Responce is \n" + responce);
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
	}

	@Test (priority = 2)
	public void HanSolo( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		hansoloResoureUR = ResourceUrls.hansoloResoureURL;

		hanSoloresponce = given().headers(ReUsableMethods.generalHeaders(subscription)).when().get(hansoloResoureUR)
				.then().assertThat().statusCode(200).extract().response().asString();

		System.out.println("HanSolo Responce is \n" + hanSoloresponce  );
		System.out.println("************************************");

		JsonPath js = ReUsableMethods.rawToJson(hanSoloresponce);
		mspHansoloToken = js.getString("mspHansoloToken");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + hanSoloresponce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test (priority = 3)
	public void SoftToken( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		softTokenResoureURL = ResourceUrls.softTokenResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Msp-Hansolo-Token", mspHansoloToken).when().get(softTokenResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("SoftToken Responce is \n" + responce  );
		System.out.println("************************************");

		JsonPath js = ReUsableMethods.rawToJson(responce);
		backendJwtSoftToken = js.getString("backendJwtSoftToken");
	//	System.out.println("backendJwtSoftToken is: " + backendJwtSoftToken);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 4)
	public void Segment( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		segmentResoureURL = ResourceUrls.segmentResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("JWT", backendJwtSoftToken).when().get(segmentResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Segment Responce is \n" + responce  );
		System.out.println("************************************");

		
		JsonPath js = ReUsableMethods.rawToJson(responce);
		segment = js.getString("segment");
		//System.out.println("Segment is :" + segment);
		JsonPath js1 = ReUsableMethods.rawToJson(responce);
		subscriptionType = js1.getString("subscriptionType.name");

		//System.out.println("SubscriptionType is :" + subscriptionType);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 5 )
	public void SubsConfigWithoutSegment( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		subsConfigResoureURL = ResourceUrls.subsConfigResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("JWT", backendJwtSoftToken).when().get(subsConfigResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("SubsConfigWithoutSegment Responce is \n" + responce  );
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 6 )
	public void Dashboard() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		dashboardResoureURL = ResourceUrls.dashboardResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(dashboardResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Dashboard Responce is \n" + responce  );
		System.out.println("************************************");

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 7 )
	public void SubsConfigWithSegment( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		subsConfigResoureURL = ResourceUrls.subsConfigResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(subsConfigResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("SubsConfigWithSegment Responce is \n" + responce  );
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 8 )
	public void VeryMe( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		veryMeOffersResoureURL = ResourceUrls.veryMeOffersResoureURL;

		String responce = given().queryParam("latitude", "0").queryParam("longitude", "0")
				.queryParam("locationStatus", "denied").header("Segment", segment)
				.header("Subscription-Type", subscriptionType).header("JWT", backendJwtSoftToken)
				.headers(ReUsableMethods.generalHeaders(subscription)).when().get(veryMeOffersResoureURL).then()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println("VeryMe Responce is \n" + responce  );
		System.out.println("************************************");

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 9 )
	public void Discover( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		discoverResoureURL = ResourceUrls.discoverResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(discoverResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Discover Responce is \n" + responce  );
		System.out.println("************************************");

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 10 )
	public void ProductsAndServices( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		productsAndServicesResoureURL = ResourceUrls.productsAndServicesResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(productsAndServicesResoureURL).then()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println("ProductsAndServices Responce is \n" + responce  );
		System.out.println("************************************");

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 11 )
	public void BillHistory( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		billHistoryResoureURL = ResourceUrls.billHistoryResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(billHistoryResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("BillHistory Responce is \n" + responce  );
		System.out.println("************************************");

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 12 )
	public void Plan( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		planResoureURL = ResourceUrls.planResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(planResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Plan Responce is \n" + responce  );
		System.out.println("************************************");

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 13 )
	public void Usages( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		usagesResoureURL = ResourceUrls.usagesResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(usagesResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Usages Responce is \n" + responce  );
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 14 )
	public void Extras( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		extrasResoureURL = ResourceUrls.extrasResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(extrasResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Extras Responce is \n" + responce  );
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 15 )
	public void CurrentCharges( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		currentChargesResoureURL = ResourceUrls.currentChargesResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(currentChargesResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("CurrentCharges Responce is \n" + responce  );
		System.out.println("************************************");

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 16 )
	public void AdditionalCharges( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		additionalChargesResoureURL = ResourceUrls.additionalChargesResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(additionalChargesResoureURL).then()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println("AdditionalCharges Responce is \n" + responce  );
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 17 )
	public void Upgrades( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		upgradesResoureURL = ResourceUrls.upgradesResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(upgradesResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Upgrades Responce is \n" + responce  );
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 18 )
	public void VOV( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		vovResoureURL = ResourceUrls.vovResoureURL;
		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(vovResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("VOV Responce is \n" + responce  );
		System.out.println("************************************");

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 19 )
	public void PasswordLogin( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		passwordLoginResoureURL = ResourceUrls.passwordLoginResoureURL;
		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("Content-type", "application/json").header("JWT", backendJwtSoftToken)
				.body(payload.passwordLoginBody(userName)).when().post(passwordLoginResoureURL).then()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println("PasswordLogin Responce is \n" + responce  );
		System.out.println("************************************");

		
		JsonPath js1 = ReUsableMethods.rawToJson(responce);
		encryptedUserName = js1.getString("username");
		fullAccessToken = js1.getString("fullAccessToken");
		accountId = js1.getString("accountId");

	
		//System.out.print("fullAccessToken:" + fullAccessToken);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test(priority = 20 )
	public void GetAccounts( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		accountsResoureURL = ResourceUrls.accountsResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).header("Root-Full-Access-Token", fullAccessToken)
				.header("accountId", accountId).header("Full-Access-Token", fullAccessToken).when()
				.get(accountsResoureURL).then().assertThat().statusCode(200).extract().response()
				.asString();
		
		System.out.println("GetAccounts Responce is \n" + responce  );
		System.out.println("************************************");

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

	@Test(priority = 21 )
	public void GetSubscriptionsList( )
			throws MalformedURLException {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		accountsResoureURL = ResourceUrls.accountsResoureURL;

		subsListsResponce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Full-Access-Token", fullAccessToken).header("JWT", backendJwtSoftToken)
				.header("Parent-Subscription", subscription).header("accountIdHash", accountIdHash)
				.header("accountType", accountType).header("Parent-Subscription-Type", subscriptionType)
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("Root-Full-Access-Token", fullAccessToken).when()
				.get(accountsResoureURL.concat("{accountId}").concat("/subscriptions"), accountId).then()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println("GetSubscriptionsList Responce is \n" + subsListsResponce  );
		System.out.println("************************************");

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + subsListsResponce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "subscriptionSwitchResoureURL" })

	@Test(priority = 22 )
	public void SubscriptionSwitch( ) {
		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		subscriptionSwitchResoureURL = ResourceUrls.subscriptionSwitchResoureURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Full-Access-Token", fullAccessToken).header("JWT", backendJwtSoftToken)
				.header("Parent-Subscription", subscription).header("accountIdHash", accountIdHash)
				.header("Parent-Subscription-Type", subscriptionType).header("Segment", segment)
				.header("accountId", accountId).when().header("Subscription-Type", subscriptionType)
				.header("msisdnHash", ReUsableMethod.returnMsisdnHashValue(subsListsResponce, subscription))
				.header("Root-Full-Access-Token", fullAccessToken).header("msisdn", subscription)

				.get(subscriptionSwitchResoureURL).then().assertThat().statusCode(200).extract().response()
				.asString();
		
		System.out.println("SubscriptionSwitch Responce is \n" + subsListsResponce  );
		System.out.println("************************************");

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	/*@Parameters({ "baseURL", "changePinResoureURL", "subscription", "userName" })

	@Test(priority = 23)
	public void ChangePin(String baseURL, String changePinResoureURL, String subscription, String userName) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("Content-type", "application/json").header("JWT", backendJwtSoftToken)
				.header("Parent-Subscription", subscription).header("Parent-Subscription-Type", subscriptionType)
				.header("Full-Access-Token", fullAccessToken).body(payload.changePinBody(userName)).when()
				.post(changePinResoureURL).then().assertThat().statusCode(200).extract().response()
				.asString();

		System.out.println("ChangePin Responce is \n" + responce  );

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "resetPasswordResoureURL", "subscription", "userName" })

	@Test(priority = 24)
	public void RestPassword(String baseURL, String resetPasswordResoureURL, String subscription, String userName) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String responce = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("JWT", backendJwtSoftToken).header("Parent-Subscription", subscription)
				.header("Parent-Subscription-Type", Segment.subscriptionType).header("Segment", segment)
				.header("Subscription-Type", subscriptionType).header("Content-type", "application/json")
				.body(payload.resetPasswordBody(userName)).when().post(resetPasswordResoureURL).then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println("RestPassword Responce is \n" + responce  );

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + responce);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
*/

}