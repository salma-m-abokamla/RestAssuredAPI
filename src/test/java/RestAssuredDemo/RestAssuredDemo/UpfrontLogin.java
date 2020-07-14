package RestAssuredDemo.RestAssuredDemo;

import com.apitesting.enums.UFLAPIS;
import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentTestManager;
import com.apitesting.uploader.VSTSFileUploader;
import com.relevantcodes.extentreports.LogStatus;
import files.ReUsableMethods;
import files.ResourceUrls;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class UpfrontLogin {
    public static String accessTokenL3;

    public static void UpfrontLogin(String baseURL, String subscription) {
        ReUsableMethods.generateExtentReport();
        RestAssured.baseURI = baseURL;

        String response = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
                .header("authTokenL3", GetAccountsUFL.authTokenL3).header("Parent-Subscription", subscription)
                .header("accountIdHash", GetAccountsUFL.accountIdHash)
                .header("accountId", GetAccountsUFL.accountId)
                .header("subscriptionId", subscription)
                .header("subscriptionIdHash", GetSubscriptionsList.msisdnHash)
                .body("").when().post(ResourceUrls.upfrontLoginResoureURL).then().log().all().assertThat().statusCode(200).extract()
                .response().asString();
        JsonPath js1 = ReUsableMethods.rawToJson(response);
        accessTokenL3 = js1.getString("accessTokenL3");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

        VSTSFileUploader.addResponseContentToUploadableFile(response, UFLAPIS.UPFORNT_LOGIN.getName());


    }
}
