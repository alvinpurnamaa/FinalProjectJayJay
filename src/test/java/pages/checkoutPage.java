package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class checkoutPage {

    WebDriver driver;

    // locator
    By checkoutButton = By.id("checkout");
    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");
    By continueButton = By.id("continue");
    By finishButton = By.id("finish");
    By successMessage = By.className("complete-header");

    public checkoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // actions
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public void inputFirstName(String fname) {
        driver.findElement(firstName).sendKeys(fname);
    }

    public void inputLastName(String lname) {
        driver.findElement(lastName).sendKeys(lname);
    }

    public void inputPostalCode(String code) {
        driver.findElement(postalCode).sendKeys(code);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}