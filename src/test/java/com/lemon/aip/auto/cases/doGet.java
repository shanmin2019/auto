package com.lemon.aip.auto.cases;

import com.lemon.aip.auto.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class doGet {
    public static void main(String[] args) {
        //请求路径
        String url = "http://apis.juhe.cn/mobile/get";
        //请求参数
        String phone = "1781570";
        String key = "4f5049ef569f8c66ef18fb4c323985f6";
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("key", key);
        HttpUtil.doGet(url, params);
    }
}
