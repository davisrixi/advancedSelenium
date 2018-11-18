package com.herokuapp;

import com.mx.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class SecurePage extends BasePageObject {

    By logOutButtonLocator = By.xpath("//a[@class='button secondary radius']");


    public SecurePage(WebDriver driver, HashMap<String, String> testConfig) {
        super(driver, testConfig);
    }


    public void waitForSecurePage(long millis) {
        System.out.println("Waiting for secure page");
        waitForElementPresent(logOutButtonLocator, 10);
    }


    public boolean isLogOutButtonVisible() {
        return find(logOutButtonLocator).isDisplayed();
    }

}
