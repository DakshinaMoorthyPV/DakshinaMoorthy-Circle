package stepdefinitions.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;

import config.WebDriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.web.AmazonHomePage;
import pages.web.AmazonSearchResultsPage;

public class AmazonSearchSteps {
	WebDriver driver = WebDriverFactory.getDriver();
	AmazonHomePage homePage = new AmazonHomePage(driver);
	AmazonSearchResultsPage resultsPage = new AmazonSearchResultsPage(driver);

	@Given("I am on the Amazon homepage")
	public void iAmOnTheAmazonHomepage() {
		homePage.openAmazon();
	}

	@When("I enter {string} into the search bar")
	public void iEnterIntoTheSearchBar(String product) {
		homePage.enterSearchText(product);
	}

	@And("I click on the search button")
	public void iClickOnTheSearchButton() {
		homePage.clickSearchButton();
	}

	@Then("I should see a list of products related to {string}")
	public void iShouldSeeAListOfProductsRelatedTo(String product) {
		StringBuffer unrelatedProductRows = resultsPage.isProductListDisplayed(product);

		assertTrue(unrelatedProductRows == null || unrelatedProductRows.length() == 0,
				"Product list contains unrelated products: "
						+ (unrelatedProductRows != null ? unrelatedProductRows.toString() : "None"));
	}

	@And("each product listed should have a name and price displayed.")
	public void eachProductListedShouldHaveANameAndPriceDisplayed() {
		StringBuffer missingDetailsProductRows = resultsPage.areProductNamesAndPricesDisplayed();

		assertTrue(missingDetailsProductRows == null || missingDetailsProductRows.length() == 0,
				"The following product rows are missing details: "
						+ (missingDetailsProductRows != null ? missingDetailsProductRows.toString()
								: "No missing details"));
	}

}
