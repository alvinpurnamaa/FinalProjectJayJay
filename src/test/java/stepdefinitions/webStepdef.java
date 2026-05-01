//package stepdefinitions;
//
//import io.cucumber.java.en.*;
//import org.openqa.selenium.WebDriver;
//import pages.loginPage;
//import utils.driverFactory;
//
//public class webStepdef {
//
//    WebDriver driver = driverFactory.getDriver();
//    loginPage loginPage = new loginPage(driver);
//
//    @Given("when user open login page")
//    public void openLoginPage() {
//        driver.get("https://www.saucedemo.com/");
//    }
//
//    @When("user login with username {string} and password {string}")
//    public void login(String username, String password) {
//        loginPage.login(username, password);
//    }
//
//    @Then("user should redirect to homepage")
//    public void validateHome() {
//        assert loginPage.isHomeDisplayed();
//    }
//}

package stepdefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.After;

import org.openqa.selenium.WebDriver;
import pages.loginPage;
import pages.homePage;
import utils.driverFactory;

import static org.junit.jupiter.api.Assertions.*;

public class webStepdef {

    WebDriver driver = driverFactory.getDriver();
    loginPage loginPage = new loginPage(driver);
    homePage inventoryPage = new homePage(driver);

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
        System.out.println("Error Message: " + loginPage.getErrorMessage());
    }

    @Then("user should see error message {string}")
    public void validateErrorMessage(String expectedMsg) {
        String actualMsg = loginPage.getErrorMessage();
        System.out.println("Actual Error: " + actualMsg);
        assertTrue(actualMsg.contains(expectedMsg));
    }

    // ✅ cleanup setelah scenario
    @After
    public void tearDown() {
        driverFactory.quitDriver();
    }
}