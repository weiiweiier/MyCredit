package com.ntou.svc.insert;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum InsertRC {
    cnsi00		("cnsi00" , "成功")
    , cnsi88	("cnsi88" , "驗證有誤")
    , cnsi99	("cnsi99" , "失敗")
    , cnsi98	("cnsi98" , "新增失敗")
    , cnsi97	("cnsi97" , "資料有誤")
    ;
    private final String code;
    @Getter
    private final String content;

    @JsonValue
    public String getCode() {return code;}
}
