package com.swagLabs.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YourCartPage extends BasePage {

	public YourCartPage(WebDriver driver) {
		super(driver);
	}

	// Locator for the checkout button
	@FindBy(xpath = "//button[@id='checkout']")
	WebElement checkoutButton;

	// Locator for all cart products
	@FindBy(xpath = "//div[@class='cart_item_label']")
	List<WebElement> allCartProducts;

	// Locator for individual cart item names
	By cartProductList = By.xpath("//div[@class='cart_item_label']");

	// Get all products listed in the cart
	public List<WebElement> getCartProductList() {
		toAppear(cartProductList);
		return allCartProducts;
	}

	// Get the products by name in the cart
	public WebElement getCartProductName(String productName) {
	    // Iterate over the list of cart products using a for loop
	    for (WebElement cartProduct : getCartProductList()) {
	        // Get the product name from the cart item
	        String cartProductName = cartProduct.findElement(By.className("inventory_item_name")).getText();
	        
	        // If the product name matches the search term, return the WebElement
	        if (cartProductName.equals(productName)) {
	            return cartProduct;
	        }
	    }
	    // If no match is found, return null
	    return null;
	}
//	public WebElement getCartProductName(String productName) {
//		return getCartProductList().stream().filter(cartProduct -> cartProduct
//				.findElement(By.className("inventory_item_name")).getText().equals(productName)).findFirst()
//				.orElse(null);
//	}

	// Click on the checkout button
	 public void clickCheckout() {
	        try {
	            // Using JavaScriptExecutor to click the button
	            JavascriptExecutor executor = (JavascriptExecutor) driver;
	            executor.executeScript("arguments[0].click();", checkoutButton);
	        } catch (Exception e) {
	            System.out.println("Error while clicking the Checkout button: " + e.getMessage());
	        }
	 }
	// Remove a product from the cart (assuming there's a remove button for each
	// item)
	public void removeProductFromCart(String productName) {
		WebElement product = getCartProductName(productName);
		if (product != null) {
			WebElement removeButton = product.findElement(By.xpath(".//button[contains(@class, 'cart_button')]"));
			removeButton.click();
		}
	}
}
