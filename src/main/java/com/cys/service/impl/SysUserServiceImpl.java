package com.cys.service.impl;

import com.cys.common.domain.Query;
import com.cys.dto.SysUserDTO;
import com.cys.dto.SysUserShopDTO;
import com.cys.enums.SysUserRelEnum;
import com.cys.model.SysAttachment;
import com.cys.model.SysShop;
import com.cys.model.SysUser;
import com.cys.repository.SysShopRepository;
import com.cys.repository.SysUserRepository;
import com.cys.service.ISysAttachmentService;
import com.cys.service.ISysUserRelService;
import com.cys.service.ISysUserService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liyuan on 2018/1/31.
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser,String> implements ISysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysShopRepository sysShopRepository;
    
    @Autowired
    private ISysAttachmentService sysAttachmentService;

    @Autowired
    private ISysUserRelService sysUserRelService;
    @Override
    public Page<SysUserDTO> find(SysUserDTO sysUserDTO, Query query) throws Exception {
        Pageable pageable = query.getPageable();
        Page<SysUser> sysUserPages = sysUserRepository.find(sysUserDTO,pageable);
        List<SysUser> sysUsers = sysUserPages.getContent();
        List<SysUserDTO> sysUserDTOs = convertToSysUserDTO(sysUsers);
        return new PageImpl<SysUserDTO>(sysUserDTOs,pageable,sysUserPages.getTotalElements());
    }

    @Override
    public List<SysUserDTO> find(SysUserDTO sysUserDTO) throws Exception{
        List<SysUser> sysUsers = sysUserRepository.find(sysUserDTO);
        List<SysUserDTO> sysUserDTOs = convertToSysUserDTO(sysUsers);
        return sysUserDTOs;
    }

    @Override
    public List<SysUserDTO> findDTOByShopId(String shopId) throws Exception {
        List<SysUser> sysUsers = sysUserRepository.findByShopId(shopId);
        List<SysUserDTO> sysUserDTOs = convertToSysUserDTO(sysUsers);

        return sysUserDTOs;
    }

    @Override
    public List<SysUser> findByShopId(String shopId) throws Exception {
        List<SysUser> sysUsers = sysUserRepository.findByShopId(shopId);
        return sysUsers;
    }

    @Override
    public SysUserDTO register(SysUserDTO sysUserDTO) throws Exception {
        sysUserDTO.setCreatorTime(new Date());
        SysUser sysUser = new SysUser();
        PropertyUtils.copyProperties(sysUser,sysUserDTO);
        sysUserRepository.save(sysUser);
        sysUserDTO.setId(sysUser.getId());

        return sysUserDTO;
    }

    @Override
    public SysAttachment upload(MultipartFile mFile) throws Exception {
        String module = "sysUser";
        String subModule = "detail";
        SysAttachment sysAttachment = new SysAttachment();
        sysAttachment.setModule(module);
        sysAttachment.setSubModule(subModule);
        sysAttachment = sysAttachmentService.upload(sysAttachment, mFile);
        return sysAttachment;
    }


    private void saveRelation(SysUserDTO sysUserDTO){
        //营业执照
        List<SysAttachment> businessLicenses = sysUserDTO.getBusinessLicenses();
        if(!CollectionUtils.isEmpty(businessLicenses)){
            sysUserRelService.createRelations(sysUserDTO.getId(), SysUserRelEnum.BUSINESS_LICENSE.toString(),businessLicenses);
        }
        //门头照片
        List<SysAttachment> doorHeadImgs = sysUserDTO.getDoorHeadImgs();
        if(!CollectionUtils.isEmpty(doorHeadImgs)){
            sysUserRelService.createRelations(sysUserDTO.getId(), SysUserRelEnum.DOOR_HEAD_IMG.toString(),doorHeadImgs);
        }
    }

    private List<SysUserDTO> convertToSysUserDTO(List<SysUser> sysUsers) throws Exception{
        List<SysUserDTO> sysUserDTOs = new ArrayList<>();
        for (SysUser sysUser:sysUsers){
            SysUserDTO sysUserDTO = new SysUserDTO();
            PropertyUtils.copyProperties(sysUserDTO,sysUser);
            sysUserDTOs.add(sysUserDTO);
        }
        return sysUserDTOs;
    }

	@Override
	public SysUserShopDTO registerShop(SysUserShopDTO sysUserShopDTO) throws Exception {
		sysUserShopDTO.setCreatorTime(new Date());
        SysUser sysUser = new SysUser();
        PropertyUtils.copyProperties(sysUser,sysUserShopDTO);
        sysUserRepository.save(sysUser);
        SysShop sysShop = new SysShop();
        PropertyUtils.copyProperties(sysShop,sysUserShopDTO);
        sysShop.setOwerUserId(sysUser.getId());
        sysShopRepository.save(sysShop);
        sysUserShopDTO.setId(sysUser.getId());

        return sysUserShopDTO;
	}
}
