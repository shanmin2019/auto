package com.lemon.aip.auto.util;

import com.alibaba.fastjson.JSONObject;
import com.lemon.aip.auto.pojo.DBChecker;
import com.lemon.aip.auto.pojo.DBQueryResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DBCheckUtil {
    /**
     * 根据脚本执行查询并返回查询结果
     * @param validateSql 需要执行的查询语句
     * @return
     */
    public static String doQuery(String validateSql) {
        /**
         * 解析Excel里面的SQL语句。是由数组解析成Json格式的，并封装成集合
         */
        List<DBChecker> dbCheckers = JSONObject.parseArray(validateSql, DBChecker.class);

        /**
         *保存返回结果
         */
        List<DBQueryResult> dbQueryResults = new ArrayList<DBQueryResult>();
        //循环遍历，取出SQL脚本
        for (DBChecker dbChecker : dbCheckers) {
            //拿到SQL编号
            String no = dbChecker.getNo();
            String sql = dbChecker.getSql();
            //执行查询，获取到结果
            Map<String,Object> columenLabelAndValues = JDBCUtil.query(sql);
            DBQueryResult dbQueryResult = new DBQueryResult();
            dbQueryResult.setNo(no);
            dbQueryResult.setColumenLabelAndValues(columenLabelAndValues);
            dbQueryResults.add(dbQueryResult);
        }


        return JSONObject.toJSONString(dbQueryResults);
    }

    /*
    * /**
     * 验证JSONObject.parseArray()这个功能是否正确
     * @param args
     */
    /*
    public static void main(String[] args) {
        String validateSql = "[{\"no\":\"1\",\"sql\":\"select COUNT(*) from staff  where staff_no = 'admin'\"},{\"no\":\"2\",\"sql\":\"select COUNT(*) FROM staff_role where staff_id = '201908011418103770001364234223'\"}]";
        List<DBChecker> dbCheckers = JSONObject.parseArray(validateSql, DBChecker.class);
        for (DBChecker dbChecker : dbCheckers) {
            System.out.println(dbChecker);
        }
        //System.out.println(dbCheckers);
    }
   */
}
