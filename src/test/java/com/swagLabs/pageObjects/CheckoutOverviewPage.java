package com.swagLabs.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends BasePage {
	public CheckoutOverviewPage(WebDriver driver) {
		super(driver);
	}

	// locators of the page
	@FindBy(css = ".title")
	WebElement pageTitle; // locator for page title

	@FindBy(xpath = "//div[@class='cart_item']")
	List<WebElement> cartItems; //locators for items in cart

	@FindBy(xpath = "//div[@class='summary_subtotal_label']")
	WebElement itemTotal; // Item total value

	@FindBy(css = ".summary_tax_label")
	WebElement tax; // Tax value

	@FindBy(css = ".summary_total_label")
	WebElement totalAmount; // Total value

	@FindBy(xpath = "//button[@id='finish']")
	WebElement finishButton; // locator of finish button

	@FindBy(id = "cancel")
	WebElement cancelButton; // locator of cancel button

	// Method to get the page title
	public String getPageTitle() {
		return pageTitle.getText();
	}
//	public double getExactPrice(WebElement element) {
//		String val= element.getText();
//		String arr[]=val.split(" ");
//		arr[2].replace("$", "");
//		double value = Double.parseDouble(arr[2]);
//		return value;
//	}

	// Method to get the item total price
	// Method to extract amount (removes '$' and parses as double)

    // Method to get the item total value
    public double getItemTotal() {
        return getExactPrice(itemTotal);
    }

    // Method to get the tax value
    public double getTax() {
        return getExactPrice(tax);
    }

    // Method to get the total value
    public double getTotalAmount() {
        return getExactPrice(totalAmount);
    }

	// Method to click on the Finish button
	public void clickFinish() {
		finishButton.click();
	}

	// Method to click on the Cancel button
	public void clickCancel() {
		cancelButton.click();
	}
}
