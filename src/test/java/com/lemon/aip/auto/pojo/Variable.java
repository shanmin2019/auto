package com.lemon.aip.auto.pojo;

public class Variable {
    private String name;
    private String value;
    private String remarks;
    private String reflectClass;
    private String reflectMethod;
    private String reflectValue;

    public Variable() {
    }

    public Variable(String name, String value, String remarks, String reflectClass, String reflectMethod, String reflectValue) {
        this.name = name;
        this.value = value;
        this.remarks = remarks;
        this.reflectClass = reflectClass;
        this.reflectMethod = reflectMethod;
        this.reflectValue = reflectValue;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", remarks='" + remarks + '\'' +
                ", reflectClass='" + reflectClass + '\'' +
                ", reflectMethod='" + reflectMethod + '\'' +
                ", reflectValue='" + reflectValue + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReflectClass() {
        return reflectClass;
    }

    public void setReflectClass(String reflectClass) {
        this.reflectClass = reflectClass;
    }

    public String getReflectMethod() {
        return reflectMethod;
    }

    public void setReflectMethod(String reflectMethod) {
        this.reflectMethod = reflectMethod;
    }

    public String getReflectValue() {
        return reflectValue;
    }

    public void setReflectValue(String reflectValue) {
        this.reflectValue = reflectValue;
    }
}
