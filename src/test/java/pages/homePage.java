package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class homePage {

    WebDriver driver;
    WebDriverWait wait;

    // locator
    By inventoryContainer = By.id("inventory_container");
    By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");
    By cartIcon = By.className("shopping_cart_link");
    By menuButton = By.id("react-burger-menu-btn");
    By logoutButton = By.id("logout_sidebar_link");

    public homePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===============================
    // VALIDATION
    // ===============================
    public boolean isOnInventoryPage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(inventoryContainer)
        ).isDisplayed();
    }

    // ===============================
    // ACTIONS
    // ===============================

    public void addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpack)).click();
    }

    public void clickCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public void openMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}