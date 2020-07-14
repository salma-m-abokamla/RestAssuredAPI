package RestAssuredDemo.RestAssuredDemo;

import com.apitesting.listners.BaseClass;
import com.apitesting.uploader.VSTSFileUploader;
import files.ConfigFileReader;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;

public class MVA10APIs_Suite extends BaseClass {


    public String baseURL, subscription, userName;

    @BeforeSuite
    public void beforeSuite() {
        ConfigFileReader.setDeploymentEnv();
        baseURL = ConfigFileReader.getBaseUrl();
        System.out.println("BaseUrl :" + baseURL);
        subscription = ConfigFileReader.getSubscriprion();
        userName = ConfigFileReader.getUserName();
    }

    @Test(priority = 1)
    public void AppConfig() {
        AppConfig.AppConfig(baseURL, subscription);
    }

    @Test(priority = 2)
    public void HanSolo() {
        HanSolo.HanSolo(baseURL, subscription);
    }

    @Test(priority = 3)
    public void SoftToken() {
        SoftToken.SoftToken(baseURL, subscription);
    }

    @Test(priority = 4)
    public void Segment() {
        Segment.Segment(baseURL, subscription);
    }

    @Test(priority = 5)
    public void SubsConfigWithoutSegment() {
        SubsConfigWithoutSegment.SubsConfigWithoutSegment(baseURL, subscription);
    }

    @Test(priority = 6)
    public void Dashboard() {
        Dashboard.Dashboard(baseURL, subscription);
    }

    @Test(priority = 7)
    public void SubsConfigWithSegment() {
        SubsConfigWithSegment.SubsConfigWithSegment(baseURL, subscription);
    }

    @Test(priority = 8)
    public void VeryMe() {
        VeryMe.VeryMe(baseURL, subscription);
    }

    @Test(priority = 9)
    public void Discover() {
        Discover.Discover(baseURL, subscription);
    }

    @Test(priority = 10)
    public void ProductsAndServices() {
        ProductsAndServices.ProductsAndServices(baseURL, subscription);
    }

    @Test(priority = 11)
    public void BillHistory() {
        BillHistory.BillHistory(baseURL, subscription);
    }

    @Test(priority = 12)
    public void Plan() {
        Plan.Plan(baseURL, subscription);
    }

    @Test(priority = 13)
    public void Usages() {
        Usages.Usages(baseURL, subscription);
    }

    @Test(priority = 14)
    public void Extras() {
        Extras.Extras(baseURL, subscription);
    }

    @Test(priority = 15)
    public void CurrentCharges() {
        CurrentCharges.CurrentCharges(baseURL, subscription);
    }

    @Test(priority = 16)
    public void AdditionalCharges() {
        AdditionalCharges.AdditionalCharges(baseURL, subscription);
    }

    @Test(priority = 17)
    public void Upgrades() {
        Upgrades.Upgrades(baseURL, subscription);
    }

    @Test(priority = 18)
    public void VOV() {
        VOV.VOV(baseURL, subscription);
    }

    @Test(priority = 19)
    public void PasswordLogin() {
        PasswordLogin.PasswordLogin(baseURL, subscription, userName);
    }

    @Test(priority = 20)
    public void GetAccounts() {
        GetAccounts.GetAccounts(baseURL, subscription);
    }

    @Test(priority = 21)
    public void GetSubscriptionsList()
            throws MalformedURLException {
        SubscriptionsList.GetSubscriptionsList(baseURL, subscription);
    }

    @Test(priority = 22)
    public void SubscriptionSwitch() {
        SubscriptionSwitch.SubscriptionSwitch(baseURL, subscription);
    }

    //uploading the files will be done here
    @AfterClass
    protected void uploadTheFiles(ITestContext result) {
        try {
            VSTSFileUploader.pushToRemote("MVA10APIs");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

	/*@Test(priority = 23)
	public void ChangePin() { ChangePin.ChangePin(baseURL,subscription,userName); }

	@Test(priority = 24)
	public void RestPassword() { ResetPassword.RestPassword(baseURL,subscription,userName); }*/


}