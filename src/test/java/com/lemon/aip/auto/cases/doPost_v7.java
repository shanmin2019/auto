package com.lemon.aip.auto.cases;

import com.alibaba.fastjson.JSONObject;
import com.lemon.aip.auto.util.ExcelUtil;
import com.lemon.aip.auto.util.HttpUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;


public class doPost_v7 {

    @Test(dataProvider = "datas")
    public void test(String apiFromCase,String paramters){

        //处理数据，将数据变成Json格式
        String excelPath = "D:\\lemenJava_v1\\src\\test\\resources\\cases_v3.xlsx";
        int[] rows = {1,2};
        int[] cells = {1,3,4};
        Object[][] datas= ExcelUtil.datas(excelPath,"接口信息",rows,cells);
        //数据已经取出来来，现在需要拿出来用
        String url = "";
        String type = "";
        for (Object[] objects : datas) {
            //现在是要取第一列与”用例“中的值进行比对
            String apiFromRest = objects[0].toString();
            if(apiFromRest.equalsIgnoreCase(apiFromCase)){
                type = objects[1].toString();
                url = objects[2].toString();
                break;
            }
        }

        //Map<String,String> params = (Map<String, String>) JSONObject.parse(paramters);
        Map<String,String> params = (Map<String, String>) JSONObject.parse(paramters);
        HttpUtil.doServer(url,type,params);

    }

    @DataProvider
    Object[][] datas(){
        int rows[] = {2,3,4,5,6};
        int cells[] = {3,4};
        String excelPath = "D:\\lemenJava_v1\\src\\test\\resources\\cases_v3.xlsx";
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
