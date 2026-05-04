//package utils;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//public class driverFactory {
//
//    private static WebDriver driver;
//
//    public static WebDriver getDriver() {
//        if (driver == null) {
//
//            ChromeOptions options = new ChromeOptions();
//
//            options.addArguments("--headless=new");
//            options.addArguments("--no-sandbox");
//            options.addArguments("--disable-dev-shm-usage");
//
//            driver = new ChromeDriver(options);
//            driver.manage().window().maximize();
//        }
//        return driver;
//    }
//
//    public static void quitDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        }
//    }
//}

//package utils;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//public class driverFactory {
//
//    private static WebDriver driver;
//
//    public static WebDriver getDriver() {
//        if (driver == null) {
//
//            ChromeOptions options = new ChromeOptions();
//
//
//            options.addArguments("--headless=new");
//
//
//            options.addArguments("--no-sandbox");
//            options.addArguments("--disable-dev-shm-usage");
//
//
//            options.addArguments("--window-size=1920,1080");
//
//
//            options.addArguments("--disable-gpu");
//            options.addArguments("--remote-allow-origins=*");
//
//            driver = new ChromeDriver(options);
//
//            driver.manage().window().maximize();
//        }
//        return driver;
//    }
//
//    public static void quitDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        }
//    }
//}

package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class driverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-extensions");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--force-device-scale-factor=1");

            driver = new ChromeDriver(options);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}