package com.cys.model;

import com.cys.common.enums.LoggerEventsEnum;

/**
 * 信息类
 * Created by liyuan on 2018/1/28.
 */

public class LoggerModel {
    /**id**/
    private String id;
    /**信息**/
    private String message;
    /**用户**/
    private String userName;
    /**主体ID**/
    private String principalId;
    /**主体类型**/
    private String principalType;
    /**用户ID**/
    private String userId;
    /**总计时间**/
    private long usedTime;
    /**ip地址**/
    private String ip;
    /**操作事件**/
    private LoggerEventsEnum evt;
    /**操作类型**/
    private String type;
    /**
     * 打印日志所在的类名
     */
    private String className;

    /** 操作对象**/
    private String operateObject;

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public long getUsedTime() {
        return usedTime;
    }
    public void setUsedTime(long usedTime) {
        this.usedTime = usedTime;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public LoggerEventsEnum getEvt() {
        return evt;
    }
    public void setEvt(LoggerEventsEnum evt) {
        this.evt = evt;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public String getPrincipalType() {
        return principalType;
    }

    public void setPrincipalType(String principalType) {
        this.principalType = principalType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperateObject() {
        return operateObject;
    }

    public void setOperateObject(String operateObject) {
        this.operateObject = operateObject;
    }
}
