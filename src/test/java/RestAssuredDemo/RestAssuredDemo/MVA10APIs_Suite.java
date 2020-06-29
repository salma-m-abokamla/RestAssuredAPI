package RestAssuredDemo.RestAssuredDemo;

import com.apitesting.enums.MVA10APIS;
import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentTestManager;
import com.apitesting.uploader.VSTSFileUploader;
import com.relevantcodes.extentreports.LogStatus;
import files.ConfigFileReader;
import files.ReUsableMethods;
import files.ResourceUrls;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;

import static io.restassured.RestAssured.given;

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

	public static String hanSoloResponse;
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
	public String subsListsresponse;
	public String baseURL;
	public String subscription;
	public String userName;
	public String tilEnv;

	@BeforeSuite
	public void beforeSuite() {
		ConfigFileReader.setDeploymentEnv();
		baseURL = ConfigFileReader.getBaseUrl();
		tilEnv = ConfigFileReader.getTilEnv();
		System.out.println("BaseUrl :" + baseURL);
		System.out.println("TilEnv :" + tilEnv);
		subscription = ConfigFileReader.getSubscriprion();
		userName = ConfigFileReader.getUserName();

	}
	
	@Test (priority = 1)
	public void AppConfig( ) {

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

	@Test (priority = 2)
	public void HanSolo( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		hansoloResoureUR = ResourceUrls.hansoloResoureURL;

		hanSoloResponse = given().headers(ReUsableMethods.generalHeaders(subscription)).when().get(hansoloResoureUR)
				.then().assertThat().statusCode(200).extract().response().asString();

		System.out.println("HanSolo response is \n" + hanSoloResponse);
		System.out.println("************************************");

		JsonPath js = ReUsableMethods.rawToJson(hanSoloResponse);
		mspHansoloToken = js.getString("mspHansoloToken");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + hanSoloResponse);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.HANS_SOLO.getName());

	}

	@Test (priority = 3)
	public void SoftToken( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		softTokenResoureURL = ResourceUrls.softTokenResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Msp-Hansolo-Token", mspHansoloToken).when().get(softTokenResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("SoftToken response is \n" + response  );
		System.out.println("************************************");

		JsonPath js = ReUsableMethods.rawToJson(response);
		backendJwtSoftToken = js.getString("backendJwtSoftToken");
	//	System.out.println("backendJwtSoftToken is: " + backendJwtSoftToken);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.SOFT_TOKEN.getName());

	}

	@Test(priority = 4)
	public void Segment( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		segmentResoureURL = ResourceUrls.segmentResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("JWT", backendJwtSoftToken).when().get(segmentResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Segment response is \n" + response  );
		System.out.println("************************************");


		JsonPath js = ReUsableMethods.rawToJson(response);
		segment = js.getString("segment");
		//System.out.println("Segment is :" + segment);
		JsonPath js1 = ReUsableMethods.rawToJson(response);
		subscriptionType = js1.getString("subscriptionType.name");

		//System.out.println("SubscriptionType is :" + subscriptionType);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.SEGMENT.getName());

	}

	@Test(priority = 5 )
	public void SubsConfigWithoutSegment( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		subsConfigResoureURL = ResourceUrls.subsConfigResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("JWT", backendJwtSoftToken).when().get(subsConfigResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("SubsConfigWithoutSegment response is \n" + response  );
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.SUBS_CONFIG_WITHOUT_SEGMENT.getName());

	}

	@Test(priority = 6 )
	public void Dashboard() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		dashboardResoureURL = ResourceUrls.dashboardResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(dashboardResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Dashboard response is \n" + response  );
		System.out.println("************************************");


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.DASHBOARD.getName());

	}

	@Test(priority = 7 )
	public void SubsConfigWithSegment( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		subsConfigResoureURL = ResourceUrls.subsConfigResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(subsConfigResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("SubsConfigWithSegment response is \n" + response  );
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.SUBS_CONFIG_WITH_SEGMENT.getName());

	}

	@Test(priority = 8 )
	public void VeryMe( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		veryMeOffersResoureURL = ResourceUrls.veryMeOffersResoureURL;

		String response = given().queryParam("latitude", "0").queryParam("longitude", "0")
				.queryParam("locationStatus", "denied").header("Segment", segment)
				.header("Subscription-Type", subscriptionType).header("JWT", backendJwtSoftToken)
				.headers(ReUsableMethods.generalHeaders(subscription)).when().get(veryMeOffersResoureURL).then()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println("VeryMe response is \n" + response  );
		System.out.println("************************************");


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.VERY_ME.getName());

	}

	@Test(priority = 9 )
	public void Discover( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		discoverResoureURL = ResourceUrls.discoverResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(discoverResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Discover response is \n" + response  );
		System.out.println("************************************");


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.DISCOVER.getName());

	}

	@Test(priority = 10 )
	public void ProductsAndServices( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		productsAndServicesResoureURL = ResourceUrls.productsAndServicesResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(productsAndServicesResoureURL).then()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println("ProductsAndServices response is \n" + response  );
		System.out.println("************************************");


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.PRODUCTS_AND_SERVICES.getName());

	}

	@Test(priority = 11 )
	public void BillHistory( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		billHistoryResoureURL = ResourceUrls.billHistoryResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(billHistoryResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("BillHistory response is \n" + response  );
		System.out.println("************************************");


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.BILL_HISTORY.getName());

	}

	@Test(priority = 12 )
	public void Plan( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		planResoureURL = ResourceUrls.planResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(planResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Plan response is \n" + response  );
		System.out.println("************************************");


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.PLAN.getName());

	}

	@Test(priority = 13 )
	public void Usages( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		usagesResoureURL = ResourceUrls.usagesResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(usagesResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Usages response is \n" + response  );
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.USAGES.getName());

	}

	@Test(priority = 14 )
	public void Extras( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		extrasResoureURL = ResourceUrls.extrasResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(extrasResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Extras response is \n" + response  );
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.EXTRAS.getName());

	}

	@Test(priority = 15 )
	public void CurrentCharges( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		currentChargesResoureURL = ResourceUrls.currentChargesResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(currentChargesResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("CurrentCharges response is \n" + response  );
		System.out.println("************************************");


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.CURRENT_CHARGES.getName());

	}

	@Test(priority = 16 )
	public void AdditionalCharges( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		additionalChargesResoureURL = ResourceUrls.additionalChargesResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(additionalChargesResoureURL).then()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println("AdditionalCharges response is \n" + response  );
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.ADDITIONAL_CHARGES.getName());

	}

	@Test(priority = 17 )
	public void Upgrades( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		upgradesResoureURL = ResourceUrls.upgradesResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(upgradesResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("Upgrades response is \n" + response  );
		System.out.println("************************************");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.UPGRADES.getName());

	}

	@Test(priority = 18 )
	public void VOV( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		vovResoureURL = ResourceUrls.vovResoureURL;
		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).when().get(vovResoureURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("VOV response is \n" + response  );
		System.out.println("************************************");


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.VOV.getName());

	}

	@Test(priority = 19 )
	public void PasswordLogin( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		passwordLoginResoureURL = ResourceUrls.passwordLoginResoureURL;
		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("Content-type", "application/json").header("JWT", backendJwtSoftToken)
				.body(payload.passwordLoginBody(userName)).when().post(passwordLoginResoureURL).then()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println("PasswordLogin response is \n" + response  );
		System.out.println("************************************");


		JsonPath js1 = ReUsableMethods.rawToJson(response);
		encryptedUserName = js1.getString("username");
		fullAccessToken = js1.getString("fullAccessToken");
		accountId = js1.getString("accountId");


		//System.out.print("fullAccessToken:" + fullAccessToken);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.PASSWORD_LOGIN.getName());

	}

	@Test(priority = 20 )
	public void GetAccounts( ) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		accountsResoureURL = ResourceUrls.accountsResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("JWT", backendJwtSoftToken).header("Root-Full-Access-Token", fullAccessToken)
				.header("accountId", accountId).header("Full-Access-Token", fullAccessToken).when()
				.get(accountsResoureURL).then().assertThat().statusCode(200).extract().response()
				.asString();

		System.out.println("GetAccounts response is \n" + response  );
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

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.GET_ACCOUNTS.getName());


	}

	@Test(priority = 21 )
	public void GetSubscriptionsList( )
			throws MalformedURLException {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		accountsResoureURL = ResourceUrls.accountsResoureURL;

		subsListsresponse = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Full-Access-Token", fullAccessToken).header("JWT", backendJwtSoftToken)
				.header("Parent-Subscription", subscription).header("accountIdHash", accountIdHash)
				.header("accountType", accountType).header("Parent-Subscription-Type", subscriptionType)
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("Root-Full-Access-Token", fullAccessToken).when()
				.get(accountsResoureURL.concat("/{accountId}").concat("/subscriptions"), accountId).then()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println("GetSubscriptionsList response is \n" + subsListsresponse  );
		System.out.println("************************************");


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + subsListsresponse);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.GET_SUBSCRIPTION_LIST.getName());

	}

	@Parameters({ "subscriptionSwitchResoureURL" })

	@Test(priority = 22 )
	public void SubscriptionSwitch( ) {
		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		subscriptionSwitchResoureURL = ResourceUrls.subscriptionSwitchResoureURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Full-Access-Token", fullAccessToken).header("JWT", backendJwtSoftToken)
				.header("Parent-Subscription", subscription).header("accountIdHash", accountIdHash)
				.header("Parent-Subscription-Type", subscriptionType).header("Segment", segment)
				.header("accountId", accountId).when().header("Subscription-Type", subscriptionType)
				.header("msisdnHash", ReUsableMethod.returnMsisdnHashValue(subsListsresponse, subscription))
				.header("Root-Full-Access-Token", fullAccessToken).header("msisdn", subscription)

				.get(subscriptionSwitchResoureURL).then().assertThat().statusCode(200).extract().response()
				.asString();

		System.out.println("SubscriptionSwitch response is \n" + subsListsresponse  );
		System.out.println("************************************");


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(hanSoloResponse, MVA10APIS.SUBSCRIPTION_SWITCH.getName());

	}


	//uploading the files will be done here
	@AfterClass
	protected void uploadTheFiles(ITestContext result) {
		try {
			VSTSFileUploader.pushToRemote("MVA10APIs");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	/*@Parameters({ "baseURL", "changePinResoureURL", "subscription", "userName" })

	@Test(priority = 23)
	public void ChangePin(String baseURL, String changePinResoureURL, String subscription, String userName) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String response = given().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Segment", segment).header("Subscription-Type", subscriptionType)
				.header("Content-type", "application/json").header("JWT", backendJwtSoftToken)
				.header("Parent-Subscription", subscription).header("Parent-Subscription-Type", subscriptionType)
				.header("Full-Access-Token", fullAccessToken).body(payload.changePinBody(userName)).when()
				.post(changePinResoureURL).then().assertThat().statusCode(200).extract().response()
				.asString();

		System.out.println("ChangePin response is \n" + response  );


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Parameters({ "baseURL", "resetPasswordResoureURL", "subscription", "userName" })

	@Test(priority = 24)
	public void RestPassword(String baseURL, String resetPasswordResoureURL, String subscription, String userName) {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String response = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("JWT", backendJwtSoftToken).header("Parent-Subscription", subscription)
				.header("Parent-Subscription-Type", Segment.subscriptionType).header("Segment", segment)
				.header("Subscription-Type", subscriptionType).header("Content-type", "application/json")
				.body(payload.resetPasswordBody(userName)).when().post(resetPasswordResoureURL).then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println("RestPassword response is \n" + response  );


		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
*/

}