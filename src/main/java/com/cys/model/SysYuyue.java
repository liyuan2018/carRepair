package com.cys.model;

import java.io.Serializable;
import java.util.Date;

public class SysYuyue implements Serializable {
    private Integer id;

    private Short yyType;

    private Date createTime;

    private Date yyDate;

    private Short yyTime;

    private Integer yyQyUserId;

    private Integer yyCzUserId;

    private Short status;

    private String carNum;

    private String carCjNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getYyType() {
        return yyType;
    }

    public void setYyType(Short yyType) {
        this.yyType = yyType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getYyDate() {
        return yyDate;
    }

    public void setYyDate(Date yyDate) {
        this.yyDate = yyDate;
    }

    public Short getYyTime() {
        return yyTime;
    }

    public void setYyTime(Short yyTime) {
        this.yyTime = yyTime;
    }

    public Integer getYyQyUserId() {
        return yyQyUserId;
    }

    public void setYyQyUserId(Integer yyQyUserId) {
        this.yyQyUserId = yyQyUserId;
    }

    public Integer getYyCzUserId() {
        return yyCzUserId;
    }

    public void setYyCzUserId(Integer yyCzUserId) {
        this.yyCzUserId = yyCzUserId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum == null ? null : carNum.trim();
    }

    public String getCarCjNum() {
        return carCjNum;
    }

    public void setCarCjNum(String carCjNum) {
        this.carCjNum = carCjNum == null ? null : carCjNum.trim();
    }
}