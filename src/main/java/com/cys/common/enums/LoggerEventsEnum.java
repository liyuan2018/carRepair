package com.cys.common.enums;

/**
 *
 *描述:日志事件
 * Created by liyuan on 2018/1/28.
 */
public enum LoggerEventsEnum {

    QUERY("QUERY", "查询"),
    CREATE("INSERT", "新增"),
    UPDATE("UPDATE", "修改"),
    DELETE("DELETE", "删除"),
    UPLOAD("UPLOAD", "上传"),
    DOWNLOAD("DOWNLOAD", "下载"),
    LOGIN("LOGIN", "登录"),
    LOGOUT("LOGOUT", "注销"),
    PUBLISH("PUBLISH", "发布");

    private final String code;

    private final String name;

    private LoggerEventsEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return code;
    }
}



