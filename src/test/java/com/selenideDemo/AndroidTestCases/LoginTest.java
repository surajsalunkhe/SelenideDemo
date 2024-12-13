package com.selenideDemo.AndroidTestCases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.codeborne.selenide.appium.SelenideAppium;
import com.selenideDemo.BaseTest;
import com.selenideDemo.Pages.HomePage;
import com.selenideDemo.Pages.LoginPage;
import com.selenideDemo.Pages.SideMenu;
import com.selenideDemo.Pages.WebViewPage;
import com.selenideDemo.report_manager.ExtentTestManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.selenideDemo.Base.AndroidDriverManager.getDriver;

public class LoginTest extends BaseTest {
    /*@Test
    public void launchAndroidAppTest() {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Android App Launch Test");
        SelenideAppium.launchApp();
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = loginPage.enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();
        //loginPage.login("standard_user", "secret_sauce");
        homePage.verifyHomePageIsDisplayed();
        SideMenu sideMenu=homePage.clickOnMenuIcon();
        WebViewPage webViewPage=sideMenu.clickOnWebViewLink();
        webViewPage.webViewPageTitle();
        ExtentTestManager.getTest().log(Status.PASS, "Home page is displayed");
    }*/
    /*@Test
    public void testLogin() {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Android App Launch Test");
        LoginPage loginPage = injector.getInstance(LoginPage.class);
        loginPage.login("standard_user", "secret_sauce");
        ExtentTestManager.getTest().log(Status.PASS, "Home page is displayed");
    }*/

    @Test
    public void launchAndroidAppTest() {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Android App Launch Test");
        SelenideAppium.launchApp();
        // Fetch page instances from the injector
        LoginPage loginPage = injector.getInstance(LoginPage.class);
        HomePage homePage = injector.getInstance(HomePage.class);
        SideMenu sideMenu = injector.getInstance(SideMenu.class);
        WebViewPage webViewPage = injector.getInstance(WebViewPage.class);
        homePage = loginPage.enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();
        homePage.verifyHomePageIsDisplayed();
        sideMenu = homePage.clickOnMenuIcon();
        webViewPage = sideMenu.clickOnWebViewLink();
        webViewPage.webViewPageTitle();
        ExtentTestManager.getTest().log(Status.PASS, "Home page is displayed");
    }
}
