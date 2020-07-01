package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentManager;
import com.apitesting.listners.ExtentTestManager;

import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import files.ResourceUrls;
import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.path.json.JsonPath;

public class MVA10APIs_Suite2 extends BaseClass {

	public static String mspHansoloToken;
	public String baseURL;
	public String subscription;
	public String userName;
	public String hansoloResoureUR;
	public String hanSoloresponce;
	public String softTokenResoureURL;
	@AfterSuite
    public void tear()
    {
		ExtentManager.extent.flush();

    }
	@Test
	public void INT1_Environment_SUP02() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = CofigFileReader.getBaseUrlINT1SUP02();
		hansoloResoureUR = ResourceUrls.hansoloResoureURL;


		try {
			hanSoloresponce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionSUP02()))
					.when().get(hansoloResoureUR).then().assertThat().statusCode(200).log().all().extract().response().asString();
		} catch (AssertionError e) {
			String failureMessage = "INT11 environment on SUP02 is down";
			System.out.println(failureMessage);
		    Allure.addAttachment("Environment Status: ", failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;
		}

		JsonPath js = ReUsableMethods.rawToJson(hanSoloresponce);
		mspHansoloToken = js.getString("mspHansoloToken");

		softTokenResoureURL = ResourceUrls.softTokenResoureURL;
		String responce = null;
		try {
			responce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionSUP02()))
					.header("Msp-Hansolo-Token", mspHansoloToken).when().get(softTokenResoureURL).then().assertThat()
					.statusCode(200).log().all().extract().response().asString();
		} catch (AssertionError e) {

			String failureMessage = "INT1 environment on SUP02 is down";
			System.out.println(failureMessage);
		    Allure.addAttachment("Environment Status: ", failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;
		}
		String sucessMessage = "INT1 environment on SUP02 is up and working fine";
		System.out.println(sucessMessage);
	    Allure.addAttachment("Environment Status: ", sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test
	public void QC1_Environment_SUP02() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = CofigFileReader.getBaseUrlQC1SUP02();
		hansoloResoureUR = ResourceUrls.hansoloResoureURL;
		try {
			hanSoloresponce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionSUP02()))
					.when().get(hansoloResoureUR).then().assertThat().statusCode(200).extract().response().asString();
		} catch (AssertionError e) {
			String failureMessage = "QC1 environment on SUP02 is down";
			System.out.println(failureMessage);
		    Allure.addAttachment("Environment Status: ", failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;

		}

		JsonPath js = ReUsableMethods.rawToJson(hanSoloresponce);
		mspHansoloToken = js.getString("mspHansoloToken");

		softTokenResoureURL = ResourceUrls.softTokenResoureURL;
		String responce = null;
		try {
			responce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionSUP02()))
					.header("Msp-Hansolo-Token", mspHansoloToken).when().get(softTokenResoureURL).then().assertThat()
					.statusCode(200).extract().response().asString();
		} catch (AssertionError e) {

			String failureMessage = "QC1 environment on SUP02 is down";
			System.out.println(failureMessage);
		    Allure.addAttachment("Environment Status: ", failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;

		}
		String sucessMessage = "QC1 environment on SUP02 is up and working fine";
		System.out.println(sucessMessage);
	    Allure.addAttachment("Environment Status: ", sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
	@Test
	public void QC1_Environment_E7() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = CofigFileReader.getBaseUrlQC1SUP02();
		hansoloResoureUR = ResourceUrls.hansoloResoureURL;
		try {
			hanSoloresponce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionE7()))
					.when().get(hansoloResoureUR).then().assertThat().statusCode(200).extract().response().asString();
		} catch (AssertionError e) {
			String failureMessage = "QC1 environment on E7 is down";
			System.out.println(failureMessage);
		    Allure.addAttachment("Environment Status: ", failureMessage);

			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;

		}

		JsonPath js = ReUsableMethods.rawToJson(hanSoloresponce);
		mspHansoloToken = js.getString("mspHansoloToken");

		softTokenResoureURL = ResourceUrls.softTokenResoureURL;
		String responce = null;
		try {
			responce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionSUP02()))
					.header("Msp-Hansolo-Token", mspHansoloToken).when().get(softTokenResoureURL).then().assertThat()
					.statusCode(200).extract().response().asString();
		} catch (AssertionError e) {

			String failureMessage = "QC1 environment on E7 is down";
			System.out.println(failureMessage);
		    Allure.addAttachment("Environment Status: ", failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;

		}
		String sucessMessage = "QC1 environment on E7 is up and working fine";
		System.out.println(sucessMessage);
	    Allure.addAttachment("Environment Status: ", sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
	@Test
	public void SIT2_Environment_SUP02() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = CofigFileReader.getBaseUrlSIT1SUP02();
		hansoloResoureUR = ResourceUrls.hansoloResoureURL;
		try {
			hanSoloresponce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionSUP02()))
					.when().get(hansoloResoureUR).then().assertThat().statusCode(200).extract().response().asString();
		} catch (AssertionError e) {
			String failureMessage = "SIT2 environment on SUP02 is down";
			System.out.println(failureMessage);
		    Allure.addAttachment("Environment Status: ", failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;

		}

		JsonPath js = ReUsableMethods.rawToJson(hanSoloresponce);
		mspHansoloToken = js.getString("mspHansoloToken");

		softTokenResoureURL = ResourceUrls.softTokenResoureURL;
		String responce = null;
		try {
			responce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionSUP02()))
					.header("Msp-Hansolo-Token", mspHansoloToken).when().get(softTokenResoureURL).then().assertThat()
					.statusCode(200).extract().response().asString();
		} catch (AssertionError e) {

			String failureMessage = "SIT2 environment on SUP02 is down";
			System.out.println(failureMessage);
		    Allure.addAttachment("Environment Status: ", failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;

		}
		String sucessMessage = "SIT2 environment on SUP02 is up and working fine";
		System.out.println(sucessMessage);
	    Allure.addAttachment("Environment Status: ", sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test
	public void DEV1_Environment_SUP02() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = CofigFileReader.getBaseUrlDEV1SUP02();
		hansoloResoureUR = ResourceUrls.hansoloResoureURL;
		try {
			hanSoloresponce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionSUP02()))
					.when().get(hansoloResoureUR).then().assertThat().statusCode(200).extract().response().asString();
		} catch (AssertionError e) {
			String failureMessage = "DEV1 environment on SUP02 is down";
			System.out.println(failureMessage);
		    Allure.addAttachment("Environment Status: ", failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;

		}

		JsonPath js = ReUsableMethods.rawToJson(hanSoloresponce);
		mspHansoloToken = js.getString("mspHansoloToken");

		softTokenResoureURL = ResourceUrls.softTokenResoureURL;
		String responce = null;
		try {
			responce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionSUP02()))
					.header("Msp-Hansolo-Token", mspHansoloToken).when().get(softTokenResoureURL).then().assertThat()
					.statusCode(200).extract().response().asString();
		} catch (AssertionError e) {

			String failureMessage = "DEV1 environment on SUP02 is down";
			System.out.println(failureMessage);
		    Allure.addAttachment("Environment Status: ", failureMessage);

			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;

		}
		String sucessMessage = "DEV1 environment on SUP02 is up and working fine";
		System.out.println(sucessMessage);
	    Allure.addAttachment("Environment Status: ", sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test
	public void SIT1_Environment_SUP02() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = CofigFileReader.getBaseUrlSIT1SUP02();
		hansoloResoureUR = ResourceUrls.hansoloResoureURL;
		try {
			hanSoloresponce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionSUP02()))
					.when().get(hansoloResoureUR).then().assertThat().statusCode(200).extract().response().asString();
		} catch (AssertionError e) {
			String failureMessage = "SIT1 environment on SUP02 is down";
			System.out.println(failureMessage);
		    Allure.addAttachment("Environment Status: ", failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;
		}

		try {
			JsonPath js = ReUsableMethods.rawToJson(hanSoloresponce);
			mspHansoloToken = js.getString("mspHansoloToken");

			softTokenResoureURL = ResourceUrls.softTokenResoureURL;
			String responce = null;
			responce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionSUP02()))
					.header("Msp-Hansolo-Token", mspHansoloToken).when().get(softTokenResoureURL).then().assertThat()
					.statusCode(200).extract().response().asString();
		} catch (AssertionError e) {

			String failureMessage = "SIT1 environment on SUP02 is down";
			System.out.println(failureMessage);
		    Allure.addAttachment("Environment Status: ", failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;
		}
		String sucessMessage = "SIT1 environment on SUP02 is up and working fine";
		System.out.println(sucessMessage);
	    Allure.addAttachment("Environment Status: ", sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
       


	}
}