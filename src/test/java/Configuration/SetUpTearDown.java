package Configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class SetUpTearDown {
    public WebDriver driver;
    int IMPLICIT_WAIT = 10;
    Properties properties = new Properties();

    @BeforeClass
    public void openBrowser(){
        browserConfig();
        String url = properties.getProperty("url");
        this.openUrl(url);
    }

    @AfterClass
    public void closeBrowser(){
        driver.close();
    }

    private void browserConfig() {
        String propertiesFilePath = this.getPropertiesUrl();

        try {
            InputStream propertiesFile = new FileInputStream(propertiesFilePath);
            properties.load(propertiesFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }

        String browser = properties.getProperty("browser");

        if (browser.equalsIgnoreCase("edge"))
            driver = new EdgeDriver();

        if (browser.equalsIgnoreCase("chrome"))
            driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
    }

    private String getPropertiesUrl() {
        return System.getProperty("user.dir") + "\\src\\test\\resources\\application.properties";
    }

    private void openUrl(String url){
        driver.get(url);
    }
}
