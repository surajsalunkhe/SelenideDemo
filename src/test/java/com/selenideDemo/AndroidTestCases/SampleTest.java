package com.selenideDemo.AndroidTestCases;

import com.aventstack.extentreports.Status;
import com.codeborne.selenide.appium.SelenideAppium;
import com.selenideDemo.BaseTest;
import com.selenideDemo.Pages.LoginPage;
import com.selenideDemo.Utils.PropertiesFileManager;
import org.junit.jupiter.api.Test;

public class SampleTest extends BaseTest {
    @Test
    public void launchAndroidAppTest() {
        extentTest = extentReports.createTest("Launch Android App Test");
        extentTest.log(Status.INFO, "Starting Android App Launch Test");
        SelenideAppium.launchApp();
        LoginPage loginPage=new LoginPage();
        loginPage.login("standard_user","secret_sauce");
    }
}
