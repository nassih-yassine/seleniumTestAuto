package Pages;

import Utils.BaseTools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class WeatherShopperHomePage extends BaseTools {
    WebDriver driver;
    @FindBy(id = "temperature")
    WebElement temperature;
    @FindBy(xpath = "//button[text()='Buy moisturizers']")
    WebElement moisturizersButton;
    @FindBy(xpath = "//button[text()='Buy sunscreens']")
    WebElement sunscreensButton;
    @FindBy(xpath = "//h2")
    WebElement pageTitle;

    public WeatherShopperHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getTemperature(){
        return Integer.getInteger(
                temperature.getText().substring(0, temperature.getText().length()-2).trim()
        );
    }

    public void clickMoisturizersButton(){
        waitForElementThenClick(driver, moisturizersButton);
    }

    public void clickSunscreensButton(){
        waitForElementThenClick(driver, moisturizersButton);
    }

    public void checkCurrentTemperaturePageTitle(){
        Assert.assertEquals(pageTitle.getText(), "Current temperature");
    }
}
