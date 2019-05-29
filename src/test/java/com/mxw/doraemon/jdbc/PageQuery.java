package com.mxw.doraemon.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PageQuery {

    /**
     * 一次只从数据库中查询最大maxCount条记录
     * 
     * @param sql
     *            传入的sql语句
     * @param startNo
     *            从哪一条记录开始
     * @param maxCount
     *            总共取多少条记录
     */
    public static void getData(String sql, int startNo, int maxCount,List values) {
        System.out.println("SQL:"+sql);
        System.out.println("startNo:"+startNo);
        System.out.println("maxCount:"+maxCount);
        System.out.println("values:"+values.toString());
        Connection conn = DBConnetion.getInstance().getCon();
        try {
            // conn.prepareStatement(sql,游标类型,能否更新记录);
            // 游标类型：
            // ResultSet.TYPE_FORWORD_ONLY:只进游标
            // ResultSet.TYPE_SCROLL_INSENSITIVE:可滚动。但是不受其他用户对数据库更改的影响。
            // ResultSet.TYPE_SCROLL_SENSITIVE:可滚动。当其他用户更改数据库时这个记录也会改变。
            // 能否更新记录：
            // ResultSet.CONCUR_READ_ONLY,只读
            // ResultSet.CONCUR_UPDATABLE,可更新

            // TYPE_FORWARD_ONLY 表示光标只能向前移动。

            // TYPE_SCROLL_INSENSITIVE 表示可以在记录之间移动，但对（其他ResultSet 引起的）记录改变不敏感
            // TYPE_SCROLL_SENSITIVE 表示可以在记录之间移动，但对（其他ResultSet

            PreparedStatement pstat = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            for (int i = 1; i <= values.size(); i++) {
                pstat.setObject(i, values.get(i - 1));
            }
            
            // 最大查询到第几条记录
            pstat.setMaxRows(startNo + maxCount - 1);
            ResultSet rs = pstat.executeQuery();
            // 将游标移动到第一条记录
            rs.first();
            // 游标移动到要输出的第一条记录
            rs.relative(startNo - 2);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            while (rs.next()){
                for (int i = 1; i <= colCount; i++) {
                    System.out.println(rs.getObject(i));
                }
            }
               
            System.out.println("The end~");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从数据库中查询所有记录，然后通过游标来获取所需maxCount条记录
     * 
     * @param sql
     *            传入的sql语句
     * @param startNo
     *            从哪一条记录开始
     * @param maxCount
     *            总共取多少条记录
     */
    public void getDataFromAll(String sql, int startNo, int maxCount) {
        Connection conn = DBConnetion.getInstance().getCon();
        try {
            PreparedStatement pstat = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstat.executeQuery();
            rs.first();
            rs.relative(startNo - 1);
            int i = startNo - 1;
            while (i < startNo + maxCount - 1 && !rs.isAfterLast()) {
                System.out.println(rs.getInt(1));
                i++;
                rs.next();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        String sql="select branch branch, channelcode channelcode, branchchlstatus branchchlstatus, singlelmtamt singlelmtamt, daytotallmtamt daytotallmtamt, branchchleffectdate branchchleffectdate, branchchlinvaliddate branchchlinvaliddate from tp_cip_branchchanneladm where  channelcode in (?,?)  and  branch in (?,?)   order by branch DESC, channelcode DESC";
        List values=new ArrayList();
        values.add("*");
        values.add("01");
        values.add("*");
        values.add("000000");
        getData(sql, 1, 1,values);
    }
}
