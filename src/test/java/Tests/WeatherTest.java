package Tests;

import Pages.CheckoutPage;
import Pages.MoisturizersPage;
import Pages.WeatherShopperHomePage;
import configuration.SetUpTearDown;
import org.testng.annotations.Test;

public class WeatherTest extends SetUpTearDown {
    @Test
    public void test1() throws InterruptedException {
        WeatherShopperHomePage weatherShopperHomePage = new WeatherShopperHomePage(driver);
        MoisturizersPage moisturizersPage = new MoisturizersPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // Je check si je me suis bien redirigé vers la page Current temperature
        weatherShopperHomePage.checkCurrentTemperaturePageTitle();
        // Je clique sur le bouton Buy moisturizers
        weatherShopperHomePage.clickMoisturizersButton();
        // Je check si je me suis redirigé vers la page Moisturizers
        moisturizersPage.checkCurrentMoisturizersPageTitle();
        // Je check que chaque produit est affiché avec son titre et son prix
        moisturizersPage.checkThatProductsGotTitleAndPrice();
        // J’ajoute le 1er produit à mon panier
        String[] productAddedToCard = moisturizersPage.addFirstProductToCard();
        // Je check que le panier contient un item
        moisturizersPage.checkElementIfAddedToCard();
        // Je clique sur carte
        moisturizersPage.openCard();
        // Je check que je me suis redirigé vers la page checkout
        checkoutPage.checkCurrentCheckoutPageTitle();
        // Je check si le produit ajouté correspond bien à mon produit (titre , prix , total)
        checkoutPage.checkIfProductIsAddedToCard(productAddedToCard);
        // Je clique sur le bouton Pay with card


        Thread.sleep(2000);
    }

}
