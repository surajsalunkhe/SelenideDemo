package com.selenideDemo.Pages;

import com.aventstack.extentreports.Status;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$x;
import static com.selenideDemo.report_manager.ExtentTestManager.getTest;

public class WebViewPage {
    private WebDriver driver;
    private SelenideElement webViewPageTitle=$x("//*[@text='WEBVIEW SELECTION']");
    public WebViewPage(WebDriver driver) {
        this.driver = driver;
        WebDriverRunner.setWebDriver(driver);
    }
    public void webViewPageTitle(){
        webViewPageTitle.shouldBe(interactable, Duration.ofSeconds(5));
        Assertions.assertTrue(webViewPageTitle.isDisplayed(),"WebView Selection text is not displayed");
        getTest().log(Status.INFO, "WebView Selection text is not displayed");
    }

}
