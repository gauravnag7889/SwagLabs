package com.swagLabs.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".shopping_cart_link")
	WebElement shoppingcart;
	@FindBy(id = "react-burger-menu-btn")
	WebElement burger_icon;
	@FindBy(xpath = "//a[normalize-space()='Twitter']")
	WebElement twitter_icon;
	@FindBy(xpath = "//a[normalize-space()='Facebook']")
	WebElement facebook_icon;
	@FindBy(xpath = "//a[normalize-space()='LinkedIn']")
	WebElement linkedin_icon;
	@FindBy(xpath="//a[@id='logout_sidebar_link']")
	WebElement btn_logout;

	public void toAppear(By findBy) {
		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		mywait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	// method for moving to cart
	public void goToCart() {
		shoppingcart.click();
	}

	// method for selecting hamburger options
	public void getLogout() {
	    // Click the burger icon to open the menu
		burger_icon.click(); 
	    btn_logout.click();
	}


	// method to click on twitter social media link
	public void goToTwitter() {
		twitter_icon.click();
	}

	// method to click on facebook social media link
	public void goToFacebook() {
		facebook_icon.click();
	}

	// method to click on linkedIn social media link
	public void goToLinkedIn() {
		linkedin_icon.click();
	}
	public double getExactPrice(WebElement element) {
	    String val = element.getText();  // Get the item total as a string, e.g., "Item total: $45.98"
	    String[] arr = val.split(" ");  // Split the string by space into an array
	    String priceStr = arr[arr.length - 1];  // Get the last element which should be "$45.98"
	    priceStr = priceStr.replace("$", "");  // Remove the dollar sign "$"
	    
	    // Parse the price to a double
	    double value = Double.parseDouble(priceStr);  // Convert the string to a double
	    
	    return value;  // Return the value as a double
	}
}