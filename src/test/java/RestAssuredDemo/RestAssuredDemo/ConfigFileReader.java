package RestAssuredDemo.RestAssuredDemo;
import com.apitesting.listners.BaseClass;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;

public class ConfigFileReader extends BaseClass {
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
    @Test
    public static void setDeploymentEnv(String appEnv) {
        readProperityFile();
        switch (appEnv) {
            case "int1":
                tilEnv = properties1.getProperty("int1");
                System.out.println("tilEnv:" + tilEnv);
                if (tilEnv.contains("SUP02")) {
                    INT1_Environment_SUP02.INT1_Environment_SUP02();
                } else if (tilEnv.contains("E7")) {
                    INT1_Environment_E7.INT1_Environment_E7();
                } else if (tilEnv.contains("E4")) {
                    INT1_Environment_E4.INT1_Environment_E4();

                }
                break;
            case "qc1":
                tilEnv = properties1.getProperty("qc1");
                System.out.println("tilEnv:" + tilEnv);
                if (tilEnv.contains("SUP02")) {
                    QC1_Environment_SUP02.QC1_Environment_SUP02();
                } else if (tilEnv.contains("E7")) {
                    QC1_Environment_E7.QC1_Environment_E7();
                } else if (tilEnv.contains("E4")) {
                    QC1_Environment_E4.QC1_Environment_E4();
                }
                break;
            case "dev1":
                tilEnv = properties1.getProperty("dev1");
                System.out.println("tilEnv:" + tilEnv);
                if (tilEnv.contains("SUP02")) {
                    DEV1_Environment_SUP02.DEV1_Environment_SUP02();
                } else if (tilEnv.contains("E7")) {
                    DEV1_Environment_E7.INT1_Environment_E7();
                } else if (tilEnv.contains("E4")) {
                    DEV1_Environment_E4.DEV1_Environment_E4();
                }
                break;
            case "sit1":
                tilEnv = properties1.getProperty("sit1");
                System.out.println("tilEnv:" + tilEnv);
                if (tilEnv.contains("SUP02")) {
                    SIT1_Environment_SUP02.SIT1_Environment_SUP02();
                } else if (tilEnv.contains("E7")) {
                    SIT1_Environment_E7.SIT1_Environment_E7();
                } else if (tilEnv.contains("E4")) {
                    SIT1_Environment_E4.SIT1_Environment_E4();
                }
                break;
            case "sit2":
                tilEnv = properties1.getProperty("sit2");
                System.out.println("tilEnv:" + tilEnv);
                if (tilEnv.contains("SUP02")) {
                    SIT2_Environment_SUP02.SIT2_Environment_SUP02();
                } else if (tilEnv.contains("E7")) {
                    SIT2_Environment_E7.SIT2_Environment_E7();
                } else if (tilEnv.contains("E4")) {
                    SIT2_Environment_E4.SIT2_Environment_E4();
                }
                break;
            case "prodsup":
                tilEnv = properties1.getProperty("prodsup");
                System.out.println("tilEnv:" + tilEnv);
                if (tilEnv.contains("SUP02")) {
                    ProdSup_Environment_SUP02.ProdSup_Environment_SUP02();
                } else if (tilEnv.contains("E7")) {
                    ProdSup_Environment_E7.ProdSup_Environment_E7();
                } else if (tilEnv.contains("E4")) {
                    ProdSup_Environment_E4.ProdSup_Environment_E4();
                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + appEnv);
        }

    }



    public static String getINT1BaseUrl() {
        readProperityFile();
        String baseUrlINT1=properties1.getProperty("baseURL.int1");
        System.out.println("BaseUrl_INT1:"+baseUrlINT1);
        return baseUrlINT1;

    }

    public static String getSIT1BaseUrl() {
        readProperityFile();
        return properties1.getProperty("baseURL.sit1");

    }

    public static String getSIT2BaseUrl() {
        readProperityFile();
        return properties1.getProperty("baseURL.sit2");

    }

    public static String getProdSubBaseUrl() {
        readProperityFile();
        return properties1.getProperty("baseURL.prodsup");

    }

    public static String getDEV1BaseUrl() {
        readProperityFile();
        return properties1.getProperty("baseURL.dev1");

    }
    public static String getTilEnv(String envName) {
        readProperityFile();
        return properties1.getProperty(envName);

    }

    public static String getQC1BaseUrl() {
        readProperityFile();
        return properties1.getProperty("baseURL.qc1");

    }

    public static String readE7TestData() {
        readProperityFile();
        return properties2.getProperty("subscription");

    }

    public static String readE4TestData() {
        readProperityFile();
        return properties3.getProperty("subscription");

    }

    public static String readSUP02TestData() {
        readProperityFile();
        return properties4.getProperty("subscription");

    }

}
