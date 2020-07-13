package RestAssuredDemo.RestAssuredDemo;

import com.apitesting.listners.BaseClass;
import com.apitesting.listners.ExtentTestManager;
import org.openqa.selenium.MutableCapabilities;
import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static RestAssuredDemo.RestAssuredDemo.ConfigFileReader.getTilEnv;

public class RunnableClass extends BaseClass implements ITest {
    private ThreadLocal<String> testName = new ThreadLocal<>();
    private MutableCapabilities dc;

    @DataProvider(name = "appEnv")
    public static Object[][] appEnv() {
        return new Object[][]{{"INT1 on ".concat(getTilEnv("int1")), "int1"}, {"QC1 on ".concat(getTilEnv("qc1")), "qc1"}, {"SIT1 on ".concat(getTilEnv("sit1")), "sit1"}, {"SIT2 on ".concat(getTilEnv("sit2")), "sit2"}, {"DEV1 on ".concat(getTilEnv("dev1")), "dev1"}, {"ProdSup on ".concat(getTilEnv("prodsup")), "prodsup"}};
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

