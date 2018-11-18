package com.mx.base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("- - - Test is starting - - -");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("- - - Test Passed - - -");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("- - - Test Failed - - -");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("- - - START - - -");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("- - - FINISH - - -");
    }
}
