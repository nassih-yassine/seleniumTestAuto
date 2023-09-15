package Cucumber.Steps;

import Cucumber.Configuration.DriverSetUp;
import Cucumber.Pages.WeatherShopperHomePage;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;


public class WeatherShopperHomeSteps {
    WebDriver driver = DriverSetUp.getDriver();
    WeatherShopperHomePage weatherShopperHomePage = new WeatherShopperHomePage(driver);

    @And("Based on hint text navigate to moisturizers or sunscreens shopping page")
    public void navigateToPageBasedOnTemperature() {
        weatherShopperHomePage.checkCurrentTemperaturePageTitle();
        int temperature = weatherShopperHomePage.getTemperature();
        if (temperature > 19) weatherShopperHomePage.clickSunscreensButton();
        else weatherShopperHomePage.clickMoisturizersButton();
    }
}
