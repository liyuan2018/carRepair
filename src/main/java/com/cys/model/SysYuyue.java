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
    private static final String TYPE_DBY="1"; 
    private static final String TYPE_XBY="2"; 
    private static final String TYPE_WX="3"; 
    private static final String TYPE_MR="4"; 
    private static final String TYPE_JC="5"; 

    @Column(name = "yy_type", columnDefinition = "VARCHAR")
    private String yyType;
    @Column(name = "create_time", columnDefinition = "Date")
    private Date createTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "yy_date", columnDefinition = "Date")
    private Date yyDate;
    @Column(name = "yy_time", columnDefinition = "VARCHAR")
    private String yyTime;
  
    @Column(name = "status", columnDefinition = "VARCHAR")
    private String status;
    @Column(name = "car_num", columnDefinition = "VARCHAR")
    private String carNum;
    @Column(name = "car_cj_num", columnDefinition = "VARCHAR")
    private String carCjNum;
    
    @Column(name = "shop_id", columnDefinition = "VARCHAR")
    private String shopId;
    
    public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@ManyToOne(cascade = { CascadeType. REFRESH })
    @JoinColumn(name="yy_qy_user_id")
    private SysUser yyQyUser;
    
    @ManyToOne(cascade = { CascadeType. REFRESH })
    @JoinColumn(name="yy_cz_user_id")
    private SysUser yyCzUser;
    
    public SysUser getYyQyUser() {
		return yyQyUser;
	}

	public void setYyQyUser(SysUser yyQyUser) {
		this.yyQyUser = yyQyUser;
	}

	public SysUser getYyCzUser() {
		return yyCzUser;
	}

	public void setYyCzUser(SysUser yyCzUser) {
		this.yyCzUser = yyCzUser;
	}

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


    public static String getYuyueDesc(String str){
    	if(str ==null){
    		return "";
    	}
    	if(str.equalsIgnoreCase(TYPE_DBY)){
    		return"大保养";
    	}else if(str.equalsIgnoreCase(TYPE_JC)){
    		return"检查";
    	}else if(str.equalsIgnoreCase(TYPE_MR)){
    		return"美容";
    	}else if(str.equalsIgnoreCase(TYPE_WX)){
    		return"维修";
    	}else if(str.equalsIgnoreCase(TYPE_XBY)){
    		return"小保养";
    	}else{
    		return "";
    	}
    }
    
    private static final String STATUS_XJ="1";//新建
    private static final String STATUS_WC="2";//完成
    public static String getStatusDesc(String str){
    	if(str ==null){
    		return "";
    	}
    	if(str.equalsIgnoreCase(STATUS_XJ)){
    		return"新建";
    	}else if(str.equalsIgnoreCase(STATUS_WC)){
    		return"完成";
    	}
    	return "";
    }
}