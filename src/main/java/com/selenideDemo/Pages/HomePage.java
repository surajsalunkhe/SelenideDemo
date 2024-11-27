package com.selenideDemo.Pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"PRODUCTS\"]")
    private WebElement productsLabel;
    @AndroidFindBy(accessibility = "test-Modal Selector Button")
    private WebElement filterIcon;
    private String elementXpath = "//android.view.ViewGroup[@content-desc=\"test-<ELEMENT_NAME>\"]";
    private String filterOptionXpath = "//android.widget.TextView[@text=\"<OPTION_NAME>\"]";
    private String selectProductXpath = "//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"<PRODUCT_NAME>\"]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyHomePageIsDisplayed() {
        waitFor.until(ExpectedConditions.visibilityOf(productsLabel));
        Assert.assertTrue("Home page is not displayed", productsLabel.isDisplayed());
        getTest().log(Status.PASS, "Home page is displayed");
        System.out.println("Home page is displayed");
    }

    public void clickElement(String elementName) {
        waitFor.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(elementXpath.replaceAll("<ELEMENT_NAME>", elementName))));
        getDriver().findElement(AppiumBy.xpath(elementXpath.replaceAll("<ELEMENT_NAME>", elementName))).click();
        getTest().log(Status.PASS, elementName + " button is clicked");
        System.out.println(elementName + " button is clicked");
    }

    /**
     * This method is used to click filter icon on homepage
     */
    public void clickFilterIcon() {
        waitFor.until(ExpectedConditions.visibilityOf(filterIcon));
        filterIcon.click();
        getTest().log(Status.PASS, "Filter icon is clicked");
        System.out.println("Filter icon is clicked");
    }

    /**
     * This method is used click a specific filter option
     * @param optionName    name of filter option to be selected
     */
    public void clickFilterOption(String optionName) {
        waitFor.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(filterOptionXpath.replaceAll("<OPTION_NAME>", optionName))));
        getDriver().findElement(AppiumBy.xpath(filterOptionXpath.replaceAll("<OPTION_NAME>", optionName))).click();
        getTest().log(Status.PASS, optionName + " filter option is clicked");
        System.out.println(optionName + " filter option is clicked");
    }

    /**
     * This method is used to select a specific product
     * @param productName   name of product to be selected
     */
    public void selectProduct(String productName) {
        waitFor.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(selectProductXpath.replaceAll("<PRODUCT_NAME>", productName))));
        getDriver().findElement(AppiumBy.xpath(selectProductXpath.replaceAll("<PRODUCT_NAME>", productName))).click();
        getTest().log(Status.PASS, productName + " product is selected");
        System.out.println(productName + " product is selected");
    }
}
