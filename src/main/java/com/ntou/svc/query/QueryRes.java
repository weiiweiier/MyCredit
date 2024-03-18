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

    private Data resBody;

    @lombok.Data
    public static class Data {
        private String CID  			= "";//VARCHAR(20) ,--使用者身分證(外國護照預留欄位長度)
        private String REGIDATE 		= "";//VARCHAR(23)	,--信用卡銀行通過(核發)時間yyyy/MM/dd HH:mm:ss.SSS
        private String ISSUING_BANK 	= "";//VARCHAR(50) ,--核卡銀行(swiftCode)
        private String CARD_NUM			= "";//VARCHAR(20) ,--信用卡號碼
        private String STATUS			= "";//VARCHAR(2)  ,--信用卡狀態(00:正常,99:註銷)
        private String REMARK			= "";//VARCHAR(50)  --備註
    }

    @Override
    public String toString() {return JsonTool.format2Json(this);}
}
