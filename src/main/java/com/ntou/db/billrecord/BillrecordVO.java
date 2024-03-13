package com.ntou.db.billrecord;

import com.ntou.tool.JsonTool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillrecordVO {
    private String UUID 		        = "";
    private String BUY_CHANNEL 		    = ""; //VARCHAR(2) ,--交易通路(01:虛擬/02:實體)
    private String BUY_DATE 			= ""; //VARCHAR(23),--消費時間yyyy/MM/dd HH:mm:ss.SSS
    private String REQ_PAYMENT_DATE	    = ""; //VARCHAR(23),--請款時間yyyy/MM/dd HH:mm:ss.SSS
    private String SHOP_ID				= ""; //VARCHAR(20),--消費店家(統編)
    private String CUSTOMER_ID			= ""; //VARCHAR(10),--消費者(keyID)
    private String BUY_CURRENCY		    = ""; //VARCHAR(10),--消費幣別
    private String BUY_AMOUNT			= ""; //VARCHAR(10),--消費金額
    private String DISPUTED_FLAG		= ""; //VARCHAR(10),--爭議款項註記(00:正常,01異常)
    private String STATUS				= ""; //VARCHAR(2) ,--狀態(00:正常,99:註銷)
    private String ACTUALLY_DATE		= ""; //VARCHAR(23),--此紀錄最終完成的時間yyyy/MM/dd HH:mm:ss.SSS
    private String REMARK				= ""; //VARCHAR(50),--備註
    private String ISSUING_BANK		    = ""; //VARCHAR(50),--發卡銀行(swiftCode)
    private String CARD_NUM			    = ""; //VARCHAR(20),--卡號
    private String SECURITY_CODE		= ""; //VARCHAR(10) --安全碼

    public static String encodeFormSQL(String s) {return s != null && !s.isEmpty() ? s : "";}

    @Override
    public String toString() {return JsonTool.format2Json(this);}
}
