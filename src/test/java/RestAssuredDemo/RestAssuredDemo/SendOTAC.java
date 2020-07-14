package RestAssuredDemo.RestAssuredDemo;

import com.apitesting.enums.UFLAPIS;
import com.apitesting.listners.ExtentTestManager;
import com.apitesting.uploader.VSTSFileUploader;
import com.relevantcodes.extentreports.LogStatus;
import files.ReUsableMethods;
import files.ResourceUrls;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class SendOTAC {
    public static void SendOTAC(String baseURL, String subscription){

        ReUsableMethods.generateExtentReport();
        RestAssured.baseURI = baseURL;

        String response = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
                .header("Parent-Subscription", subscription)
                .header("registeredNumberId",RegisteredNumbers.registeredNumberId )
                .header("authTokenL2", RegisteredNumbers.authTokenL2)
                .when().get(ResourceUrls.sendOTACResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
                .asString();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

        VSTSFileUploader.addResponseContentToUploadableFile(response, UFLAPIS.SEND_OTAC.getName());


    }
}
