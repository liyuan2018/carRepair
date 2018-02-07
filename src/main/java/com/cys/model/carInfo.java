package com.cys.model;

import java.io.Serializable;
import java.util.Date;

public class carInfo implements Serializable {
    private Integer id;

    private String carNum;

    private Short carAge;

    private Integer carJhNum;

    private Date createTime;

    private Integer owerUserId;

    private String carType;

    private String carBrand;

    private String yl1;

    private String yl2;

    private String yl3;

    private String yl4;

    private String yl5;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum == null ? null : carNum.trim();
    }

    public Short getCarAge() {
        return carAge;
    }

    public void setCarAge(Short carAge) {
        this.carAge = carAge;
    }

    public Integer getCarJhNum() {
        return carJhNum;
    }

    public void setCarJhNum(Integer carJhNum) {
        this.carJhNum = carJhNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOwerUserId() {
        return owerUserId;
    }

    public void setOwerUserId(Integer owerUserId) {
        this.owerUserId = owerUserId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand == null ? null : carBrand.trim();
    }

    public String getYl1() {
        return yl1;
    }

    public void setYl1(String yl1) {
        this.yl1 = yl1 == null ? null : yl1.trim();
    }

    public String getYl2() {
        return yl2;
    }

    public void setYl2(String yl2) {
        this.yl2 = yl2 == null ? null : yl2.trim();
    }

    public String getYl3() {
        return yl3;
    }

    public void setYl3(String yl3) {
        this.yl3 = yl3 == null ? null : yl3.trim();
    }

    public String getYl4() {
        return yl4;
    }

    public void setYl4(String yl4) {
        this.yl4 = yl4 == null ? null : yl4.trim();
    }

    public String getYl5() {
        return yl5;
    }

    public void setYl5(String yl5) {
        this.yl5 = yl5 == null ? null : yl5.trim();
    }
}