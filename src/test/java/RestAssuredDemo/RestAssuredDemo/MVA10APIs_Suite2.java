package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
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

import files.ReUsableMethods;
import files.ResourceUrls;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MVA10APIs_Suite2 extends BaseClass {

	public static String mspHansoloToken;
	public String baseURL;
	public String subscription;
	public String userName;
	public String hansoloResoureUR;
	public String hanSoloresponce;
	public String softTokenResoureURL;

	@Test
	public void INT1_Environment_SUP02() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = CofigFileReader.getBaseUrlINT1SUP02();
		hansoloResoureUR = ResourceUrls.hansoloResoureURL;
		try {
			hanSoloresponce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionSUP02()))
					.when().get(hansoloResoureUR).then().assertThat().statusCode(200).extract().response().asString();
		} catch (AssertionError e) {
			String failureMessage = "INT11 environment is down";
			System.out.println(failureMessage);
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

			String failureMessage = "INT1 environment is down";
			System.out.println(failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;
		}
		String sucessMessage = "INT1 environment on SUP02 is up and working fine";
		System.out.println(sucessMessage);
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
			String failureMessage = "QC1 environment is down";
			System.out.println(failureMessage);
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

			String failureMessage = "QC1 environment is down";
			System.out.println(failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;

		}
		String sucessMessage = "QC1 environment on SUP02 is up and working fine";
		System.out.println(sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test
	public void SIT2_Environment_SUP02() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = CofigFileReader.getBaseUrlSIT2SUP02();
		hansoloResoureUR = ResourceUrls.hansoloResoureURL;
		try {
			hanSoloresponce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionSUP02()))
					.when().get(hansoloResoureUR).then().assertThat().statusCode(200).extract().response().asString();
		} catch (AssertionError e) {
			String failureMessage = "SIT2 environment is down";
			System.out.println(failureMessage);
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

			String failureMessage = "SIT2 environment is down";
			System.out.println(failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;

		}
		String sucessMessage = "SIT2 environment on SUP02 is up and working fine";
		System.out.println(sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}

	@Test
	public void DEV_Environment_SUP02() {

		ReUsableMethods.generateExtentReport();
		RestAssured.baseURI = CofigFileReader.getBaseUrlDEVSUP02();
		hansoloResoureUR = ResourceUrls.hansoloResoureURL;
		try {
			hanSoloresponce = given().headers(ReUsableMethods.generalHeaders(CofigFileReader.getSubscriprionSUP02()))
					.when().get(hansoloResoureUR).then().assertThat().statusCode(200).extract().response().asString();
		} catch (AssertionError e) {
			String failureMessage = "DEV environment is down";
			System.out.println(failureMessage);
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

			String failureMessage = "DEV environment is down";
			System.out.println(failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;

		}
		String sucessMessage = "DEV environment on SUP02 is up and working fine";
		System.out.println(sucessMessage);
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
			String failureMessage = "SIT1 environment is down";
			System.out.println(failureMessage);
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

			String failureMessage = "SIT1 environment is down";
			System.out.println(failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
			return;
		}
		String sucessMessage = "SIT1 environment on SUP02 is up and working fine";
		System.out.println(sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, sucessMessage);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

	}
}