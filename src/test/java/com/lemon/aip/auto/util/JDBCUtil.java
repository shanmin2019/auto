package com.lemon.aip.auto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JDBCUtil {


    public static Properties properties = new Properties();



    static {
        System.out.println("静态代码块解析Properties数据");
        InputStream iStream;
        try{
            iStream = new FileInputStream(new File("D:\\lemenJava_v1\\src\\test\\resources\\jdbc.properties"));
            properties.load(iStream);
        }catch (Exception e){
            System.out.println("文件找不到发生了异常");
            e.printStackTrace();
        }
    }

    /**
     * 根据SQL查询表数据，并以map返回，可以为字段名，Value为字段值
     * @param sql       SQL要执行查询语句
     * @param values    条件字段的值
     * @return
     */
    public static Map<String,Object> query(String sql, Object ... values){
        Map<String,Object> columnLableAndValues = null;
        try{
            //1、根据连接信息，获取数据库连接(连上数据库)
            Connection connection = getConnection();
            //2、获取PerparedStatement对象(此类型的对象有提供数据库操作的方法)
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //3、设置条件字段的值
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i+1,values[i]);
            }
            //4、调用查询方法，执行查询，返回ResultSet(结果集)
            ResultSet resultSet = preparedStatement.executeQuery();
            //获取查询相关的信息
            ResultSetMetaData metaData = resultSet.getMetaData();
            //获取查询字段的个数（得到查询字段的数目）
            int columnCount = metaData.getColumnCount();
            //5、从结果集取查询数据
            columnLableAndValues = new HashMap<String,Object>();
            while(resultSet.next()){
                //循环取出每个查询字段的数据
                for (int i = 1; i <= columnCount; i++) {
                    //获取列名的序号(列的名字)
                    String columnLable = metaData.getColumnLabel(i);
                    String columnValue = resultSet.getObject(columnLable).toString();
                    columnLableAndValues.put(columnLable,columnValue);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return columnLableAndValues;
    }





    /**
     * 获取数据库连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        //直接从properties取就可以了
        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        Connection connection = DriverManager.getConnection(url,user,password);
        return connection;
    }
}
