package RestAssuredDemo.RestAssuredDemo;

import com.apitesting.enums.UFLAPIS;
import com.apitesting.listners.ExtentTestManager;
import com.apitesting.uploader.VSTSFileUploader;
import com.relevantcodes.extentreports.LogStatus;
import files.ReUsableMethods;
import files.ResourceUrls;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class RegisteredNumbers{
    public static String registeredNumbersResoureURL,registeredNumberId,authTokenL2;

    public static void RegisteredNumbers(String baseURL, String subscription,String userName) {
        ReUsableMethods.generateExtentReport();
        RestAssured.baseURI = baseURL;
        registeredNumbersResoureURL = ResourceUrls.registeredNumbersResoureURL;

        String response = given().log().all().headers(ReUsableMethods.generalHeaders(subscription))
                .header("Parent-Subscription", subscription).header("Content-type", "application/json")
                .body(payload.passwordLoginBody(userName)).when().post(registeredNumbersResoureURL).then().log().all()
                .assertThat().statusCode(200).extract().response().asString();
        JsonPath js1 = ReUsableMethods.rawToJson(response);
        registeredNumberId = js1.getString("numbers[0].id");
        authTokenL2 = js1.getString("authToken");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

        VSTSFileUploader.addResponseContentToUploadableFile(response, UFLAPIS.REGISTERED_NUMBERS.getName());

    }

}
