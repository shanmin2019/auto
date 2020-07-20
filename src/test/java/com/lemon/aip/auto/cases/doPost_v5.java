package com.lemon.aip.auto.cases;

import com.lemon.aip.auto.util.ExcelUtil;
import com.lemon.aip.auto.util.HttpUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class doPost_v5 {
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
        int rows[] = {7,8,9,10,11};
        int cells[] = {6,7};
        String excelPath = "D:\\lemenJava_v1\\src\\test\\resources\\cases_v1.xlsx";
        Object[][] datas = ExcelUtil.datas(excelPath,"用例",rows,cells);
    return datas;
    }

}
