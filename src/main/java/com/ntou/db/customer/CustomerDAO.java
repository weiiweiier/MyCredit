package com.ntou.db.customer;

import com.ntou.db.ConnControl;
import com.ntou.tool.Common;
import lombok.extern.log4j.Log4j2;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Log4j2
public class CustomerDAO extends ConnControl {
    final String TABLE_GreyList = "Customer";

    final private String COL_CID  		= "CID"; //VARCHAR(20) PRIMARY KEY  ,-- 使用者身分證(外國護照預留欄位長度)
    final private String COL_CH_NAME 	= "CH_NAME"; //VARCHAR(50)              ,-- 使用者中文姓名
    final private String COL_EN_NAME 	= "EN_NAME"; //VARCHAR(50)              ,-- 使用者英文姓名
    final private String COL_MAIL 		= "MAIL"; //VARCHAR(50)              ,-- 使用者電子信箱
    final private String COL_PHONE 		= "PHONE"; //VARCHAR(10)              ,-- 使用者電話
    final private String COL_BIRTH 		= "BIRTH"; //VARCHAR(10)              ,-- 使用者生日yyyy/mm/dd
    final private String COL_ADDR 		= "ADDR"; //VARCHAR(100) 		     ,-- 使用者地址
    final private String COL_REGIDATE 	= "REGIDATE"; //VARCHAR(23)		    	  -- 註冊時間yyyy/MM/dd HH:mm:ss.SSS

    public static void main(String[] args) {
        CustomerDAO c = new CustomerDAO();
        CustomerVO vo = new CustomerVO();
        vo.setCID("A123456789");
        System.out.println(c.selectCustomerID(vo));
    }
    public CustomerVO selectCustomerID(CustomerVO vo){
        log.info(Common.ARROW + "selectResendFISCData" + Common.START_B);
        log.info(Common.VO + vo);
        Connection conn = getConnection();
        String SQL = String.format("select * from %s where %s=? order by %s desc"
                ,TABLE_GreyList
                ,COL_CID
                ,COL_REGIDATE
        );
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CustomerVO out = new CustomerVO();
        int i = 0;
        int j = 0;
        try {
            log.info(Common.SQL + SQL);
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(++i , vo.getCID());

            rs = pstmt.executeQuery();
            while (rs.next()) {
                out = new CustomerVO(
                        CustomerVO.encodeFormSQL(rs.getString(++j).trim())
                        , CustomerVO.encodeFormSQL(rs.getString(++j).trim())
                        , CustomerVO.encodeFormSQL(rs.getString(++j).trim())
                        , CustomerVO.encodeFormSQL(rs.getString(++j).trim())
                        , CustomerVO.encodeFormSQL(rs.getString(++j).trim())
                        , CustomerVO.encodeFormSQL(rs.getString(++j).trim())
                        , CustomerVO.encodeFormSQL(rs.getString(++j).trim())
                        , CustomerVO.encodeFormSQL(rs.getString(++j).trim())
                );
                j = 0;
            }
            pstmt.clearParameters();
            log.info(Common.RESULT + out);
        } catch(Exception e) {
            log.error(Common.EXCEPTION, e);
        } finally {
            closePS(pstmt);
            closeRS(rs);
            log.info(Common.ARROW + "selectResendFISCData" + Common.END_B);
        }
        return out;
    }
}

//    public ArrayList<CustomerVO> selectCustomerList(CustomerVO vo){
////        log.info(Common.ARROW + "selectResendFISCData" + Common.START_B);
////        log.info(Common.VO + vo);
//        Connection conn = getConnection();
//        String SQL = String.format("select * from %s where %s=? order by %s desc"
//                ,TABLE_GreyList
//                ,COL_UID
//                ,COL_UREGIDATE
//        );
//        PreparedStatement pstmt;
//        ResultSet rs;
//        ArrayList<CustomerVO> out = new ArrayList<>();
//        int i = 0;
//        int j = 0;
//        try {
////            log.info(Common.SQL + SQL);
//            pstmt = conn.prepareStatement(SQL);
//            pstmt.setString(++i , vo.getUID());
//
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                out.add(new CustomerVO(
//                        CustomerVO.encodeFormSQL(rs.getString(++j).trim())
//                        , CustomerVO.encodeFormSQL(rs.getString(++j).trim())
//                        , CustomerVO.encodeFormSQL(rs.getString(++j).trim())
//                        , CustomerVO.encodeFormSQL(rs.getString(++j).trim())
//                        , CustomerVO.encodeFormSQL(rs.getString(++j).trim())
//                        , CustomerVO.encodeFormSQL(rs.getString(++j).trim())
//                        , CustomerVO.encodeFormSQL(rs.getString(++j).trim())
//                ));
//                j = 0;
//            }
//            pstmt.clearParameters();
////            log.info(Common.RESULT + out);
//        } catch(Exception e) {
//            e.printStackTrace();
////            log.error(Common.EXCEPTION, e);
//        } finally {
////            closePS(pstmt);
////            log.info(Common.ARROW + "selectResendFISCData" + Common.END_B);
//        }
//        return out;
//    }
