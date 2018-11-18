package com.mx.base;

import com.herokuapp.LogInPage;
import com.herokuapp.SecurePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class FirstTest extends BaseTest {

    @Test
    @Parameters({"username", "password"})
    public void firstTest(String username, String password) {
        SoftAssert softAssert = new SoftAssert();
        LogInPage loginPage = new LogInPage(driver, testConfig);
        loginPage.open();

        // Clicking LogIn button
        SecurePage securePage = loginPage.logIn(username, password);
        securePage.waitForSecurePage(10);

        //Create expected message for IE
        String expectedMessage = "You logged into a secure area!";
        if(testConfig.get("browser").equals("ie")){
            System.out.println("expectedMessage is changed for IE");
            // code to change expected message
        }



        // Verifications
        softAssert.assertTrue(securePage.isLogOutButtonVisible(), "LogOut button is not displayed.");
        softAssert.assertTrue(securePage.getPageSource().contains(expectedMessage),
                "Page source doesn't contain expected text: " + expectedMessage);

        softAssert.assertAll();
    }

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void negativeLogInTest(Map<String, String> testData) {
        //Data
        String username = testData.get("username");
        String password = testData.get("password");
        String expectedErrorMessage = testData.get("expectedError");

        LogInPage logInPage = new LogInPage(driver, testConfig);
        logInPage.open();

        //Clicking LogIn button
        logInPage.negativeLogIn(username, password);
        String errorMessage = logInPage.getErrorMessageText();

        //Verification
        Assert.assertTrue(errorMessage.contains(expectedErrorMessage),
                "Actual and expected error messages are different. \nExpected: " + expectedErrorMessage + "\nActual: "
                        + errorMessage);

    }
}
