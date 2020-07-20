package com.lemon.aip.auto.util;

import org.testng.Assert;

public class AssertUtil {
    public static  String assertEquals(String actualResponseData,String expectedResponseData) {
        String result = "通过";
        try{
            Assert.assertEquals(actualResponseData,expectedResponseData);
        }catch (Throwable e){
            result = actualResponseData;
        }
        return result;
    }
}
