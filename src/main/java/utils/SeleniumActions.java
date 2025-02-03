package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SeleniumActions {
    WebDriver driver;

    public SeleniumActions(WebDriver driver) {
        this.driver = driver;
    }

    // Click action
    public void click(By elementLocator) {
        WebElement element = driver.findElement(elementLocator);
        element.click();
    }

    // Send keys action
    public void sendKeys(By elementLocator, String value) {
        WebElement element = driver.findElement(elementLocator);
        element.sendKeys(value);
    }

    // Get text action
    public String getText(By elementLocator) {
        WebElement element = driver.findElement(elementLocator);
        return element.getText();
    }

    // Clear text action
    public void clear(By elementLocator) {
        WebElement element = driver.findElement(elementLocator);
        element.clear();
    }

    // Dropdown select by visible text
    public void selectDropdownByVisibleText(By dropdownLocator, String visibleText) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    // Dropdown select by value
    public void selectDropdownByValue(By dropdownLocator, String value) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    // Select checkbox
    public void selectCheckbox(By checkboxLocator) {
        WebElement checkbox = driver.findElement(checkboxLocator);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    // Deselect checkbox
    public void deselectCheckbox(By checkboxLocator) {
        WebElement checkbox = driver.findElement(checkboxLocator);
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    // Radio button select
    public void selectRadioButton(By radioButtonLocator) {
        WebElement radioButton = driver.findElement(radioButtonLocator);
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }

    // Set date for date picker (assuming it's a simple text field with a date format)
    public void setDate(By dateFieldLocator, String date) {
        WebElement dateField = driver.findElement(dateFieldLocator);
        dateField.clear();
        dateField.sendKeys(date);  // Example: "2023-10-05"
    }

    // Select date from calendar popup (if applicable)
    public void selectDateFromCalendar(By dateFieldLocator, String date) {
        WebElement dateField = driver.findElement(dateFieldLocator);
        dateField.click();  // Open calendar popup

        // Logic to select date
        WebElement calendarDay = driver.findElement(By.xpath("//td[text()='" + date + "']"));
        calendarDay.click();
    }

    // Switch to iframe by index
    public void switchToIframe(int index) {
        driver.switchTo().frame(index);
    }

    // Switch to iframe by name or ID
    public void switchToIframe(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    // Switch to iframe by WebElement
    public void switchToIframe(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }

    // Switch back to the main (default) content
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // Handle Alert - Accept (click OK)
    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    // Handle Alert - Dismiss (click Cancel)
    public void dismissAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    // Handle Alert - Get Text
    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    // Handle Alert - Send Keys to Prompt
    public void sendKeysToAlert(String keys) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(keys);
    }
}
