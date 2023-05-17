package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import extentlisteners.ExtentListeners;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;

public class BaseTest {
	/*
	 * Webdriver testng screenshots Reporting log4j properties database JavaMail
	 * ExcelReading keywords
	 */

	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\TestBook.xlsx");
	public static Logger log = Logger.getLogger(BaseTest.class);
	public static WebDriverWait wait;

	public Object takeText(String key) {

		if (key.endsWith("_XPATH")) {
			WebElement ele = driver.findElement(By.xpath(OR.getProperty(key)));
			String a = ele.getText();
			// driver.findElement(By.id(OR.getProperty(a))).getText();
			log.info(a);

		} else if (key.endsWith("_ID")) {
			System.out.println("not found anything");
		}
		ExtentListeners.test.info("Clicking on an Element: " + key);
		return key;
	}

	public boolean isElementPresent(String key) {
		try {
			if (key.endsWith("_XPATH")) {

				driver.findElement(By.xpath(OR.getProperty(key)));
			} else if (key.endsWith("_ID")) {

				driver.findElement(By.id(OR.getProperty(key)));
			} else if (key.endsWith("_CSS")) {

				driver.findElement(By.cssSelector(OR.getProperty(key)));
			}

			log.info("Checking the Element Presence for: " + key);
			ExtentListeners.test.info("Clicking on an Element: " + key);
			return true;

		} catch (Throwable t) {

			log.info("Error while finding element: " + key + "Error message is: " + t.getMessage());
			ExtentListeners.test.info("Error while finding element:  " + key + "Error message is: " + t.getMessage());
			return false;
		}
	}

	public void click(String key) {

		if (key.endsWith("_XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(key))).click();
		} else if (key.endsWith("_ID")) {

			driver.findElement(By.id(OR.getProperty(key))).click();
		} else if (key.endsWith("_CSS")) {

			driver.findElement(By.cssSelector(OR.getProperty(key))).click();
		}

		log.info("Clicking on an Element: " + key);
		ExtentListeners.test.info("Clicking on an Element: " + key);

	}

	public void quit(String key, String value) {

		driver.quit();
	}

	public void type(String key, String value) {

		if (key.endsWith("_XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(key))).sendKeys(value);
		} else if (key.endsWith("_ID")) {

			driver.findElement(By.id(OR.getProperty(key))).sendKeys(value);
			;
		} else if (key.endsWith("_CSS")) {

			driver.findElement(By.cssSelector(OR.getProperty(key))).sendKeys(value);
		}

		log.info("Typing in an Element: " + key + " entered the value as : " + value);
		ExtentListeners.test.info("Clicking on an Element: " + key);

	}

	@BeforeSuite // Before execution starts
	public void setUp() {

		if (driver == null) {

			PropertyConfigurator.configure(".\\src\\test\\resources\\properties\\log4j.properties");
			log.info(" Test Execution Started!!! ");

			try {
				fis = new FileInputStream(".\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Config.load(fis);
				log.info(" Config propeties loaded !!! ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			fis = new FileInputStream(".\\src\\test\\resources\\properties\\OR.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			OR.load(fis);
			log.info(" OR propeties loaded !!! ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		log.info("Chrome browser Launched !!!");

		driver.get(Config.getProperty("bankurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);

		wait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));

	}

	@AfterSuite // After execution ends
	public void tearDown() {

	}

}
