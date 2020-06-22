package RestAssuredDemo.RestAssuredDemo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

public class CofigFileReader {
	public static Properties properties;
	private final static String propertyFilePath = "configs//Configuration.properties";

	public static void main(String[] args) throws InterruptedException {

		readProperityFile();
		getDeployemntEnv();
	}

	public static void readProperityFile() {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}

	}

	public static int getNoOfDeploymentEnv() {
		int lenght;
		readProperityFile();
		String[] deploymentEnv = properties.getProperty("deploymentEnv").split(",");
		return deploymentEnv.length;

	}

	public static void getDeployemntEnv() throws InterruptedException {
		readProperityFile();
		String[] deploymentEnv = properties.getProperty("deploymentEnv").split(",");
		setDeploymentEnv(deploymentEnv);

	}

	public static void setDeploymentEnv(String[] deploymentEnv) throws InterruptedException {
		try (OutputStream outputStream = new FileOutputStream(propertyFilePath)) {
			for (int i = 0; i < deploymentEnv.length; i++) {

				if (deploymentEnv[i].equals("INT1")) {
					properties.setProperty("subscription", "447386011953");
					properties.setProperty("baseUrl", "https://mvax.dx-int1-common.digital.vodafoneaws.co.uk");
					properties.setProperty("userName", "DNU_S2_7000330528");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
					TestListenerAdapter tla = new TestListenerAdapter();
					TestNG testng = new TestNG();
					List<String> suites = Lists.newArrayList();
					suites.add("C:\\eclipse_workspace\\RestAssuredPipeline\\testng.xml");// path to xml..
					testng.setTestSuites(suites);
					testng.run();

				}
				if (deploymentEnv[i].equals("QC1"))

				properties.setProperty("subscription", "447386011953");
				properties.setProperty("baseUrl", "https://mvax.dx-qc1-common.digital.vodafoneaws.co.uk");
				properties.setProperty("userName", "DNU_S2_7000330528");
				// store the values
				properties.store(outputStream, null);
				properties.load(new FileInputStream(propertyFilePath));
				TestListenerAdapter tla1 = new TestListenerAdapter();
				TestNG testng1 = new TestNG();
				List<String> suites1 = Lists.newArrayList();
				suites1.add("C:\\eclipse_workspace\\RestAssuredPipeline\\testng.xml");// path to xml..
				testng1.setTestSuites(suites1);
				testng1.run();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getSubscriprionSUP02() {
		readProperityFile();
		return properties.getProperty("subscription.SUP02");

	}
	public static String getSubscriprionE7() {
		readProperityFile();
		return properties.getProperty("subscription.E7");

	}

	public static String getBaseUrlINT1SUP02() {
		readProperityFile();
		return properties.getProperty("baseUrl.INT1");

	}
	public static String getBaseUrlQC1SUP02() {
		readProperityFile();
		return properties.getProperty("baseUrl.QC1");

	}

	public static String getBaseUrlSIT1SUP02() {
		readProperityFile();
		return properties.getProperty("baseUrl.SIT1");

	}
	public static String getBaseUrlSIT2SUP02() {
		readProperityFile();
		return properties.getProperty("baseUrl.SIT2");

	}
	public static String getBaseUrlDEVSUP02() {
		readProperityFile();
		return properties.getProperty("baseUrl.SIT2");

	}
	

}
