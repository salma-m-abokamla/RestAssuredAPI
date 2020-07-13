package RestAssuredDemo.RestAssuredDemo;

import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentTestManager;
import org.openqa.selenium.MutableCapabilities;
import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class RunnableClass extends BaseClass implements ITest {
    private ThreadLocal<String> testName = new ThreadLocal<>();
    private MutableCapabilities dc;

    @DataProvider(name = "appEnv")
    public static Object[][] appEnv() {
        return new Object[][]{{"INT1 On E4", "int1"}, {"QC1 On E7", "qc1"}, {"SIT1 On SUP02", "sit1"}, {"SIT2 On SUP02", "sit2"}, {"DEV1 On E4", "dev1"}, {"ProdSup on E4", "prodsup"}};
    }

    @Test(dataProvider = "appEnv")
    public void Environment(String description, String appEnv) {
        // some testing stuff
        ConfigFileReader.setDeploymentEnv(appEnv);
    }
    @BeforeMethod
    public void BeforeMethod(Method method, Object[] testData) {
        testName.set(method.getName() + " " + testData[0]);
        ExtentTestManager.startTest(method.getName() + " " + testData[0]);

    }

    @Override
    public String getTestName() {
        return testName.get();
    }
}

