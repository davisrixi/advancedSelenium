package com.mx.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Cookies extends BaseTest {

    @Test
    public void addingCookiesTest() {
        setCookies();
        sleep(2000);

        // Opening page
        driver.get("http://echoecho.com/samplecookie1.htm");
        log.info("Page opened!");
        sleep(5000);

        // steps of our test

    }

    private void setCookies() {
        log.info("* Adding cookie.");
        driver.get("http://echoecho.com/");
        Cookie ck = new Cookie("username", "Davis", ".echoecho.com","/",null);
        driver.manage().addCookie(ck);
        log.info("* Cookie added.");
    }


    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
