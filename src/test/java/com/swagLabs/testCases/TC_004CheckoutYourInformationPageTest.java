package com.swagLabs.testCases;

import com.swagLabs.baseClass.BaseClassTest;
import com.swagLabs.pageObjects.CheckoutYourInformationPage;
import com.swagLabs.pageObjects.LoginPage;
import com.swagLabs.pageObjects.YourCartPage;
import com.swagLabs.pageObjects.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_004CheckoutYourInformationPageTest extends BaseClassTest {

    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public YourCartPage cartPage;
    public CheckoutYourInformationPage checkoutYourInformationPage;

    // Setup method to log in and add products to the cart
    @BeforeClass
    public void loginAndAddProductsToCart() {
        logger.info("**  Logging in with valid credentials  **");
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        logger.info("**  Login successful  **");

        // Add products to cart
        inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.addProductToCart("Sauce Labs Bolt T-Shirt");

        // Opening cart page
        inventoryPage.clickCartIcon();
        cartPage = new YourCartPage(driver);
        cartPage.clickCheckout();
    }

    // Test case to verify the checkout page
    @Test(priority = 1)
    public void testCheckOutPageHeading() {
        logger.info("**  Starting test: testCheckOutPageHeading  **");

        // Initialize CheckoutYourInformationPage object
        checkoutYourInformationPage = new CheckoutYourInformationPage(driver);

        // Verify that the page heading is correct
        String pageHeading = checkoutYourInformationPage.pageHeading();
        Assert.assertEquals(pageHeading, "Checkout: Your Information", "Checkout page heading is incorrect.");

        logger.info("** Test Passed: Checkout page heading is correct. **");
    }

    // Test case to verify filling in the checkout form and clicking continue
    @Test(priority = 2)
    public void testFillCheckoutFormAndContinue() throws InterruptedException {
        logger.info("**  Starting test: testFillCheckoutFormAndContinue  **");

        // Initialize CheckoutYourInformationPage object
        checkoutYourInformationPage = new CheckoutYourInformationPage(driver);

        // Fill in checkout form with valid details
        checkoutYourInformationPage.testFillCheckoutFormAndContinue(randomName(), randomName(), randomZip());
        // Verify that the user is directed to the next page (step two of checkout)
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two"), "Failed to navigate to the next step of checkout.");

        logger.info("** Test Passed: Successfully navigated to the next step of checkout. **");
    }
}
