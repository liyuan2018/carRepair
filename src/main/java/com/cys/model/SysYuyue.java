package com.cys.model;

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="sys_yuyue")
@Where(clause = "status > '0'")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("SysYuyue")
public class SysYuyue extends BaseModel {
    private static final long serialVersionUID = 1L;

    @Column(name = "yy_type", columnDefinition = "VARCHAR")
    private String yyType;
    @Column(name = "create_time", columnDefinition = "Date")
    private Date createTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "yy_date", columnDefinition = "Date")
    private Date yyDate;
    @Column(name = "yy_time", columnDefinition = "VARCHAR")
    private String yyTime;
    @Column(name = "yy_qy_user_id", columnDefinition = "VARCHAR")
    private String yyQyUserId;
    @Column(name = "yy_cz_user_id", columnDefinition = "VARCHAR")
    private String yyCzUserId;
    @Column(name = "status", columnDefinition = "VARCHAR")
    private String status;
    @Column(name = "car_num", columnDefinition = "VARCHAR")
    private String carNum;
    @Column(name = "car_cj_num", columnDefinition = "VARCHAR")
    private String carCjNum;

    public String getYyType() {
        return yyType;
    }

    public void setYyType(String yyType) {
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

    public String getYyTime() {
        return yyTime;
    }

    public void setYyTime(String yyTime) {
        this.yyTime = yyTime;
    }

    public String getYyQyUserId() {
        return yyQyUserId;
    }

    public void setYyQyUserId(String yyQyUserId) {
        this.yyQyUserId = yyQyUserId;
    }

    public String getYyCzUserId() {
        return yyCzUserId;
    }

    public void setYyCzUserId(String yyCzUserId) {
        this.yyCzUserId = yyCzUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCarCjNum() {
        return carCjNum;
    }

    public void setCarCjNum(String carCjNum) {
        this.carCjNum = carCjNum;
    }


}