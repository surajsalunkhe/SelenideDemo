package com.selenideDemo.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.selenideDemo.Base.Base.getEnvironmentName;
import static com.selenideDemo.Utils.Constants.TEST_RESOURCE_PATH;


public class PropertiesFileManager {
    private static final String ENVIRONMENT_PROPERTIES_FILE = "/config.properties";
    private static final String ERROR_CANNOT_LOAD_FILE = "Cannot load properties file: ";

    private static String environment;

    public static Properties loadPropertiesFile(final String propFileName) {
        try (InputStream input = new FileInputStream(propFileName)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (IOException ex) {
            logError(ERROR_CANNOT_LOAD_FILE + propFileName, ex);
            return new Properties(); // Return empty properties to avoid null checks
        }
    }

    public static String getPropertyValue(String key) {
        String filePath = TEST_RESOURCE_PATH + getEnvironmentName() + ENVIRONMENT_PROPERTIES_FILE;
        String value = getPropertyValueFromFile(key, filePath);

        if (value == null || value.isEmpty()) {
            value = searchKeyInAllFiles(key, getAllFiles(TEST_RESOURCE_PATH + environment));
        }

        return value;
    }

    private static String getPropertyValueFromFile(String key, String filePath) {
        Properties prop = loadPropertiesFile(filePath);
        return prop.getProperty(key);
    }

    private static List<String> getAllFiles(String directoryPath) {
        List<String> results = new ArrayList<>();
        File[] files = new File(directoryPath).listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    results.add(file.getPath());
                }
            }
        }
        return results;
    }

    private static String searchKeyInAllFiles(String key, List<String> files) {
        for (String filePath : files) {
            Properties prop = loadPropertiesFile(filePath);
            String value = prop.getProperty(key);
            if (value != null && !value.isEmpty()) {
                return value;
            }
        }
        return null; // Return null if the key is not found in any file
    }

    public static void writeToProperties(String key, String value, String completeFileName) {
        Properties prop = loadPropertiesFile(completeFileName);

        try (FileOutputStream out = new FileOutputStream(completeFileName)) {
            prop.setProperty(key, value);
            prop.store(out, null);
            logInfo("Setting key <" + key + "> and value <" + value + ">");
        } catch (IOException e) {
            logError("Error writing to properties file: " + completeFileName, e);
        }
    }

    private static void logError(String message, Exception ex) {
        System.err.println(message);
        ex.printStackTrace();  // Replace with a logging framework in production
    }

    private static void logInfo(String message) {
        System.out.println(message);  // Replace with a logging framework in production
    }
}
