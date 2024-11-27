package com.selenideDemo.AndroidTestCases;

import com.aventstack.extentreports.Status;
import com.codeborne.selenide.appium.SelenideAppium;
import com.selenideDemo.BaseTest;
import com.selenideDemo.Pages.LoginPage;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {
    @Test
    public void launchAndroidAppTest() {
        extentTest = extentReports.createTest("Launch Android App Test");
        extentTest.log(Status.INFO, "Starting Android App Launch Test");
        System.out.println("Test 1 .... first start");
        SelenideAppium.launchApp();
        LoginPage loginPage=new LoginPage();
        loginPage.login("standard_user","secret_sauce");
        extentTest.log(Status.PASS,"Test 1 .... Passed");
    }
    /*@Test
    public void myAndroidAppTest() {
        extentTest = extentReports.createTest("Test 2");
        extentTest.log(Status.INFO, "Starting test 2");
        System.out.println("Test 2 .... second");
    }*/
}
