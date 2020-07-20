package com.lemon.aip.auto.cases;

import com.lemon.aip.auto.util.HttpUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;


public class doPost_v2 {
    String url = "http://10.20.11.72:1050/enesys-web/login/login";

    @Test(dataProvider = "datas")
    public void test(String loginAccount,String password){

        //处理数据，将数据变成Json格式
        Map<String,String> params = new HashMap<>();
        params.put("loginAccount",loginAccount);
        params.put("password",password);
        HttpUtil.doPost(url,params);
    }

    @DataProvider
    public static Object[][] datas(){
        Object [][] datas = {
                {"admin","000000"},
                {"admin","000000"},
                {"admin","0000003"},
                {"admin","000000"},
                {"admin","000000"},
                {"admin","0000030"}
        };
        return datas;
    }


}
