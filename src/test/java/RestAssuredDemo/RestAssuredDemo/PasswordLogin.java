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
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class PasswordLogin {
    public static String passwordLoginResoureURL;
    public static String encryptedUserName;
    public static String fullAccessToken;
    public static String accountId;

    public static void PasswordLogin(String baseURL, String subscription, String userName) {


        ReUsableMethods.generateExtentReport();
        RestAssured.baseURI = baseURL;
        passwordLoginResoureURL = ResourceUrls.passwordLoginResoureURL;
        String response = given().headers(ReUsableMethods.generalHeaders(subscription))
                .header("Segment", Segment.segment).header("Subscription-Type", Segment.subscriptionType)
                .header("Content-type", "application/json").header("JWT", SoftToken.backendJwtSoftToken)
                .body(payload.passwordLoginBody(userName)).when().post(passwordLoginResoureURL).then()
                .assertThat().statusCode(200).extract().response().asString();

        System.out.println("PasswordLogin response is \n" + response);
        System.out.println("************************************");


        JsonPath js1 = ReUsableMethods.rawToJson(response);
        encryptedUserName = js1.getString("username");
        fullAccessToken = js1.getString("fullAccessToken");
        accountId = js1.getString("accountId");


        //System.out.print("fullAccessToken:" + fullAccessToken);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

        VSTSFileUploader.addResponseContentToUploadableFile(response, MVA10APIS.PASSWORD_LOGIN.getName());

    }
}
