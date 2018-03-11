package com.cys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "car_info")
public class CarInfo extends BaseModel {

    @Column(name = "car_num", columnDefinition = "VARCHAR")
    private String carNum;
    @Column(name = "car_age", columnDefinition = "Short")
    private Short carAge;
    @Column(name = "car_jh_num", columnDefinition = "Integer")
    private Integer carJhNum;
    @Column(name = "create_time", columnDefinition = "TIMESTAMP")
    private Date createTime;
    @Column(name = "ower_user_id", columnDefinition = "VARCHAR")
    private String owerUserId;
    @Column(name = "car_type", columnDefinition = "VARCHAR")
    private String carType;
    @Column(name = "car_brand", columnDefinition = "VARCHAR")
    private String carBrand;
    @Column(name = "yl1", columnDefinition = "VARCHAR")
    private String yl1;
    @Column(name = "yl2", columnDefinition = "VARCHAR")
    private String yl2;
    @Column(name = "yl3", columnDefinition = "VARCHAR")
    private String yl3;
    @Column(name = "yl4", columnDefinition = "VARCHAR")
    private String yl4;
    @Column(name = "yl5", columnDefinition = "VARCHAR")
    private String yl5;

    private static final long serialVersionUID = 1L;

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

    public String getOwerUserId() {
        return owerUserId;
    }

    public void setOwerUserId(String owerUserId) {
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