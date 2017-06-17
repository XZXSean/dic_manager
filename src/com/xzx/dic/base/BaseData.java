package com.xzx.dic.base;
import java.sql.*;

/**
 * Created by xzx14 on 2017/6/13.
 */
public class BaseData {

    /*
    public static void main(String args[]){
        BaseData ba = new BaseData();
        Connection cn = ba.getConnection();
        System.out.print("**********");
        ba.closeAll(cn, null, null);
    }
    */

    //连接数据库
    public Connection getConnection(){
        Connection conn = null;
        try{
            //加载MySql的驱动类
            Class.forName("com.mysql.jdbc.Driver") ;
            System.out.println("加载驱动成功");
        }catch(ClassNotFoundException e){
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace() ;
        }
        try {
            //连接数据库
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dic","root","hadoop");
            System.out.println("成功连接数据库");
        } catch (Exception e){
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return conn;
    }
    //关闭数据库
    public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs){
        if(rs != null){
            try {
                rs.close(); //关闭结果集
            } catch (Exception e){
                e.printStackTrace();
            }
        } // end-of-if
        if (pstmt != null){
            try {
                pstmt.close(); //关闭编译执行sql对象
            } catch (Exception e){
                e.printStackTrace();
            }
        } //end-of-if
        if (conn != null){
            try{
                conn.close(); //关闭连接对象
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
