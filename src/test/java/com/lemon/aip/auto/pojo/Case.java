package com.lemon.aip.auto.pojo;


/**
 * 2020-06-27 这个是用例对象,
 * 2020-07-05 更改这个用例添加的数据库验证的字段
 */
public class Case {
    private String caseId;
    private String apiId;
    private String desc;
    private String params;
    private String expectedResponseData;
    private String actualResponseData;
    private String preValidateSql;
    private String preValidateResult;
    private String afterValidateSql;
    private String afterValidateResult;

    @Override
    public String toString() {
        return "Case{" +
                "caseId='" + caseId + '\'' +
                ", apiId='" + apiId + '\'' +
                ", desc='" + desc + '\'' +
                ", params='" + params + '\'' +
                ", expectedResponseData='" + expectedResponseData + '\'' +
                ", actualResponseData='" + actualResponseData + '\'' +
                ", preValidateSql='" + preValidateSql + '\'' +
                ", preValidateResult='" + preValidateResult + '\'' +
                ", afterValidateSql='" + afterValidateSql + '\'' +
                ", afterValidateResult='" + afterValidateResult + '\'' +
                '}';
    }

    public Case(String caseId, String apiId, String desc, String params, String expectedResponseData, String actualResponseData, String preValidateSql, String preValidateResult, String afterValidateSql, String afterValidateResult) {
        this.caseId = caseId;
        this.apiId = apiId;
        this.desc = desc;
        this.params = params;
        this.expectedResponseData = expectedResponseData;
        this.actualResponseData = actualResponseData;
        this.preValidateSql = preValidateSql;
        this.preValidateResult = preValidateResult;
        this.afterValidateSql = afterValidateSql;
        this.afterValidateResult = afterValidateResult;
    }

    public Case() {
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getExpectedResponseData() {
        return expectedResponseData;
    }

    public void setExpectedResponseData(String expectedResponseData) {
        this.expectedResponseData = expectedResponseData;
    }

    public String getActualResponseData() {
        return actualResponseData;
    }

    public void setActualResponseData(String actualResponseData) {
        this.actualResponseData = actualResponseData;
    }

    public String getPreValidateSql() {
        return preValidateSql;
    }

    public void setPreValidateSql(String preValidateSql) {
        this.preValidateSql = preValidateSql;
    }

    public String getPreValidateResult() {
        return preValidateResult;
    }

    public void setPreValidateResult(String preValidateResult) {
        this.preValidateResult = preValidateResult;
    }

    public String getAfterValidateSql() {
        return afterValidateSql;
    }

    public void setAfterValidateSql(String afterValidateSql) {
        this.afterValidateSql = afterValidateSql;
    }

    public String getAfterValidateResult() {
        return afterValidateResult;
    }

    public void setAfterValidateResult(String afterValidateResult) {
        this.afterValidateResult = afterValidateResult;
    }
}
