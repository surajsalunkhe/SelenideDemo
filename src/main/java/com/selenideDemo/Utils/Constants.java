package com.selenideDemo.Utils;


import static com.selenideDemo.Base.Base.getEnvironmentName;

public class Constants {
	public static final String OUTPUT_EXCEL_PATH=System.getProperty("user.dir")+"/src/test/resources/output.xlsx";
	public static final String TEST_RESOURCE_PATH="./src/test/resources/environment/";
	public static final String EMPTY="";
	public static final String REPORT_PROPERTIES="./src/test/resources/Extent.properties";
	public static final String SCREENSHOTS="/screenshots/";
	public static final String PORT_FOR_ANDROID = "4723";
	public static final String PORT_FOR_IOS = "4724";
	public static final String HOSTNAME = "127.0.0.1";
	public static final String APP_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\environment\\"+getEnvironmentName()+"\\app\\";
}
