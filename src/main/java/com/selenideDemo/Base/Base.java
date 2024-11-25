package com.selenideDemo.Base;

public class Base {
    public static String getEnvironmentName(){
        return System.getProperty("env","QA");
    }
    public static String getBrowserName(){
        String browserName=System.getProperty("browser");
        if(browserName==null || browserName.isEmpty()){
            browserName="edge";
        }
        return browserName;
    }
    public static String getOSName(){
        String osDetails=System.getProperty("os.name");
        return osDetails;
    }

}
