package com.selenideDemo.Utils;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import com.codeborne.selenide.WebDriverRunner;

import java.util.Set;

import static com.codeborne.selenide.appium.SelenideAppium.$x;

public class AppiumUtils {

    private static AppiumDriver getAppiumDriver() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        if (driver instanceof AppiumDriver) {
            return (AppiumDriver) driver;
        }
        throw new IllegalStateException("Driver is not an instance of AppiumDriver. Ensure you are running on a mobile platform.");
    }

    public static void switchToContext(String contextName) {
        AppiumDriver appiumDriver = getAppiumDriver();

        // Use platform-specific drivers for getContextHandles
        Set<String> contexts;
        if (appiumDriver instanceof AndroidDriver) {
            contexts = ((AndroidDriver) appiumDriver).getContextHandles();
        } else if (appiumDriver instanceof IOSDriver) {
            contexts = ((IOSDriver) appiumDriver).getContextHandles();
        } else {
            throw new IllegalStateException("Unsupported driver type: " + appiumDriver.getClass().getName());
        }
        for (String context : contexts) {
            if (context.equalsIgnoreCase(contextName)) {
                ((io.appium.java_client.remote.SupportsContextSwitching) appiumDriver).context(context);
                System.out.println("Switched to context: " + contextName);
                return;
            }
        }
        throw new IllegalArgumentException("Context " + contextName + " not found. Available contexts: " + contexts);
    }

    public static void switchToNativeApp() {
        switchToContext("NATIVE_APP");
    }

    public static void switchToWebView() {
        switchToContext("WEBVIEW");
    }
    public static void clickOnElement(String locatorName){
        SelenideElement element = $x("//*[@content-desc='" + locatorName + "']");
        element.click();
    }
}