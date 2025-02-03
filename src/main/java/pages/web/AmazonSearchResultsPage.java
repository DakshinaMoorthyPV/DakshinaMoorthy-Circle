package pages.web;

import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonSearchResultsPage {
	private WebDriver driver;

	private static final By PRODUCT_LIST_LOCATOR = By.cssSelector("[cel_widget_id*='SEARCH_RESULTS']");
	private static final By PRODUCT_NAME_LOCATOR = By.cssSelector(" h2>span");
	private static final By PRODUCT_PRICE_LOCATOR = By
			.xpath(" //*[@data-cy='price-recipe']/following-sibling::div//span[@class='a-color-base']");

	public AmazonSearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}


	public StringBuffer isProductListDisplayed(String product) {
		List<WebElement> productList = driver.findElements(PRODUCT_LIST_LOCATOR);
		StringBuffer unrelatedProductRows = new StringBuffer();

		IntStream.range(0, productList.size()).mapToObj(index -> getProductInfo(productList.get(index), index))
				.forEach(productInfo -> {
					// Scroll to the product first
					scrollToProduct(productList.get(productInfo.index));

					// Then filter and append if the product is unrelated or the name is missing
					if (productInfo.productName.isEmpty()
							|| !productInfo.productName.toLowerCase().contains(product.toLowerCase().trim())) {
						appendUnrelatedProduct(unrelatedProductRows, productInfo, product);
					}
				});

		return unrelatedProductRows.length() > 0 ? unrelatedProductRows : null;
	}

	public StringBuffer areProductNamesAndPricesDisplayed() {
		StringBuffer missingDetailsProductRows = new StringBuffer();
		List<WebElement> productList = driver.findElements(PRODUCT_LIST_LOCATOR);
		System.out.println(productList.size());
		System.out.println(productList.get(1)
				.findElement(
						By.xpath(" //*[@data-cy='price-recipe']/following-sibling::div//span[@class='a-color-base']"))
				.getText());

		IntStream.range(0, productList.size()).mapToObj(index -> getProductInfo(productList.get(index), index))
				.forEach(productInfo -> {
					scrollToProduct(productList.get(productInfo.index));

					if (productInfo.productName.isEmpty() || productInfo.productPrice.isEmpty()) {
						appendMissingDetails(missingDetailsProductRows, productInfo);
					}
				});

		return missingDetailsProductRows.length() > 0 ? missingDetailsProductRows : null;
	}

	private void scrollToProduct(WebElement productElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});",
				productElement);
	}

	private ProductInfo getProductInfo(WebElement productElement, int index) {
		String productName = productElement.findElement(PRODUCT_NAME_LOCATOR).getText().trim();
		String productPrice = productElement.findElement(PRODUCT_PRICE_LOCATOR).getText().trim();
		return new ProductInfo(index, productName, productPrice);
	}

	private void appendUnrelatedProduct(StringBuffer result, ProductInfo productInfo, String product) {
		result.append("Product row " + (productInfo.index + 1) + " not related to " + product);
		if (productInfo.index < driver.findElements(PRODUCT_LIST_LOCATOR).size() - 1) {
			result.append(", ");
		}
	}

	private void appendMissingDetails(StringBuffer result, ProductInfo productInfo) {
		if (productInfo.productName.isEmpty()) {
			result.append("Product row " + (productInfo.index + 1) + " name is empty");
		}
		if (productInfo.productPrice.isEmpty()) {
			if (result.length() > 0) {
				result.append(", ");
			}
			result.append("Product row " + (productInfo.index + 1) + " price is empty");
		}
		if (productInfo.index < driver.findElements(PRODUCT_LIST_LOCATOR).size() - 1) {
			result.append(", ");
		}
	}

	private static class ProductInfo {
		int index;
		String productName;
		String productPrice;

		ProductInfo(int index, String productName, String productPrice) {
			this.index = index;
			this.productName = productName;
			this.productPrice = productPrice;
		}
	}
}
