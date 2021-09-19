package com.example;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.lang.reflect.Method;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleTest {

  private static final Logger LOGGER = LogManager.getLogger(GoogleTest.class);
  private static WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public synchronized void beforeMethod(Method method, ITestResult result, ITestContext context) {
    if (ExtentTestManager.getTest() == null)
      ExtentTestManager.startTest(context.getName(), null);
    ExtentTest test = ExtentTestManager.getTest().createNode(method.getName());
    ExtentTestManager.setNode(test);
    test.info("Test Started");
    WebDriverManager.chromedriver().setup();
    ChromeOptions chromeOptions = new ChromeOptions();
    driver = new ChromeDriver(chromeOptions);
    driver.manage().window().maximize();
  }

  @AfterMethod(alwaysRun = true)
  public void closeBrowser(ITestResult result, ITestContext context) {
    driver.quit();
  }

  @Test(description = "Google test")
  public void googleTest() {
    ExtentTest test = ExtentTestManager.getNode();
    test.assignCategory("Google");
    LOGGER.info("Logging into application");
    driver.get("https://www.google.com");
    takeScreenShot("Google home page");
  }

  public void takeScreenShot(String message) {
    String base64Screenshot = ((TakesScreenshot) Objects.requireNonNull(driver))
        .getScreenshotAs(OutputType.BASE64);
    ExtentTestManager.getNode()
        .info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
  }

}
