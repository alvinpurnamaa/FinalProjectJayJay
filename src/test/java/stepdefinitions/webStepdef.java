//package stepdefinitions;
//
//import io.cucumber.java.en.*;
//import io.cucumber.java.Before;
//import io.cucumber.java.After;
//
//import org.openqa.selenium.WebDriver;
//
//import pages.loginPage;
//import pages.homePage;
//import pages.checkoutPage;
//import utils.driverFactory;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class webStepdef {
//
//    WebDriver driver;
//    loginPage loginPage;
//    homePage homePage;
//    checkoutPage checkoutPage;
//
//    @Before("@web")
//    public void setUp() {
//        driver = driverFactory.getDriver();
//        loginPage = new loginPage(driver);
//        homePage = new homePage(driver);
//        checkoutPage = new checkoutPage(driver);
//    }
//
//
//    @Given("user open the login page")
//    public void openLoginPage() {
//        loginPage.openPage();
//    }
//
//    @When("user input username {string}")
//    public void inputUsername(String username) {
//        loginPage.inputUsername(username);
//    }
//
//    @And("user input password {string}")
//    public void inputPassword(String password) {
//        loginPage.inputPassword(password);
//    }
//
//    @And("user click login button")
//    public void clickLogin() {
//        loginPage.clickLogin();
//    }
//
//    @Then("user should be redirected to inventory page")
//    public void validateLoginSuccess() {
//        assertTrue(homePage.isOnInventoryPage(), "Login failed!");
//    }
//
//    @Then("user should see error message")
//    public void validateError() {
//        assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed!");
//    }
//
//
//    @And("user add product to cart")
//    public void addProduct() {
//        homePage.addProductToCart();
//    }
//
//    @And("user click cart icon")
//    public void clickCart() {
//        homePage.clickCart();
//
//        assertTrue(homePage.isOnCartPage(), "Not on cart page!");
//    }
//
//
//    @And("user click checkout button")
//    public void clickCheckout() {
//        checkoutPage.clickCheckout();
//        System.out.println("URL after checkout: " + driver.getCurrentUrl());
//    }
//
//    @And("user input first name {string}")
//    public void inputFirstName(String fname) {
//        checkoutPage.inputFirstName(fname);
//    }
//
//    @And("user input last name {string}")
//    public void inputLastName(String lname) {
//        checkoutPage.inputLastName(lname);
//    }
//
//    @And("user input postal code {string}")
//    public void inputPostalCode(String code) {
//        checkoutPage.inputPostalCode(code);
//    }
//
//    @And("user click continue button")
//    public void clickContinue() {
//        checkoutPage.clickContinue();
//        System.out.println("URL after continue: " + driver.getCurrentUrl());
//    }
//
//    @And("user click finish button")
//    public void clickFinish() {
//        System.out.println("URL before finish: " + driver.getCurrentUrl());
//        checkoutPage.clickFinish();
//    }
//
//    @Then("user should see checkout success message")
//    public void validateCheckoutSuccess() {
//        String actual = checkoutPage.getSuccessMessage();
//        assertEquals("Thank you for your order!", actual, "Checkout failed!");
//    }
//
//    @After("@web")
//    public void tearDown() {
//        driverFactory.quitDriver();
//    }
//}

