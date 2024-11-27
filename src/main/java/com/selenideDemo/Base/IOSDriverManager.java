package com.selenideDemo.Base;

import com.selenideDemo.Utils.PropertiesFileManager;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

import static com.selenideDemo.Utils.Constants.*;

public class IOSDriverManager {
    private static final Logger logger = LoggerFactory.getLogger(IOSDriverManager.class);
    private static IOSDriver driver;

    public static IOSDriver createDriver() {
        if (driver == null) {
            try {
                XCUITestOptions options = getXCUITestOptions();
                options.setApp(PropertiesFileManager.getPropertyValue("IOS_APP_NAME"));
                driver = new IOSDriver(new URL("http://" + HOSTNAME + ":" + PORT_FOR_IOS),options);
                logger.info("iOS Driver initialized successfully.");
            } catch (MalformedURLException e) {
                logger.error("Invalid Appium server URL", e);
                throw new RuntimeException(e);
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
        }
    }
}
