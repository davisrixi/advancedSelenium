package com.herokuapp;

import com.mx.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage extends BasePageObject {

    By usernameLocator = By.id("username");
    By passwordLocator = By.cssSelector("input[name=password]");
    By loginButtonLocator = By.xpath("//button[@type='submit']");
    By errorMessageLocator = By.id("flash-messages");
    private String logInPageUrl = "http://the-internet.herokuapp.com/login";

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public SecurePage logIn(String username, String password) {
        System.out.println("Entering username and password");
        find(usernameLocator).sendKeys(username);
        find(passwordLocator).sendKeys(password);

        System.out.println("Clicking Login button");
        find(loginButtonLocator).click();

        return new SecurePage(driver);
    }

    public void negativeLogIn(String username, String password) {
        System.out.println("Entering username and password");
        find(usernameLocator).sendKeys(username);
        find(passwordLocator).sendKeys(password);

        System.out.println("Clicking Login button");
        find(loginButtonLocator).click();

        waitForErrorMessage();
    }

    private void waitForErrorMessage() {
        waitForVisibilityOf(errorMessageLocator, 10);
    }

    public String getErrorMessageText() {
        return find(errorMessageLocator).getText();
    }

    public void open() {
        openUrl(logInPageUrl);
    }

}
