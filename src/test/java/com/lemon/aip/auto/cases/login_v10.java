package com.lemon.aip.auto.cases;

import com.lemon.aip.auto.util.CaseUtil;
import org.testng.annotations.DataProvider;

/**
 * 2020-06-28  执行完成后 在一次性写入
 */
public class login_v10 extends BaseProcessor {


    @DataProvider
    Object[][] datas(){
        String [] cellNames = {"CaseId","ApiId","Params","ExpectedResponseData"};

        Object[][] datas = CaseUtil.getCaseDatasByApiId("4",cellNames);
    return datas;
    }


}
