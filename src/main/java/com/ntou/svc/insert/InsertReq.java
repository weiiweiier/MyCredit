package com.ntou.svc.insert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntou.tool.JsonTool;
import com.ntou.tool.RegexpUtils;
import lombok.Data;

@Data
public class InsertReq {
    private String uuid            ;
    private String buyChannel      ;//交易通路(01:虛擬/02:實體)
    private String buyDate         ;//消費時間yyyy/MM/dd HH:mm:ss.SSS
    private String reqPaymentDate  ;//請款時間yyyy/MM/dd HH:mm:ss.SSS
    private String shopId          ;//消費店家(統編)
    private String customerId      ;//消費者(keyID)
    private String buyCurrency     ;//消費幣別
    private String buyAmount       ;//消費金額
    private String disputedFlag    ;//爭議款項註記(00:正常,01異常)
    private String status          ;//狀態(00:正常,99:註銷)
    private String actuallyDate    ;//此紀錄最終完成的時間yyyy/MM/dd HH:mm:ss.SSS
    private String remark          ;//備註
    private String issuingBank     ;//發卡銀行(swiftCode)
    private String cardNum         ;//卡號
    private String securityCode    ;//安全碼

    @JsonIgnore
    private String errMsg;
    public boolean checkReq(){
        if(!RegexpUtils.NUM_2.matcher(buyChannel).matches()){
            this.errMsg = "交易通路"+RegexpUtils.C_INVALID_NUM_LEN;
            return false;
        }
        if(!RegexpUtils.FULLY_DATE.matcher(buyDate).matches()){
            this.errMsg = "消費時間"+RegexpUtils.C_INVALID_DATE;
            return false;
        }
        if(!RegexpUtils.FULLY_DATE.matcher(reqPaymentDate).matches()){
            this.errMsg = "請款時間"+RegexpUtils.C_INVALID_DATE;
            return false;
        }
        if(!RegexpUtils.NUM_20.matcher(shopId).matches()){
            this.errMsg = "消費店家統編"+RegexpUtils.C_INVALID_NUM_LEN;
            return false;
        }
        if(!RegexpUtils.E_NUM_10.matcher(customerId).matches()){
            this.errMsg = "消費者身分證"+RegexpUtils.C_INVALID_ENUM_LEN;
            return false;
        }
        if(!RegexpUtils.E_NUM_10.matcher(buyCurrency).matches()){
            this.errMsg = "消費幣別"+RegexpUtils.C_INVALID_ENUM_LEN;
            return false;
        }
        if(!RegexpUtils.NUM_10.matcher(buyAmount).matches()){
            this.errMsg = "消費金額"+RegexpUtils.C_INVALID_NUM_LEN;
            return false;
        }
        if(!RegexpUtils.NUM_2.matcher(disputedFlag).matches()){
            this.errMsg = "爭議款項註記"+RegexpUtils.C_INVALID_NUM_LEN;
            return false;
        }
        if(!RegexpUtils.NUM_2.matcher(status).matches()){
            this.errMsg = "狀態"+RegexpUtils.C_INVALID_NUM_LEN;
            return false;
        }
        if(!RegexpUtils.FULLY_DATE.matcher(actuallyDate).matches()){
            this.errMsg = "交易完成的時間"+RegexpUtils.C_INVALID_DATE;
            return false;
        }
        if(!RegexpUtils.LENGTH_50.matcher(remark).matches()){
            this.errMsg = "備註"+RegexpUtils.C_INVALID_LEN;
            return false;
        }
        if(!RegexpUtils.E_NUM_50.matcher(issuingBank).matches()){
            this.errMsg = "發卡銀行(swiftCode)"+RegexpUtils.C_INVALID_ENUM_LEN;
            return false;
        }
        if(!RegexpUtils.NUM_20.matcher(cardNum).matches()){
            this.errMsg = "卡號"+RegexpUtils.C_INVALID_NUM_LEN;
            return false;
        }
        if(!RegexpUtils.NUM_10.matcher(securityCode).matches()){
            this.errMsg = "安全碼"+RegexpUtils.C_INVALID_NUM_LEN;
            return false;
        }

        return true;
    }

    @Override
    public String toString() {return JsonTool.format2Json(this);}
}
