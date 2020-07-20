package com.lemon.aip.auto.pojo;

public class Rest {
    private String apiId;
    private String apiName;
    private String type;
    private String url;

    @Override
    public String toString() {
        return "Rest{" +
                "apiId='" + apiId + '\'' +
                ", apiName='" + apiName + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public Rest(String apiId, String apiName, String type, String url) {
        this.apiId = apiId;
        this.apiName = apiName;
        this.type = type;
        this.url = url;
    }

    public Rest() {
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
