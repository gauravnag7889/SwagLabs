package com.swagLabs.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage  {
	WebDriver driver;

	// Constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(id = "user-name")
	WebElement txt_username;

	@FindBy(id = "password")
	WebElement txt_password;

	@FindBy(id = "login-button")
	WebElement btn_login;

	@FindBy(css = "h3[data-test='error']")
	WebElement errorMessage;
	@FindBy(css=("button[class='error-button'] svg path"))
	WebElement errorMessageClose_btn;

	@FindBy(className = "login_logo")
	WebElement logo;

	// Methods for interacting with the Login Page

	// Method to check if the logo is displayed
	public boolean isLogoDisplayed() {
		return logo.isDisplayed();
	}

	// Method to login with provided username and password
	public void login(String username, String password) {
		txt_username.clear();
		txt_username.sendKeys(username);
		txt_password.clear();
		txt_password.sendKeys(password);
		btn_login.click();
	}
	public void closeErrorMessage() {
		errorMessageClose_btn.click();
		
	}

	// Method to get the error message displayed (for invalid login)
	public String getErrorMessage() {
		return errorMessage.getText();
	}
}
