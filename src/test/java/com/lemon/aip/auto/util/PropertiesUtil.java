package com.lemon.aip.auto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties properties = new Properties();
    static {
        try{
            InputStream inputStream = new FileInputStream(new File("D:\\lemenJava_v1\\src\\test\\resources\\config.properties"));
            properties.load(inputStream);

        }catch (Exception e){}

    }

    public static String getExcelPath(){

        return properties.getProperty("excel.path");
    }
}
