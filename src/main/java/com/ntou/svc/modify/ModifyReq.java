package com.ntou.svc.modify;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntou.tool.JsonTool;
import com.ntou.tool.RegexpUtils;
import lombok.Data;

@Data
public class ModifyReq {
    private String uuid            ;//KEY
    private String disputedFlag    ;//爭議款項註記(00:正常,01異常)
    private String status          ;//狀態(00:正常,99:註銷)
    private String actuallyDate    ;//此紀錄最終完成的時間yyyy/MM/dd HH:mm:ss.SSS

    @JsonIgnore
    private String errMsg;
    public boolean checkReq(){
        if(!RegexpUtils.LENGTH_36.matcher(uuid).matches()){
            this.errMsg = "交易編號"+RegexpUtils.C_INVALID_ENUM_LEN;
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

        return true;
    }

    @Override
    public String toString() {return JsonTool.format2Json(this);}
}
