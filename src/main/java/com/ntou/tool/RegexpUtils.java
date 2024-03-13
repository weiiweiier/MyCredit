package com.ntou.tool;

import java.util.regex.Pattern;

public interface RegexpUtils {

    String STR_FORMAT_ERR = "格式錯誤";
/////////////////////////////////////////////////////

    String RULE_EN = "英";
    String RULE_NUM = "數字";
    String RULE_DATE = "日期";
    String RULE_LENGTH = "長度";
/////////////////////////////////////////////////////

    String C_INVALID_LEN = RULE_LENGTH + STR_FORMAT_ERR;
    String C_INVALID_ENUM_LEN = RULE_EN + RULE_NUM + RULE_LENGTH + STR_FORMAT_ERR;
    String C_INVALID_NUM_LEN = RULE_NUM + RULE_LENGTH + STR_FORMAT_ERR;
    String C_INVALID_DATE = RULE_DATE + STR_FORMAT_ERR;
/////////////////////////////////////////////////////

    Pattern LENGTH_36 = Pattern.compile(".{36}"); //長度36位
    Pattern LENGTH_50 = Pattern.compile(".{0,50}"); //長度50位

    Pattern NUM_2 = Pattern.compile("\\d{2}"); //數字2碼
    Pattern NUM_10 = Pattern.compile("\\d{1,10}"); //數字10位
    Pattern NUM_20 = Pattern.compile("\\d{1,20}"); //數字20位

    Pattern E_NUM_10 = Pattern.compile("[a-zA-Z\\d]{1,10}"); //英數字10位
    Pattern E_NUM_50 = Pattern.compile("[a-zA-Z\\d]{1,50}"); //英數字50位

    Pattern FULLY_DATE = Pattern.compile("\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3}"); //yyyy/MM/dd HH:mm:ss.SSS
}
