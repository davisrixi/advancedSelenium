package com.herokuapp;

import com.mx.base.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class AccessPage extends BasePageObject {

    By usernameLocator = By.xpath("//bvl-root[@class='w-100']/bvl-main-layout[@class='g-sticky ng-star-inserted']/bvl-login[@class='ng-star-inserted']/div/div[@class='container']//bvl-login-form/form//bvl-form-input[@label='Email']/div/input[@type='text']");
    By passwordLocator = By.cssSelector("//bvl-root[@class='w-100']/bvl-main-layout[@class='g-sticky ng-star-inserted']/bvl-login[@class='ng-star-inserted']/div/div[@class='container']//bvl-login-form/form//bvl-form-input[@label='Contraseña']/div/input[@type='password']");
    By loginButtonLocator = By.xpath("//bvl-root[@class='w-100']/bvl-main-layout[@class='g-sticky ng-star-inserted']/bvl-login[@class='ng-star-inserted']/div/div[@class='container']//bvl-login-form/form//button[.=' Ingresar ']");
    //By errorMessageLocator = By.id("flash-messages");
    private String logInPageUrl = "https://sitedev.bvl.com.pe/login";

    public AccessPage(WebDriver driver, HashMap<String, String> testConfig, Logger log) {
        super(driver, testConfig, log);
    }

    public SecurePage logIn(String username, String password) {
        log.info("Entering username and password");
        find(usernameLocator).sendKeys(username);
        find(passwordLocator).sendKeys(password);

        log.info("Clicking Login button");
        find(loginButtonLocator).click();


        if (testConfig.get("browser").equals("firefox")) {
            log.info("Additional click needs to be done in firefox");
            // code to click some kind of confirmation after LogIn button clicked
        }


        return new SecurePage(driver, testConfig, log);
    }
/*
    public void negativeLogIn(String username, String password) {
        log.info("Entering username and password");
        find(usernameLocator).sendKeys(username);
        find(passwordLocator).sendKeys(password);

        log.info("Clicking Login button");
        find(loginButtonLocator).click();
        AccessPage
        //waitForErrorMessage();
    }
    private void waitForErrorMessage() {
        waitForVisibilityOf(errorMessageLocator, 10);
    }

    public String getErrorMessageText() {
        return find(errorMessageLocator).getText();
    }
*/

    public void waitForAccessPage(long millis) {
        log.info("Waiting for access page");
        waitForElementPresent(loginButtonLocator, 10);
    }

    public boolean isLoginButtonVisible() {
        return find(loginButtonLocator).isDisplayed();
    }


    public void open() {
        openUrl(logInPageUrl);
    }

}
