package com.mx.base;

import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class JavascriptErrors extends BaseTest {

    @Test
    public void javascriptErrorsTest() {
        SoftAssert softAssert = new SoftAssert();

        // Opening page
        //driver.get("http://the-internet.herokuapp.com/javascript_error");
        driver.get("http://the-internet.herokuapp.com/login");
        log.info("Page opened!");

        // Verifying there are no Javascript Errors on the page
        List<LogEntry> logs = getBrowserLogs();
        log.info("Logs: " + logs);

        for (LogEntry log : logs) {
            if (log.getLevel().toString().equals("SEVERE")) {
                softAssert.fail("Severe error: " + log.getMessage());
            }
        }

        softAssert.assertAll();
    }
}
