package com.selenideDemo.Utils;

import com.codeborne.selenide.Screenshots;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
public class ScreenshotUtils {
    public static String captureScreenshot(String testName) {
        File screenshot = Screenshots.takeScreenShotAsFile();
        if (screenshot != null) {
            String screenshotPath = "target/screenshots/" + testName + ".png";
            try {
                FileUtils.copyFile(screenshot, new File(screenshotPath));
                return screenshotPath;
            } catch (IOException e) {
                throw new RuntimeException("Failed to save screenshot to " + screenshotPath, e);
            }
        }
        return null;
    }
}
