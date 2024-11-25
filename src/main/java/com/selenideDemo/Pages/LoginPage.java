package com.selenideDemo.Pages;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage {
    private final SelenideElement usernameField = $(AppiumBy.accessibilityId("test-Username"));
    private final SelenideElement passwordField = $(AppiumBy.accessibilityId("test-Password"));
    private final SelenideElement loginButton = $(AppiumBy.accessibilityId("test-LOGIN"));

    public void login(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password);
        loginButton.click();
    }
}
