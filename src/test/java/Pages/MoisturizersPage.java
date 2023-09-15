package Pages;

import Utils.BaseTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class MoisturizersPage extends BaseTools {
    WebDriver driver;
    @FindBy(xpath = "//h2")
    WebElement pageTitle;
    @FindBy(xpath = "//div[@class='text-center col-4']")
    List<WebElement> products;
    @FindBy(xpath = "//button[@class='thin-text nav-link']")
    WebElement cardButton;
    @FindBy(id = "cart")
    WebElement numberOfElementInCard;
    public MoisturizersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkCurrentMoisturizersPageTitle() {
        Assert.assertEquals(pageTitle.getText(), "Moisturizers");
    }

    public void checkThatProductsGotTitleAndPrice() {
        boolean allProductsGotTitleAndPrice = true;
        for (WebElement product : products) {
            if (productHasTitle(product) || productHasPrice(product)) {
                System.out.println(product.getText() + "\t Has a Missing Title or Price");
                allProductsGotTitleAndPrice = false;
                break;
            }
        }
        Assert.assertTrue(allProductsGotTitleAndPrice);
    }

    private boolean productHasPrice(WebElement element) {
        String price = this.getProductPrice(element);
        return price.isBlank() || price.isEmpty();
    }

    private String getProductPrice(WebElement element){
        WebElement priceElement = element.findElements(By.tagName("p")).get(1);
        return priceElement.getText().replace("Price:", "").replace("Rs.", "").trim();
    }

    private boolean productHasTitle(WebElement element) {
        String title = this.getProductTitle(element);
        return title.isBlank() || title.isEmpty();
    }

    private String getProductTitle(WebElement element){
        return element.findElements(By.tagName("p")).get(0).getText();
    }

    private String[] addProductToCard(WebElement element) {
        WebElement addToCard = element.findElements(By.tagName("button")).get(0);
        waitForElementThenClick(driver, addToCard);
        return new String[]{
                this.getProductTitle(element),
                this.getProductPrice(element)
        };
    }

    public String[] addFirstProductToCard() {
        WebElement firstProduct = products.get(0);
        return this.addProductToCard(firstProduct);
    }

    public void checkElementIfAddedToCard() {
        String cardText = numberOfElementInCard.getText();
        boolean addedToCard;
        addedToCard = !cardText.equalsIgnoreCase("Empty");
        Assert.assertTrue(addedToCard);
    }

    public void openCard(){
        waitForElementThenClick(driver, cardButton);
    }


}
