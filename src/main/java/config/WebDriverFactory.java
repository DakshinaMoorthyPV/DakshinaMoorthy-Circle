package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class WebDriverFactory {

	private static WebDriver driver;

	public static WebDriver initializeDriver() {
		if (driver != null) {
			return driver;
		}

		String browserName = ConfigReader.getNestedProperty("browser", "name").toLowerCase();
		boolean isHeadless = Boolean.parseBoolean(ConfigReader.getNestedProperty("browser", "headless"));

		switch (browserName) {
		case "chrome":
			
			  WebDriverManager.chromedriver().setup(); ChromeOptions chromeOptions = new
			  ChromeOptions(); if (isHeadless) {
			  chromeOptions.addArguments("--headless=new"); }
			  chromeOptions.addArguments("--window-size=1920,1080"); driver = new
			  ChromeDriver(chromeOptions);
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			if (isHeadless) {
				edgeOptions.addArguments("--headless=new");
			}
			edgeOptions.addArguments("--window-size=1920,1080");
			driver = new EdgeDriver(edgeOptions);
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (isHeadless) {
				firefoxOptions.addArguments("--headless");
			}
			firefoxOptions.addArguments("--window-size=1920x1080");
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "safari":
			if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
				throw new RuntimeException("❌ Safari is only supported on macOS.");
			}
			driver = new SafariDriver();
			break;

		default:
			throw new RuntimeException("❌ Unsupported browser: " + browserName);
		}

		return driver;
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			driver = initializeDriver();
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
