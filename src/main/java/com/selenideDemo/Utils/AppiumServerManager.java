package com.selenideDemo.Utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class AppiumServerManager {

    private AppiumDriverLocalService appiumDriverLocalService;
    private final String os = System.getProperty("os.name").toLowerCase();

    /**
     * Configures the Appium server to use any free port and set required arguments.
     */
    private void configureAppiumService() {
        appiumDriverLocalService = new AppiumServiceBuilder()
                .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
                .usingAnyFreePort()
                .withArgument(() -> "--allow-insecure","chromedriver_autodownload")
                .build();
    }

    /**
     * Starts the Appium server, ensuring no conflicting sessions are running.
     */
    public void startServer() {
        terminateExistingAppiumSessions();
        configureAppiumService();
        System.out.println("Starting Appium server at port: " + appiumDriverLocalService.getUrl().getPort());
        appiumDriverLocalService.start();
    }

    /**
     * Stops the Appium server if it is running.
     */
    public void stopServer() {
        if (appiumDriverLocalService != null && appiumDriverLocalService.isRunning()) {
            appiumDriverLocalService.stop();
            System.out.println("Appium server stopped successfully.");
        }
    }

    /**
     * Gets the URL of the running Appium server.
     *
     * @return URL of the Appium server.
     */
    public URL getServiceURL() {
        if (appiumDriverLocalService == null) {
            throw new IllegalStateException("Appium server is not running. Start the server before getting the URL.");
        }
        return appiumDriverLocalService.getUrl();
    }

    /**
     * Terminates any existing Appium server sessions to avoid conflicts.
     */
    private void terminateExistingAppiumSessions() {
        try {
            String command = getKillCommand();
            if (command.isEmpty()) {
                System.out.println("Unsupported operating system for killing Appium sessions.");
                return;
            }

            Process process = Runtime.getRuntime().exec(command);
            process.waitFor(); // Wait for the command to execute

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            System.out.println("Appium server sessions terminated successfully.");
        } catch (IOException | InterruptedException e) {
            System.err.println("Failed to terminate existing Appium sessions: " + e.getMessage());
        }
    }

    /**
     * Constructs the appropriate command to kill Appium server sessions based on the operating system.
     *
     * @return Command string to kill Appium sessions.
     */
    private String getKillCommand() {
        if (os.contains("mac") || os.contains("darwin") || os.contains("linux")) {
            return "pkill -f appium";
        } else if (os.contains("win")) {
            return "taskkill /F /IM node.exe";
        }
        return "";
    }
}