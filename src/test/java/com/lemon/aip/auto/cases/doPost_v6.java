package com.lemon.aip.auto.cases;

import com.alibaba.fastjson.JSONObject;
import com.lemon.aip.auto.util.ExcelUtil;
import com.lemon.aip.auto.util.HttpUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;


public class doPost_v6 {

    @Test(dataProvider = "datas")
    public void test(String paramters){

        //处理数据，将数据变成Json格式
        String url = "http://10.20.11.72:80/enesys-web/login/login";

        //Map<String,String> params = (Map<String, String>) JSONObject.parse(paramters);
        Map<String,String> params = (Map<String, String>) JSONObject.parse(paramters);

        HttpUtil.doPost(url,params);;
    }

    @DataProvider
    Object[][] datas(){
        int rows[] = {2,3,4,5,6};
        int cells[] = {6};
        String excelPath = "D:\\lemenJava_v1\\src\\test\\resources\\cases_v2.xlsx";
        Object[][] datas = ExcelUtil.datas(excelPath,"用例",rows,cells);
    return datas;
    }

   /*
   *
   *
   * public static void main(String[] args) {
        String parameters = "{\"loginAccount\":\"admin\",\"password\":\"000000\"}";
        Map<String,String> parse = (Map<String, String>) JSONObject.parse(parameters);
        Set<String> keys = parse.keySet();
        for (String key : keys) {
            System.out.println("key="+key+",value="+parse.get(key));
        }
    }*/

}
