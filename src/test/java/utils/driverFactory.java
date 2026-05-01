//package utils;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//public class driverFactory {
//
//    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//
//    public static WebDriver getDriver() {
//        if (driver.get() == null) {
//            ChromeOptions options = new ChromeOptions();
//
//            options.addArguments("--remote-allow-origins=*");
//            options.addArguments("--start-maximized");
//
//            if (System.getProperty("headless") != null) {
//                options.addArguments("--headless=new");
//                options.addArguments("--disable-gpu");
//            }
//
//            driver.set(new ChromeDriver(options));
//        }
//        return driver.get();
//    }
//
//    public static void quitDriver() {
//        if (driver.get() != null) {
//            driver.get().quit();
//            driver.remove();
//        }
//    }
//}

package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class driverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
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