package com.lemon.aip.auto.cases;

import com.alibaba.fastjson.JSONObject;
import com.lemon.aip.auto.pojo.WriteBackData;
import com.lemon.aip.auto.util.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.Map;

public class BaseProcessor {
    public Logger logger =Logger.getLogger(BaseProcessor.class);
    public String [] cellNames = {"CaseId","ApiId","Params","ExpectedResponseData","PreValidateSql","AfterValidateSql"};

    @Test(dataProvider = "datas")
    public void test(String caseId,String apiId,String paramters,String expectedResponseData,
                     String preValidateSql,String afterValidateSql){
        logger.info("开始调用接口前的数据验证");
        //接口调用前，如果是没有SQL语句那么就不需要执行SQL查询了
        if(preValidateSql!=null &&  preValidateSql.trim().length()>0){
            preValidateSql = VariableUtil.replaceVariables(preValidateSql);
            String preValidateResult = DBCheckUtil.doQuery(preValidateSql);
            WriteBackData writeBackData = new WriteBackData("用例", caseId, "PreValidateResult", preValidateResult);
            ExcelUtil.writeBackDatas.add(writeBackData);
        }

        logger.info("根据接口编【"+apiId+"】号获取接口请求地址");
        String url = RestUtil.getUrlByApiId(apiId);
        logger.info("根据接口编号获取接口请求类型");
        String type = RestUtil.getTypeByApiId(apiId);
        //需要处理这个变量，替换掉用例里面的变量
        logger.info("替换变量");
        paramters=VariableUtil.replaceVariables(paramters);
        //Map<String,String> params = (Map<String, String>) JSONObject.parse(paramters);
        Map<String,String> params = (Map<String, String>) JSONObject.parse(paramters);
        //System.out.println("【"+url+"】"+"【"+type+"】"+"【"+params+"】");
        String actualResponseData = HttpUtil.doServer(url,type,params);
        actualResponseData=AssertUtil.assertEquals(actualResponseData,expectedResponseData);
        //Assert.assertEquals(result,expectedResponseData);
        System.out.println("【"+url+"】"+"【"+type+"】"+"【"+params+"】");
        //ExcelUtil.writeBackData(excelPath,"用例",caseId,"ActualResponseData",result);



        WriteBackData writeBackData = new WriteBackData("用例", caseId, "ActualResponseData", actualResponseData);
        //保存数据会写对象(思考：什么时候写数据-->所有用例都执行完成后)
        ExcelUtil.writeBackDatas.add(writeBackData);



        //如果接口调用后不需要查询SQL那么也就不需要进行查询验证了
        if (afterValidateSql!=null && afterValidateSql.trim().length()>0){
            afterValidateSql = VariableUtil.replaceVariables(afterValidateSql);
            String afterValidateResult = DBCheckUtil.doQuery(afterValidateSql);
            writeBackData = new WriteBackData("用例", caseId, "AfterValidateResult", afterValidateResult);
            ExcelUtil.writeBackDatas.add(writeBackData);
        }

    }

    @AfterSuite
    public void batchWriteBackDatas(){
        ExcelUtil.batchWriteBackDatas(PropertiesUtil.getExcelPath());
    }
}
