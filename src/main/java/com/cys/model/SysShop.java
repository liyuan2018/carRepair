package com.cys.model;

import java.io.Serializable;
import java.util.Date;

public class SysShop implements Serializable {
    private Integer id;

    private String shopName;

    private String shopAdress;

    private Float shopZbY;

    private Float shopZbX;

    private String serviceType;

    private Short status;

    private String shopImgUrl;

    private String yyImg;

    private Date createTime;

    private Date lastUpdateTime;

    private Integer owerUserId;

    private String desc;

    private Short shopLevel;

    private String yl1;

    private String yl2;

    private String yl3;

    private String yl4;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopAdress() {
        return shopAdress;
    }

    public void setShopAdress(String shopAdress) {
        this.shopAdress = shopAdress == null ? null : shopAdress.trim();
    }

    public Float getShopZbY() {
        return shopZbY;
    }

    public void setShopZbY(Float shopZbY) {
        this.shopZbY = shopZbY;
    }

    public Float getShopZbX() {
        return shopZbX;
    }

    public void setShopZbX(Float shopZbX) {
        this.shopZbX = shopZbX;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getShopImgUrl() {
        return shopImgUrl;
    }

    public void setShopImgUrl(String shopImgUrl) {
        this.shopImgUrl = shopImgUrl == null ? null : shopImgUrl.trim();
    }

    public String getYyImg() {
        return yyImg;
    }

    public void setYyImg(String yyImg) {
        this.yyImg = yyImg == null ? null : yyImg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getOwerUserId() {
        return owerUserId;
    }

    public void setOwerUserId(Integer owerUserId) {
        this.owerUserId = owerUserId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Short getShopLevel() {
        return shopLevel;
    }

    public void setShopLevel(Short shopLevel) {
        this.shopLevel = shopLevel;
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
}