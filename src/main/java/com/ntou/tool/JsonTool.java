package com.ntou.tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import com.fasterxml.jackson.core.type.TypeReference;

@Log4j2
public class JsonTool {
    private static final ObjectMapper OM_Serial_Default = new ObjectMapper();
    public static String format2Json(final Object str2Json) {
        try {
            return OM_Serial_Default.writeValueAsString(str2Json);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException:", e);
            return jsonError();
        }
    }
    public static <T> T readValue(String str, Class<T> c) {
        try {
            return OM_Serial_Default.readValue(str, c);
        } catch (Exception e) {
            log.error(Common.EXCEPTION, e);
            return null;
        }
    }
    public static <T> T convertValue(Object fromValue, TypeReference<T> toValueTypeRef) {
        return OM_Serial_Default.convertValue(fromValue, toValueTypeRef);
    }
    private static String jsonError() {return  "{\"msg\":\"error\"}";}
}
