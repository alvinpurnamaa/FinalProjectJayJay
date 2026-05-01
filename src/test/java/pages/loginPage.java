//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//public class loginPage {
//
//    WebDriver driver;
//
//    By username = By.id("user-name");
//    By password = By.id("password");
//    By loginBtn = By.id("login-button");
//
//    public loginPage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    public void login(String user, String pass) {
//        driver.findElement(username).sendKeys(user);
//        driver.findElement(password).sendKeys(pass);
//        driver.findElement(loginBtn).click();
//    }
//
//    public boolean isHomeDisplayed() {
//        return driver.getCurrentUrl().contains("inventory");
//    }
//}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginPage {

    WebDriver driver;
    WebDriverWait wait;

    By username = By.id("user-name");
    By password = By.id("password");
    By loginBtn = By.id("login-button");
    By errorContainer = By.cssSelector(".error-message-container.error");

    public loginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void openPage() {
        driver.get("https://www.saucedemo.com/");
    }

    public void inputUsername(String user) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).clear();
        driver.findElement(username).sendKeys(user);
    }

    public void inputPassword(String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(password)).clear();
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }


    public boolean isErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorContainer)).isDisplayed();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorContainer)).getText();
    }
}