package com.ntou.svc.query;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum QueryRC {
    cnsq00		("cnsq00" , "成功")
    , cnsq88	("cnsq88" , "驗證有誤")
    , cnsq99	("cnsq99" , "失敗")
    , cnsq97	("cnsq97" , "查無資料")
    ;
    private final String code;
    @Getter
    private final String content;

    @JsonValue
    public String getCode() {return code;}
}
