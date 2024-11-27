package com.selenideDemo.Utils;

import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class SwitchContextUtil {
    private static final Logger logger = LoggerFactory.getLogger(SwitchContextUtil.class);
    public static void changeDriverContext(WebDriver driver, String contextType, String platform) {
        Set<String> contextNames;

        if (driver instanceof AndroidDriver androidDriver) {
            contextNames = androidDriver.getContextHandles();
            for (String contextName : contextNames) {
                if (contextName.contains(contextType)) {
                    androidDriver.context(contextName);
                    logger.info("Changed 'android' driver context to {}", contextType);
                    return;
                }
            }
        } else if (driver instanceof IOSDriver) {
            IOSDriver iosDriver = (IOSDriver) driver;
            contextNames = iosDriver.getContextHandles();
            for (String contextName : contextNames) {
                if (contextName.contains(contextType)) {
                    iosDriver.context(contextName);
                    logger.info("Changed 'ios' driver context to {}", contextType);
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("Unsupported driver type: " + driver.getClass().getSimpleName());
        }

        throw new IllegalStateException("Failed to change driver context to " + contextType);
    }
}