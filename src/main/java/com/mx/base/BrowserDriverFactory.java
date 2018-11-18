package com.mx.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserDriverFactory {

    public static WebDriver createDriver(String browser) {
        System.out.println("[Setting up driver: " + browser + "]");
        WebDriver driver = null;
        browser = browser.toLowerCase();

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                driver = new ChromeDriver();
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                driver = new FirefoxDriver();
                break;

            case "safari":
                driver = new SafariDriver();
                break;
        }

        return driver;
    }

}
