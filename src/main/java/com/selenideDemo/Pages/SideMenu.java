package com.selenideDemo.Pages;

import com.aventstack.extentreports.Status;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.conditions.Visible;
import com.selenideDemo.report_manager.ExtentTestManager;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.selenideDemo.report_manager.ExtentTestManager.getTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SideMenu {
    private WebDriver driver;
    private final SelenideElement allItems = $x("//*[@content-desc='test-ALL ITEMS']");
    private final SelenideElement webView = $x("//*[@content-desc='test-WEBVIEW']");
    private final SelenideElement qrCodeScanner = $x("//*[@content-desc='test-QR CODE SCANNER']");
    private final SelenideElement geoLocation = $x("//*[@content-desc='test-GEO LOCATION']");
    private final SelenideElement drawing = $x("//*[@content-desc='test-DRAWING']");
    private final SelenideElement logout = $x("//*[@content-desc='test-LOGOUT']");
    private final SelenideElement closeButton= $x("//*[@content-desc='test-Close']");
    public SideMenu(WebDriver driver) {
        this.driver = driver;
        WebDriverRunner.setWebDriver(driver);
    }
    public HomePage clickOnAllItemsLink(){
        assertTrue(allItems.isDisplayed(),"Home page is not displayed");
        allItems.click();
        ExtentTestManager.getTest().log(Status.PASS, "Clicked on All Item link is displayed");
        return new HomePage(driver);
    }

    public WebViewPage clickOnWebViewLink(){
        webView.shouldBe(interactable, Duration.ofSeconds(5));
        assertTrue(webView.isDisplayed(),"Side Menu Web View link is not displayed");
        webView.click();
        ExtentTestManager.getTest().log(Status.PASS, "Clicked on All Item link is displayed");
        return new WebViewPage(driver);
    }

}
