package com.ntou.db;

import com.ntou.tool.Common;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class ConnControl {
    // MySQL 連線資訊
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/apdata";
    static final String USER = "root";
    static final String PASS = "root";

    Connection conn = null;

    public Connection getConnection(){
        try {
            if(conn==null||conn.isClosed()) {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }
        } catch (Exception e) {
            log.error(Common.EXCEPTION, e);
        }

        return conn;
    }

//    public static void main(String[] args) {
//        Connection conn = null;
//        try {
//            // 註冊 JDBC 驅動程式
//            Class.forName(JDBC_DRIVER);
//
//            // 建立連線
//            System.out.println("連線中...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            System.out.println("連線成功！"+conn);
//
//            // 在這裡執行你的數據庫操作
//            String SQL  = "select * from costomer";
//            PreparedStatement ps = conn.prepareStatement(SQL);
//            ResultSet rs =ps.executeQuery();
//            CostomerVO u = null;
//            while(rs.next())
//                u = new CostomerVO(rs) ;
//            System.out.println("*********"+u);
//            rs.close();
//            ps.close();
//        } catch (SQLException se) {
//            // 處理 JDBC 錯誤
//            se.printStackTrace();
//        } catch (Exception e) {
//            // 處理 Class.forName 錯誤
//            e.printStackTrace();
//        } finally {
//            // 關閉連線
//            try {
//                if (conn != null) conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        System.out.println("再見！");
//    }
    public void closePS(PreparedStatement ps) {
        try { if(ps != null) ps.close(); } catch (Exception e) {log.error("Exception:",e);}
    }
    public void closeRS(ResultSet rs) {
        try { if(rs != null) rs.close(); } catch (Exception e) {log.error("Exception:",e);}
    }
}

