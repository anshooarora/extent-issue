package com.example;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

  @Override
  public void onTestStart(ITestResult result) {
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    ExtentTestManager.getNode().pass("Test Passed");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    ExtentTestManager.getNode().fail("Test Failed");
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    ExtentTestManager.getNode().fail("Test Skipped");
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
  }

  @Override
  public void onStart(ITestContext context) {
    ExtentTestManager.startTest(context.getName(), "");
  }

  @Override
  public void onFinish(ITestContext context) {
    ExtentManager.getExtentReports().flush();
  }
}
