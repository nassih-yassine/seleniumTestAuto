package Cucumber.Steps;

import Cucumber.Configuration.DriverSetUp;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.testng.Reporter;

public class CommonSteps {

    @Before
    public void setupBrowser() {
        DriverSetUp.loadProperties();
        DriverSetUp.setUpBrowser();
    }

    @Given("Go To Home Page")
    public void goToHomePage() {
        DriverSetUp.openUrl();
    }

    @After
    public void closeBrowser() {
        Reporter.log("Closing The Driver", true);
        DriverSetUp.getDriver().close();
        DriverSetUp.getDriver().quit();
    }
}
