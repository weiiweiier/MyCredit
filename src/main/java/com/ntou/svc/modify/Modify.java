package com.ntou.svc.modify;

import com.ntou.db.billrecord.BillrecordDAO;
import com.ntou.exceptions.TException;
import com.ntou.tool.Common;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class Modify {
    public ModifyRes doAPI(ModifyReq req) {
        log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        ModifyRes res = new ModifyRes();

        try {
            if(!req.checkReq()){
                res.setResCode (ModifyRC.cnsm88.getCode());
                res.setResMsg  (ModifyRC.cnsm88.getContent() + "(" +req.getErrMsg() + ")");
                throw new TException(res);
            }
            BillrecordDAO dao = new BillrecordDAO();
            if(dao.selectKey(req.getUuid())==null){//預防攻擊:查不查得到UUID
                res.setResCode (ModifyRC.cnsm97.getCode());
                res.setResMsg  (ModifyRC.cnsm97.getContent());
                throw new TException(res);
            }

            int bInsertCusDateBill = dao.updateFlagCusDateBill(req);
            if(bInsertCusDateBill !=1) {
                res.setResCode (ModifyRC.cnsm98.getCode());
                res.setResMsg  (ModifyRC.cnsm98.getContent());
                throw new TException(res);
            }
            res.setResCode (ModifyRC.cnsm00.getCode());
            res.setResMsg  (ModifyRC.cnsm00.getContent());

        } catch (TException e){
            log.warn(Common.RES + e.msg);
            log.warn(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
            return res;

        } catch (Exception e){
            log.error(Common.EXCEPTION, e);
            res.setResCode (ModifyRC.cnsm99.getCode());
            res.setResMsg  (ModifyRC.cnsm99.getContent());
            log.info(Common.RES + res);
            log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
            return res;
        }
        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        return res;
    }
}
