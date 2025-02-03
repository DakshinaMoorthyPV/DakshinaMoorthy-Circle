package pages.web;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;

public class AmazonHomePage {
	private WebDriver driver;
	private By searchBar = By.xpath("//*[@id='twotabsearchtextbox']");
	private By searchButton = By.id("nav-search-submit-button");

	public AmazonHomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void openAmazon() {
		String amazonUrl = ConfigReader.getAmazonUrl(); // ✅ Fetch Amazon URL dynamically
		driver.get(amazonUrl);
		waitForPageToLoad(60);

		int attempts = 0;
		while (driver.findElements(By.cssSelector("[id='nav-logo-sprites']")).size() != 1 && attempts < 150) {
			driver.navigate().refresh();
			waitForPageToLoad(30);
			attempts++;
		}
	}

	public void enterSearchText(String product) {
		driver.findElement(searchBar).sendKeys(product);
	}

	public void clickSearchButton() {
		driver.findElement(searchButton).click();
	}

	public void waitForPageToLoad(long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

		// Wait until the page's readyState is 'complete'
		wait.until(
				driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
	}
}
