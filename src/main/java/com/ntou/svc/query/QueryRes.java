package com.ntou.svc.query;

import com.ntou.tool.JsonTool;
import com.ntou.tool.Utils;
import lombok.Data;

import java.util.ArrayList;


@Data
public class QueryRes {
    private String resCode      ;
    private String resMsg       ;
    private String resTime      = Utils.getDateTime();

    private ArrayList<Data> resBody;

    @lombok.Data
    public static class Data {
        private String buyChannel 		    ;//VARCHAR(2) ,--交易通路(01:虛擬/02:實體)
        private String buyDate 			    ;//VARCHAR(23),--消費時間yyyy/MM/dd HH:mm:ss.SSS
        private String reqPaymentDate	    ;//VARCHAR(23),--請款時間yyyy/MM/dd HH:mm:ss.SSS
        private String shopId				;//VARCHAR(20),--消費店家(統編)
        private String customerId			;//VARCHAR(10),--消費者(keyID)
        private String buyCurrency		    ;//VARCHAR(10),--消費幣別
        private String buyAmount			;//VARCHAR(10),--消費金額
        private String disputedFlag		    ;//VARCHAR(10),--爭議款項註記(00:正常,01異常)
        private String status				;//VARCHAR(2) ,--狀態(00:正常,99:註銷)
        private String actaullyDate		    ;//VARCHAR(23),--此紀錄最終完成的時間yyyy/MM/dd HH:mm:ss.SSS
        private String remark				;//VARCHAR(50),--備註
        private String issuingBank		    ;//VARCHAR(50),--發卡銀行(swiftCode)
        private String cardNnum			    ;//VARCHAR(20),--卡號
        private String securityCode		    ;//VARCHAR(10) --安全碼
    }

    @Override
    public String toString() {return JsonTool.format2Json(this);}
}
