package com.apitesting.listners;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author nagtirum
 *
 */
public abstract class BaseClass {
    public static ExtentReports extent =new ExtentReports();//initiating here is very important

    public static ExtentHtmlReporter htmlReporter;


	@BeforeSuite
    public void beforeSuiteSetup() {
        String filepath = System.getProperty("user.dir");
        htmlReporter = new ExtentHtmlReporter(filepath+"/Report.html");     
        extent.attachReporter(htmlReporter);
    }
	@AfterSuite(alwaysRun = true)
    public void afterSuite() {
        extent.flush();
    }
	/**
	 * 
	 * @param result
	 */
    @AfterMethod
    protected void afterMethod(ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
        } else {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
        }
        
        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
        ExtentManager.getReporter().flush();
    }
    
    /**
     * 
     * @param t
     * @return
     */
    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }
}