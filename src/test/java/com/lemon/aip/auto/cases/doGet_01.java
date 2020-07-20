package com.lemon.aip.auto.cases;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class doGet_01 {
    public static void main(String[] args) throws IOException {
        //请求路径
        String url = "http://10.20.11.72/enesys-web/menu/info";

        HttpGet get = new HttpGet(url);
        HttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse = client.execute(get);
        int code = httpResponse.getStatusLine().getStatusCode();
        String resout = EntityUtils.toString(httpResponse.getEntity());
        Header[] allHeaders = httpResponse.getAllHeaders();
        for (Header allHeader : allHeaders) {
            System.out.println(allHeader);
        }
        System.out.println("【code="+code+"】");
        System.out.printf(resout);

    }
}
