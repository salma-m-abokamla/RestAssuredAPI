package RestAssuredDemo.RestAssuredDemo;

import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import files.ReUsableMethods;
import files.ResourceUrls;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DEV1_Environment_E7 extends BaseClass {

    public static String mspHansoloToken;
    public static String baseURL;
    public static String subscription;
    public static String hanSoloresponce;

    @Test
    public static void INT1_Environment_E7() {
        baseURL = ConfigFileReader.getDEV1BaseUrl();
        System.out.println("BaseUrl :" + baseURL);
        subscription = ConfigFileReader.readE7TestData();
        System.out.println("subscription :" + subscription);
        RestAssured.baseURI = baseURL;


        try {
            hanSoloresponce = given().headers(ReUsableMethods.generalHeaders(subscription))
                    .when().get(ResourceUrls.hansoloResoureURL).then().assertThat().statusCode(200).log().all().extract().response().asString();
        } catch (AssertionError e) {
            String failureMessage = "DEV1 environment on E7 is down";
            System.out.println(failureMessage);
            Allure.addAttachment("Environment Status: ", failureMessage);
            ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
            ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
            return;
        }

        JsonPath js = ReUsableMethods.rawToJson(hanSoloresponce);
        mspHansoloToken = js.getString("mspHansoloToken");
        String responce = null;
        try {
            responce = given().headers(ReUsableMethods.generalHeaders(subscription))
                    .header("Msp-Hansolo-Token", mspHansoloToken).when().get(ResourceUrls.softTokenResoureURL).then().assertThat()
                    .statusCode(200).log().all().extract().response().asString();
        } catch (AssertionError e) {

            String failureMessage = "DEV1 environment on E7 is down";
            System.out.println(failureMessage);
            Allure.addAttachment("Environment Status: ", failureMessage);
            ExtentTestManager.getTest().log(LogStatus.INFO, failureMessage);
            ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");
            return;
        }
        String sucessMessage = "DEV1 environment on E7 is up and working fine";
        System.out.println(sucessMessage);
        Allure.addAttachment("Environment Status: ", sucessMessage);
        ExtentTestManager.getTest().log(LogStatus.INFO, sucessMessage);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

    }

}

