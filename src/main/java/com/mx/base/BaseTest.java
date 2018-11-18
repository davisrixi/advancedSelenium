package com.mx.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.HashMap;

public class BaseTest {

    protected WebDriver driver;
    protected HashMap<String, String> testConfig = new HashMap<String, String>();
    protected Logger log;


    @DataProvider(name = "negativeLoginData")
    public static Object[][] negativeLoginData() {
        return new Object[][]{
                {"incorrectUsername", "SuperSecretPassword!", "Your username is invalid!"},
                {"tomsmith", "IncorrectPassword", "Your password is invalid!"}
        };
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    protected void setUp(@Optional("chrome") String browser, ITestContext ctx) {
        //driver = BrowserDriverFactory.createDriver(browser);
        BrowserDriverFactory factory = new BrowserDriverFactory(browser);
        driver = factory.createDriver();
        testConfig.put("browser", browser);

        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);


    }

    @AfterMethod
    protected void tearDown() {
        //Closing driver
        log.info("[Closing driver]");
        driver.quit();
    }

}
