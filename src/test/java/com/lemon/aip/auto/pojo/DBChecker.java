package com.lemon.aip.auto.pojo;

public class DBChecker {
    private String no;
    private String sql;

    @Override
    public String toString() {
        return "DBChecker{" +
                "no='" + no + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }

    public DBChecker(String no, String sql) {
        this.no = no;
        this.sql = sql;
    }

    public DBChecker() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
