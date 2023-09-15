package Cucumber.Configuration;


import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {


    public static Properties getProperties() {
        Properties properties = new Properties();
        String propertiesFilePath = getPropertiesUrl();

        try {
            InputStream propertiesFile = new FileInputStream(propertiesFilePath);
            properties.load(propertiesFile);
            Reporter.log("Properties File Has Been Loaded Successfully", true);
        } catch (Exception e) {
            Reporter.log("Error While Loading Properties File!!!", true);
        }
        return properties;
    }

    private static String getPropertiesUrl() {
        return System.getProperty("user.dir") + "\\src\\test\\resources\\application.properties";
    }
}
