package com.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.ExtentManager;

public class ExtentTestManager {

  private static final InheritableThreadLocal<ExtentTest> extentTestMap = new InheritableThreadLocal<>();
  private static final InheritableThreadLocal<ExtentTest> extentNode = new InheritableThreadLocal<>();
  private static final ExtentReports extent = ExtentManager.getExtentReports();

  public static synchronized ExtentTest getTest() {
    return extentTestMap.get();
  }

  public static synchronized ExtentTest startTest(String testName, String desc) {
    ExtentTest test = extent.createTest(testName, desc);
    extentTestMap.set(test);
    return extentTestMap.get();
  }

  public static synchronized ExtentTest getNode() {
    return extentNode.get();
  }

  public static synchronized void setNode(ExtentTest extentTestNode) {
    extentNode.set(extentTestNode);
  }
}