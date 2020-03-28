package com.ordersystem.data;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;


//dialog保存以后，需要的返回信息
public class ReturnData {
    private int code = 0;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public static ReturnData returnError(int code, String error) {
        ReturnData retData = new ReturnData();
        retData.setCode(code);
        retData.setMsg(error);
        retData.setData(null);
        return retData;
    }

    public static ReturnData returnError(int code, String msg, Object data) {
        ReturnData retData = new ReturnData();
        retData.setCode(code);
        retData.setMsg(msg);
        retData.setData(data);
        return retData;
    }

    public static ReturnData returnData(String msg, Object data) {
        ReturnData retData = new ReturnData();
        retData.setCode(1000);
        retData.setMsg(msg);
        retData.setData(data);
        return retData;
    }

    public static ReturnData returnSuccess(JSONObject data) {
        ReturnData retData = new ReturnData();
        retData.setCode(1000);
        retData.setData(data);
        return retData;
    }

    public static ReturnData returnData(String msg) {
        return returnData(msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
