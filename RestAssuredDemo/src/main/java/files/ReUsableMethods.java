package files;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONArray;

public class ReUsableMethods {

	public static JsonPath rawToJson(String response) {

		JsonPath js1 = new JsonPath(response);

		return js1;

	}

	public static Map generalHeaders(String subscription) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("Device-Model", "iOS");
		params.put("Manufacturer", "Apple");
		params.put("Platform", "iOS");
		params.put("App-Version", "10.1");
		params.put("Device-UID", "iuiiuiuiuiu");
		params.put("Subscription", subscription);
		params.put("OS-Version", "12.0");
		params.put("Root-Subscription", "True");
		params.put("authenticationToken", "iTgXNCyUYSdVLOXZiQDSWAj2baDAUC0Hw0ySsp+pKNTKvY0+jOFppAsRr5XKIlPK9N93GL1G6YPlpRxmpA1tjeR7oCP+l2Fkr2IE9reAYKff+0Y1JqleJqFYtuvlus68vnLCndsuUzIcJht/N0R1A1h7GPahNYqQLa1KAdpI5npceSQXTOBSGov9Dsqd/um73aF9yx9V3OT0jjpyCGxC74rNLywp2lVmCeEQRcGkKvYZqwoghgEs7D7JPvKHtcGROjsbVgAUxyk10Su7/GeY1kSIkyPUajqn2qMCsVmU3tCCSzkdFezUIm9BNFx56egl04utKBv8F3Rwn+aCTxIROQ==");
		return params;
	}
	public static Map segmentSubJWT(String backendJwtSoftToken) {

		Map<String, String> params = new HashMap<String, String>();
	
		params.put("Root-Subscription", "True");
		params.put("JWT", backendJwtSoftToken);

		return params;
	}
	
	public static void generateExtentReport() {
		
		ExtentReports extent;
		ExtentTest logger;
		ExtentHtmlReporter htmlReporter;
		String htmlReportPath = "C:\\Users\\ElMetwally2S\\Documents\\ExtentReport"; // Path for the HTML report to be
																					// saved
		htmlReporter = new ExtentHtmlReporter(htmlReportPath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		logger = extent.createTest("Api Test", "Validat AppConfig Api status");

		
		
		
	}
}
