package com.cys.dto;

import com.cys.model.SysAttachment;
import com.cys.model.SysShop;
import com.cys.model.SysUser;

import java.util.List;

/**
 * Created by liyuan on 2018/2/5.
 */
public class SysUserDTO extends SysUser {
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
}
