package RestAssuredDemo.RestAssuredDemo;

import com.apitesting.enums.UFLAPIS;
import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentTestManager;
import com.apitesting.uploader.VSTSFileUploader;
import com.relevantcodes.extentreports.LogStatus;
import files.ReUsableMethods;
import files.ResourceUrls;
import files.payload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class ChangePinUFL {
    public static void ChangePinUFL(String baseURL, String subscription,String userName){
        ReUsableMethods.generateExtentReport();
        RestAssured.baseURI = baseURL;

        String response = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
                .header("Parent-Subscription", subscription)
                .header("Access-Token-L3",UpfrontLogin.accessTokenL3)
                .header("Segment",GetSubscriptionsList.segment)
                .header("Subscription-Type",GetSubscriptionsList.subscriptionType)
                .header("Content-type", "application/json")
                .body(payload.passwordLoginBody(userName)).when()
                .post(ResourceUrls.changePinResoureURL).then().log().all().assertThat().statusCode(200).extract().response()
                .asString();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

        VSTSFileUploader.addResponseContentToUploadableFile(response, UFLAPIS.CHANGE_PIN.getName());


    }


}
