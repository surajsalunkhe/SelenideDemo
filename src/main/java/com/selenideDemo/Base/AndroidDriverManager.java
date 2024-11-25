package com.selenideDemo.Base;

import com.selenideDemo.Utils.PropertiesFileManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.MalformedURLException;
import java.net.URL;

import static com.selenideDemo.Utils.Constants.*;

public class AndroidDriverManager {

    private static final Logger logger = LoggerFactory.getLogger(AndroidDriverManager.class);
    private static AndroidDriver driver;

    // Method to create the AndroidDriver
    public static AndroidDriver createDriver() {
        if (driver == null) {
            try {
                UiAutomator2Options options = getUIAutomator2Options();
                options.setApp(APP_PATH+PropertiesFileManager.getPropertyValue("ANDROID_APP_NAME"));
                driver = new AndroidDriver(new URL("http://" + HOSTNAME + ":" + PORT_FOR_ANDROID),options);
                logger.info("Android Driver initialized successfully.");
            } catch (MalformedURLException e) {
                logger.error("Invalid Appium server URL", e);
                throw new RuntimeException(e);
            } catch (Exception e) {
                logger.error("Error initializing Android driver", e);
                throw new RuntimeException(e);
            }
        }
        return driver;
    }

    // Method to get the AndroidDriver instance
    public static AndroidDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Call createDriver() first.");
        }
        return driver;
    }
    private static UiAutomator2Options getUIAutomator2Options(){
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName("Android");
        options.setDeviceName(PropertiesFileManager.getPropertyValue("ANDROID_EMULATOR"));
        options.setNoReset(true);
        options.setFullReset(false);
        options.setCapability("uiautomator2ServerInstallTimeout", 60000);
        options.setCapability("adbExecTimeout", 30000);
        options.setCapability("autoGrantPermissions", true);
        return options;
    }
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Android Driver quit successfully.");
        }
    }
}
