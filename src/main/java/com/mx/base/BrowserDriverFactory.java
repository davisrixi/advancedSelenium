package com.mx.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class BrowserDriverFactory {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;

    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    public WebDriver createDriver() {
        System.out.println("[Setting up driver: " + browser + "]");
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                driver.set(new ChromeDriver());
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                driver.set(new FirefoxDriver());
                break;

            case "safari":
                driver.set(new FirefoxDriver());
                break;

            case "phantomjs":
                System.setProperty("phantomjs.binary.path", "src/main/resources/phantomjs");
                driver.set(new PhantomJSDriver());
                break;

            case "htmlunit":
                driver.set(new HtmlUnitDriver());
                break;

            case "headlesschrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver.set(new ChromeDriver(chromeOptions));
                break;

            case "headlessfirefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                FirefoxBinary firefoxBinary = new FirefoxBinary();
                firefoxBinary.addCommandLineOptions("--headless");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary(firefoxBinary);
                driver.set(new FirefoxDriver(firefoxOptions));
                break;

            case "chromeprofile":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("user-data-dir=/src/main/resources/ChromeProfile");
                driver.set(new ChromeDriver(options));
                break;
        }

        return driver.get();
    }

}
