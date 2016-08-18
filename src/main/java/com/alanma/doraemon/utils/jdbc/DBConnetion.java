package com.alanma.doraemon.utils.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnetion {

    private volatile static DBConnetion instance;

    private Connection con;

    public static DBConnetion getInstance() {
        if (instance == null) {
            synchronized (DBConnetion.class) {
                instance = new DBConnetion();
            }
        }
        return instance;
    }

    private DBConnetion() {
        con = null;// 创建一个数据库连接
        try {
            Class.forName(getProperValue("className"));// 加载Oracle驱动程序
            System.out.println("开始尝试连接数据库！");
            String url = getProperValue("url");// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
            String user = getProperValue("user");// 用户名,系统默认的账户名
            String password = getProperValue("password");// 你安装时选设置的密码
            con = DriverManager.getConnection(url, user, password);// 获取连接
            con.setAutoCommit(false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("连接数据库成功！");
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    private static String getProperValue(String key) {
        Properties pps = new Properties();
        String value = null;
        try {
            pps.load(new FileInputStream("E:\\工作文档\\workspace\\github\\DoraemonPocketE\\src\\tc\\platform\\dbconfig.properties"));
            value = pps.getProperty(key);
            System.out.println(key + " = " + value);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return value;
    }

}
