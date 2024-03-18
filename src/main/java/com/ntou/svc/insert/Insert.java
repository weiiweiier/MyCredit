package com.ntou.svc.insert;

import com.ntou.db.billrecord.BillrecordDAO;
import com.ntou.db.cuscredit.CuscreditDAO;
import com.ntou.db.cuscredit.CuscreditVO;
import com.ntou.exceptions.TException;
import com.ntou.svc.query.QueryRC;
import com.ntou.tool.Common;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class Insert {
    public InsertRes doAPI(InsertReq req) {
        log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        InsertRes res = new InsertRes();

        try {
            BillrecordDAO billrecordDAO = new BillrecordDAO();
            if(!req.checkReq()){
                res.setResCode (InsertRC.cnsi88.getCode());
                res.setResMsg  (InsertRC.cnsi88.getContent() + "(" +req.getErrMsg() + ")");
                throw new TException(res);
            }
            CuscreditVO cusDateBillList = new CuscreditDAO().selectKey(req.getCustomerId(), req.getCardNum());
            if(cusDateBillList==null) {
                res.setResCode (QueryRC.cnsq97.getCode());
                res.setResMsg  (QueryRC.cnsq97.getContent());
                throw new TException(res);
            }
            if(billrecordDAO.selectKey(req.getUuid())!=null){//預防攻擊:查不查得到UUID
                res.setResCode (InsertRC.cnsi97.getCode());
                res.setResMsg  (InsertRC.cnsi97.getContent());
                throw new TException(res);
            }

            int bInsertCusDateBill = billrecordDAO.insertCusDateBill(req);
            if(bInsertCusDateBill !=1) {
                res.setResCode (InsertRC.cnsi98.getCode());
                res.setResMsg  (InsertRC.cnsi98.getContent());
                throw new TException(res);
            }
            res.setResCode (InsertRC.cnsi00.getCode());
            res.setResMsg  (InsertRC.cnsi00.getContent());

        } catch (TException e){
            log.warn(Common.RES + e.msg);
            log.warn(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
            return res;

        } catch (Exception e){
            log.error(Common.EXCEPTION, e);
            res.setResCode (InsertRC.cnsi99.getCode());
            res.setResMsg  (InsertRC.cnsi99.getContent());
            log.info(Common.RES + res);
            log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
            return res;
        }
        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        return res;
    }
}
