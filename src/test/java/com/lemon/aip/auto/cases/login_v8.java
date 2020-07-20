package com.lemon.aip.auto.cases;

import com.lemon.aip.auto.util.CaseUtil;
import org.testng.annotations.DataProvider;

/**
 * 2020-06-28  执行完成后 在一次性写入
 */
public class login_v8 extends BaseProcessor {


    @DataProvider
    Object[][] datas(){
        Object[][] datas = CaseUtil.getCaseDatasByApiId("2",cellNames);
    return datas;
    }


}
