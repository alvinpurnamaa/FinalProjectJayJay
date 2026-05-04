//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.time.Duration;
//
//public class homePage {
//
//    WebDriver driver;
//    WebDriverWait wait;
//
//    By inventoryContainer = By.id("inventory_container");
//    By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");
//    By cartIcon = By.className("shopping_cart_link");
//
//    By cartTitle = By.className("title");
//
//    public homePage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    public boolean isOnInventoryPage() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(inventoryContainer)
//        ).isDisplayed();
//    }
//
//    public boolean isOnCartPage() {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle))
//                .getText().equals("Your Cart");
//    }
//
//
//    public void addProductToCart() {
//        wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpack)).click();
//    }
//
//    public void clickCart() {
//        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
//    }
//}



package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class homePage {

    WebDriver driver;
    WebDriverWait wait;

    By inventoryContainer = By.id("inventory_container");
    By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");

    By cartTitle = By.className("title");
    By cartIcon = By.className("shopping_cart_link");
    By cartBadge = By.className("shopping_cart_badge");

    public homePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isOnInventoryPage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(inventoryContainer)
        ).isDisplayed();
    }

        public boolean isOnCartPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle))
                .getText().equals("Your Cart");
    }

    public void addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpack)).click();

        // 🔥 VALIDASI item masuk cart
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
    }

    public void clickCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();

        // 🔥 pastikan benar-benar ke cart page
        wait.until(driver -> driver.getCurrentUrl().contains("cart.html"));
    }
}

