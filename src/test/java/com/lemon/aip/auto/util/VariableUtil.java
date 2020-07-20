package com.lemon.aip.auto.util;

import com.lemon.aip.auto.pojo.Variable;
import com.lemon.aip.auto.pojo.WriteBackData;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 2020-07-18 参数化工具类
 */

public class VariableUtil {

    public static Map<String,String> variableNameAndValueMap = new HashMap<String,String>();
    /**
     * 该行代码一定要放在加载数据之前，因为加载后的数据要保存在该变量里面，如果是放在它的后面就是因为找不到变量报错null
     */
    public static List<Variable> variables = new ArrayList<Variable>();


    static {
        //第一次加载表单里面的数据一次将每行封装成对象，然后统一添加到集合
         List<Variable> list = ExcelUtil.load(PropertiesUtil.getExcelPath(),"变量", Variable.class);
        variables.addAll(list);
        //将变量名及变量值，保存到map集合中
        loadVariablesToMap();
        ExcelUtil.loadRownumAndCellnumMapping(PropertiesUtil.getExcelPath(),"变量");
    }

    /**
     * 遍历变量集合，将变量名和对应变量值保存到Map
     */
    private static void loadVariablesToMap() {
        for (Variable variable : variables) {
            //获取变量名称
            String variableName = variable.getName();
            //获取变量值
            String variableValue = variable.getValue();
            //如果value值为空
            if(variableValue ==null || variableValue.trim().length()==0){
                //要反射的类
                String reflectClass = variable.getReflectClass();
                //通过反射创建对象

                //要反射调用的方法
                String reflectMethod = variable.getReflectMethod();
                try{
                    //通过反射类型获取字节码clazz
                    Class clazz = Class.forName(reflectClass);
                    //通过反射创建对象
                    Object object = clazz.newInstance();
                    //获取反射调用的方法对象method
                    Method method = clazz.getMethod(reflectMethod);
                    //反射调用方法，获取到方法的返回值
                    variableValue = (String) method.invoke(object);
                    //保存到要写回的数据集合
                    ExcelUtil.writeBackDatas.add(new WriteBackData("变量",variableName,"ReflectValue",variableValue));

                }catch (Exception e){
                    e.printStackTrace();
                }



            }
            //变量名及变量值都已经取出，现在放入到Map中
            variableNameAndValueMap.put(variableName,variableValue);
        }
    }

    public static String replaceVariables(String paramters) {
        //取出所有的变量名
        Set<String> variableNames = variableNameAndValueMap.keySet();
        for (String variableName : variableNames) {
            //判断如果测试数据中出现变量名就给他替换掉
            if(paramters.contains(variableName)){
                paramters = paramters.replace(variableName, variableNameAndValueMap.get(variableName));
            }
        }

        return paramters;
    }


}
