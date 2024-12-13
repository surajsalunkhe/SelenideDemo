package com.selenideDemo;

import com.aventstack.extentreports.ExtentReports;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.selenideDemo.Base.AndroidDriverManager;
import com.selenideDemo.Base.IOSDriverManager;
import com.selenideDemo.Utils.AppiumServerManager;
import com.selenideDemo.report_manager.ExtentManager;
import com.selenideDemo.report_manager.ExtentTestManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public abstract class BaseTest {
    protected static ExtentReports extentReports;
    protected static WebDriver driver;
    static String platform = System.getProperty("platform", "android");  // Default to Android if not specified
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeAll
    public static void globalSetup() {
        String system = System.getProperty("os.name").toLowerCase();
        logger.info("Appium server started for all tests.");

        // Initialize ExtentReports using ExtentManager
        extentReports = ExtentManager.createExtentReports();
        logger.info("Extent Report initialized");

        // Start Appium server
        try {
            AppiumServerManager appiumServerManager = new AppiumServerManager();
            appiumServerManager.startServer();
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

            // Start a new test using ExtentTestManager
            ExtentTestManager.startTest(getClass().getSimpleName(), "Test Run On Platform: " + platform.toUpperCase());
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
        logger.info("Driver quit for platform: " + platform);

        if (extentReports != null) {
            extentReports.flush();
            logger.info("Extent Report flushed");
        }
    }

    @AfterAll
    public static void globalTearDown() {
        // Stop Appium Server after all tests
        AppiumServerManager appiumServerManager = new AppiumServerManager();
        appiumServerManager.stopServer();
        logger.info("Appium server stopped after all tests.");
    }
}