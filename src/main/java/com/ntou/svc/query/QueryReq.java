package com.ntou.svc.query;

import com.ntou.tool.JsonTool;
import lombok.Data;

@Data
public class QueryReq {
    private String cid;

    @Override
    public String toString() {return JsonTool.format2Json(this);}
}
