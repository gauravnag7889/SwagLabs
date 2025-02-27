package com.swagLabs.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends BasePage {
	public ConfirmationPage(WebDriver driver) {
		super(driver);
	}

	// locators of final page
	@FindBy(xpath = "//img[@alt='Pony Express']")
	WebElement ponyExpress_logo;
	@FindBy(xpath = "//h2[normalize-space()='Thank you for your order!']")
	WebElement confirmationMessage;
	@FindBy(xpath = "//button[@id='back-to-products']")
	WebElement btn_backHome;

	// Actions for above Methods
	public boolean isLogoPreasent() { // is logo present
		return ponyExpress_logo.isDisplayed();
	}

	public String isConfirmationTextPresent() { // is confirmation present
		return confirmationMessage.getText();
	}

	public void clickHome() { // clicking on Home button
		btn_backHome.click();
	}

}
