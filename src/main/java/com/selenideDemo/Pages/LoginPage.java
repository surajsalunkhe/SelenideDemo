package com.selenideDemo.Pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage {
    private WebDriver driver;
    private final SelenideElement usernameField = $(AppiumBy.accessibilityId("test-Username"));
    private final SelenideElement passwordField = $(AppiumBy.accessibilityId("test-Password"));
    private final SelenideElement loginButton = $(AppiumBy.accessibilityId("test-LOGIN"));

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        WebDriverRunner.setWebDriver(driver);
    }

    public void login(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password);
        loginButton.click();
        //return new HomePage(driver);
    }
    public LoginPage enterUsername(String username) {
        usernameField.should(appear).setValue(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField.should(appear).setValue(password);
        return this;
    }

    public HomePage clickLoginButton() {
        loginButton.should(appear).click();
        return new HomePage(driver);
    }
}
