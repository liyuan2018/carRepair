package com.cys.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "yuyue_order")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("YuyueOrder")
public class YuyueOrder extends BaseModel {
	
	
	

    @Column(name = "should_pay_money", columnDefinition = "Double")
    private Double ShouldPayMoney;
    @Column(name = "pay_noney", columnDefinition = "Double")
    private Double payMoney;
  
    @Column(name = "create_time", columnDefinition = "TIMESTAMP")
    private Date createTime;
    @Column(name = "preferential_info", columnDefinition = "VARCHAR")
    private String preferentialInfo;
    @Column(name = "service_info", columnDefinition = "VARCHAR")
    private String serviceInfo;
    
    @ManyToOne(cascade = { CascadeType. REFRESH })
    @JoinColumn(name="yuyue_id")
    private SysYuyue sysYuyue;
    
    @Column(name = "yy_cz_id", columnDefinition = "VARCHAR")
    private String yyCzId;
    
    public String getYyCzId() {
		return yyCzId;
	}

	public void setYyCzId(String yyCzId) {
		this.yyCzId = yyCzId;
	}

	public String getYyQxId() {
		return yyQxId;
	}

	public void setYyQxId(String yyQxId) {
		this.yyQxId = yyQxId;
	}



	@Column(name = "yy_qx_id", columnDefinition = "VARCHAR")
    private String yyQxId;
    
    public Double getShouldPayMoney() {
		return ShouldPayMoney;
	}

	public void setShouldPayMoney(Double shouldPayMoney) {
		ShouldPayMoney = shouldPayMoney;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public String getPreferentialInfo() {
		return preferentialInfo;
	}

	public void setPreferentialInfo(String preferentialInfo) {
		this.preferentialInfo = preferentialInfo;
	}

	public String getServiceInfo() {
		return serviceInfo;
	}

	public void setServiceInfo(String serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	public SysYuyue getSysYuyue() {
		return sysYuyue;
	}

	public void setSysYuyue(SysYuyue sysYuyue) {
		this.sysYuyue = sysYuyue;
	}

	
   
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

    

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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