package com.mx.base;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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

    protected void takeScreenshot(String fileName) {
        if (!testConfig.get("browser").equals("htmlunit")) {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "//test-output//screenshots//" + fileName + ".png";
            try {
                FileUtils.copyFile(srcFile, new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "platform", "environment"})
    protected void setUp(@Optional("chrome") String browser, @Optional String platform, @Optional("local") String environment, ITestContext ctx) {

        String testName = ctx.getCurrentXmlTest().getName();

        //driver = BrowserDriverFactory.createDriver(browser);
        BrowserDriverFactory factory = new BrowserDriverFactory(browser,platform,testName);

        if (environment.equals("SauceLabs")) {
            driver = factory.createDriveSauce();
        }

        else if(environment.equals("CBT")){
            driver = factory.createDriveCBT();

        }else {
            driver = factory.createDriver();
        }
        testConfig.put("browser", browser);

        // maximize browser window
        //driver.manage().window().maximize();

        log = LogManager.getLogger(testName);
    }

    @AfterMethod
    protected void tearDown() {
        //Closing driver
        log.info("[Closing driver]");
        driver.quit();
    }

    protected List<LogEntry> getBrowserLogs() {
        LogEntries log = driver.manage().logs().get("browser");
        List<LogEntry> logList = log.getAll();
        return logList;
    }

}
