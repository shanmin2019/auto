package com.lemon.aip.auto.util;

import com.lemon.aip.auto.pojo.WriteBackData;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelUtil {


    /**
     * 2020-06-28 事先准备好一个Map，保存了caseId(用例编号)和行号的映射-->目的：传caseId就可以获取行号
     */
    public static Map<String, Integer> rowIdentifierRownumMapping = new HashMap<String, Integer>();
    /**
     * 2020-06-28 事先准备好一个Map，保存了cellName(表头名称)和列号的映射-->目的：传cellName就可以获取列号
     */
    public static Map<String, Integer> cellNameCellnumMapping = new HashMap<String, Integer>();
    /**
     * 2020-06-28 接口返回报文数据都会保存这个这个集合中，属于共享数据
     */
    public static List<WriteBackData> writeBackDatas = new ArrayList<WriteBackData>();

    /**
     * 2020-06-28  加载行索引和列索引中的映射数据
     */

    static {
        loadRownumAndCellnumMapping(PropertiesUtil.getExcelPath(), "用例");
    }


    /**
     * 2020-06-28 获取caseid以及对应的行索引，获取cellName以及对应的列索引，
     *
     * @param execlPath 需要加载的文件路径
     * @param sheetName 需要加载的表单名
     */

    public static void loadRownumAndCellnumMapping(String execlPath, String sheetName) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(execlPath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            //拿第一行(标题行)
            Row titleRow = sheet.getRow(0);
            if (titleRow != null && !isEmptyRow(titleRow)) {
                int lastCellNum = titleRow.getLastCellNum();
                //因为这个遍历的是索引所以开始是0，循环遍历每一列
                for (int i = 0; i < lastCellNum; i++) {

                    //拿到cell对象
                    Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String title = cell.getStringCellValue();
                    title = title.substring(0, title.indexOf("("));
                    //拿列的索引
                    int cellnum = cell.getAddress().getColumn();
                    //放入到映射
                    cellNameCellnumMapping.put(title, cellnum);
                }
            }
            //拿到每一行的数据,从第二行开始
            //拿到最后一行
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Row dataRow = sheet.getRow(i);
                //拿到第一列
                Cell fisrtCellOfRow = dataRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                fisrtCellOfRow.setCellType(CellType.STRING);
                String caseId = fisrtCellOfRow.getStringCellValue();
                //获取这一行的索引
                int rowNum = dataRow.getRowNum();
                rowIdentifierRownumMapping.put(caseId, rowNum);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {


            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 2020-06-26  缺点：当前是获取的行与列的数据写成固定的值，这个是不友好的
     *
     * @param excelPath 文件路径
     * @param sheetName sheet名称
     * @return 返回数据
     */

    public static Object[][] datas(String excelPath, String sheetName) {
        //获取Excel文件路径
        File file = new File(excelPath);
        Object[][] datas = null;
        try {
            //创建Excel的对象
            Workbook workbook = WorkbookFactory.create(file);
            //获取sheet页面
            Sheet sheet = workbook.getSheet(sheetName);
            datas = new Object[6][2];
            //获取行
            for (int i = 1; i <= 6; i++) {
                //这个是获取索引
                Row row = sheet.getRow(i);
                //获取列
                for (int j = 6; j <= 7; j++) {
                    Cell cell = row.getCell(j - 1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    //将获取出来的值设置为字符串格式
                    cell.setCellType(CellType.STRING);
                    //获取列的值
                    String value = cell.getStringCellValue();
                    datas[i - 1][j - 6] = value;

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        //返回数据
        return datas;
    }

    /**
     * 2020-06-26，该方法缺陷在于选择的数据不能连续
     *
     * @param excelPath 文件路径
     * @param sheetName sheet名称
     * @param startRow  开始行号
     * @param endRow    结束行号
     * @param startCell 开始列号
     * @param endCell   结束列号
     * @return 返回datas数组数据
     */
    public static Object[][] datas(String excelPath, String sheetName, int startRow, int endRow, int startCell, int endCell) {
        //获取Excel文件路径
        File file = new File(excelPath);
        Object[][] datas = null;
        try {
            //创建Excel的对象
            Workbook workbook = WorkbookFactory.create(file);
            //获取sheet页面
            Sheet sheet = workbook.getSheet(sheetName);
            datas = new Object[endRow - startRow + 1][endCell - startCell + 1];
            //获取行
            for (int i = startRow; i <= endRow; i++) {
                //这个是获取索引
                Row row = sheet.getRow(i);
                //获取列
                for (int j = startCell; j <= endCell; j++) {
                    Cell cell = row.getCell(j - 1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    //将获取出来的值设置为字符串格式
                    cell.setCellType(CellType.STRING);
                    //获取列的值
                    String value = cell.getStringCellValue();
                    datas[i - startRow][j - startCell] = value;

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        //返回数据
        return datas;
    }

    /**
     * 2020-06-27 该方法用于可连续，缺点咋于耦合性太强
     *
     * @param excelPath 需要读取的文件路径
     * @param sheetName sheet名字
     * @param rows      需要读取的行号
     * @param cells     需要读取的列数据
     * @return 返回的数据
     */
    public static Object[][] datas(String excelPath, String sheetName, int[] rows, int[] cells) {
        //获取Excel文件路径
        File file = new File(excelPath);
        Object[][] datas = null;
        try {
            //创建Excel的对象
            Workbook workbook = WorkbookFactory.create(file);
            //获取sheet页面
            Sheet sheet = workbook.getSheet(sheetName);
            //定义保存数据的数组
            datas = new Object[rows.length][cells.length];
            //通过循环每一行
            for (int i = 0; i < rows.length; i++) {
                //这个是获取索引
                Row row = sheet.getRow(rows[i] - 1);
                //根据行引获取出一行
                for (int j = 0; j < cells.length; j++) {
                    Cell cell = row.getCell(cells[j] - 1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    //将获取出来的值设置为字符串格式
                    cell.setCellType(CellType.STRING);
                    //获取列的值
                    String value = cell.getStringCellValue();
                    datas[i][j] = value;

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        //返回数据
        return datas;
    }

    /**
     * 2020-06-28  加载“用例”中所有的数据
     *
     * @param execlPath 用例文件路径
     * @param sheetName 分页名称
     */
    public static <T> List<T> load(String execlPath, String sheetName, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        //创建一个字节码对象
        // Class clazz=Case.class;
        try {
            //创建Workbook对象
            Workbook workbook = WorkbookFactory.create(new File(execlPath));
            //获取分页名称
            Sheet sheet = workbook.getSheet(sheetName);
            //获取行,获取第一行只需要获取表头名称即可
            Row row = sheet.getRow(0);
            //获取最后一列
            int lastCellNum = row.getLastCellNum();
            //将获取到表头存放到数组中，因为当前是一维数组
            String[] fields = new String[lastCellNum];
            for (int i = 0; i < lastCellNum; i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                //设置字符串
                cell.setCellType(CellType.STRING);
                //获取对应的值
                String title = cell.getStringCellValue();
                title = title.substring(0, title.indexOf("("));
                //可以获取到表头中每个字段名称
                fields[i] = title;
            }

            //获取最后一行的索引
            int lastRowNum = sheet.getLastRowNum();
            //数据从第二列也就是开始有数据的地方开始，行索引为1，因为行索引是从0开始的
            for (int i = 1; i <= lastRowNum; i++) {

                //创建一个字节码对象（原始对象）
                T obj = clazz.newInstance();
                //一次取出每一行(数据行)
                Row dataRow = sheet.getRow(i);
                //拿到一行数据需要判断是否为空的
                if (dataRow == null || isEmptyRow(dataRow)) {
                    continue;
                }
                //获取每一行每一列的数据，将数据封装到cs对象中去
                for (int j = 0; j < fields.length; j++) {
                    Cell cell = dataRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    //需要把这里的数据保存到对象里面，前提是要获取到反射的方法名
                    //1、从数组中取出列名称，并获取反射名称-->得到想要的方法名
                    String methodName = "set" + fields[j];
                    //2、获取到方法名并拿到方法的   -->method对象,因为set方法有传参所以要指定传参类型
                    Method method = clazz.getMethod(methodName, String.class);
                    //3、完成反射调用,即全部封装到反射对象中去了
                    method.invoke(obj, value);

                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 2020-06-28  判断该行数据每一列是否都是空的
     *
     * @param dataRow 所代表的行
     * @return 返回结果为布尔值
     */
    private static boolean isEmptyRow(Row dataRow) {
        //通过行号获取最后一列数据
        int lastCellNum = dataRow.getLastCellNum();
        for (int i = 0; i < lastCellNum; i++) {
            Cell cell = dataRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            String value = cell.getStringCellValue();
            if (value != null && value.trim().length() > 0) {
                return false;
            }

        }
        return true;
    }



    

   /*
   *
   *  public static void main(String[] args) {
        String excelpath = "D:\\lemenJava_v1\\src\\test\\resources\\cases_v1.xlsx";
        int rows[] = {7,8,9,10,11};
        int cells[] = {6,7};
        Object[][] datas = datas(excelpath,"用例",rows,cells);
        for (Object[] data : datas) {
            for (Object datum : data) {
                System.out.print("【"+datum+"】");
            }
            System.out.println();
        }

    }
   * */

    /** 2020-06-28  这个是验证 load 函数是否起作用了  main函数
     *
     * @param args
     */

    /*public static void main(String[] args) {
        load("D:\\lemenJava_v1\\src\\test\\resources\\cases_v7.xlsx","用例",Case.class);
        for (Case cs : CaseUtil.cases) {
            System.out.println(cs);
        }
    }*/



    /**
     * 2020-06-28 回写接口响应报文
     *
     * @param execlPath 用例文件路径
     * @param sheetName 表单名称
     * @param caseId    用例接口号
     * @param result    接口响应返回结果
     */
    public static void writeBackData(String execlPath, String sheetName, String caseId, String cellName, String result) {
        //数据回写到Excel里面，需要知道回写到哪一行哪一列，所以需要获取到行索引和列索引来确定写的位置
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(new File(execlPath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            /**
             * 获取行索引
             */
            int rownum = rowIdentifierRownumMapping.get(caseId);
            Row row = sheet.getRow(rownum);

            /**
             * 获取列索引
             * 有了行索引和列索引就可以定位到我们所要写的数据写到那个格子上面，
             * 这边存在一个问题，如何保证在使用前这个是就是就已经加载到这两个类里面了？
             */
            int cellNum = cellNameCellnumMapping.get(cellName);
            //Cell cell = row.getCell(cellNum, MissingCellPolicy.CREATE_NULL_AS_BLANK);
            Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            //该处需要的是写数据进去，所以用的方法不是读
            cell.setCellValue(result);
            outputStream = new FileOutputStream(new File(execlPath));
            workbook.write(outputStream);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    /**
     * 2020-06-28  批量回写数据，这个方法是 当用例全部执行完一起把数据写入到Excel表格中
     *
     * @param execlPath
     */
    public static void batchWriteBackDatas(String execlPath) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(new File(execlPath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            //遍因为返回报文数据都是在该集合中，所以需要遍历该集合“writeBackDatas”
            for (WriteBackData writeBackData : writeBackDatas) {
                //获取表单名称，因为需要知道写到哪个表单中
                String sheetName = writeBackData.getSheetName();
                Sheet sheet = workbook.getSheet(sheetName);
                String rowIdentifier = writeBackData.getRowIdentifier();
                /**
                 * 通过caseId获取行索引
                 */
                int rowNum = rowIdentifierRownumMapping.get(rowIdentifier);
                Row row = sheet.getRow(rowNum);
                /**
                 * 获取列索引
                 */
                String cellName = writeBackData.getCellName();
                Integer cellnum = cellNameCellnumMapping.get(cellName);
                Cell cell = row.getCell(cellnum , Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String result = writeBackData.getResult();
                cell.setCellValue(result);
            }
            outputStream = new FileOutputStream(new File(execlPath));
            workbook.write(outputStream);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
