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

public class GetSubscriptionsList {
    public static String msisdnHash, subscriptionType, segment;

    public static void GetSubscriptionsList(String baseURL, String subscription) {
        ReUsableMethods.generateExtentReport();
        RestAssured.baseURI = baseURL;

        String response = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
                .header("authTokenL3", GetAccountsUFL.authTokenL3).header("Parent-Subscription", subscription)
                .header("accountIdHash", GetAccountsUFL.accountIdHash).header("accountType", GetAccountsUFL.accountType)
                .when()
                .get(ResourceUrls.accountsResoureURL.concat("/{accountId}").concat("/subscriptions"), GetAccountsUFL.accountId).then().log().all()
                .assertThat().statusCode(200).extract().response().asString();

        JsonPath js1 = ReUsableMethods.rawToJson(response);
        msisdnHash = js1.getString("subscriptions[0].msisdnHash");
        subscriptionType = js1.getString("subscriptions[0].subscriptionType.name");
        segment = js1.getString("subscriptions[0].segment");

        System.out.println("msisdnHash: " + msisdnHash);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

        VSTSFileUploader.addResponseContentToUploadableFile(response, UFLAPIS.GET_SUBSCRIPTIONS_LIST.getName());


    }
}
