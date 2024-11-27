package com.selenideDemo;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.selenideDemo.Base.AndroidDriverManager;
import com.selenideDemo.Base.IOSDriverManager;
import com.selenideDemo.Utils.AppiumServerManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTest {
    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;
    protected static WebDriver driver;
    static String platform = System.getProperty("platform", "android");  // Default to Android if not specified
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);


    @BeforeAll
    public static void globalSetup() {
        String system = System.getProperty("os.name").toLowerCase();
        logger.info("Appium server started for all tests.");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-reports/extentReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        logger.info("Extent Report initialized");
        try {
            AppiumServerManager.startAppiumServer(system);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setUp() {
        logger.info("Starting test on platform: " + platform);
        try {
            if ("android".equalsIgnoreCase(platform)) {
                driver = AndroidDriverManager.createDriver();
            } else if ("ios".equalsIgnoreCase(platform)) {
                driver = IOSDriverManager.createDriver();
            } else {
                throw new IllegalArgumentException("Invalid platform specified: " + platform);
            }

            // Set Selenide to use the Appium driver
            WebDriverRunner.setWebDriver(driver);
            Configuration.timeout = 10000;  // Set default timeout for Selenide actions
            extentTest = extentReports.createTest(getClass().getSimpleName());
            logger.info("Driver initialized and Selenide configured for: " + platform);
        } catch (Exception e) {
            logger.error("Error initializing driver", e);
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void tearDown() {
        if ("android".equalsIgnoreCase(platform)) {
            AndroidDriverManager.quitDriver();
        } else if ("ios".equalsIgnoreCase(platform)) {
            IOSDriverManager.quitDriver();
        }
        if (extentReports != null) {
            extentReports.flush();
            logger.info("Extent Report flushed");
        }
    }
    @AfterAll
    public static void globalTearDown() {
        // Stop Appium Server after all tests
        AppiumServerManager.stopAppiumServer();
        logger.info("Appium server stopped after all tests.");
    }
}