//package stepdefinitions;
//
//import io.cucumber.java.en.*;
//import io.cucumber.java.Before;
//import io.cucumber.java.After;
//
//import org.openqa.selenium.WebDriver;
//
//import pages.loginPage;
//import pages.homePage;
//import pages.checkoutPage;
//import utils.driverFactory;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class webStepdef {
//
//    WebDriver driver;
//    loginPage loginPage;
//    homePage homePage;
//    checkoutPage checkoutPage;
//
//    @Before("@web")
//    public void setUp() {
//        driver = driverFactory.getDriver();
//        loginPage = new loginPage(driver);
//        homePage = new homePage(driver);
//        checkoutPage = new checkoutPage(driver);
//    }
//
//    // ================= LOGIN =================
//
//    @Given("user open the login page")
//    public void openLoginPage() {
//        loginPage.openPage();
//    }
//
//    @When("user input username {string}")
//    public void inputUsername(String username) {
//        loginPage.inputUsername(username);
//    }
//
//    @And("user input password {string}")
//    public void inputPassword(String password) {
//        loginPage.inputPassword(password);
//    }
//
//    @And("user click login button")
//    public void clickLogin() {
//        loginPage.clickLogin();
//    }
//
//    @Then("user should be redirected to inventory page")
//    public void validateLoginSuccess() {
//        assertTrue(homePage.isOnInventoryPage(), "Login failed!");
//    }
//
//    @Then("user should see error message")
//    public void validateError() {
//        assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed!");
//    }
//
//    // ================= PRODUCT =================
//
//    @And("user add product to cart")
//    public void addProduct() {
//        homePage.addProductToCart();
//    }
//
//    @And("user click cart icon")
//    public void clickCart() {
//        homePage.clickCart();
//
//        // FIX: jangan langsung assert keras tanpa wait ready state
//        assertTrue(homePage.isOnCartPage(), "Not on cart page!");
//    }
//
//    // ================= CHECKOUT =================
//
//    @And("user click checkout button")
//    public void clickCheckout() {
//        checkoutPage.clickCheckout();
//
//        // DEBUG SAFE (boleh tetap)
//        System.out.println("URL after checkout: " + driver.getCurrentUrl());
//    }
//
//    @And("user input first name {string}")
//    public void inputFirstName(String fname) {
//        checkoutPage.inputFirstName(fname);
//    }
//
//    @And("user input last name {string}")
//    public void inputLastName(String lname) {
//        checkoutPage.inputLastName(lname);
//    }
//
//    @And("user input postal code {string}")
//    public void inputPostalCode(String code) {
//        checkoutPage.inputPostalCode(code);
//    }
//
//    @And("user click continue button")
//    public void clickContinue() {
//        checkoutPage.clickContinue();
//
//        // FIX: jangan cuma print URL (di CI ini sering misleading)
//        System.out.println("URL after continue: " + driver.getCurrentUrl());
//    }
//
//    @And("user click finish button")
//    public void clickFinish() {
//        checkoutPage.clickFinish();
//    }
//
//    // ================= ASSERT FINAL =================
//
//    @Then("user should see checkout success message")
//    public void validateCheckoutSuccess() {
//
//        // FIX: jangan ambil text tanpa wait (CI sering delay)
//        String actual = checkoutPage.getSuccessMessage();
//
//        assertEquals("Thank you for your order!", actual,
//                "Checkout failed!");
//    }
//
//    // ================= TEARDOWN =================
//
//    @After("@web")
//    public void tearDown() {
//        driverFactory.quitDriver();
//    }
//}

