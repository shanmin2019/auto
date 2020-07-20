package com.lemon.aip.auto.cases;

import com.alibaba.fastjson.JSONObject;
import com.lemon.aip.auto.util.CaseUtil;
import com.lemon.aip.auto.util.HttpUtil;
import com.lemon.aip.auto.util.RestUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;


public class login_v4 {
   // String excelPath = "D:\\lemenJava_v1\\src\\test\\resources\\cases_v3.xlsx";

    @Test(dataProvider = "datas")
    public void test(String apiId,String paramters){

        String url = RestUtil.getUrlByApiId(apiId);
        String type = RestUtil.getTypeByApiId(apiId);
        //Map<String,String> params = (Map<String, String>) JSONObject.parse(paramters);
        Map<String,String> params = (Map<String, String>) JSONObject.parse(paramters);
       // System.out.println("【"+url+"】"+"【"+type+"】"+"【"+params+"】");
        HttpUtil.doServer(url,type,params);

    }

    @DataProvider
    Object[][] datas(){
        String [] cellNames = {"ApiId","Params"};

        Object[][] datas = CaseUtil.getCaseDatasByApiId("1",cellNames);
    return datas;
    }


}
