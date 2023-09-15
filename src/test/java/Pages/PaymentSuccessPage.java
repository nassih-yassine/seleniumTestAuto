package Pages;

import Utils.BaseTools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PaymentSuccessPage extends BaseTools {
    @FindBy(xpath = "//h2")
    WebElement pageTitle;
    @FindBy(xpath = "//p[@class='text-justify']")
    WebElement pageMessage;
    WebDriver driver;

    public PaymentSuccessPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkCurrentPaymentSuccessPageTitle(){
        try{
            Thread.sleep(5000);
        } catch (Exception ignored){
        }
        waitForElementToBeVisible(driver, pageTitle);
        Assert.assertEquals(pageTitle.getText(), "PAYMENT SUCCESS");
    }

    public void checkPageMessage(){
        String expectedMessage = "Your payment was successful. You should receive a follow-up call from our sales team.";
        Assert.assertEquals(pageMessage.getText(), expectedMessage);
    }
}
