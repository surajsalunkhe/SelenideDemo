package com.selenideDemo.AndroidTestCases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.codeborne.selenide.appium.SelenideAppium;
import com.selenideDemo.BaseTest;
import com.selenideDemo.report_manager.ExtentTestManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Android App Launch Test");
        loginPage.login("standard_user", "secret_sauce");
        ExtentTestManager.getTest().log(Status.PASS, "Home page is displayed");
    }

    @Test
    public void launchAndroidAppTest() {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Android App Launch Test");
        SelenideAppium.launchApp();
        loginPage.enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();
        homePage.verifyHomePageIsDisplayed();
        homePage.clickOnMenuIcon();
        sideMenu.clickOnWebViewLink();
        webViewPage.webViewPageTitle();
        ExtentTestManager.getTest().log(Status.PASS, "Home page is displayed");
    }
}
