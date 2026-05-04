

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.time.Duration;

public class checkoutPage {

    WebDriver driver;
    WebDriverWait wait;

    // ===============================
    // LOCATORS
    // ===============================
    private final By checkoutButton = By.id("checkout");
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By successMessage = By.className("complete-header");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    // ===============================
    // CONSTRUCTOR
    // ===============================
    public checkoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===============================
    // STEP 1: CLICK CHECKOUT
    // ===============================
    public void clickCheckout() {
        wait.until(ExpectedConditions.urlContains("cart.html"));

        takeDebugScreenshot("before-checkout");

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));

        // Gunakan JS Click untuk stabilitas di Cart
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
        System.out.println("✅ Navigated to: " + driver.getCurrentUrl());
    }

    // ===============================
    // STEP 2: INPUT FORM
    // ===============================
    public void inputFirstName(String fname) {
        ensureOnStepOne();
        typeAndVerify(firstName, fname);
    }

    public void inputLastName(String lname) {
        ensureOnStepOne();
        typeAndVerify(lastName, lname);
    }

    public void inputPostalCode(String code) {
        ensureOnStepOne();
        typeAndVerify(postalCode, code);
    }

    // ===============================
    // STEP 3: CONTINUE
    // ===============================
    public void clickContinue() {
        ensureOnStepOne();
        takeDebugScreenshot("before-continue");

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        // Validasi jika ada error message (misal form kosong)
        if (isErrorVisible()) {
            String err = driver.findElement(errorMessage).getText();
            takeDebugScreenshot("form-error");
            throw new RuntimeException("❌ Checkout Step One Error: " + err);
        }

        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
        System.out.println("✅ Navigated to Overview: " + driver.getCurrentUrl());
    }

    // ===============================
    // STEP 4: FINISH
    // ===============================
    public void clickFinish() {
        // Pastikan halaman overview sudah siap
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(finishButton));

        // Scroll dan klik menggunakan JS untuk menghindari element intercept
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        System.out.println("✅ Finish button clicked. Waiting for completion page...");
    }

    // ===============================
    // VALIDATION
    // ===============================
    public String getSuccessMessage() {
        try {
            // KRUSIAL: Tunggu URL berubah ke halaman finish sebelum cari element
            wait.until(ExpectedConditions.urlContains("checkout-complete"));

            WebElement successLabel = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(successMessage)
            );

            String text = successLabel.getText();
            System.out.println("🎉 Order Success Message: " + text);
            return text;

        } catch (Exception e) {
            takeDebugScreenshot("failed-getting-success-msg");
            System.err.println("❌ Gagal mendapatkan success message. URL saat ini: " + driver.getCurrentUrl());
            throw e;
        }
    }

    public boolean isErrorVisible() {
        return !driver.findElements(errorMessage).isEmpty();
    }

    // ===============================
    // HELPERS (PRIVATE)
    // ===============================

    private void ensureOnStepOne() {
        if (!driver.getCurrentUrl().contains("checkout-step-one")) {
            throw new RuntimeException("❌ Stop: Browser tidak berada di halaman Checkout Step One!");
        }
    }


    private void typeAndVerify(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(locator));

        // 1. Bersihkan field secara menyeluruh
        field.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
        field.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
        field.clear();

        // 2. Ketik manual
        field.sendKeys(value);

        // 3. Verifikasi apakah teks benar-benar masuk
        String actual = field.getAttribute("value");
        if (actual == null || !actual.equals(value)) {
            System.out.println("⚠️ SendKeys gagal untuk " + locator + ", mencoba Force JS Input...");

            // 4. Force Input via JavaScript (untuk React/Vue state)
            String script = "var el = arguments[0];" +
                    "var value = arguments[1];" +
                    "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                    "nativeInputValueSetter.call(el, value);" +
                    "el.dispatchEvent(new Event('input', { bubbles: true }));" +
                    "el.dispatchEvent(new Event('change', { bubbles: true }));" +
                    "el.dispatchEvent(new Event('blur', { bubbles: true }));";

            ((JavascriptExecutor) driver).executeScript(script, field, value);
        }

        // Cek sekali lagi, jika masih kosong, lempar error sebelum klik Continue
        if (!field.getAttribute("value").equals(value)) {
            throw new RuntimeException("❌ Gagal mengisi field: " + locator.toString());
        }
    }

    private void takeDebugScreenshot(String name) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("debug-" + name + ".png"));
        } catch (Exception e) {
            System.out.println("⚠️ Gagal ambil screenshot: " + e.getMessage());
        }
    }
}