//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.time.Duration;
//
//public class checkoutPage {
//
//    WebDriver driver;
//    WebDriverWait wait;
//
//    By checkoutButton = By.id("checkout");
//
//    By firstName = By.id("first-name");
//    By lastName = By.id("last-name");
//    By postalCode = By.id("postal-code");
//
//    By continueButton = By.id("continue");
//    By finishButton = By.id("finish");
//
//    By successMessage = By.className("complete-header");
//    By errorMessage = By.cssSelector("[data-test='error']"); // 🔥 untuk validasi gagal
//
//    public checkoutPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//
//    public void clickCheckout() {
//        WebElement element = wait.until(
//                ExpectedConditions.presenceOfElementLocated(checkoutButton)
//        );
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollIntoView(true);", element
//        );
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();", element
//        );
//
//        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
//    }
//
//    public void inputFirstName(String fname) {
//        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
//        el.clear();
//        el.sendKeys(fname);
//
//        System.out.println("First Name value: " + el.getAttribute("value"));
//    }
//
//    public void inputLastName(String lname) {
//        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
//        el.clear();
//        el.sendKeys(lname);
//
//        System.out.println("Last Name value: " + el.getAttribute("value"));
//    }
//
//    public void inputPostalCode(String code) {
//        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode));
//        el.clear();
//        el.sendKeys(code);
//
//        System.out.println("Postal Code value: " + el.getAttribute("value"));
//    }
//
//    public void clickContinue() {
//        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
//
//        if (driver.getCurrentUrl().contains("checkout-step-one")) {
//            System.out.println("❌ Continue gagal, masih di step-one");
//
//            try {
//                String error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
//                System.out.println("Error message: " + error);
//            } catch (Exception e) {
//                System.out.println("Tidak ada error message muncul");
//            }
//        }
//
//        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
//    }
//
//    public void clickFinish() {
//        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
//        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
//    }
//
//    public String getSuccessMessage() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(successMessage)
//        ).getText();
//    }
//}
//





//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.time.Duration;
//
//public class checkoutPage {
//
//    WebDriver driver;
//    WebDriverWait wait;
//
//    // ===============================
//    // LOCATOR
//    // ===============================
//    By checkoutButton = By.id("checkout");
//
//    By firstName = By.id("first-name");
//    By lastName = By.id("last-name");
//    By postalCode = By.id("postal-code");
//
//    By continueButton = By.id("continue");
//    By finishButton = By.id("finish");
//
//    By successMessage = By.className("complete-header");
//    By errorMessage = By.cssSelector("[data-test='error']");
//
//    // ===============================
//    // CONSTRUCTOR
//    // ===============================
//    public checkoutPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    // ===============================
//    // ACTIONS
//    // ===============================
//
//    // 🔥 Cart → Checkout Step One
//    public void clickCheckout() {
//        WebElement element = wait.until(
//                ExpectedConditions.presenceOfElementLocated(checkoutButton)
//        );
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollIntoView(true);", element
//        );
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();", element
//        );
//
//        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
//
//        // pastikan field siap
//        wait.until(ExpectedConditions.elementToBeClickable(firstName));
//    }
//
//    // 🔥 SUPER STABLE INPUT (FINAL)
//    private void fillField(By locator, String value) {
//        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollIntoView(true);", el
//        );
//
//        // coba normal dulu
//        try {
//            el.clear();
//            el.sendKeys(value);
//
//            String current = el.getAttribute("value");
//            System.out.println("Normal input → " + current);
//
//            if (current != null && !current.isEmpty()) return;
//
//        } catch (Exception e) {
//            System.out.println("Normal input gagal, pakai JS");
//        }
//
//        // 🔥 fallback + trigger event (INI KUNCI)
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].value = arguments[1];" +
//                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
//                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
//                el, value
//        );
//
//        String jsValue = el.getAttribute("value");
//        System.out.println("JS input → " + jsValue);
//
//        if (jsValue == null || jsValue.isEmpty()) {
//            throw new RuntimeException("❌ Gagal isi field: " + locator);
//        }
//    }
//
//    // 🔥 INPUT FIELD
//    public void inputFirstName(String fname) {
//        fillField(firstName, fname);
//    }
//
//    public void inputLastName(String lname) {
//        fillField(lastName, lname);
//    }
//
//    public void inputPostalCode(String code) {
//        fillField(postalCode, code);
//    }
//
//    // 🔥 STEP ONE → STEP TWO (FINAL FIX)
//    public void clickContinue() {
//        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollIntoView(true);", btn
//        );
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();", btn
//        );
//
//        // 🔥 smart wait (tidak kaku)
//        wait.until(driver -> {
//            String url = driver.getCurrentUrl();
//
//            // sukses
//            if (url.contains("checkout-step-two")) return true;
//
//            // kalau masih di step-one → cek error
//            if (url.contains("checkout-step-one")) {
//                try {
//                    WebElement err = driver.findElement(errorMessage);
//                    if (err.isDisplayed()) {
//                        throw new RuntimeException("❌ Continue gagal: " + err.getText());
//                    }
//                } catch (Exception ignored) {}
//            }
//
//            return false;
//        });
//    }
//
//    // 🔥 FINAL SUBMIT
//    public void clickFinish() {
//        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
//
//        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();", btn
//        );
//    }
//
//    // 🔥 SUCCESS MESSAGE
//    public String getSuccessMessage() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(successMessage)
//        ).getText();
//    }
//}


package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class checkoutPage {

    WebDriver driver;
    WebDriverWait wait;

    // ===============================
    // LOCATOR
    // ===============================
    By checkoutButton = By.id("checkout");

    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");

    By continueButton = By.id("continue");
    By finishButton = By.id("finish");

    By successMessage = By.className("complete-header");

    // ===============================
    // CONSTRUCTOR
    // ===============================
    public checkoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===============================
    // STEP: CART → CHECKOUT STEP ONE
    // ===============================
    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();

        // pastikan sudah masuk step-one
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
    }

    // ===============================
    // INPUT FIELD (VALIDATED)
    // ===============================
    private void fillField(By locator, String value) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));

        el.clear();
        el.sendKeys(value);

        // 🔥 pastikan value benar-benar masuk
        wait.until(driver -> el.getAttribute("value").equals(value));
    }

    public void inputFirstName(String fname) {
        fillField(firstName, fname);
    }

    public void inputLastName(String lname) {
        fillField(lastName, lname);
    }

    public void inputPostalCode(String code) {
        fillField(postalCode, code);
    }

    // ===============================
    // STEP ONE → STEP TWO
    // ===============================
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();

        // 🔥 tunggu sampai finish muncul (indikasi step-two)
        wait.until(driver ->
                driver.findElements(finishButton).size() > 0
        );
    }

    // ===============================
    // STEP TWO → COMPLETE
    // ===============================
    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    // ===============================
    // VALIDATION
    // ===============================
    public String getSuccessMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(successMessage)
        ).getText();
    }
}



