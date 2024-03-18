package com.ntou.svc.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntou.tool.JsonTool;
import com.ntou.tool.RegexpUtils;
import lombok.Data;

@Data
public class QueryReq {
    private String cid;
    private String cardNum;

    @JsonIgnore
    private String errMsg;
    public boolean checkReq(){
        if(!RegexpUtils.E_NUM_10.matcher(cid).matches()){
            this.errMsg = "消費者身分證"+RegexpUtils.C_INVALID_ENUM_LEN;
            return false;
        }
        if(!RegexpUtils.NUM_20.matcher(cardNum).matches()){
            this.errMsg = "信用卡號"+RegexpUtils.C_INVALID_NUM_LEN;
            return false;
        }

        return true;
    }
    @Override
    public String toString() {return JsonTool.format2Json(this);}
}
