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


//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
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
//    // STEP: CART → CHECKOUT STEP ONE
//    // ===============================
//    public void clickCheckout() {
//        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
//
//        // pastikan sudah masuk step-one
//        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
//    }
//
//    // ===============================
//    // INPUT FIELD (VALIDATED)
//    // ===============================
//    private void fillField(By locator, String value) {
//        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
//
//        el.clear();
//        el.sendKeys(value);
//
//        // 🔥 pastikan value benar-benar masuk
//        wait.until(driver -> el.getAttribute("value").equals(value));
//    }
//
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
//    // ===============================
//    // STEP ONE → STEP TWO
//    // ===============================
//    public void clickContinue() {
//        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
//
//        // 🔥 tunggu sampai finish muncul (indikasi step-two)
//        wait.until(driver ->
//                driver.findElements(finishButton).size() > 0
//        );
//    }
//
//    // ===============================
//    // STEP TWO → COMPLETE
//    // ===============================
//    public void clickFinish() {
//        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
//    }
//
//    // ===============================
//    // VALIDATION
//    // ===============================
//    public String getSuccessMessage() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(successMessage)
//        ).getText();
//    }
//}
//

//
//
//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class checkoutPage {
//
//    WebDriver driver;
//    WebDriverWait wait;
//
//    // ===============================
//    // LOCATORS
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
//    // HELPER
//    // ===============================
//    private void waitForUrlContains(String text) {
//        wait.until(driver -> driver.getCurrentUrl().contains(text));
//    }
//
//    private void fillField(By locator, String value) {
//        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//        el.clear();
//        el.sendKeys(value);
//    }
//
//    private void ensureOnStepOne() {
//        waitForUrlContains("checkout-step-one");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
//    }
//
//    private void ensureOnStepTwo() {
//        waitForUrlContains("checkout-step-two");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton));
//    }
//
//    // ===============================
//    // ACTIONS
//    // ===============================
//
//    // CART → STEP ONE
//    public void clickCheckout() {
//        waitForUrlContains("cart.html");
//
//        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
//
//        ensureOnStepOne();
//    }
//
//    // INPUT FORM
//    public void inputFirstName(String fname) {
//        ensureOnStepOne();
//        fillField(firstName, fname);
//    }
//
//    public void inputLastName(String lname) {
//        ensureOnStepOne();
//        fillField(lastName, lname);
//    }
//
//    public void inputPostalCode(String code) {
//        ensureOnStepOne();
//        fillField(postalCode, code);
//    }
//
//    // STEP ONE → STEP TWO
//    public void clickContinue() {
//        ensureOnStepOne();
//
//        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
//
//        ensureOnStepTwo();
//    }
//
//    // STEP TWO → COMPLETE
//    public void clickFinish() {
//        ensureOnStepTwo();
//
//        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
//
//        waitForUrlContains("checkout-complete");
//    }
//
//    // ===============================
//    // VALIDATION
//    // ===============================
//    public String getSuccessMessage() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(successMessage)
//        ).getText();
//    }
//}




//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class checkoutPage {
//
//    WebDriver driver;
//    WebDriverWait wait;
//
//    // ===============================
//    // LOCATORS
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
//    // STEP: CART → CHECKOUT STEP ONE
//    // ===============================
//    public void clickCheckout() {
//
//        // pastikan kita di cart page
//        wait.until(driver -> driver.getCurrentUrl().contains("cart.html"));
//
//        System.out.println("BEFORE CHECKOUT: " + driver.getCurrentUrl());
//
//        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
//
//        // tunggu pindah ke step one
//        wait.until(driver -> driver.getCurrentUrl().contains("checkout-step-one"));
//
//        System.out.println("AFTER CHECKOUT: " + driver.getCurrentUrl());
//
//        // pastikan field muncul
//        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
//    }
//
//    // ===============================
//    // INPUT FORM
//    // ===============================
//    public void inputFirstName(String fname) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fname);
//    }
//
//    public void inputLastName(String lname) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lname);
//    }
//
//    public void inputPostalCode(String code) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode)).sendKeys(code);
//    }
//
//    // ===============================
//    // STEP ONE → STEP TWO
//    // ===============================
//    public void clickContinue() {
//
//        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
//
//        try {
//            // tunggu pindah ke step two
//            wait.until(driver -> driver.getCurrentUrl().contains("checkout-step-two"));
//
//            // validasi tombol finish muncul
//            wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton));
//
//        } catch (Exception e) {
//
//            // kalau gagal, cek error message
//            if (driver.findElements(errorMessage).size() > 0) {
//                String error = driver.findElement(errorMessage).getText();
//                throw new RuntimeException("❌ Checkout gagal: " + error);
//            }
//
//            throw new RuntimeException("❌ Gagal ke checkout step two");
//        }
//    }
//
//    // ===============================
//    // STEP TWO → COMPLETE
//    // ===============================
//    public void clickFinish() {
//
//        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
//
//        wait.until(driver -> driver.getCurrentUrl().contains("checkout-complete"));
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
//    }
//
//    // ===============================
//    // VALIDATION
//    // ===============================
//    public String getSuccessMessage() {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
//    }
//}
//


//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
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
//    By errorMessage = By.cssSelector("[data-test='error']");
//
//    public checkoutPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    // ===============================
//    // CART → STEP ONE
//    // ===============================
//    public void clickCheckout() {
//
//        System.out.println("URL BEFORE CHECKOUT: " + driver.getCurrentUrl());
//
//        // pastikan ada item di cart
//        int itemCount = driver.findElements(By.className("cart_item")).size();
//        if (itemCount == 0) {
//            throw new RuntimeException("❌ Cart kosong!");
//        }
//
//        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
//
//        // 🔥 tunggu element muncul (bukan URL)
//        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
//
//        System.out.println("MASUK STEP ONE");
//    }
//
//    // ===============================
//    // INPUT FORM
//    // ===============================
//    public void inputFirstName(String fname) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fname);
//    }
//
//    public void inputLastName(String lname) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lname);
//    }
//
//    public void inputPostalCode(String code) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode)).sendKeys(code);
//    }
//
//    // ===============================
//    // STEP ONE → STEP TWO
//    // ===============================
//    public void clickContinue() {
//
//        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
//
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton));
//        } catch (Exception e) {
//
//            if (driver.findElements(errorMessage).size() > 0) {
//                String err = driver.findElement(errorMessage).getText();
//                throw new RuntimeException("❌ Error form: " + err);
//            }
//
//            throw new RuntimeException("❌ Gagal ke step two");
//        }
//    }
//
//    // ===============================
//    // STEP TWO → COMPLETE
//    // ===============================
//    public void clickFinish() {
//        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
//    }
//
//    public String getSuccessMessage() {
//        return driver.findElement(successMessage).getText();
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

    public checkoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===============================
    // ACTION
    // ===============================

    public void clickCheckout() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        btn.click();

        // 🔥 WAJIB: tunggu pindah ke step one
        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
    }

    public void inputFirstName(String fname) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fname);
    }

    public void inputLastName(String lname) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lname);
    }

    public void inputPostalCode(String code) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode)).sendKeys(code);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();

        // 🔥 WAJIB: tunggu pindah ke step two
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();

        // tunggu success page
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}

