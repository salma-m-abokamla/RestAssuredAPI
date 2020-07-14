package RestAssuredDemo.RestAssuredDemo;

import com.apitesting.enums.UFLAPIS;
import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentTestManager;
import com.apitesting.uploader.VSTSFileUploader;
import com.relevantcodes.extentreports.LogStatus;
import files.ConfigFileReader;
import files.ReUsableMethods;
import files.ResourceUrls;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import static com.apitesting.enums.UFLAPIS.REGISTERED_NUMBERS;
import static io.restassured.RestAssured.given;

public class UFLAPIs_Suite extends BaseClass {
    public String baseURL,subscription,userName;
    @BeforeSuite
    public void beforeSuite() {
        ConfigFileReader.setDeploymentEnv();
        baseURL= ConfigFileReader.getBaseUrl();
        subscription= ConfigFileReader.getSubscriprion();
        userName= ConfigFileReader.getUserName();
    }
    @Test(priority = 1)
    public void RegisteredNumbers() { RegisteredNumbers.RegisteredNumbers(baseURL,subscription,userName); }
    @Test(priority = 2)
    public void SendOTAC() { SendOTAC.SendOTAC(baseURL,subscription); }
    @Test(priority = 3)
    public void GetAccounts() { GetAccountsUFL.GetAccounts(baseURL,subscription); }
    @Test(priority = 4)
    public void GetSubscriptionsList()
            throws MalformedURLException { GetSubscriptionsList.GetSubscriptionsList(baseURL,subscription); }
    @Test(priority = 5)
    public void UpfrontLogin() { UpfrontLogin.UpfrontLogin(baseURL,subscription);}

    //uploading the files will be done here
    @AfterClass
    protected void uploadTheFiles(ITestContext result) {
        try {
            VSTSFileUploader.pushToRemote("UFLAPIs");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }
	/*@Test(priority = 6)
	public void ChangePin() {ChangePin.ChangePin(baseURL,subscription,userName);}*/
}
