package com.lemon.aip.auto.util;

import com.lemon.aip.auto.pojo.Rest;

import java.util.ArrayList;
import java.util.List;

public class RestUtil {
    /** 2020-06-28  存储Rest对象中的所有数据
     *
     */

    public static List<Rest> rests = new ArrayList<Rest>();

    /**程序运行前需要加载数据 ,获取的是“接口信息”中所有的数据
     *
     */

    static {
        List<Rest> list =ExcelUtil.load(PropertiesUtil.getExcelPath(),"接口信息",Rest.class);
        rests.addAll(list);
    }

    /**  2020-06-28  根据apiId 获取最新URL
     *
     * @param apiId  通过“接口信息”中的接口编号信息获取对应URL
     * @return      返回对应URL
     */
    public static String getUrlByApiId(String apiId) {
        for (Rest rest : rests) {
            if(rest.getApiId().equals(apiId)){
                return rest.getUrl();
            }
        }
        return "";
    }

    /**  2020-06-28  根据apiId 获取最新请求方法
     *
     * @param apiId  通过“接口信息”中的接口编号信息获取对应请求类型
     * @return      返回对应请求类型
     */
    public static String getTypeByApiId(String apiId) {
        for (Rest rest : rests) {
            if(rest.getApiId().equals(apiId)){
                return rest.getType();
            }
        }
        return "";
    }

}
