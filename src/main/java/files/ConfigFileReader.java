package files;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigFileReader {
	public static String appEnv;
	public static String tilEnv;

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
		deploymentEnv= properties.getProperty("deploymentEnv");
		tilEnv = properties.getProperty("tilEnv");
		try (OutputStream outputStream = new FileOutputStream(propertyFilePath)) {

			switch (deploymentEnv) {
			case "int1":
				if (tilEnv.equalsIgnoreCase("SUP02")) {
					properties.setProperty("subscription", "447386011953");
					properties.setProperty("baseUrl", "https://mvax.dx-int1-common.digital.vodafoneaws.co.uk");
					properties.setProperty("userName", "DNU_S2_7000330528");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				} else if (tilEnv.equalsIgnoreCase("E7")) {
					properties.setProperty("subscription", "447389768879");
					properties.setProperty("baseUrl", "https://mvax.dx-int1-common.digital.vodafoneaws.co.uk");
					properties.setProperty("userName", "DNU7000386844");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				} else if (tilEnv.equalsIgnoreCase("E4")) {
					properties.setProperty("subscription", "447387926001");
					properties.setProperty("baseUrl", "https://mvax.dx-int1-common.digital.vodafoneaws.co.uk");
					properties.setProperty("userName", "MYVAR65_7000260567");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				}
				break;
			case "qc1":
				if (tilEnv.equalsIgnoreCase("SUP02")) {
					properties.setProperty("subscription", "447386011953");
					properties.setProperty("baseUrl", "https://mvax.dx-qc1-common.digital.vodafoneaws.co.uk");
					properties.setProperty("userName", "DNU_S2_7000330528");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				} else if (tilEnv.equalsIgnoreCase("E7")) {
					properties.setProperty("subscription", "447389768879");
					properties.setProperty("baseUrl", "https://mvax.dx-qc1-common.digital.vodafoneaws.co.uk");
					properties.setProperty("userName", "DNU7000386844");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				} else if (tilEnv.equalsIgnoreCase("E4")) {
					properties.setProperty("subscription", "447387926001");
					properties.setProperty("baseUrl", "https://mvax.dx-qc1-common.digital.vodafoneaws.co.uk");
					properties.setProperty("userName", "MYVAR65_7000260567");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				}

				break;
			case "dev1":
				if (tilEnv.equalsIgnoreCase("SUP02")) {
					properties.setProperty("subscription", "447386011953");
					properties.setProperty("baseUrl", "https://mvax.dx-dev1-common.digital.vodafoneaws.co.uk");
					properties.setProperty("userName", "DNU_S2_7000330528");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				} else if (tilEnv.equalsIgnoreCase("E7")) {
					properties.setProperty("subscription", "447389768879");
					properties.setProperty("baseUrl", "https://mvax.dx-dev1-common.digital.vodafoneaws.co.uk");
					properties.setProperty("userName", "DNU7000386844");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				} else if (tilEnv.equalsIgnoreCase("E4")) {
					properties.setProperty("subscription", "447387926001");
					properties.setProperty("baseUrl", "https://mvax.dx-dev1-common.digital.vodafoneaws.co.uk");
					properties.setProperty("userName", "MYVAR65_7000260567");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				}

				break;
			case "sit1":
				if (tilEnv.equalsIgnoreCase("SUP02")) {
					properties.setProperty("subscription", "447386011953");
					properties.setProperty("baseUrl", "https://mvax-sit1.newvoe.eu");
					properties.setProperty("userName", "DNU_S2_7000330528");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				} else if (tilEnv.equalsIgnoreCase("E7")) {
					properties.setProperty("subscription", "447389768879");
					properties.setProperty("baseUrl", "https://mvax-sit1.newvoe.eu");
					properties.setProperty("userName", "DNU7000386844");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				} else if (tilEnv.equalsIgnoreCase("E4")) {
					properties.setProperty("subscription", "447387926001");
					properties.setProperty("baseUrl", "https://mvax-sit1.newvoe.eu");
					properties.setProperty("userName", "MYVAR65_7000260567");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				}

				break;
			case "sit2":
				if (tilEnv.equalsIgnoreCase("SUP02")) {
					properties.setProperty("subscription", "447386011953");
					properties.setProperty("baseUrl", "https://mvax-sit2.newvoe.eu");
					properties.setProperty("userName", "DNU_S2_7000330528");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				} else if (tilEnv.equalsIgnoreCase("E7")) {
					properties.setProperty("subscription", "447389768879");
					properties.setProperty("baseUrl", "https://mvax-sit2.newvoe.eu");
					properties.setProperty("userName", "DNU7000386844");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				} else if (tilEnv.equalsIgnoreCase("E4")) {
					properties.setProperty("subscription", "447387926001");
					properties.setProperty("baseUrl", "https://mvax-sit2.newvoe.eu");
					properties.setProperty("userName", "MYVAR65_7000260567");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				}

				break;
			case "prodsup":
				if (tilEnv.equalsIgnoreCase("SUP02")) {
					properties.setProperty("subscription", "447386011953");
					properties.setProperty("baseUrl", "https://mvax.dx-prodsup-common.digital.vodafoneaws.co.uk");
					properties.setProperty("userName", "DNU_S2_7000330528");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				} else if (tilEnv.equalsIgnoreCase("E7")) {
					properties.setProperty("subscription", "447389768879");
					properties.setProperty("baseUrl", "https://mvax.dx-prodsup-common.digital.vodafoneaws.co.uk");
					properties.setProperty("userName", "DNU7000386844");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				} else if (tilEnv.equalsIgnoreCase("E4")) {
					properties.setProperty("subscription", "447387926001");
					properties.setProperty("baseUrl", "https://mvax.dx-prodsup-common.digital.vodafoneaws.co.uk");
					properties.setProperty("userName", "MYVAR65_7000260567");
					// store the values
					properties.store(outputStream, null);
					properties.load(new FileInputStream(propertyFilePath));
				}

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

	}

	public static String getUserName() {
		readProperityFile();
		return properties.getProperty("userName");

	}
	public static String getTilEnv() {
		readProperityFile();
		return properties.getProperty("tilEnv");

	}
}
