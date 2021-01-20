package com.ln.druid;

import java.sql.*;
import java.util.Properties;

/**
 * @ProjectName: shop_realtime_warehouse
 * @Package: com.ln.druid
 * @Name:DruidJDBCDemo
 * @Author:linianest
 * @CreateTime:2020/12/30 11:13
 * @version:1.0
 * @Description TODO: 使用jdbc方式连接druid
 */
public class DruidJDBCDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 加载druid的jdbc驱动
        Class.forName("org.apache.calcite.avatica.remote.Driver");
        // 获取druid的jdbc连接方式

        Connection connection = DriverManager.getConnection("jdbc:avatica:remote:url-http://node3:8888/druid/v2/sql/avatica/", new Properties());

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from \"metrics_kafka\"");

        while (resultSet.next()){
            resultSet.getString("url");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
