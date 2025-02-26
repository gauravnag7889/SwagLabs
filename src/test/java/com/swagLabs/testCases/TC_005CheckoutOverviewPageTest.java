package com.swagLabs.testCases;

import com.swagLabs.baseClass.BaseClassTest;
import com.swagLabs.pageObjects.CheckoutOverviewPage;
import com.swagLabs.pageObjects.CheckoutYourInformationPage;
import com.swagLabs.pageObjects.InventoryPage;
import com.swagLabs.pageObjects.LoginPage;
import com.swagLabs.pageObjects.YourCartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_005CheckoutOverviewPageTest extends BaseClassTest {

    public InventoryPage inventoryPage;
    public LoginPage loginPage;
    public YourCartPage cartPage;
    public CheckoutYourInformationPage checkoutYourInformationPage;
    public CheckoutOverviewPage checkoutOverviewPage; // Declare the CheckoutOverviewPage object

    @BeforeClass
    public void setUp() {
        // Log in to the application
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Add products to the cart
        inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.addProductToCart("Sauce Labs Bolt T-Shirt");

        // Go to cart and proceed to checkout
        inventoryPage.clickCartIcon();
        cartPage = new YourCartPage(driver);
        cartPage.clickCheckout();

        // Fill checkout information and continue
        checkoutYourInformationPage = new CheckoutYourInformationPage(driver);
        checkoutYourInformationPage.testFillCheckoutFormAndContinue(randomName(), randomName(), randomZip());

        // Initialize CheckoutOverviewPage after proceeding to checkout overview page
        checkoutOverviewPage = new CheckoutOverviewPage(driver); // Initialize the page object
    }

    @Test
    public void testPriceTotal() {
        logger.info("** Starting test: testPriceTotal **");

        // Get the item total, tax, and total values from the page
        double itemTotalValue = checkoutOverviewPage.getItemTotal();
        System.out.println(itemTotalValue);
        logger.info("Item Total: " + itemTotalValue);

        double taxValue = checkoutOverviewPage.getTax();
        System.out.println(taxValue);
        logger.info("Tax: " + taxValue);

        double totalAmountValue = checkoutOverviewPage.getTotalAmount();
        logger.info("Total Amount: " + totalAmountValue);

        // Calculate the expected total
        double expectedTotal = itemTotalValue + taxValue;

        // Assertion to check if the expected total matches the total value
        Assert.assertEquals(expectedTotal, totalAmountValue, "Price total is incorrect!");

        logger.info("** Test Passed: Price total is correct. Item Total + Tax = Total Amount **");

        // Click the Finish button to complete the checkout
        checkoutOverviewPage.clickFinish();
    }
}
