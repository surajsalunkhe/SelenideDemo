package com.selenideDemo.Utils;

import com.selenideDemo.report_manager.ExtentTestManager;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class ScreenshotOnFailureExtension implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        String screenshotPath = ScreenshotUtils.captureScreenshot(testName);
        if (screenshotPath != null) {
            ExtentTestManager.getTest().fail("Test Failed: " + testName)
                    .addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        ExtentTestManager.getTest().pass("Test Passed: " + context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        ExtentTestManager.getTest().skip("Test Skipped: " + context.getDisplayName());
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        ExtentTestManager.getTest().skip("Test Disabled: " + context.getDisplayName());
    }
}
