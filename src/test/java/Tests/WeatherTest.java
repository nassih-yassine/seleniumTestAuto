package Tests;

import Pages.*;
import Configuration.SetUpTearDown;
import org.testng.annotations.Test;

public class WeatherTest extends SetUpTearDown {
    @Test(priority = 0)
    public void test1() {
        WeatherShopperHomePage weatherShopperHomePage = new WeatherShopperHomePage(driver);
        MoisturizersPage moisturizersPage = new MoisturizersPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentSuccessPage paymentSuccessPage = new PaymentSuccessPage(driver);

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
        checkoutPage.clickPayWithCardButton();
        // Je check si la popup Stripe.com s’affiche
        checkoutPage.checkIfStripePopUpExist();
        // Je saisis les informations nécessaires pour compléter l’achat (email, card number , date , cvc, zip code)
        checkoutPage.fillStripeForm();
        // Je check le total affiché sur le pop-up
        checkoutPage.checkTotalPriceInStripePopUp(Integer.parseInt(productAddedToCard[1]));
        // Je clique sur le bouton pay iner
        checkoutPage.clickPayWithCardButton();
        // Je check si je me suis bien redirigé vers la page confirmation
        paymentSuccessPage.checkCurrentPaymentSuccessPageTitle();
        // Je check si le message Your payment was successful. You should receive a follow-up call from our sales team est bien affiché
        paymentSuccessPage.checkPageMessage();
    }

    @Test(priority = 1, enabled = false)
    public void test2() {
        WeatherShopperHomePage weatherShopperHomePage = new WeatherShopperHomePage(driver);
        SunscreensPage sunscreensPage = new SunscreensPage(driver);

        // Je check si je me suis bien redirigé vers la page Current temperature
        weatherShopperHomePage.checkCurrentTemperaturePageTitle();
        // Je clique sur le bouton Buy sunscreens
        weatherShopperHomePage.clickSunscreensButton();
        //Je check si je suis redirigé vers la page Sunscreens
        sunscreensPage.checkCurrentSunscreensPageTitle();
        // Je check que chaque produit est affiché avec son titre et son prix
    }
}