//package stepdefinitions;
//
//import io.cucumber.java.en.*;
//import io.cucumber.java.Before;
//import io.cucumber.java.After;
//
//import org.openqa.selenium.WebDriver;
//
//import pages.*;
//import utils.driverFactory;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class webStepdef {
//
//    WebDriver driver;
//    loginPage loginPage;
//    homePage homePage;
//    checkoutPage checkoutPage;
//
//    @Before("@web")
//    public void setUp() {
//        driver = driverFactory.getDriver();
//        loginPage = new loginPage(driver);
//        homePage = new homePage(driver);
//        checkoutPage = new checkoutPage(driver);
//    }
//
//    // ================= LOGIN =================
//
//    @Given("user open the login page")
//    public void openLoginPage() {
//        loginPage.openPage();
//    }
//
//    @When("user input username {string}")
//    public void inputUsername(String username) {
//        loginPage.inputUsername(username);
//    }
//
//    @And("user input password {string}")
//    public void inputPassword(String password) {
//        loginPage.inputPassword(password);
//    }
//
//    @And("user click login button")
//    public void clickLogin() {
//        loginPage.clickLogin();
//    }
//
//    @Then("user should be redirected to inventory page")
//    public void validateLoginSuccess() {
//        assertTrue(homePage.isOnInventoryPage());
//    }
//
//    // ================= CART =================
//
//    @And("user add product to cart")
//    public void addProduct() {
//        homePage.addProductToCart();
//    }
//
//    @And("user click cart icon")
//    public void clickCart() {
//        homePage.clickCart();
//        assertTrue(homePage.isOnCartPage());
//    }
//
//    // ================= CHECKOUT =================
//
//    @And("user click checkout button")
//    public void clickCheckout() {
//        checkoutPage.clickCheckout();
//    }
//
//    @And("user input first name {string}")
//    public void inputFirstName(String fname) {
//        checkoutPage.inputFirstName(fname);
//    }
//
//    @And("user input last name {string}")
//    public void inputLastName(String lname) {
//        checkoutPage.inputLastName(lname);
//    }
//
//    @And("user input postal code {string}")
//    public void inputPostalCode(String code) {
//        checkoutPage.inputPostalCode(code);
//    }
//
//    @And("user click continue button")
//    public void clickContinue() {
//        checkoutPage.clickContinue();
//    }
//
//    @And("user click finish button")
//    public void clickFinish() {
//        checkoutPage.clickFinish();
//    }
//
//    @Then("user should see checkout success message")
//    public void validateCheckoutSuccess() {
//        assertEquals("Thank you for your order!", checkoutPage.getSuccessMessage());
//    }
//
//    @After("@web")
//    public void tearDown() {
//        driverFactory.quitDriver();
//    }
//}

package stepdefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.loginPage;
import pages.homePage;
import pages.checkoutPage;
import utils.driverFactory;

import static org.junit.jupiter.api.Assertions.*;

public class webStepdef {

    WebDriver driver;
    loginPage loginPage;
    homePage inventoryPage;
    checkoutPage checkoutPage;

    @Before("@web")
    public void setUp() {
        driver = driverFactory.getDriver();
        loginPage = new loginPage(driver);
        inventoryPage = new homePage(driver);
        checkoutPage = new checkoutPage(driver);
    }

    @Given("user open the login page")
    public void openLoginPage() {
        loginPage.openPage();
    }

    @When("user input username {string}")
    public void inputUsername(String username) {
        loginPage.inputUsername(username);
    }

    @And("user input password {string}")
    public void inputPassword(String password) {
        loginPage.inputPassword(password);
    }

    @And("user click login button")
    public void clickLogin() {
        loginPage.clickLogin();
    }

    @Then("user should be redirected to inventory page")
    public void validateLoginSuccess() {
        assertTrue(inventoryPage.isOnInventoryPage());
    }

    @Then("user should see error message")
    public void validateError() {
        assertTrue(loginPage.isErrorDisplayed());
    }

    // ===============================
    // ✅ ADD TO CART
    // ===============================

    @And("user add product to cart")
    public void addProduct() {
        inventoryPage.addProductToCart();
    }

    @Then("product should be added to cart")
    public void validateProductAdded() {
        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        assertEquals("1", cartCount);
    }

    @And("user click cart icon")
    public void clickCart() {
        inventoryPage.clickCart();
    }

    // ===============================
    // ✅ CHECKOUT FLOW
    // ===============================

    @And("user click checkout button")
    public void clickCheckout() {
        checkoutPage.clickCheckout();
    }

    @And("user input first name {string}")
    public void inputFirstName(String fname) {
        checkoutPage.inputFirstName(fname);
    }

    @And("user input last name {string}")
    public void inputLastName(String lname) {
        checkoutPage.inputLastName(lname);
    }

    @And("user input postal code {string}")
    public void inputPostalCode(String code) {
        checkoutPage.inputPostalCode(code);
    }

    @And("user click continue button")
    public void clickContinue() {
        checkoutPage.clickContinue();
    }

    @And("user click finish button")
    public void clickFinish() {
        checkoutPage.clickFinish();
    }

    @Then("user should see checkout success message")
    public void validateCheckoutSuccess() {
        String actual = checkoutPage.getSuccessMessage();
        assertEquals("Thank you for your order!", actual);
    }

    @After("@web")
    public void tearDown() {
        driverFactory.quitDriver();
    }
}