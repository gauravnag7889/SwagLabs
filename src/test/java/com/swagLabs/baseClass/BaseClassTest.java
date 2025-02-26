package com.swagLabs.baseClass;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.swagLabs.utilities.ReadConfig;

public class BaseClassTest {
	public static Logger logger;

	public static WebDriver driver;
	public static ReadConfig readconfig = new ReadConfig();
	public static String url = readconfig.getAppURL();
	public static String browser = readconfig.getBrowser();

	// method to generate random name
	public String randomName() {
		String randName = RandomStringUtils.randomAlphabetic(8);
		return randName;
	}

	// method to create random Zipcode
	public String randomZip() {
		String zip = RandomStringUtils.randomNumeric(6);
		return zip;
	}

	@BeforeClass
	public static void setup() {
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		logger = LogManager.getLogger("CaseStudy_2");
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
