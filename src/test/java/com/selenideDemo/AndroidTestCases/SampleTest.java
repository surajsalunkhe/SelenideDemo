package com.selenideDemo.AndroidTestCases;

import com.aventstack.extentreports.Status;
import com.codeborne.selenide.appium.SelenideAppium;
import com.selenideDemo.BaseTest;
import com.selenideDemo.Utils.PropertiesFileManager;
import org.junit.jupiter.api.Test;

public class SampleTest extends BaseTest {
    @Test
    public void launchAndroidAppTest() {
        extentTest = extentReports.createTest("Launch Android App Test");
        extentTest.log(Status.INFO, "Starting Android App Launch Test");
        String osDetails=System.getProperty("os.name");
        System.out.println(osDetails);
        // Launch the app (implicitly done by BaseTest setup)
        SelenideAppium.launchApp();
    }
}
