package com.lemon.aip.auto.cases;

import com.lemon.aip.auto.util.CaseUtil;
import org.testng.annotations.DataProvider;

/**
 * 2020-06-28  执行完成后 在一次性写入
 */
public class info_该处乱码请解决 extends BaseProcessor {


    @DataProvider
    Object[][] datas(){
        String [] cellNames = {"CaseId","ApiId","Params"};

        Object[][] datas = CaseUtil.getCaseDatasByApiId("1",cellNames);
    return datas;
    }


}
