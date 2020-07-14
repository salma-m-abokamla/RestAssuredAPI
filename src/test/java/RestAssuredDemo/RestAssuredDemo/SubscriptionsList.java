package RestAssuredDemo.RestAssuredDemo;

import static io.restassured.RestAssured.given;

import java.net.MalformedURLException;

import com.apitesting.enums.MVA10APIS;
import com.apitesting.uploader.VSTSFileUploader;
import files.ResourceUrls;
import org.apache.tools.ant.taskdefs.Get;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.apitesting.listners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class SubscriptionsList {
    public static String accountsResoureURL, subsListsresponse;

    public static void GetSubscriptionsList(String baseURL, String subscription)
            throws MalformedURLException {

        ReUsableMethods.generateExtentReport();
        RestAssured.baseURI = baseURL;
        accountsResoureURL = ResourceUrls.accountsResoureURL;

        subsListsresponse = given().headers(ReUsableMethods.generalHeaders(subscription))
                .header("Full-Access-Token", PasswordLogin.fullAccessToken).header("JWT", SoftToken.backendJwtSoftToken)
                .header("Parent-Subscription", subscription).header("accountIdHash", GetAccounts.accountIdHash)
                .header("accountType", GetAccounts.accountType).header("Parent-Subscription-Type", Segment.subscriptionType)
                .header("Segment", Segment.segment).header("Subscription-Type", Segment.subscriptionType)
                .header("Root-Full-Access-Token", PasswordLogin.fullAccessToken).when()
                .get(accountsResoureURL.concat("/{accountId}").concat("/subscriptions"), GetAccounts.accountId).then()
                .assertThat().statusCode(200).extract().response().asString();

        System.out.println("GetSubscriptionsList response is \n" + subsListsresponse);
        System.out.println("************************************");


        ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + subsListsresponse);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

        VSTSFileUploader.addResponseContentToUploadableFile(subsListsresponse, MVA10APIS.GET_SUBSCRIPTION_LIST.getName());

    }
}
