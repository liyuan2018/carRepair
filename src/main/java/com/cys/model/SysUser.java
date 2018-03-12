package com.cys.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="sys_user")
@Where(clause = "status > '0'")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("SysUser")
public class SysUser extends BaseModel {
    private static final long serialVersionUID = 1L;

    @Column(name = "user_type", columnDefinition = "VARCHAR")
    private Short userType;
    
    @Column(name = "open_id", columnDefinition = "VARCHAR")
    private String openId;

    public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "name", columnDefinition = "VARCHAR")
    private String name;

    @Column(name = "frist_name", columnDefinition = "VARCHAR")
    private String fristName;

    @Column(name = "second_name", columnDefinition = "VARCHAR")
    private String secondName;

    @Column(name = "account", columnDefinition = "VARCHAR")
    private String account;

    @Column(name = "password", columnDefinition = "VARCHAR")
    private String password;

    @Column(name = "office_phone", columnDefinition = "VARCHAR")
    private String officePhone;

    @Column(name = "home_phone", columnDefinition = "VARCHAR")
    private String homePhone;

    @Column(name = "mobile", columnDefinition = "VARCHAR")
    private String mobile;

    @Column(name = "address", columnDefinition = "VARCHAR")
    private String address;

    @Column(name = "avatar", columnDefinition = "VARCHAR")
    private String avatar;

    @Column(name = "wechat_no", columnDefinition = "VARCHAR")
    private String wechatNo;

    @Column(name = "sex", columnDefinition = "VARCHAR")
    private String sex;

    @Column(name = "email", columnDefinition = "VARCHAR")
    private String email;

    @Column(name = "error_count", columnDefinition = "VARCHAR")
    private Integer errorCount;

    @Column(name = "error_time", columnDefinition = "TIMESTAMP")
    private Date errorTime;

    @Column(name = "modified_time", columnDefinition = "TIMESTAMP")
    private Date modifiedTime;

    @Column(name = "creator_time", columnDefinition = "TIMESTAMP")
    private Date creatorTime;

    @Column(name = "status", columnDefinition = "VARCHAR")
    private String status;

    @Column(name = "shop_id", columnDefinition = "VARCHAR")
    private String shopId;

    @Column(name = "work_year", columnDefinition = "VARCHAR")
    private Short workYear;

    @Column(name = "description", columnDefinition = "VARCHAR")
    private String description;
    /**
     * 
     */
    @Column(name = "type_dby", columnDefinition = "VARCHAR")
    private Integer typeDby;
    
    @Column(name = "type_xby", columnDefinition = "VARCHAR")
    private Integer typeXby;
    
    @Column(name = "type_wx", columnDefinition = "VARCHAR")
    private Integer typeWx;
    
    @Column(name = "type_mr", columnDefinition = "VARCHAR")
    private Integer typeMr;
    
    @Column(name = "type_jc", columnDefinition = "VARCHAR")
    private Integer typeJc;
    
    @Column(name = "jf", columnDefinition = "VARCHAR")
    private Integer jf;
    
    @Column(name = "dj", columnDefinition = "VARCHAR")
    private Integer dj;

    public Integer getTypeDby() {
		return typeDby;
	}

	public void setTypeDby(Integer typeDby) {
		this.typeDby = typeDby;
	}

	public Integer getTypeXby() {
		return typeXby;
	}

	public void setTypeXby(Integer typeXby) {
		this.typeXby = typeXby;
	}

	public Integer getTypeWx() {
		return typeWx;
	}

	public void setTypeWx(Integer typeWx) {
		this.typeWx = typeWx;
	}

	public Integer getTypeMr() {
		return typeMr;
	}

	public void setTypeMr(Integer typeMr) {
		this.typeMr = typeMr;
	}

	public Integer getTypeJc() {
		return typeJc;
	}

	public void setTypeJc(Integer typeJc) {
		this.typeJc = typeJc;
	}

	public Integer getJf() {
		return jf;
	}

	public void setJf(Integer jf) {
		this.jf = jf;
	}

	public Integer getDj() {
		return dj;
	}

	public void setDj(Integer dj) {
		this.dj = dj;
	}

	public Short getUserType() {
        return userType;
    }

    public void setUserType(Short userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName == null ? null : fristName.trim();
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName == null ? null : secondName.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone == null ? null : officePhone.trim();
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone == null ? null : homePhone.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo == null ? null : wechatNo.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public Date getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(Date errorTime) {
        this.errorTime = errorTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Date getCreatorTime() {
        return creatorTime;
    }

    public void setCreatorTime(Date creatorTime) {
        this.creatorTime = creatorTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Short getWorkYear() {
        return workYear;
    }

    public void setWorkYear(Short workYear) {
        this.workYear = workYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}