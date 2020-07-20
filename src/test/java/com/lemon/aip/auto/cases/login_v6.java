package com.lemon.aip.auto.cases;

import com.alibaba.fastjson.JSONObject;
import com.lemon.aip.auto.util.CaseUtil;
import com.lemon.aip.auto.util.ExcelUtil;
import com.lemon.aip.auto.util.HttpUtil;
import com.lemon.aip.auto.util.RestUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * 2020-06-28  该用例的主要实现的是 将返回的数据批量回写到Excel里面,
 * 当前该方式写到Excel中会很慢，每执行一条用例就会返写一次导致，浪费I/O读写
 */
public class login_v6 {
    String excelPath = "D:\\lemenJava_v1\\src\\test\\resources\\cases_v5.xlsx";

    @Test(dataProvider = "datas")
    public void test(String caseId,String apiId,String paramters){

        String url = RestUtil.getUrlByApiId(apiId);
        String type = RestUtil.getTypeByApiId(apiId);
        //Map<String,String> params = (Map<String, String>) JSONObject.parse(paramters);
        Map<String,String> params = (Map<String, String>) JSONObject.parse(paramters);
        // System.out.println("【"+url+"】"+"【"+type+"】"+"【"+params+"】");
        String result = HttpUtil.doServer(url,type,params);
        //Assert.assertEquals(result,expectedResponseData);
        System.out.println("【"+url+"】"+"【"+type+"】"+"【"+params+"】");
        ExcelUtil.writeBackData(excelPath,"用例",caseId,"ActualResponseData",result);


    }

    @DataProvider
    Object[][] datas(){
        String [] cellNames = {"CaseId","ApiId","Params"};

        Object[][] datas = CaseUtil.getCaseDatasByApiId("2",cellNames);
    return datas;
    }


}
