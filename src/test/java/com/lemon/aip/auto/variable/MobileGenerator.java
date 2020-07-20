package com.lemon.aip.auto.variable;

import com.lemon.aip.auto.util.JDBCUtil;

import java.util.Map;

public class MobileGenerator {
    /**
     * 生成用于注册的手机号
     * @return
     */
    public String generateToBeReisterMobile(){
        String sql = "SELECT concat(max(account_id)+1,'') AS toBeRegisterMobile FROM settle_account";
        Map<String,Object> columnLabelAndValues = JDBCUtil.query(sql);
        return columnLabelAndValues.get("toBeRegisterMobile").toString();
    }

    /**
     * 生成系统中还未注册的手机号
     * @return
     */
    public String generateSystemNoExistMobile(){
        String sql = "SELECT concat(max(account_id)+2,'') AS systemNotExistMobile FROM settle_account";
        Map<String,Object> columnLabelAndValues = JDBCUtil.query(sql);
        return columnLabelAndValues.get("systemNotExistMobile").toString();
    }

    }
