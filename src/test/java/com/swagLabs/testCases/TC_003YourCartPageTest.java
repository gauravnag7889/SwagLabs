package com.swagLabs.testCases;


import com.swagLabs.BaseTest.BaseClassTest;
import com.swagLabs.pageObjects.InventoryPage;
import com.swagLabs.pageObjects.LoginPage;
import com.swagLabs.pageObjects.YourCartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_003YourCartPageTest extends BaseClassTest {

    public InventoryPage inventoryPage;
    public LoginPage loginPage;
    public YourCartPage cartPage;

    @BeforeClass
    public void login() {
        logger.info("**  Logging in with valid credentials  **");
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        logger.info("**  Login successful  **");
        logger.info("** Opening Inventory Page and Cart Page **");
        inventoryPage = new InventoryPage(driver);
        cartPage = new YourCartPage(driver);
    }

    // Test case to verify adding multiple products to the cart
    @Test(priority = 1)
    public void testAddMultipleProductsToCart() {
        logger.info("**  Starting test: testAddMultipleProductsToCart **");

        // Add multiple products  -------------------will check data from inventory page using DDT
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.addProductToCart("Sauce Labs Bolt T-Shirt");

        // Navigate to the cart page
        inventoryPage.clickCartIcon();

        // Verify that the products are in the cart
        Assert.assertTrue(cartPage.getCartProductName("Sauce Labs Backpack") != null, "Sauce Labs Backpack not found in cart");
        Assert.assertTrue(cartPage.getCartProductName("Sauce Labs Bolt T-Shirt") != null, "Sauce Labs Bolt T-Shirt not found in cart");

        logger.info("** Test Passed: Multiple products were successfully added to the cart **");
    }

    // Test case to verify checking out from the cart
    @Test(priority = 2)
    public void testCheckout() {
        logger.info("**  Starting test: testCheckout **");

        // Add products to the cart
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.addProductToCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.clickCartIcon();

        // Click checkout
        cartPage = new YourCartPage(driver);
        cartPage.clickCheckout();

        // Verify that we are on the checkout page (this depends on the actual implementation of your app)
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"), "Checkout page did not load.");

        logger.info("** Test Passed: Successfully navigated to checkout page **");
    }
}
