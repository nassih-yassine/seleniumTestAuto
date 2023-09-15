package Pages;

import Constants.KeyWords;
import Utils.BaseTools;
import org.checkerframework.checker.units.qual.K;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CheckoutPage extends BaseTools {
    WebDriver driver;
    @FindBy(xpath = "//h2")
    WebElement pageTitle;
    @FindBy(xpath = "//tbody/tr")
    List<WebElement> productsInCard;
    @FindBy(id = "total")
    WebElement total;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement payWithCardButton;
    @FindBy(xpath = "//iframe[@name='stripe_checkout_app']")
    WebElement stripePopUpIFrame;
    @FindBy(xpath = "//input[@id='email']")
    WebElement emailInput;
    @FindBy(xpath = "//input[@id='card_number']")
    WebElement cardNumberInput;
    @FindBy(xpath = "//input[@id='cc-exp']")
    WebElement expDateInput;
    @FindBy(xpath = "//input[@id='cc-csc']")
    WebElement cvcInput;
    @FindBy(xpath = "//input[@name='zip']")
    WebElement postalCodeInput;
    @FindBy(xpath = "//button[@id='submitButton']")
    WebElement payButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkCurrentCheckoutPageTitle() {
        Assert.assertEquals(pageTitle.getText(), "Checkout");
    }

    public void checkIfProductIsAddedToCard(String[] productNameAndPrice) {
        AtomicInteger numberOfFoundedItems = new AtomicInteger();
        productsInCard.forEach(element -> {
            String title = element.findElements(By.tagName("td")).get(0).getText();
            String price = element.findElements(By.tagName("td")).get(1).getText();

            if (title.equalsIgnoreCase(productNameAndPrice[0]) && price.equalsIgnoreCase(productNameAndPrice[1])) {
                numberOfFoundedItems.getAndIncrement();
            }
        });
    }

    public void clickPayWithCardButton() {
        waitForElementThenClick(driver, payWithCardButton);
    }

    public void checkIfStripePopUpExist() {
        String stripePopUpXPath = "//form[@class='checkoutView']";
        boolean isPopUpDisplayed = driver.switchTo().frame(stripePopUpIFrame).findElements(By.xpath(stripePopUpXPath)).size() > 0;
        Assert.assertTrue(isPopUpDisplayed);
    }

    public int numberOfProductsInCard() {
        return productsInCard.size();
    }

    public void fillStripeForm() {
        emailInput.sendKeys(KeyWords.email);
        //TODO: Need to be Modified
        cardNumberInput.sendKeys(KeyWords.cardNumber.trim());
        cardNumberInput.sendKeys(KeyWords.cardNumber.trim());
        cardNumberInput.sendKeys(KeyWords.cardNumber.trim());
        cardNumberInput.sendKeys(KeyWords.cardNumber.trim());
        //
        expDateInput.sendKeys(KeyWords.expDateMonth);
        expDateInput.sendKeys(KeyWords.expDateYear);
        cvcInput.sendKeys(KeyWords.cvc);
        waitForElementToBeVisible(driver, postalCodeInput);
        postalCodeInput.sendKeys(KeyWords.postalCode);
    }

    public void checkTotalPriceInStripePopUp(int price){
        Assert.assertEquals(this.getPriceFromPayButton(), price);
    }

    private int getPriceFromPayButton(){
        String payButtonPrice = payButton.getText()
                .split(" ")[1]
                .split(",")[0];
        return Integer.parseInt(payButtonPrice);
    }
}
