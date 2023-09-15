package Pages;

import Utils.BaseTools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SunscreensPage extends BaseTools {
    WebDriver driver;
    @FindBy(xpath = "//h2")
    WebElement pageTitle;

    public SunscreensPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkCurrentSunscreensPageTitle() {
        Assert.assertEquals(pageTitle.getText(), "Sunscreens");
    }
}
