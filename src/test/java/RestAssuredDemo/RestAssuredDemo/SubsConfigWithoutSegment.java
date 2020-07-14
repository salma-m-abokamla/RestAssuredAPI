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
import io.restassured.RestAssured;

public class SubsConfigWithoutSegment {
    public static String subsConfigResoureURL;

    public static void SubsConfigWithoutSegment(String baseURL, String subscription) {
        ReUsableMethods.generateExtentReport();
        RestAssured.baseURI = baseURL;
        subsConfigResoureURL = ResourceUrls.subsConfigResoureURL;

        String response = given().headers(ReUsableMethods.generalHeaders(subscription))
                .header("JWT", SoftToken.backendJwtSoftToken).when().get(subsConfigResoureURL).then().assertThat()
                .statusCode(200).extract().response().asString();

        System.out.println("SubsConfigWithoutSegment response is \n" + response);
        System.out.println("************************************");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Response is : " + response);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verified the Status code successfully !!");

        VSTSFileUploader.addResponseContentToUploadableFile(response, MVA10APIS.SUBS_CONFIG_WITHOUT_SEGMENT.getName());

    }
}
