package com.selenideDemo.Base;

import com.selenideDemo.Utils.AppiumServerManager;
import com.selenideDemo.Utils.PropertiesFileManager;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URL;

import static com.selenideDemo.Utils.Constants.*;

public class IOSDriverManager {
    private static final Logger logger = LoggerFactory.getLogger(IOSDriverManager.class);
    private static IOSDriver driver;
    private static AppiumServerManager appiumServerManager = new AppiumServerManager(); // Create an instance of AppiumServerManager

    public static IOSDriver createDriver() {
        if (driver == null) {
            try {
                // Start the Appium server and get the URL dynamically
                appiumServerManager.startServer();
                URL appiumServerURL = appiumServerManager.getServiceURL(); // Get the URL from AppiumServerManager

                XCUITestOptions options = getXCUITestOptions();
                options.setApp(PropertiesFileManager.getPropertyValue("IOS_APP_NAME"));

                // Use the dynamic URL for the IOSDriver
                driver = new IOSDriver(appiumServerURL, options);
                logger.info("iOS Driver initialized successfully.");
            } catch (Exception e) {
                logger.error("Error initializing iOS driver", e);
                throw new RuntimeException(e);
            }
        }
        return driver;
    }

    private static XCUITestOptions getXCUITestOptions(){
        XCUITestOptions options = new XCUITestOptions();
        options.setAutomationName(AutomationName.IOS_XCUI_TEST);
        options.setPlatformName("iOS");
        options.setDeviceName("iPhone Simulator");
        options.setCapability("udid", PropertiesFileManager.getPropertyValue("IOS_DEVICE_ID"));
        options.setCapability("wdaLaunchTimeout", 60000);
        options.setCapability("useNewWDA", false);
        options.setCapability("autoAcceptAlerts", true);
        options.setCapability("simulatorStartupTimeout", 180000);
        options.setCapability("autoGrantPermissions", true);
        return options;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("iOS Driver quit successfully.");
            appiumServerManager.stopServer(); // Stop the Appium server when quitting the driver
        }
    }
}