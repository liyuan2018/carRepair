package com.cys.dto;

import com.cys.model.SysAttachment;
import com.cys.model.SysUser;

import java.util.List;

/**
 * Created by liyuan on 2018/2/5.
 */
public class SysUserDTO extends SysUser {
    private static final long serialVersionUID = 1L;

    /**
     * 营业执照
     */
    List<SysAttachment>  businessLicenses;

    /**
     * 门头照片
     */
    List<SysAttachment>  doorHeadImgs;

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

}
