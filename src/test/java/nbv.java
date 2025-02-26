import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class nbv {
@Test
public void login() throws InterruptedException {
	WebDriver driver=new ChromeDriver();
	driver.get("https://www.saucedemo.com/");
	driver.findElement(By.cssSelector("#user-name")).sendKeys("skgja");
	
	driver.findElement(By.cssSelector("#password")).sendKeys("nagsku");
	driver.findElement(By.cssSelector("#login-button")).click();
	Thread.sleep(2000);
	driver.findElement(By.cssSelector("button[class='error-button'] svg path")).click();
	WebElement user=driver.findElement(By.cssSelector("#user-name"));
	user.clear();
	user.sendKeys("skgja");
}
}
