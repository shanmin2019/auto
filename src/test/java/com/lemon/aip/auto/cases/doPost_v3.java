package com.lemon.aip.auto.cases;

import com.lemon.aip.auto.util.ExcelUtil;
import com.lemon.aip.auto.util.HttpUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class doPost_v3 {
    String url = "http://10.20.11.72:80/enesys-web/login/login";

    @Test(dataProvider = "datas")
    public void test(String loginAccount,String password){

        //处理数据，将数据变成Json格式
        Map<String,String> params = new HashMap<>();
        params.put("loginAccount",loginAccount);
        params.put("password",password);
        HttpUtil.doPost(url,params);
    }

    @DataProvider
    Object[][] datas(){
        Object[][] datas = ExcelUtil.datas("D:\\lemenJava_v1\\src\\test\\resources\\cases_v1.xlsx","用例");
    return datas;
    }

}
