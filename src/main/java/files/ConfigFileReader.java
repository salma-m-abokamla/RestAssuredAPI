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
    public static String deploymentEnv;
    public static String tilEnv = null;

    public static Properties properties;
    public static Properties properties1;
    public static Properties properties2;
    public static Properties properties3;
    public static Properties properties4;

    private final static String configFilePath = "configs//Configuration.properties";
    private final static String TilEnvRepointingFilePath = "TillConfigs//TilEnvRepointing.properties";
    private final static String TestDataE7propertyFilePath = "TillConfigs//TestData_E7.properties";
    private final static String TestDataE4propertyFilePath = "TillConfigs//TestData_E4.properties";
    private final static String TestDataSUP02propertyFilePath = "TillConfigs//TestData_SUP02.properties";

    public static void readProperityFile() {

        BufferedReader reader;
        BufferedReader reader1;
        BufferedReader reader2;
        BufferedReader reader3;
        BufferedReader reader4;
        try {
            reader = new BufferedReader(new FileReader(configFilePath));
            reader1 = new BufferedReader(new FileReader(TilEnvRepointingFilePath));
            reader2 = new BufferedReader(new FileReader(TestDataE7propertyFilePath));
            reader3 = new BufferedReader(new FileReader(TestDataE4propertyFilePath));
            reader4 = new BufferedReader(new FileReader(TestDataSUP02propertyFilePath));
            properties = new Properties();
            properties1 = new Properties();
            properties2 = new Properties();
            properties3 = new Properties();
            properties4 = new Properties();


            try {
                properties.load(reader);
                reader.close();
                properties1.load(reader1);
                reader1.close();
                properties2.load(reader2);
                reader2.close();
                properties3.load(reader3);
                reader3.close();
                properties4.load(reader4);
                reader4.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + configFilePath);
        }

    }

    public static void setDeploymentEnv() {
        readProperityFile();
        deploymentEnv = properties.getProperty("deploymentEnv");
        System.out.println("deploymentEnv:" + deploymentEnv);

        try (OutputStream outputStream = new FileOutputStream(configFilePath)) {
            switch (deploymentEnv) {
                case "int1":
                    properties.setProperty("baseUrl", properties1.getProperty("baseURL.int1"));
                    tilEnv = properties1.getProperty("int1");
                    properties.setProperty("tilEnv", properties1.getProperty("int1"));
                    System.out.println("tilEnv:" + tilEnv);
                    if (tilEnv.contains("SUP02")) {
                        readSUP02TestData();
                    } else if (tilEnv.contains("E7")) {
                        readE7TestData();
                    } else if (tilEnv.contains("E4")) {
                        readE4TestData();
                    }
                    // store the values
                    properties.store(outputStream, null);
                    properties.load(new FileInputStream(configFilePath));
                    break;
                case "qc1":
                    properties.setProperty("baseUrl", properties1.getProperty("baseURL.qc1"));
                    tilEnv = properties1.getProperty("qc1");
					properties.setProperty("tilEnv", properties1.getProperty("qc1"));
					System.out.println("tilEnv:" + tilEnv);
                    if (tilEnv.contains("SUP02")) {
                        readSUP02TestData();
                    } else if (tilEnv.contains("E7")) {
                        readE7TestData();
                    } else if (tilEnv.contains("E4")) {
                        readE4TestData();
                    }
                    // store the values
                    properties.store(outputStream, null);
                    properties.load(new FileInputStream(configFilePath));
                    break;
                case "dev1":
                    properties.setProperty("baseUrl", properties1.getProperty("baseURL.dev1"));
                    tilEnv = properties1.getProperty("dev1");
					properties.setProperty("tilEnv", properties1.getProperty("dev1"));
					System.out.println("tilEnv:" + tilEnv);
                    if (tilEnv.contains("SUP02")) {
                        readSUP02TestData();
                    } else if (tilEnv.contains("E7")) {
                        readE7TestData();
                    } else if (tilEnv.equalsIgnoreCase("E4")) {
                        readE4TestData();
                    }
                    // store the values
                    properties.store(outputStream, null);
                    properties.load(new FileInputStream(configFilePath));
                    break;
                case "sit1":
                    properties.setProperty("baseUrl", properties1.getProperty("baseURL.sit1"));
                    tilEnv = properties1.getProperty("sit1");
					properties.setProperty("tilEnv", properties1.getProperty("sit1"));
					System.out.println("tilEnv:" + tilEnv);
                    if (tilEnv.contains("SUP02")) {
                        readSUP02TestData();
                    } else if (tilEnv.contains("E7")) {
                        readE7TestData();
                    } else if (tilEnv.contains("E4")) {
                        readE4TestData();
                    }
                    // store the values
                    properties.store(outputStream, null);
                    properties.load(new FileInputStream(configFilePath));
                    break;
                case "sit2":
                    properties.setProperty("baseUrl", properties1.getProperty("baseURL.sit2"));
                    tilEnv = properties1.getProperty("sit2");
					properties.setProperty("tilEnv", properties1.getProperty("sit2"));
					System.out.println("tilEnv:" + tilEnv);
                    if (tilEnv.contains("SUP02")) {
                        readSUP02TestData();
                    } else if (tilEnv.contains("E7")) {
                        readE7TestData();
                    } else if (tilEnv.contains("E4")) {
                        readE4TestData();
                    }
                    // store the values
                    properties.store(outputStream, null);
                    properties.load(new FileInputStream(configFilePath));
                    break;
                case "prodsup":
                    properties.setProperty("baseUrl", properties1.getProperty("baseURL.prodsup"));
                    tilEnv = properties1.getProperty("prodsup");
					properties.setProperty("tilEnv", properties1.getProperty("prodsup"));
					System.out.println("tilEnv:" + tilEnv);
                    if (tilEnv.contains("SUP02")) {
                        readSUP02TestData();
                    } else if (tilEnv.contains("E7")) {
                        readE7TestData();
                    } else if (tilEnv.contains("E4")) {
                        readE4TestData();
                    }
                    // store the values
                    properties.store(outputStream, null);
                    properties.load(new FileInputStream(configFilePath));
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

    public static void readE7TestData() {
        properties.setProperty("subscription", properties2.getProperty("subscription"));
        properties.setProperty("userName", properties2.getProperty("userName"));

    }

    public static void readE4TestData() {
        properties.setProperty("subscription", properties3.getProperty("subscription"));
        properties.setProperty("userName", properties3.getProperty("userName"));

    }

    public static void readSUP02TestData() {
        properties.setProperty("subscription", properties4.getProperty("subscription"));
        properties.setProperty("userName", properties4.getProperty("userName"));

    }
}
