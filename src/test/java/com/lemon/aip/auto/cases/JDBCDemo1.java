package com.lemon.aip.auto.cases;

import com.lemon.aip.auto.util.JDBCUtil;

import java.util.Map;
import java.util.Set;

public class JDBCDemo1 {
    public static void main(String[] args) {
        String sql = "SELECT * FROM students WHERE age =? AND card =? ";
        Object [] values = {"20","340322199001247654"};
        Map<String,Object> cl= JDBCUtil.query(sql,values);
        Set<String> keySet = cl.keySet();
        for (String s : keySet) {
            System.out.println("【"+s+"】"+","+cl.get(s));
        }
    }
}
