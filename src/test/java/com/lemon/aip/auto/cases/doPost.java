package com.lemon.aip.auto.cases;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;


public class doPost {
    public static void main(String[] args) {
        //请求路径
        String url = "http://120.78.128.25:8765/Frontend/Index/login";
        //准备数据
        String phone="17815702160";
        String password="a83597410";
        String remember_me = "1";
        //处理数据，将数据变成Json格式
        Map<String,String> params = new HashMap<>();
        params.put("phone",phone);
        params.put("password",password);
        params.put("remember_me",remember_me);
        testPost(url,params);
    }

    private static String testPost(String url, Map<String, String> params) {
        //创建一个请求方式
        HttpPost post = new HttpPost(url);
        //将数据放入到集合中
        List<BasicNameValuePair> parameters = new ArrayList<>();
        Set<String> names = params.keySet();
        for (String name : names) {
            String value = params.get(name);
            parameters.add(new BasicNameValuePair(name,value));
        }
        String result="";

        try {
            //设置请求字符集
            post.setEntity(new UrlEncodedFormEntity(parameters,"UTF-8"));
            post.addHeader("Content-Type","application/json; charset=utf-8");
            //准备客户端
            CloseableHttpClient client = HttpClients.createDefault();
            //点击发送
            HttpResponse httpResponse = client.execute(post);
            int code = httpResponse.getStatusLine().getStatusCode();
            Header[] allHeaders = httpResponse.getAllHeaders();
            for (Header allHeader : allHeaders) {
                System.out.println(allHeader);
            }
            System.out.println(code);
            result = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(result);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
