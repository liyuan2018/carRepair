package com.cys.common.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by liyuan on 2018/3/16.
 */
public class ErrorMessage extends LinkedHashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 811899486654614381L;
    private String code;
    private String message;
    private String moreInfo;
    private Object data;

    private void setErrorMap() {
        HashMap messageMap = new HashMap();
        messageMap.put("code", this.code);
        messageMap.put("message", this.message);
        messageMap.put("moreInfo", this.moreInfo);
        messageMap.put("data", this.data);
        this.put("error", messageMap);
    }

    public ErrorMessage(String code, String message, String moreInfo) {
        this.setCode(code);
        this.setMessage(message);
        this.setMoreInfo(moreInfo);
        this.setErrorMap();
    }

    public ErrorMessage(String code, String message) {
        this.setCode(code);
        this.setMessage(message);
        this.setErrorMap();
    }

    public ErrorMessage(String code, String message, Object data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
        this.setErrorMap();
    }

    public ErrorMessage(String message) {
        this.setMessage(message);
        this.setErrorMap();
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMoreInfo() {
        return this.moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String toString() {
        return "error{ code=\'" + this.code + '\'' + ", message=\'" + this.message + '\'' + ", moreInfo=\'" + this.moreInfo + '\'' + '}';
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
