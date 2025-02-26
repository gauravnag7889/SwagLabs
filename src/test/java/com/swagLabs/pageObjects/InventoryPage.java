package com.swagLabs.pageObjects;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    // Locator elements for inventory page
    @FindBy(xpath = "//div[@class='inventory_item']")
    List<WebElement> products; // Locator for all products on page
    
 // Locator elements for cartIcon
    @FindBy(className = "shopping_cart_link")
    WebElement cart_icon; // Locator for all products on page
    
    @FindBy(css = ".product_sort_container")
    WebElement sort_link; // Locator for sort/filter link

    @FindBy(className = "title")
    WebElement page_title; // Locator for page title

    // Actions for inventory page
    public boolean pageTitleIsDisplayed() {
        return page_title.isDisplayed();
    }

    public void sortProducts(String sortOption) {
        sort_link.click();
        // Further actions for sorting logic if required.
    }

    // Method to get the list of products
    public List<WebElement> getProductList() {
        toAppear(By.xpath("//div[@class='inventory_item']"));
        return products;
    }

    // Method to get a product by its name using Java Streams
    public WebElement getProductName(String productName) {
        Optional<WebElement> product = getProductList().stream()
            .filter(prod -> prod.findElement(By.className("inventory_item_name")).getText().equalsIgnoreCase(productName))
            .findFirst();  // Stream will return the first matching product
        
        return product.orElse(null);  // Return the product if found, or null if not found
    }

    // Method to add product to cart
    public void addProductToCart(String prodName) {
        WebElement product = getProductName(prodName);
        if (product != null) {
            product.findElement(By.xpath(".//button[contains(text(),'Add to cart')]")).click();
        }
    }

	public void clickCartIcon() {
		cart_icon.click();
		
	}
}
