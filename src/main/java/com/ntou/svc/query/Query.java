package com.ntou.svc.query;

import com.ntou.db.customer.CustomerVO;
import com.ntou.tool.Common;
import com.ntou.tool.Utils;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class Query {
//    String sIP = "";
//    public Query(HttpServletRequest request) {
//        sIP = request.getRemoteAddr();
//    }
    public QueryRes doAPI(QueryReq req) throws Exception {
        log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        QueryRes res = new QueryRes();
        res.setResTime (Utils.getDateTime());
        try {
            CustomerVO vo = new CustomerVO();
            vo.setCID(req.getCid());

            CustomerVO cvo = new CustomerVO.CustomerDAO().selectCostomerID(vo);
            QueryRes.Data queryData = new QueryRes.Data();
            queryData.setUid          (cvo.getCID());
            queryData.setUname        (cvo.getCH_NAME());
            queryData.setUmail        (cvo.getMAIL());
            queryData.setUphone       (cvo.getPHONE());
            queryData.setUbirth       (cvo.getBIRTH());
            queryData.setUadd         (cvo.getADDR());
            queryData.setUregidate    (cvo.getREGIDATE());
            res.setResBody            (queryData);
            res.setResCode            ("M000");
            res.setResMsg             ("成功");

        } catch (Exception e){
            res.setResCode            ("M999");
            res.setResMsg             ("失敗");
            log.error(Common.EXCEPTION, e);
            log.info(Common.API_DIVIDER +  Common.END_B + Common.API_DIVIDER);
            throw new Exception(e);
        }
        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER +  Common.END_B + Common.API_DIVIDER);
        return res;
    }
}
