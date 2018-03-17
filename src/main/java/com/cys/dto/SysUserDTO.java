package com.cys.dto;

import com.cys.model.SysAttachment;
import com.cys.model.SysShop;
import com.cys.model.SysUser;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Transient;



/**
 * Created by liyuan on 2018/2/5.
 */
public class SysUserDTO extends SysUser {
	
	@Transient
	private String DTOStatus="200";
	public final static String  IS_NOT_IXEST="304";
	public final static String IS_EXCEPTION="500";
    public String getDTOStatus() {
		return DTOStatus;
	}

	public void setDTOStatus(String dTOStatus) {
		DTOStatus = dTOStatus;
	}

	private static final long serialVersionUID = 1L;

    /**
     * 营业执照
     */
   private List<SysAttachment>  businessLicenses;
    
   

    /**
     * 门头照片
     */
    private List<SysAttachment>  doorHeadImgs;

    /**
     * 车辆信息
     */
    private List<CarInfoDTO> carInfoDTOs;

    /**
     * 用户精度
     */
    private BigDecimal userZbY;

    /**
     * 用户纬度
     */
    private BigDecimal userZbX;

    /**
     * 距离
     */
    private String  location;


    /**
     * 店铺纬度
     */
    private BigDecimal shopZbX;
    /**
     * 店铺经度
     */
    private BigDecimal shopZbY;

    /**
     * 排序
     */
    private List<Sort.Order> orders;

    public List<SysAttachment> getBusinessLicenses() {
        return businessLicenses;
    }

    public List<SysAttachment> getDoorHeadImgs() {
        return doorHeadImgs;
    }

    public void setDoorHeadImgs(List<SysAttachment> doorHeadImgs) {
        this.doorHeadImgs = doorHeadImgs;
    }

    public void setBusinessLicenses(List<SysAttachment> businessLicenses) {
        this.businessLicenses = businessLicenses;
    }

    public List<CarInfoDTO> getCarInfoDTOs() {
        return carInfoDTOs;
    }

    public void setCarInfoDTOs(List<CarInfoDTO> carInfoDTOs) {
        this.carInfoDTOs = carInfoDTOs;
    }

    public BigDecimal getUserZbY() {
        return userZbY;
    }

    public void setUserZbY(BigDecimal userZbY) {
        this.userZbY = userZbY;
    }

    public BigDecimal getUserZbX() {
        return userZbX;
    }

    public void setUserZbX(BigDecimal userZbX) {
        this.userZbX = userZbX;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Sort.Order> getOrders() {
        return orders;
    }

    public BigDecimal getShopZbX() {
        return shopZbX;
    }

    public void setShopZbX(BigDecimal shopZbX) {
        this.shopZbX = shopZbX;
    }

    public BigDecimal getShopZbY() {
        return shopZbY;
    }

    public void setShopZbY(BigDecimal shopZbY) {
        this.shopZbY = shopZbY;
    }

    public void setOrders(List<Sort.Order> orders) {
        this.orders = orders;
    }
}
