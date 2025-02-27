package com.swagLabs.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.swagLabs.baseClass.BaseClassTest;
import com.swagLabs.pageObjects.BasePage;
import com.swagLabs.pageObjects.InventoryPage;
import com.swagLabs.pageObjects.LoginPage;

public class TC_001LoginPageTest extends BaseClassTest {

	private LoginPage loginPage;
	private InventoryPage inventoryPage;
	private BasePage basePage;

	@Test(dataProvider = "LoginTestData", dataProviderClass = com.swagLabs.utilities.DataProviders.class)
	public void verifyLoginWithDDT(String username, String password, String expectedResult) {
		logger.info("** Starting TC_001Login **");

		try {
			// Initialize Page Objects
			loginPage = new LoginPage(driver);
			inventoryPage = new InventoryPage(driver);
			basePage = new BasePage(driver);

			// Validate Swag Labs Logo on Login Page
			Assert.assertTrue(loginPage.isLogoDisplayed(), "Swag Labs logo is missing");

			// Perform login
			loginPage.login(username, password);

			// Verify if Inventory page is displayed
			boolean isInventoryPageDisplayed = inventoryPage.pageTitleIsDisplayed();

			// Validate based on the expected result
			if ("Valid".equalsIgnoreCase(expectedResult)) {
				if (isInventoryPageDisplayed) {
					basePage.getLogout();
					Assert.assertTrue(true, "Login successful and user logged out.");
				} else {
					loginPage.closeErrorMessage();
					Assert.fail("Inventory page not displayed after valid login.");
				}
			} else if ("Invalid".equalsIgnoreCase(expectedResult)) {
				if (isInventoryPageDisplayed) {
					basePage.getLogout();
					Assert.fail("Inventory page displayed despite invalid login.");
				} else {
					loginPage.closeErrorMessage();
					Assert.assertTrue(true, "Error message displayed for invalid login.");
				}
			}
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e.getMessage());
		}

		logger.info("** Finished TC_001Login **");
	}

}
