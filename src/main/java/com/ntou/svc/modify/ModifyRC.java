package com.ntou.svc.modify;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ModifyRC {
    cnsm00		("cnsm00" , "成功")
    , cnsm88	("cnsm88" , "驗證有誤")
    , cnsm99	("cnsm99" , "失敗")
    , cnsm98	("cnsm98" , "修改失敗")
    , cnsm97	("cnsm98" , "查無資料")
    ;
    private final String code;
    @Getter
    private final String content;

    @JsonValue
    public String getCode() {return code;}
}
