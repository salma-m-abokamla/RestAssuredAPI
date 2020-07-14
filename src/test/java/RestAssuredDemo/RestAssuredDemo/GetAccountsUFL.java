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

public class GetAccountsUFL extends BaseClass {
    public static String accountType, accountIdHash, accountId, authTokenL3;

    public static void GetAccounts(String baseURL, String subscription) {
        ReUsableMethods.generateExtentReport();
        RestAssured.baseURI = baseURL;

        String response = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
                .header("Parent-Subscription", subscription)
                .header("registeredNumberId", RegisteredNumbers.registeredNumberId)
                .header("authTokenL2", RegisteredNumbers.authTokenL2)
                .header("otac", "GIBKK")
                .when()
                .get(ResourceUrls.accountsResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
                .asString();

        JsonPath js1 = ReUsableMethods.rawToJson(response);
        accountType = js1.getString("accounts[0].accountType");
        accountIdHash = js1.getString("accounts[0].accountIdHash");
        accountId = js1.getString("accounts[0].accountId");
        authTokenL3 = js1.getString("authTokenL3");

        System.out.println("accountType" + accountType);
        System.out.println("accountIdHash" + accountIdHash);
        System.out.println("accountId" + accountId);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

        VSTSFileUploader.addResponseContentToUploadableFile(response, UFLAPIS.GET_ACCOUNTS.getName());


    }
}
