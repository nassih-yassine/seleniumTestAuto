package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CheckoutPage {
    WebDriver driver;
    @FindBy(xpath = "//h2")
    WebElement pageTitle;
    @FindBy(xpath = "//tbody/tr")
    List<WebElement> productsInCard;
    @FindBy(id = "total")
    WebElement total;

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkCurrentCheckoutPageTitle() {
        Assert.assertEquals(pageTitle.getText(), "Checkout");
    }

    public void checkIfProductIsAddedToCard(String[] productNameAndPrice){
        AtomicInteger numberOfFoundedItems = new AtomicInteger();
        productsInCard.forEach(element -> {
            String title = element.findElements(By.tagName("td")).get(0).getText();
            String price = element.findElements(By.tagName("td")).get(1).getText();

            if (title.equalsIgnoreCase(productNameAndPrice[0]) && price.equalsIgnoreCase(productNameAndPrice[1])){
                numberOfFoundedItems.getAndIncrement();
            }
        });


    }

}
