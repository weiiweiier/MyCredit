package com.ntou.db.customer;

import com.ntou.db.ConnControl;
import com.ntou.tool.Common;
import com.ntou.tool.JsonTool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVO {

    private String CID  		= ""; //VARCHAR(20) PRIMARY KEY  ,-- 使用者身分證(外國護照預留欄位長度)
    private String CH_NAME 	    = ""; //VARCHAR(50)              ,-- 使用者中文姓名
    private String EN_NAME 	    = ""; //VARCHAR(50)              ,-- 使用者英文姓名
    private String MAIL 		= ""; //VARCHAR(50)              ,-- 使用者電子信箱
    private String PHONE 		= ""; //VARCHAR(10)              ,-- 使用者電話
    private String BIRTH 		= ""; //VARCHAR(10)              ,-- 使用者生日yyyy/mm/dd
    private String ADDR 		= ""; //VARCHAR(100) 		     ,-- 使用者地址
    private String REGIDATE 	= ""; //VARCHAR(23)		    	  -- 註冊時間yyyy/MM/dd HH:mm:ss.SSS

//    public CostomerVO(ResultSet res) throws SQLException {
//        if(res!=null) {
//            UID         = res.getString("UID");
//            UNAME       = res.getString("UNAME");
//            UMAIL       = res.getString("UMAIL");
//            UPHONE      = res.getString("UPHONE");
//            UBIRTH      = res.getString("UBIRTH");
//            UADD        = res.getString("UADD");
//            UREGIDATE   = res.getString("UREGIDATE");
//        }
//    }
    public static String encodeFormSQL(String s) {return s != null && !s.isEmpty() ? s : "";}

    @Override
    public String toString() {return JsonTool.format2Json(this);}

    @Log4j2
    public static class CustomerDAO extends ConnControl {
        private final String TABLE_GreyList = "customer";

        private final String COL_CID  		= "CID"; //VARCHAR(20) PRIMARY KEY  ,-- 使用者身分證(外國護照預留欄位長度)
        private final String COL_CH_NAME 	= "CH_NAME"; //VARCHAR(50)              ,-- 使用者中文姓名
        private final String COL_EN_NAME 	= "EN_NAME"; //VARCHAR(50)              ,-- 使用者英文姓名
        private final String COL_MAIL 		= "MAIL"; //VARCHAR(50)              ,-- 使用者電子信箱
        private final String COL_PHONE 		= "PHONE"; //VARCHAR(10)              ,-- 使用者電話
        private final String COL_BIRTH 		= "BIRTH"; //VARCHAR(10)              ,-- 使用者生日yyyy/mm/dd
        private final String COL_ADDR 		= "ADDR"; //VARCHAR(100) 		     ,-- 使用者地址
        private final String COL_REGIDATE 	= "REGIDATE"; //VARCHAR(23)		    	  -- 註冊時間yyyy/MM/dd HH:mm:ss.SSS

        public static void main(String[] args) {
            CustomerDAO c = new CustomerDAO();
            CustomerVO vo = new CustomerVO();
            vo.setCID("A123456789");
            System.out.println(c.selectCostomerID(vo));
        }
        public CustomerVO selectCostomerID(CustomerVO vo){
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
    //            log.info(Common.SQL + SQL);
                pstmt = conn.prepareStatement(SQL);
                pstmt.setString(++i , vo.getCID());

                rs = pstmt.executeQuery();
                while (rs.next()) {
                    out = new CustomerVO(
                            encodeFormSQL(rs.getString(++j).trim())
                            , encodeFormSQL(rs.getString(++j).trim())
                            , encodeFormSQL(rs.getString(++j).trim())
                            , encodeFormSQL(rs.getString(++j).trim())
                            , encodeFormSQL(rs.getString(++j).trim())
                            , encodeFormSQL(rs.getString(++j).trim())
                            , encodeFormSQL(rs.getString(++j).trim())
                            , encodeFormSQL(rs.getString(++j).trim())
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

    //    public ArrayList<CostomerVO> selectCostomerList(CostomerVO vo){
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
    //        ArrayList<CostomerVO> out = new ArrayList<>();
    //        int i = 0;
    //        int j = 0;
    //        try {
    ////            log.info(Common.SQL + SQL);
    //            pstmt = conn.prepareStatement(SQL);
    //            pstmt.setString(++i , vo.getUID());
    //
    //            rs = pstmt.executeQuery();
    //            while (rs.next()) {
    //                out.add(new CostomerVO(
    //                        CostomerVO.encodeFormSQL(rs.getString(++j).trim())
    //                        , CostomerVO.encodeFormSQL(rs.getString(++j).trim())
    //                        , CostomerVO.encodeFormSQL(rs.getString(++j).trim())
    //                        , CostomerVO.encodeFormSQL(rs.getString(++j).trim())
    //                        , CostomerVO.encodeFormSQL(rs.getString(++j).trim())
    //                        , CostomerVO.encodeFormSQL(rs.getString(++j).trim())
    //                        , CostomerVO.encodeFormSQL(rs.getString(++j).trim())
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
    }
}
