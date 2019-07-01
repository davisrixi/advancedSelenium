package com.mx.base;

import com.herokuapp.AccessPage;
import com.herokuapp.HomePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class BVLCBTTest extends BaseTest {

    @Test
    public void loginTest() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver, testConfig, log);
        homePage.open();
        //takeScreenshot("Opened_LogIn_Page");

        // Clicking User button
        AccessPage accessPage = homePage.userPage();
        accessPage.waitForAccessPage(10);
        //takeScreenshot("Opened_Secure_Page");

        // Verifications
        softAssert.assertTrue(accessPage.isLoginButtonVisible(), "Login button is displayed.");
        softAssert.assertAll();
    }
}
