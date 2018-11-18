package com.mx.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;

    @DataProvider(name = "negativeLoginData")
    public static Object[][] negativeLoginData() {
        return new Object[][]{
                {"incorrectUsername", "SuperSecretPassword!", "Your username is invalid!"},
                {"tomsmith", "IncorrectPassword", "Your password is invalid!"}
        };
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    protected void setUp(@Optional("chrome") String browser) {
        driver = BrowserDriverFactory.createDriver(browser);

    }

    @AfterMethod
    protected void tearDown() {
        //Closing driver
        System.out.println("[Closing driver");
        driver.quit();
    }

}
