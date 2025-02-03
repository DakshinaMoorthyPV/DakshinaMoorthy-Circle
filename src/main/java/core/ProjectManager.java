package core;

import org.openqa.selenium.WebDriver;

import config.WebDriverFactory;

public class ProjectManager {

	private static WebDriver driver;

	public static void initializeFramework() {
		System.out.println("Initializing Framework...");
		driver = WebDriverFactory.getDriver();
	}

	public static void shutdownFramework() {
		System.out.println("Closing WebDriver...");
		WebDriverFactory.quitDriver();
	}

	public static WebDriver getDriver() {
		return driver;
	}
}
