package com.ntou.svc.query;

import com.ntou.tool.JsonTool;
import lombok.Data;


@Data
public class QueryRes {
    private String resCode      ;
    private String resMsg       ;
    private String resTime      ;

    private Data resBody        ;

    @lombok.Data
    public static class Data {
        private String uid;
        private String uname;
        private String umail;
        private String uphone;
        private String ubirth;
        private String uadd;
        private String uregidate;
    }

    @Override
    public String toString() {return JsonTool.format2Json(this);}
}
