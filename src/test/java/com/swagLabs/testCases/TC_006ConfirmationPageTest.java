package com.swagLabs.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.swagLabs.BaseTest.BaseClassTest;
import com.swagLabs.pageObjects.BasePage;
import com.swagLabs.pageObjects.CheckoutOverviewPage;
import com.swagLabs.pageObjects.CheckoutYourInformationPage;
import com.swagLabs.pageObjects.ConfirmationPage;
import com.swagLabs.pageObjects.InventoryPage;
import com.swagLabs.pageObjects.LoginPage;
import com.swagLabs.pageObjects.YourCartPage;

public class TC_006ConfirmationPageTest extends BaseClassTest {
	public LoginPage loginPage;
	public InventoryPage inventoryPage;
	public YourCartPage cartPage;
	public CheckoutYourInformationPage checkoutYourInformationPage;
	public CheckoutOverviewPage checkoutOverviewPage;
	public ConfirmationPage confirmationPage;
	public BasePage basePage;

	@BeforeClass
	public void setUp() {
		// Log in to the application
		logger.info("** Starting TC_006Confirmation Test  **");
		loginPage = new LoginPage(driver);
		loginPage.login("standard_user", "secret_sauce");
		logger.info("** Login successful **");

		// Add products to the cart
		inventoryPage = new InventoryPage(driver);
		inventoryPage.addProductToCart("Sauce Labs Backpack");
		inventoryPage.addProductToCart("Sauce Labs Bolt T-Shirt");
		logger.info("** product successfully added **");

		// Go to cart and proceed to checkout
		inventoryPage.clickCartIcon();
		cartPage = new YourCartPage(driver);
		cartPage.clickCheckout();
		logger.info("** items verified in cart  **");

		// Fill checkout information and continue
		checkoutYourInformationPage = new CheckoutYourInformationPage(driver);
		checkoutYourInformationPage.testFillCheckoutFormAndContinue(randomName(), randomName(), randomZip());
		logger.info("** Checkout information filled  **");

		// Initialize CheckoutOverviewPage after proceeding to checkout overview page
		checkoutOverviewPage = new CheckoutOverviewPage(driver); // Initialize the page object
		checkoutOverviewPage.clickFinish();
		logger.info("** Price, Tax and totl Value varified  **");
		confirmationPage = new ConfirmationPage(driver);
	}

	@Test(priority = 1)
	public void logoVerification() {

		Assert.assertEquals(confirmationPage.isLogoPreasent(), true);
		logger.info("** pony logo present  **");
	}

	@Test(priority = 2)
	public void confirmationMessage() {

		String confirmMessage = confirmationPage.isConfirmationTextPresent();
		String messa = "Thank you for your order!";
		Assert.assertEquals(confirmMessage.equalsIgnoreCase(messa), true);
		logger.info("** Confirmation Message present  **");
	}

	@Test(priority = 3)
	public void backHome() {
		confirmationPage.clickHome();
		logger.info("** Moving to inventory page  **");
	}

	@Test(priority = 4)
	public void inventoryPageConfirmation() {
		Boolean page_status = inventoryPage.pageTitleIsDisplayed();
		Assert.assertEquals(true, page_status);
		logger.info("** Inventory page confirmed  **");
	}

	@Test(priority = 5)
	public void logOut() {
		basePage.getLogout();
		logger.info("** Logout  **");
	}
}
