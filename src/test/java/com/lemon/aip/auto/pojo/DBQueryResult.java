package com.lemon.aip.auto.pojo;

import java.util.Map;

/**
 * 数据库查询结果实体类
 */

public class DBQueryResult {
    /**
     * SQL编号（脚本编号）
     */
    private String no;
    /**
     * 指定SQL查询得到的字段名，返回的类型是Map类型，但是Value是一个Json格式的，所以定义成了一个对象
     */
    private Map<String,Object> columenLabelAndValues;

    @Override
    public String toString() {
        return "DBQueryResult{" +
                "no='" + no + '\'' +
                ", columenLabelAndValues=" + columenLabelAndValues +
                '}';
    }

    public DBQueryResult(String no, Map<String, Object> columenLabelAndValues) {
        this.no = no;
        this.columenLabelAndValues = columenLabelAndValues;
    }

    public DBQueryResult() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Map<String, Object> getColumenLabelAndValues() {
        return columenLabelAndValues;
    }

    public void setColumenLabelAndValues(Map<String, Object> columenLabelAndValues) {
        this.columenLabelAndValues = columenLabelAndValues;
    }
}
