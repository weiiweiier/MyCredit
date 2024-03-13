package com.ntou.svc.query;

import com.ntou.db.billrecord.BillrecordDAO;
import com.ntou.db.billrecord.BillrecordVO;
import com.ntou.exceptions.TException;
import com.ntou.svc.insert.InsertRC;
import com.ntou.tool.Common;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;

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

            BillrecordDAO dao = new BillrecordDAO();
            ArrayList<BillrecordVO> cusDateBillList = dao.selectCusDateBill(req);

            ArrayList<QueryRes.Data> resList = new ArrayList<>();
            cusDateBillList.forEach(action->{
                QueryRes.Data queryData = new QueryRes.Data();
                queryData.setBuyChannel 	(action.getBUY_CHANNEL 	    ());
                queryData.setBuyDate 	 	(action.getBUY_DATE 		());
                queryData.setReqPaymentDate (action.getREQ_PAYMENT_DATE ());
                queryData.setShopId		 	(action.getSHOP_ID			());
                queryData.setCustomerId	 	(action.getCUSTOMER_ID		());
                queryData.setBuyCurrency	(action.getBUY_CURRENCY	    ());
                queryData.setBuyAmount	 	(action.getBUY_AMOUNT		());
                queryData.setDisputedFlag 	(action.getDISPUTED_FLAG	());
                queryData.setStatus		 	(action.getSTATUS			());
                queryData.setActaullyDate 	(action.getACTUALLY_DATE	());
                queryData.setRemark		 	(action.getREMARK			());
                queryData.setIssuingBank	(action.getISSUING_BANK	    ());
                queryData.setCardNnum	 	(action.getCARD_NUM		    ());
                queryData.setSecurityCode 	(action.getSECURITY_CODE	());
                resList.add(queryData);
            });
            res.setResBody (resList);

            if(resList.isEmpty()) {
                res.setResCode (QueryRC.cnsq97.getCode());
                res.setResMsg  (QueryRC.cnsq97.getContent());
                throw new TException(res);
            }
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
