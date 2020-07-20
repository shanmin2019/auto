package com.lemon.aip.auto.pojo;


/**
 * 2020=06-28
 * 该类是为了保存返回数据写入Excel表单，而准备的类
 */
public class WriteBackData {
    private String sheetName;
    /**
     * 行标识
     */
    private String rowIdentifier;
    /**
     * 列名
     */
    private String cellName;
    /**
     * 要写入的数据
     */
    private String result;

    @Override
    public String toString() {
        return "WriteBackData{" +
                "sheetName='" + sheetName + '\'' +
                ", rowIdentifier='" + rowIdentifier + '\'' +
                ", cellName='" + cellName + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public WriteBackData() {
    }

    public WriteBackData(String sheetName, String rowIdentifier, String cellName, String result) {
        this.sheetName = sheetName;
        this.rowIdentifier = rowIdentifier;
        this.cellName = cellName;
        this.result = result;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getRowIdentifier() {
        return rowIdentifier;
    }

    public void setRowIdentifier(String rowIdentifier) {
        this.rowIdentifier = rowIdentifier;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
