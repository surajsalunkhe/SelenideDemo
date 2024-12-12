package com.selenideDemo.Utils;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.List;
import static com.selenideDemo.Base.AndroidDriverManager.getDriver;
import static com.selenideDemo.report_manager.ExtentTestManager.getTest;


public class ElementUtil {

    public static void scroll(WebElement element, String dir, double scrollRatio) {
        Point midPoint;
        Dimension size;

        if (scrollRatio < 0 || scrollRatio > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }

        if (element != null) {
            midPoint = getCenter(element);
        } else { // entire screen is scrollable
            size = getDriver().manage().window().getSize();
            midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
        }

        int bottom = midPoint.y + (int) (midPoint.y * scrollRatio);
        int top = midPoint.y - (int) (midPoint.y * scrollRatio);
        int left = midPoint.x - (int) (midPoint.x * scrollRatio);
        int right = midPoint.x + (int) (midPoint.x * scrollRatio);

        if (dir.equalsIgnoreCase("up")) {
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), Duration.ofMillis(1000));
        } else if (dir.equalsIgnoreCase("down")) {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), Duration.ofMillis(1000));
        } else if (dir.equalsIgnoreCase("left")) {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), Duration.ofMillis(1000));
        } else {
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), Duration.ofMillis(1000));
        }
        getTest().log(Status.PASS, "Scrolled in '" + dir + "' direction");
    }

    protected static void swipe(Point start, Point end, Duration duration) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        ((AppiumDriver) getDriver()).perform(List.of(swipe));
        getTest().log(Status.PASS, "Swiped from point " + start + " to point " + end);
    }

    private static Point getCenter(WebElement el) {
        Point location = el.getLocation();
        Dimension size = el.getSize();
        return new Point(location.x + size.getWidth() / 2, location.y + size.getHeight() / 2);
    }

    public static void scrollInWebViewUntilElementIsVisible(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].scrollIntoView();", element);
        getTest().log(Status.PASS, "Scrolled in web view to specific element");
    }

    public static void scrollInWebView(String direction) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        switch (direction.toLowerCase()) {
            case "down":
                executor.executeScript("window.scrollBy(0,450)", "");
                break;
            case "up":
                executor.executeScript("window.scrollBy(0,-450)", "");
                break;
        }
        getTest().log(Status.PASS, "Scrolled in web view in '" + direction + "' direction");
    }
}
