package com.ntou.svc.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntou.tool.JsonTool;
import com.ntou.tool.RegexpUtils;
import lombok.Data;

@Data
public class QueryReq {
    private String cid;
    private String startDate;
    private String endDate;

    @JsonIgnore
    private String errMsg;
    public boolean checkReq(){
        if(!RegexpUtils.E_NUM_10.matcher(cid).matches()){
            this.errMsg = "消費者身分證"+RegexpUtils.C_INVALID_ENUM_LEN;
            return false;
        }
        if(!RegexpUtils.FULLY_DATE.matcher(startDate).matches()){
            this.errMsg = "開始時間"+RegexpUtils.C_INVALID_DATE;
            return false;
        }
        if(!RegexpUtils.FULLY_DATE.matcher(endDate).matches()){
            this.errMsg = "結束時間"+RegexpUtils.C_INVALID_DATE;
            return false;
        }

        return true;
    }
    @Override
    public String toString() {return JsonTool.format2Json(this);}
}
