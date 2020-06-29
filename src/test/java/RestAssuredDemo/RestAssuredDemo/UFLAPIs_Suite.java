package RestAssuredDemo.RestAssuredDemo;

import com.apitesting.enums.UFLAPIS;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import static io.restassured.RestAssured.given;

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
    public String baseURL;
	public String subscription;
	public String userName;
	
    @BeforeSuite
	public void beforeSuite() {
		ConfigFileReader.setDeploymentEnv();
		baseURL= ConfigFileReader.getBaseUrl();
		subscription= ConfigFileReader.getSubscriprion();
		userName= ConfigFileReader.getUserName();
		
			}
	@Test(priority = 1)
	public void RegisteredNumbers() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;
		registeredNumbersResoureURL=ResourceUrls.registeredNumbersResoureURL;

		String response = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Parent-Subscription", subscription).header("Content-type", "application/json")
				.body(payload.passwordLoginBody(userName)).when().post(registeredNumbersResoureURL).then().log().all()
				.assertThat().statusCode(200).extract().response().asString();
		JsonPath js1 = ReUsableMethods.rawToJson(response);
		registeredNumberId = js1.getString("numbers[0].id");
		authTokenL2=js1.getString("authToken");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
		
		VSTSFileUploader.addResponseContentToUploadableFile(response, UFLAPIS.REGISTERED_NUMBERS.getName());

	}
	@Test(priority = 2)
	public void SendOTAC() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String response = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Parent-Subscription", subscription)
				.header("registeredNumberId",registeredNumberId )
				.header("authTokenL2", authTokenL2)
				.when().get(ResourceUrls.sendOTACResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(response, UFLAPIS.SEND_OTAC.getName());


	}
	@Test(priority = 3)
	public void GetAccounts() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String response = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Parent-Subscription", subscription)
				.header("registeredNumberId",registeredNumberId )
				.header("authTokenL2", authTokenL2)
				.header("otac", "GIBKK")
				.when()
				.get(ResourceUrls.accountsResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
				.asString();
		JsonPath js1 = ReUsableMethods.rawToJson(response);
		accountType = js1.getString("accounts[0].accountType");
		accountIdHash = js1.getString("accounts[0].accountIdHash");
		accountId = js1.getString("accounts[0].accountId");
		authTokenL3=js1.getString("authTokenL3");
		
		System.out.println("accountType" + accountType);
		System.out.println("accountIdHash" + accountIdHash);
		System.out.println("accountId" + accountId);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(response, UFLAPIS.GET_ACCOUNTS.getName());

	}

	@Test(priority = 4)
	public void GetSubscriptionsList()
			throws MalformedURLException {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String response = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("authTokenL3", authTokenL3).header("Parent-Subscription", subscription)
				.header("accountIdHash", accountIdHash).header("accountType", accountType)
				.when()
				.get(ResourceUrls.accountsResoureURL.concat("/{accountId}").concat("/subscriptions"), accountId).then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath js1 = ReUsableMethods.rawToJson(response);
		msisdnHash=js1.getString("subscriptions[0].msisdnHash");
		subscriptionType=js1.getString("subscriptions[0].subscriptionType.name");
		segment=js1.getString("subscriptions[0].segment");

		System.out.println("msisdnHash: "+msisdnHash);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(response, UFLAPIS.GET_SUBSCRIPTIONS_LIST.getName());

	}


	@Test(priority = 5)
	public void UpfrontLogin() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String response = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("authTokenL3", authTokenL3).header("Parent-Subscription", subscription)
				.header("accountIdHash", accountIdHash)
				.header("accountId", accountId)
				.header("subscriptionId", subscription)
				.header("subscriptionIdHash",msisdnHash )
				.body("").when().post(ResourceUrls.upfrontLoginResoureURL).then().log().all().assertThat().statusCode(200).extract()
				.response().asString();
		JsonPath js1 = ReUsableMethods.rawToJson(response);
		accessTokenL3=js1.getString("accessTokenL3");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

		VSTSFileUploader.addResponseContentToUploadableFile(response, UFLAPIS.UPFORNT_LOGIN.getName());

	}

	//uploading the files will be done here
	@AfterClass
	protected void uploadTheFiles(ITestContext result) {
		try {
			VSTSFileUploader.pushToRemote("UFLAPIs");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	/*@Test(priority = 6)
	public void ChangePin() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = baseURL;

		String response = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
				.header("Parent-Subscription", subscription)
				.header("Access-Token-L3",accessTokenL3)
				.header("Segment",segment)
				.header("Subscription-Type",subscriptionType)
				.header("Content-type", "application/json")
				.body(payload.changePinBody(userName)).when()
				.post(ResourceUrls.changePinResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}*/
}
