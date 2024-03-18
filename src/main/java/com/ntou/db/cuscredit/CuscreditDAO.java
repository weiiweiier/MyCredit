package com.ntou.db.cuscredit;

import com.ntou.db.ConnControl;
import com.ntou.svc.insert.InsertReq;
import com.ntou.svc.modify.ModifyReq;
import com.ntou.svc.query.QueryReq;
import com.ntou.tool.Common;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Log4j2
public class CuscreditDAO extends ConnControl {
    final String TABLE_CUSCREDIT	= "cuscredit";
    final String COL_CID  				= "CID";          //VARCHAR(20) ,--使用者身分證(外國護照預留欄位長度)
    final String COL_REGIDATE 			= "REGIDATE";     //VARCHAR(23)	,--信用卡銀行通過(核發)時間yyyy/MM/dd HH:mm:ss.SSS
    final String COL_ISSUING_BANK 		= "ISSUING_BANK"; //VARCHAR(50) ,--核卡銀行(swiftCode)
    final String COL_CARD_NUM			= "CARD_NUM";     //VARCHAR(20) ,--信用卡號碼
    final String COL_STATUS				= "STATUS";       //VARCHAR(2)  ,--信用卡狀態(00:正常,99:註銷)
    final String COL_REMARK				= "REMARK";       //VARCHAR(50)  --備註

    public static void main(String[] args) {
        CuscreditDAO c = new CuscreditDAO();
        System.out.println(c.selectKey("A123456789","1234567891234567"));
    }

    public CuscreditVO selectKey(String cid, String num){
        log.info(Common.ARROW + "selectResendFISCData" + Common.START_B);
        log.info("cid:" + cid + "num:" + num);
        Connection conn = getConnection();
        String SQL = String.format("select * from %s where %s=? and %s=?"
                ,TABLE_CUSCREDIT
                ,COL_CID
                ,COL_CARD_NUM
        );
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CuscreditVO out = null;
        int i = 0;
        int j = 0;
        try {
            log.info(Common.SQL + SQL);
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(++i , cid);
            pstmt.setString(++i , num);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                out = new CuscreditVO(
                        CuscreditVO.encodeFormSQL(rs.getString(++j).trim())
                        , CuscreditVO.encodeFormSQL(rs.getString(++j).trim())
                        , CuscreditVO.encodeFormSQL(rs.getString(++j).trim())
                        , CuscreditVO.encodeFormSQL(rs.getString(++j).trim())
                        , CuscreditVO.encodeFormSQL(rs.getString(++j).trim())
                        , CuscreditVO.encodeFormSQL(rs.getString(++j).trim())
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
