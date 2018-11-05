package com.mx.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    protected void setUp(/*@Optional("IE") String browser*/) {
        //Creating driver
        //System.out.println("[Setting up driver: " + browser + "]");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterMethod
    protected void tearDown() {
        //Closing driver
        System.out.println("[Closing driver");
        driver.quit();
    }

    @DataProvider(name = "negativeLoginData")
    public static Object[][] negativeLoginData(){
        return new Object[][]{
                {"incorrectUsername","SuperSecretPassword!", "Your username is invalid!"},
                {"tomsmith","incorrectPassword", "Your password is invalid!"}
        };
    }


}
