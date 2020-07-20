package com.lemon.aip.auto.util;

import com.lemon.aip.auto.pojo.Case;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CaseUtil {
    //在取出第一行之前，需要使用静态代码块把所有的数据都存放到内存中
    //首先需要将第一行用例的第一行
    //将用例都封装到集合中去
    //把集合中的数据全部取出来

    /**
     *2020-06-28  保存所有用例对象(共享数据)
     */
    public static List<Case> cases= new ArrayList<Case>();


    /**
     * 2020-06-28  使用静态代码将数据提前读取出来放入到内存中
     */
    static {
        //将所有数据解析封装到case
        List<Case> list=ExcelUtil.load(PropertiesUtil.getExcelPath(),"用例",Case.class);
        cases.addAll(list);
    }


    /**     2020-06-28 通过传入apiId和cellName返回数据驱动搜需要的数据
     *
     * @param apiId     接口编号
     * @param cellNames 列名
     * @return      返回的是数据驱动所需要提供的数据
     */

    public static Object[][] getCaseDatasByApiId(String apiId, String[] cellNames) {
        //需要一个Class字节码对象
        Class<Case> clazz=Case.class;
        //保存指定接口编号对应的这些用例数据
        ArrayList<Case> csList = new ArrayList<Case>();
        //循环比遍历cases集合中的对象信息,通过指定APIId来获取对应的数据
        for (Case cs : CaseUtil.cases) {
            if(cs.getApiId().equals(apiId)){
                csList.add(cs);
            }
        }
        //需要确认数组的大小(行、列)
        Object[][] datas = new Object[csList.size()][cellNames.length];
        for (int i = 0; i < csList.size(); i++) {
            //根据索引取出对象
            Case cs = csList.get(i);
            //根据对象把列提取出来
            for (int j = 0; j < cellNames.length; j++) {

                //为了解耦，在这个地方用反射的方式进行处理
                //1、提取要反射的方法名
                String methodName="get"+ cellNames[j];
                try {
                    //2、需要一个方法对象，获取到反射的方法对象，因为get方法没有入参所以不需要类型
                    Method method = clazz.getMethod(methodName);
                    String value = (String) method.invoke(cs);
                    datas[i][j] = value;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }


        return datas;
    }

    /*
    *
    * /** getCaseDatasByApiId 调试获取数据是否可行
     *
     * @param args

    public static void main(String[] args) {
        String[] cellName = {"Params"};
        Object[][] datas = getCaseDatasByApiId("1",cellName);
        for (Object[] data : datas) {
            for (Object datum : data) {
                System.out.print(datum);
            }
            System.out.println();
        }
    }
    * */
}
