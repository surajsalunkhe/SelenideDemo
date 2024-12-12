package com.selenideDemo.report_manager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extentReports;

    public synchronized static ExtentReports createExtentReports() {
        if (extentReports == null) {
            String osDetails = System.getProperty("os.name") + "\t" + System.getProperty("os.version");
            System.out.println("OS Details: " + osDetails);

            // Ensure the directory exists
            String reportPath = "target/extent-reports/extent-report.html";
            File reportDir = new File(reportPath).getParentFile();
            if (!reportDir.exists() && !reportDir.mkdirs()) {
                throw new RuntimeException("Failed to create report directory: " + reportDir.getAbsolutePath());
            }

            // Configure ExtentReports
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("Sauce Labs Application Test Automation Report");
            reporter.config().setTheme(Theme.DARK);

            extentReports = new ExtentReports();
            extentReports.attachReporter(reporter);
            extentReports.setSystemInfo("System OS", osDetails);
            extentReports.setSystemInfo("Author", "Suraj Salunkhe");
        }
        return extentReports;
    }
}