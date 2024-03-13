package com.ntou.exceptions;

import com.ntou.tool.JsonTool;

public class TException extends Exception {
    public String msg;
    public Object o;

    public TException(Object o){
        this.o = o;
        msg = JsonTool.format2Json(o);
    }
}
