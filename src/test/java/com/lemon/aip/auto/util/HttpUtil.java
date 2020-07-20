package com.lemon.aip.auto.util;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class HttpUtil {

    /**
     * 2020-06-28  保存会话ID
     * 思考：什么时候保存该数据呢？--->只要响应头中有会话ID我就给它保存起来
     */
    public static Map<String,String> cookies = new HashMap<String,String>();
    /**
     *POSt请求   2020-06-26
     * @param url       请求路径
     * @param params    请求的参数
     * @return          返回的报文
     */
    public static String doPost(String url, Map<String, String> params) {
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
            //准备客户端
            HttpClient client = HttpClients.createDefault();

            addCookieInRequestHeaderBeforeRequest(post);
            //点击发送
            HttpResponse httpResponse = client.execute(post);
            /**
             * 只要发现响应头中出现会话ID 我就把它保存到cookies里面去
             */
            getAndStoreCookiesFromResponseHeader(httpResponse);
            int code = httpResponse.getStatusLine().getStatusCode();
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

    /**
     * 只要发现cookies中存在会话ID那么就在请求之前放到请求头(放入到请求头中去用的名称是：Cookie)中去，因为服务器要拿去做校验的
     */
    private static void addCookieInRequestHeaderBeforeRequest(HttpRequest request) {
        String sharejsessionid = cookies.get("SHAREJSESSIONID");
        if(sharejsessionid!= null){
            request.addHeader("Cookie",sharejsessionid);
        }
    }


    /**
     * 2020-06-28 从响应头中获取会话ID并且将会话ID保存到Map的cookies中去
     * @param httpResponse
     */
    private static void getAndStoreCookiesFromResponseHeader(HttpResponse httpResponse) {
        /**
         * 从响应头中取出名字为“Set-Cookie”的响应头
         */
        //Set-Cookie: SHAREJSESSIONID=a81af84d-93ab-4ce5-8505-eecaff3207bc; Path=/; HttpOnly
        //Set-Cookie: rememberMe=deleteMe; Path=/enesys-web; Max-Age=0; Expires=Sat, 27-Jun-2020 14:44:28 GMT
        Header setCookieHeader = httpResponse.getFirstHeader("Set-Cookie");
        //如果不为空，取出响应头的值并且以分号分割
        if(setCookieHeader!=null){
            String cookiePairsString = setCookieHeader.getValue();
            if(cookiePairsString!=null && cookiePairsString.trim().length()>0){
                String[] cookiepairs = cookiePairsString.split(";");
                if(cookiepairs!=null){
                    for (String cookiepair : cookiepairs) {
                        //如果包含了SHAREJSESSIONID则意味着响应头中有会话ID这个数据，有这个数据那么我们就放到Map里面去
                        if(cookiepair.contains("SHAREJSESSIONID")){
                            cookies.put("SHAREJSESSIONID",cookiepair);
                        }
                    }
                }
            }

        }
    }

    /**
     *  get 方法
     * @param url   请求路径
     * @param params    请求值
     * @return      返回报文
     */
    public static String doGet(String url , Map<String,String> params){
        //请求路径
        //参数准备
        Set<String> names = params.keySet();
        //定义一个标志位
        int mark =1;
        for (String name : names) {
            if(mark == 1){
                url += ("?"+name+"="+params.get(name));
            }else {
                url += ("&"+name + "="+params.get(name));
            }
            mark++;
        }
        //指定请求方式
        HttpGet get = new HttpGet(url);
        //准备一个客户端
        String result = "";
        HttpClient client = HttpClients.createDefault();
        get.addHeader("Content-Type","text/html;charset=UTF-8");
        try {
            //发起请求
            addCookieInRequestHeaderBeforeRequest(get);
            HttpResponse httpResponse = client.execute(get);
            getAndStoreCookiesFromResponseHeader(httpResponse);
            Header[] allHeaders = httpResponse.getAllHeaders();
            for (Header allHeader : allHeaders) {
                System.out.println(allHeader);
            }
            //获取返回码
            int code = httpResponse.getStatusLine().getStatusCode();
            System.out.println(code);
            //获取返回报文
            result = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     *  2020-06-27 该方法为根据请求请求类型而写的方法
     * @param url       请求路径
     * @param type      请求类型
     * @param params    请求参数
     */
    public static String doServer(String url,String type,Map<String,String> params) {
        String result = "";
        if("post".equalsIgnoreCase(type)){
           result= HttpUtil.doPost(url,params);
        }else if("get".equalsIgnoreCase(type)){
            result= HttpUtil.doGet(url, params);
        }
        return result;
    }


}
