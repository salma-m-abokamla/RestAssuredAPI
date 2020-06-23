package files;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class CofigFileReader {
	public static String deploymentEnv;
	public static Properties properties;
	private final static String propertyFilePath = "configs//Configuration.properties";

	
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
	public static void setDeploymentEnv() {
		readProperityFile();
		deploymentEnv = properties.getProperty("deploymentEnv");
		try (OutputStream outputStream = new FileOutputStream(propertyFilePath)) {

			switch (deploymentEnv) {
			case "INT1":
				properties.setProperty("subscription", "447389841597");
				properties.setProperty("baseUrl", "https://mvax.dx-int1-common.digital.vodafoneaws.co.uk");
				properties.setProperty("userName", "SANCHIT01");
				// store the values
				properties.store(outputStream, null);
				properties.load(new FileInputStream(propertyFilePath));

				break;
			case "QC1":
				properties.setProperty("subscription", "447386013638");
				properties.setProperty("baseUrl", "https://mvax.dx-qc1-common.digital.vodafoneaws.co.uk");
				properties.setProperty("userName", "AUT7000349298 ");
				// store the values
				properties.store(outputStream, null);
				properties.load(new FileInputStream(propertyFilePath));

				break;
			case "SIT1":
				properties.setProperty("subscription", "447386011953");
				properties.setProperty("baseUrl", "https://mvax-sit1.newvoe.eu");
				properties.setProperty("userName", "DNU_S2_7000330528");
				// store the values
				properties.store(outputStream, null);
				properties.load(new FileInputStream(propertyFilePath));

				break;
		    case "SIT2":
				properties.setProperty("subscription", "447386011953");
				properties.setProperty("baseUrl", "https://mvax-sit2.newvoe.eu");
				properties.setProperty("userName", "DNU_S2_7000330528");
				// store the values
				properties.store(outputStream, null);
				properties.load(new FileInputStream(propertyFilePath));

				break;
				
			case "DEV":
				properties.setProperty("subscription", "447386011953");
				properties.setProperty("baseUrl", "https://mvax.dx-dev.digital.vodafoneaws.co.uk");
				properties.setProperty("userName", "DNU_S2_7000330528");
				// store the values
				properties.store(outputStream, null);
				properties.load(new FileInputStream(propertyFilePath));

				break;
					
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static String getSubscriprion() {
		readProperityFile();
		return properties.getProperty("subscription");

	}
	public static String getBaseUrl() {
		readProperityFile();
		return properties.getProperty("baseUrl");

	}	public static String getUserName() {
		readProperityFile();
		return properties.getProperty("userName");

	}
}
