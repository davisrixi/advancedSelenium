package com.mx.tests;

import com.herokuapp.LogInPage;
import com.herokuapp.SecurePage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FirstTest extends BaseTest {

    @Test
    @Parameters({"username","password"})
    public void firstTest(String username, String password) {
        SoftAssert softAssert = new SoftAssert();
        LogInPage loginPage = new LogInPage(driver);
        loginPage.open();

        SecurePage securePage = loginPage.logIn(username,password);
        securePage.waitForSecurePage(10);

        // Verifications
        softAssert.assertTrue(securePage.isLogOutButtonVisible(), "LogOut button is not displayed.");
        softAssert.assertTrue(securePage.getPageSource().contains("You logged into a secure area!"),
                "Page source doesn't contain expected text: 'You logged into a secure area!'");

        softAssert.assertAll();
    }
}
