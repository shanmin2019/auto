package com.lemon.aip.auto.cases;

import com.lemon.aip.auto.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class doGet_02 {
    public static void main(String[] args) {
        //请求路径
        String url = "http://10.20.11.72/enesys-web/js/demo/metaTamplateTestData.json";
        //请求参数
        String staffId = "202004211313203950001598321269";
        Map<String, String> params = new HashMap<>();
        params.put("staffId", staffId);
        HttpUtil.doGet(url, params);
    }
}
