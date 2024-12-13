package com.selenideDemo.Utils;

import com.selenideDemo.Pages.HomePage;
import com.selenideDemo.Pages.LoginPage;
import com.selenideDemo.Pages.SideMenu;
import com.selenideDemo.Pages.WebViewPage;
import org.openqa.selenium.WebDriver;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class DependencyInjector {
    private MutablePicoContainer container;

    public DependencyInjector(WebDriver driver) {
        container = new DefaultPicoContainer();
        container.addComponent(WebDriver.class, driver);
        container.addComponent(HomePage.class);
        container.addComponent(LoginPage.class);
        container.addComponent(SideMenu.class);
        container.addComponent(WebViewPage.class);
        // Add other page classes as needed
    }

    public <T> T getInstance(Class<T> type) {
        return container.getComponent(type);
    }
}
