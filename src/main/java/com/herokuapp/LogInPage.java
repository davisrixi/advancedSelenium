package com.herokuapp;

import com.mx.tests.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage extends BasePageObject {

    private String logInPageUrl = "http://the-internet.herokuapp.com/login";

    By usernameLocator = By.id("username");
    By passwordLocator = By.cssSelector("input[name=password]");
    By loginButtonLocator = By.xpath("//button[@type='submit']");


    public LogInPage(WebDriver driver) {
        super(driver);
    }


    public void open() {
        openUrl(logInPageUrl);
    }


    public SecurePage logIn(String username, String password) {
        System.out.println("Entering username and password");
        find(usernameLocator).sendKeys(username);
        find(passwordLocator).sendKeys(password);

        System.out.println("Clicking Login button");
        find(loginButtonLocator).click();

        return new SecurePage(driver);
    }

}
