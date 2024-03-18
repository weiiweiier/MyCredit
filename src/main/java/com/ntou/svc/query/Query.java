package com.ntou.svc.query;

import com.ntou.db.cuscredit.CuscreditDAO;
import com.ntou.db.cuscredit.CuscreditVO;
import com.ntou.exceptions.TException;
import com.ntou.svc.insert.InsertRC;
import com.ntou.tool.Common;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class Query {
    public QueryRes doAPI(QueryReq req) {
        log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        QueryRes res = new QueryRes();

        try {
            if(!req.checkReq()){
                res.setResCode (QueryRC.cnsq88.getCode());
                res.setResMsg  (QueryRC.cnsq88.getContent() + "(" +req.getErrMsg() + ")");
                throw new TException(res);
            }

            CuscreditVO cusDateBillList = new CuscreditDAO().selectKey(req.getCid(), req.getCardNum());
            if(cusDateBillList==null) {
                res.setResCode (QueryRC.cnsq97.getCode());
                res.setResMsg  (QueryRC.cnsq97.getContent());
                throw new TException(res);
            }

            QueryRes.Data queryData = new QueryRes.Data();
            queryData.setCID  			(cusDateBillList.getCID  		());
            queryData.setREGIDATE 		(cusDateBillList.getREGIDATE 	());
            queryData.setISSUING_BANK 	(cusDateBillList.getISSUING_BANK());
            queryData.setCARD_NUM		(cusDateBillList.getCARD_NUM	());
            queryData.setSTATUS			(cusDateBillList.getSTATUS		());
            queryData.setREMARK			(cusDateBillList.getREMARK		());

            res.setResBody (queryData);

            res.setResCode (QueryRC.cnsq00.getCode());
            res.setResMsg  (QueryRC.cnsq00.getContent());

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
