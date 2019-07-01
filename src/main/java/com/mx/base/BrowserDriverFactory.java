package com.mx.base;

import com.mashape.unirest.http.Unirest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserDriverFactory {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;
    private String platform;
    private String name;

    private CBTAPI api;


    public BrowserDriverFactory(String browser, String platform, String name) {
        this.browser = browser.toLowerCase();
        this.platform = platform;
        this.name = name;
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

            case "chromenode":
                String hubUrl = "http://192.168.1.19:4444/wd/hub";
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
                try {
                    driver.set(new RemoteWebDriver(new URL(hubUrl), capabilities));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;

        }

        return driver.get();
    }

    public WebDriver createDriveSauce() {
        System.out.println("[Setting up driver: " + browser + " on SauceLabs]");
        String username = "jorgebastidas";
        String accessKey = "e211bf2e-47fc-45bd-b186-1f94cfda79ca";
        String url = "http://" + username + ":" + accessKey + "@ondemand.saucelabs.com:80/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", browser);
        if (platform == null) {
            caps.setCapability("platform", "macOS 10.13");
        } else {
            caps.setCapability("platform", platform);
        }

        caps.setCapability("name", name);

        try {
            driver.set(new RemoteWebDriver(new URL(url), caps));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return driver.get();


    }

    public WebDriver createDriveCBT() {
        System.out.println("[Setting up driver: " + browser + " on CBT]");

        String username = "dari3ndoomed%40gmail.com"; // Your username
        String authkey = "u7deff900553b8f0";  // Your authkey

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("name", name);
        caps.setCapability("build", "1.0");
        caps.setCapability("browserName", browser);
        caps.setCapability("version", "72");
        if (platform == null) {
            caps.setCapability("platform", "macOS 10.13");
        } else {
            caps.setCapability("platform", platform);
        }
        caps.setCapability("screenResolution", "1366x768");
        caps.setCapability("record_video", "true");
        caps.setCapability("record_network", "false");

        try {
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL("http://" + username + ":" + authkey + "@hub.crossbrowsertesting.com:80/wd/hub"), caps);
            driver.set(remoteWebDriver);
            System.out.println(remoteWebDriver.getSessionId());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        System.out.println("[Finished Setting up driver: " + browser + " on CBT]");

        return driver.get();

    }


}
