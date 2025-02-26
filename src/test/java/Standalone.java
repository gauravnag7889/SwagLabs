import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalone {
	public static void main(String args[]) {
		String productName = "Sauce Labs Bolt T-Shirt";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		List<WebElement> pro1ducts = driver.findElements(By.xpath("//div[@class='inventory_item']"));
		WebElement prod = products.stream().filter(
				product -> product.findElement(By.className("inventory_item_name")).getText().equals(productName))
				.findFirst().orElse(null);

		// Click "Add to cart" if the product is found
		if (prod != null) {
			prod.findElement(By.xpath(".//button[contains(text(),'Add to cart')]")).click();
			System.out.println("Added to cart: Sauce Labs Bolt T-Shirt");
		} else {
			System.out.println("Product not found!");// Add soft assertaion
		}
		driver.findElement(By.cssSelector(".shopping_cart_link")).click();
		String card_product = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
		if (card_product.equals(productName)) {
			driver.findElement(By.cssSelector("#checkout")).click();
		} else {
			driver.findElement(By.cssSelector("#remove-sauce-labs-bolt-t-shirt")).click();
		}
		driver.findElement(By.cssSelector("#first-name")).sendKeys("Gaurav");
		driver.findElement(By.cssSelector("#last-name")).sendKeys("Nag");
		driver.findElement(By.cssSelector("#postal-code")).sendKeys("227612");
		driver.findElement(By.cssSelector("#continue")).click();
		String Final_pro_check = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
		if (Final_pro_check.equals(productName)) {
			driver.findElement(By.cssSelector("#finish")).click();
		}

	}
}