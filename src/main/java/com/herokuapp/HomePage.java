package com.herokuapp;

import com.mx.base.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class HomePage extends BasePageObject {


    By privateAccessButton = By.xpath("//div[@id='header']/div[@class='main-header']/div[@class='container']//a[@title='Area de usaurio']");
    private String homePageUrl = "https://sitedev.bvl.com.pe";

    public HomePage(WebDriver driver, HashMap<String, String> testConfig, Logger log) {
        super(driver, testConfig, log);
    }


    public AccessPage userPage() {
        log.info("Clicking user button");
        find(privateAccessButton).click();
        return new AccessPage(driver, testConfig, log);
    }

    public void open() {
        openUrl(homePageUrl);
    }
}
