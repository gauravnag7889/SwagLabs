package com.swagLabs.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutYourInformationPage extends BasePage {

	// Constructor
	public CheckoutYourInformationPage(WebDriver driver) {
		super(driver);
	}

	// Locators of checkout page
	@FindBy(css = ".title")
	WebElement txt_heading; // locator for page heading

	@FindBy(css = "#first-name")
	WebElement txt_firstName; // locator for first name

	@FindBy(css = "#last-name")
	WebElement txt_lastName; // locator for last name

	@FindBy(css = "#postal-code")
	WebElement txt_zipcode; // locator for zip code

	@FindBy(css = "#continue")
	WebElement btn_continue; // locator for continue button

	// Actions for locators

	// Get page heading text
	public String pageHeading() {
		return txt_heading.getText();
	}

	public void testFillCheckoutFormAndContinue(String fName, String lName, String zCode) {
		txt_firstName.sendKeys(fName);
		txt_lastName.sendKeys(lName);
		txt_zipcode.sendKeys(zCode);
		btn_continue.click();
	}

	
}
