package com.ntou.db.billrecord;

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
public class BillrecordDAO extends ConnControl {
    final String TABLE_BILLRECORD               = "billrecord";
    final private String COL_UUID                   = "UUID";
    final private String COL_BUY_CHANNEL        = "BUY_CHANNEL";//VARCHAR(2) ,--交易通路(01:虛擬/02:實體)
    final private String COL_BUY_DATE           = "BUY_DATE";//VARCHAR(23),--消費時間yyyy/MM/dd HH:mm:ss.SSS
    final private String COL_REQ_PAYMENT_DATE   = "REQ_PAYMENT_DATE";//VARCHAR(23),--請款時間yyyy/MM/dd HH:mm:ss.SSS
    final private String COL_SHOP_ID            = "SHOP_ID";//VARCHAR(20),--消費店家(統編)
    final private String COL_CUSTOMER_ID        = "CUSTOMER_ID";//VARCHAR(10),--消費者(keyID)
    final private String COL_BUY_CURRENCY       = "BUY_CURRENCY";//VARCHAR(10),--消費幣別
    final private String COL_BUY_AMOUNT         = "BUY_AMOUNT";//VARCHAR(10),--消費金額
    final private String COL_DISPUTED_FLAG      = "DISPUTED_FLAG";//VARCHAR(10),--爭議款項註記(00:正常,01異常)
    final private String COL_STATUS             = "STATUS";//VARCHAR(2) ,--狀態(00:正常,99:註銷)
    final private String COL_ACTUALLY_DATE      = "ACTUALLY_DATE";//VARCHAR(23),--此紀錄最終完成的時間yyyy/MM/dd HH:mm:ss.SSS
    final private String COL_REMARK             = "REMARK";//VARCHAR(50),--備註
    final private String COL_ISSUING_BANK       = "ISSUING_BANK";//VARCHAR(50),--發卡銀行(swiftCode)
    final private String COL_CARD_NUM           = "CARD_NUM";//VARCHAR(20),--卡號
    final private String COL_SECURITY_CODE      = "SECURITY_CODE";//VARCHAR(10) --安全碼

    public static void main(String[] args) {
        BillrecordDAO c = new BillrecordDAO();
        QueryReq vo = new QueryReq();
        vo.setCid("A123456789");
        vo.setStartDate("2024/03/05 00:00:00");
        vo.setEndDate("2024/03/12 23:59:59");
        System.out.println(c.selectCusDateBill(vo));
    }

    public int insertCusDateBill(InsertReq insertReq) throws Exception{
        log.info(Common.ARROW + "insertCusDateBill" + Common.START_B);
        log.info(Common.VO + insertReq);
        int result;
        Connection conn = getConnection();
        String SQL = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                , TABLE_BILLRECORD
                , COL_UUID
                , COL_BUY_CHANNEL
                , COL_BUY_DATE
                , COL_REQ_PAYMENT_DATE
                , COL_SHOP_ID
                , COL_CUSTOMER_ID
                , COL_BUY_CURRENCY
                , COL_BUY_AMOUNT
                , COL_DISPUTED_FLAG
                , COL_STATUS
                , COL_ACTUALLY_DATE
                , COL_REMARK
                , COL_ISSUING_BANK
                , COL_CARD_NUM
                , COL_SECURITY_CODE
        );
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            log.info(Common.SQL + SQL);
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(++i, insertReq.getUuid           ());
            pstmt.setString(++i, insertReq.getBuyChannel     ());
            pstmt.setString(++i, insertReq.getBuyDate        ());
            pstmt.setString(++i, insertReq.getReqPaymentDate());
            pstmt.setString(++i, insertReq.getShopId         ());
            pstmt.setString(++i, insertReq.getCustomerId     ());
            pstmt.setString(++i, insertReq.getBuyCurrency    ());
            pstmt.setString(++i, insertReq.getBuyAmount      ());
            pstmt.setString(++i, insertReq.getDisputedFlag   ());
            pstmt.setString(++i, insertReq.getStatus         ());
            pstmt.setString(++i, insertReq.getActuallyDate());
            pstmt.setString(++i, insertReq.getRemark         ());
            pstmt.setString(++i, insertReq.getIssuingBank    ());
            pstmt.setString(++i, insertReq.getCardNum        ());
            pstmt.setString(++i, insertReq.getSecurityCode   ());

            result = pstmt.executeUpdate();
            pstmt.clearParameters();
            log.info(Common.RESULT + result);
        } finally {
            closePS(pstmt);
            log.info(Common.ARROW + "insertCusDateBill" + Common.END_B);
        }
        return result;
    }

    public ArrayList<BillrecordVO> selectCusDateBill(QueryReq queryReq) {
        log.info(Common.ARROW + "selectCusDateBill" + Common.START_B);
        log.info(Common.VO + queryReq);
        Connection conn = getConnection();
        String SQL = String.format("select * from %s where %s=? and %s between ? and ? order by %s desc"
                , TABLE_BILLRECORD
                , COL_CUSTOMER_ID
                , COL_BUY_DATE
                , COL_BUY_DATE
        );
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<BillrecordVO> out = new ArrayList<>();
        int i = 0;
        int j = 0;
        try {
            log.info(Common.SQL + SQL);
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(++i, queryReq.getCid());
            pstmt.setString(++i, queryReq.getStartDate());
            pstmt.setString(++i, queryReq.getEndDate());

            rs = pstmt.executeQuery();
            while (rs.next()) {
                out.add(new BillrecordVO(
                        BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                ));
                j = 0;
            }
            pstmt.clearParameters();
            log.info(Common.RESULT + out);
        } catch (Exception e) {
            log.error(Common.EXCEPTION, e);
        } finally {
            closePS(pstmt);
            closeRS(rs);
            log.info(Common.ARROW + "selectCusDateBill" + Common.END_B);
        }
        return out;
    }
    public int updateFlagCusDateBill(ModifyReq req){
        log.info(Common.ARROW + "updateFlagCusDateBill" + Common.START_B);
        log.info(Common.VO + req);
        Connection conn = getConnection();
        String SQL = String.format("update %s set %s=?, %s=?, %s=? where %s=?"
                ,TABLE_BILLRECORD
                ,COL_DISPUTED_FLAG
                ,COL_STATUS
                ,COL_ACTUALLY_DATE

                ,COL_UUID
        );
        PreparedStatement pstmt = null;
        int out = 0;
        int i = 0;
        try {
            log.info(Common.SQL + SQL);
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(++i , req.getDisputedFlag());
            pstmt.setString(++i , req.getStatus());
            pstmt.setString(++i , req.getActuallyDate());

            pstmt.setString(++i , req.getUuid());
            out = pstmt.executeUpdate();
            pstmt.clearParameters();
            log.info(Common.RESULT + out);
        } catch(Exception e) {
            log.error(Common.EXCEPTION, e);
        } finally {
            closePS(pstmt);
            log.info(Common.ARROW + "updateFlagCusDateBill" + Common.END_B);
        }
        return out;
    }
    public BillrecordVO selectKey(String uuid){
        log.info(Common.ARROW + "selectResendFISCData" + Common.START_B);
        log.info("uuid:" + uuid);
        Connection conn = getConnection();
        String SQL = String.format("select * from %s where %s=?"
                ,TABLE_BILLRECORD
                ,COL_UUID
        );
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BillrecordVO out = null;
        int i = 0;
        int j = 0;
        try {
            log.info(Common.SQL + SQL);
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(++i , uuid);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                out = new BillrecordVO(
                        BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
                        , BillrecordVO.encodeFormSQL(rs.getString(++j).trim())
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
