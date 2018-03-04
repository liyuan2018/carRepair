package com.cys.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name="sys_shop")
@Where(clause = "status > '0'")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("SysShop")
public class SysShop extends BaseModel {


    @Column(name = "shop_name", columnDefinition = "VARCHAR")
    private String shopName;

    @Column(name = "shop_adress", columnDefinition = "VARCHAR")
    private String shopAdress;

    @Column(name = "shop_zb_y", columnDefinition = "VARCHAR")
    private Float shopZbY;

    @Column(name = "shop_zb_x", columnDefinition = "VARCHAR")
    private Float shopZbX;

    @Column(name = "service_type", columnDefinition = "VARCHAR")
    private String serviceType;

    @Column(name = "status", columnDefinition = "VARCHAR")
    private Short status;

    @Column(name = "shop_img_url", columnDefinition = "VARCHAR")
    private String shopImgUrl;

    @Column(name = "yy_img", columnDefinition = "VARCHAR")
    private String yyImg;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP")
    private Date createTime;

    @Column(name = "last_update_time", columnDefinition = "TIMESTAMP")
    private Date lastUpdateTime;

    @Column(name = "ower_user_id", columnDefinition = "VARCHAR")
    private String owerUserId;

    @Column(name = "desc", columnDefinition = "VARCHAR")
    private String desc;

    @Column(name = "shop_level", columnDefinition = "VARCHAR")
    private Short shopLevel;

    @Column(name = "yl1", columnDefinition = "TIMESTAMP")
    private Date yl1;

    @Column(name = "yl2", columnDefinition = "TIMESTAMP")
    private Date yl2;

    @Column(name = "yl3", columnDefinition = "TIMESTAMP")
    private Date yl3;

    @Column(name = "yl4", columnDefinition = "TIMESTAMP")
    private Date yl4;
    
    @Column(name = "dby_time", columnDefinition = "VARCHAR")
    private Integer dbyTime;
    
    @Column(name = "xby_time", columnDefinition = "VARCHAR")
    private Integer xbyTime;
    
    @Column(name = "mr_time", columnDefinition = "VARCHAR")
    private Integer mrTime;
    @Column(name = "jc_time", columnDefinition = "VARCHAR")
    private Integer jcTime;

    public Integer getDbyTime() {
		return dbyTime;
	}

	public void setDbyTime(Integer dbyTime) {
		this.dbyTime = dbyTime;
	}

	public Integer getXbyTime() {
		return xbyTime;
	}

	public void setXbyTime(Integer xbyTime) {
		this.xbyTime = xbyTime;
	}

	public Integer getMrTime() {
		return mrTime;
	}

	public void setMrTime(Integer mrTime) {
		this.mrTime = mrTime;
	}

	public Integer getJcTime() {
		return jcTime;
	}

	public void setJcTime(Integer jcTime) {
		this.jcTime = jcTime;
	}

	private static final long serialVersionUID = 1L;

   

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

    public String getOwerUserId() {
        return owerUserId;
    }

    public void setOwerUserId(String owerUserId) {
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

    public Date getYl1() {
        return yl1;
    }

    public void setYl1(Date yl1) {
        this.yl1 = yl1 == null ? null : yl1;
    }

    public Date getYl2() {
        return yl2;
    }

    public void setYl2(Date yl2) {
        this.yl2 = yl2 == null ? null : yl2;
    }

    public Date getYl3() {
        return yl3;
    }

    public void setYl3(Date yl3) {
        this.yl3 = yl3 == null ? null : yl3;
    }

    public Date getYl4() {
        return yl4;
    }

    public void setYl4(Date yl4) {
        this.yl4 = yl4 == null ? null : yl4;
    }
}