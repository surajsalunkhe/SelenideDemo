package com.selenideDemo.Pages;

import com.aventstack.extentreports.Status;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.selenideDemo.report_manager.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.appium.AppiumClickOptions.tap;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePage {
    private WebDriver driver;
    private SelenideElement productsLabel = $x("//android.widget.TextView[@text='PRODUCTS']");
    private SelenideElement filterIcon = $x("//*[@content-desc='test-Modal Selector Button']");
    private SelenideElement menuIcon = $x("//*[@content-desc='test-Menu']");


    private String elementXpath = "//android.view.ViewGroup[@content-desc='test-<ELEMENT_NAME>']";
    private String filterOptionXpath = "//android.widget.TextView[@text='<OPTION_NAME>']";
    private String selectProductXpath = "//android.widget.TextView[@content-desc='test-Item title' and @text='<PRODUCT_NAME>']";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        WebDriverRunner.setWebDriver(driver);
    }


    public void verifyHomePageIsDisplayed() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(productsLabel.isDisplayed(),"Home page is not displayed");
        ExtentTestManager.getTest().log(Status.PASS, "Home page is displayed");
    }

    public void clickOnFilterIcon(){
        filterIcon.shouldBe(visible);
        filterIcon.click();
    }
    public SideMenu clickOnMenuIcon(){
        assertTrue(menuIcon.isDisplayed(),"Menu Icon is not displayed");
        menuIcon.click();
        ExtentTestManager.getTest().log(Status.INFO, "Menu Icon is displayed");
        return new SideMenu(driver);
    }
}
