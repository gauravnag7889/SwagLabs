package com.swagLabs.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swagLabs.baseClass.BaseClassTest;
import com.swagLabs.pageObjects.BasePage;
import com.swagLabs.pageObjects.InventoryPage;
import com.swagLabs.pageObjects.LoginPage;

public class TC_001LoginPageTest extends BaseClassTest {
	public LoginPage loginpage;
	public InventoryPage inventorypage;
	public BasePage basepage;

	@Test(dataProvider = "LoginTestData", dataProviderClass = com.swagLabs.utilities.DataProviders.class)
	public void verify_loginDDT(String uName, String password, String result) {
		logger.info("**  Starting TC_001Login  **");

		try {

			// LoginPage
			loginpage = new LoginPage(driver);
			Assert.assertEquals(true, loginpage.isLogoDisplayed(), "Swag labs logo missing");
			loginpage.login(uName, password);
			// inventory page
			inventorypage = new InventoryPage(driver);
			Boolean inventorypage_status = inventorypage.pageTitleIsDisplayed();
			// BasePage 
			basepage = new BasePage(driver);
			if (result.equalsIgnoreCase("Valid")) {
				if (inventorypage_status == true) {
					basepage.getLogout();
					Assert.assertTrue(true);
				} else {
					loginpage.closeErrorMessage();
					Assert.assertTrue(false);
				}
			}

			if (result.equalsIgnoreCase("Invalid")) {
				if (inventorypage_status == true) {
					basepage.getLogout();
					Assert.assertTrue(false);
				} else {
					loginpage.closeErrorMessage();
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e.getMessage());
		}

		logger.info("**  Finished TC_001Login  **");
	}
}
