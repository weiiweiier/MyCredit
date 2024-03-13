package com.ntou.svc.modify;

import com.ntou.tool.JsonTool;
import com.ntou.tool.Utils;
import lombok.Data;

@Data
public class ModifyRes {
    private String resCode      ;
    private String resMsg       ;
    private String resTime      = Utils.getDateTime();

    @Override
    public String toString() {return JsonTool.format2Json(this);}
}
