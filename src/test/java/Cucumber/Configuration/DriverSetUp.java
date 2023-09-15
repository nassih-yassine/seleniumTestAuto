package Cucumber.Configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;

import java.time.Duration;
import java.util.Properties;

public class DriverSetUp {
    public static WebDriver driver;
    public static int IMPLICIT_WAIT = 10;
    public static Properties properties;

    public static void setUpBrowser() {
        String browser = properties.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            Reporter.log("Opening Browser In 'Chrome'", true);
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            Reporter.log("Opening Browser In 'Edge'", true);
            driver = new EdgeDriver();
        } else {
            Reporter.log("Browser Option Not Valid, Opening Browser In 'Chrome'", true);
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
    }

    public static void openUrl() {
        driver.get(properties.getProperty("url"));
    }

    public static void loadProperties() {
        properties = PropertiesFileReader.getProperties();
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
