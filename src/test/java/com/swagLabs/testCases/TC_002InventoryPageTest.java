package com.swagLabs.testCases;

import com.swagLabs.BaseTest.BaseClassTest;
import com.swagLabs.pageObjects.InventoryPage;
import com.swagLabs.pageObjects.LoginPage;
import com.swagLabs.utilities.ReadConfig;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_002InventoryPageTest extends BaseClassTest {

	public InventoryPage inventoryPage;
	public LoginPage loginpage;
	public static ReadConfig readconfig = new ReadConfig();
	public static String item1 = readconfig.getCartProduct1();
	public static String item2 = readconfig.getCartProduct2();
	public static String item3 = readconfig.getCartProduct3();

	@BeforeClass
	public void login() {
		logger.info("**  Logging in with valid credentials  **");
		loginpage = new LoginPage(driver);
		loginpage.login("standard_user", "secret_sauce");
		logger.info("**  Login successful  **");
		logger.info("** Opening InventoryPage  **");
		inventoryPage = new InventoryPage(driver);
	}

	// Test case to verify that the inventory page title is displayed
	@Test(priority = 1)
	public void testPageTitleIsDisplayed() {
		logger.info("**  Starting test: testPageTitleIsDisplayed  **");

		boolean titleDisplayed = inventoryPage.pageTitleIsDisplayed();
		Assert.assertTrue(titleDisplayed, "Inventory page title is not displayed.");

		logger.info("** Test Passed: Inventory page title is displayed.  **");
	}

	// Test case to verify that the product can be added to the cart
	@Test(priority = 2)
	public void addProductsToCart() {
	    String[] productNames = {item1, item2, item3};

	    logger.info("**  Starting test: testAddMultipleProductsToCart **");

	    for (String productName : productNames) {
	        logger.info("**  Adding product: " + productName + " to cart **");
	        inventoryPage.addProductToCart(productName);

	        boolean isProductAdded = inventoryPage.getProductName(productName) != null;
	        Assert.assertTrue(isProductAdded, "Product " + productName + " was not added to the cart.");
	    }

	    logger.info("**  Test Passed: Multiple products were successfully added to the cart. **");
	}
	
	// Test case to verify sorting functionality with DataProvider
//	@Test
	public void testSortProducts(String sortOption) {
		logger.info("**  Starting test: testSortProducts with sort option: " + sortOption + "  **");

		// Perform sorting action on the page
		inventoryPage.sortProducts(sortOption);

		// After sorting, verify that the products were sorted correctly.
		String firstProductNameAfterSort = inventoryPage.getProductList().get(0)
				.findElement(By.className("inventory_item_name")).getText();

		// Verify that sorting happens (for example, by verifying that the first product
		// name is as expected)
		Assert.assertNotNull(firstProductNameAfterSort, "Sorting did not update the product list.");

		logger.info("**  Test Passed: Sorting functionality worked for " + sortOption + ".  **");
	}

}
