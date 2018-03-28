package com.cys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cys.common.domain.Query;
import com.cys.constants.HardCode;
import com.cys.dao.SysUserMapper;
import com.cys.dto.CarInfoDTO;
import com.cys.dto.SysUserDTO;
import com.cys.dto.SysUserShopDTO;
import com.cys.enums.SysUserRelEnum;
import com.cys.exception.BusinessException;
import com.cys.model.SysAttachment;
import com.cys.model.SysShop;
import com.cys.model.SysUser;
import com.cys.model.SysYuyue;
import com.cys.repository.SysShopRepository;
import com.cys.repository.SysUserRepository;
import com.cys.service.ICarInfoService;
import com.cys.service.ISysAttachmentService;
import com.cys.service.ISysUserRelService;
import com.cys.service.ISysUserService;
import com.cys.util.LocationUtils;
import com.cys.util.WXUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private ICarInfoService carInfoService;

    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Override
    public Page<SysUserDTO> find(SysUserDTO sysUserDTO, Query query) throws Exception {
        logger.info("查询用户信息：");
        Pageable pageable = query.getPageable();
        if(pageable.getSort() != null){
            sysUserDTO.setOrders(IteratorUtils.toList(pageable.getSort().iterator()));
        }
        Page<SysUserDTO> sysUserPages = sysUserMapper.find(sysUserDTO,pageable);
        List<SysUserDTO> sysUserDTOs = sysUserPages.getContent();
        if(!CollectionUtils.isEmpty(sysUserDTOs)){
            addRelInfoSysUserDTO(sysUserDTOs);
            //设置距离
            setLocation(sysUserDTO.getUserZbX(),sysUserDTO.getUserZbY(),sysUserDTOs);
        }
        return sysUserPages;
    }

    @Override
    public List<SysUserDTO> find(SysUserDTO sysUserDTO) throws Exception{
        List<SysUserDTO> sysUserDTOs = sysUserMapper.find(sysUserDTO);
        addRelInfoSysUserDTO(sysUserDTOs);
        return sysUserDTOs;
    }

    @Override
    public List<SysUserDTO> findDTOByShopId(String shopId) throws Exception {
        SysUserDTO sysUserDTO = new SysUserDTO();
        sysUserDTO.setShopId(shopId);
        List<SysUserDTO> sysUserDTOs = sysUserMapper.find(sysUserDTO);
        addRelInfoSysUserDTO(sysUserDTOs);
        return sysUserDTOs;
    }

    @Override
    public List<SysUser> findByShopId(String shopId) throws Exception {
        List<SysUser> sysUsers = sysUserRepository.findByShopId(shopId);
        return sysUsers;
    }

    @Override
    public SysUserDTO register(SysUserDTO sysUserDTO) throws Exception {
    	SysUser sysUser1 = new SysUser();
    	if(!StringUtils.isEmpty(sysUserDTO.getOpenId())){
    		sysUser1.setOpenId(sysUserDTO.getOpenId());
    		SysUser sysUser = sysUserRepository.findOne(sysUser1);
    		if(sysUser!=null){
    			if(StringUtils.isNotEmpty(sysUserDTO.getName())){
    				sysUser.setName(sysUserDTO.getName());
    			}
    			if(StringUtils.isNotEmpty(sysUserDTO.getMobile())){
    				sysUser.setMobile(sysUserDTO.getMobile());
    			}
    			sysUser = sysUserRepository.save(sysUser);
    			PropertyUtils.copyProperties(sysUserDTO,sysUser);
    			sysUserDTO.setDTOStatus(sysUserDTO.STATUS_QY);
    			return sysUserDTO;
    		}
    	}
		
        sysUserDTO.setCreatorTime(new Date());
        SysUser sysUser = new SysUser();
        PropertyUtils.copyProperties(sysUser,sysUserDTO);
        sysUser.setStatus(SysUser.STATUS_QY);
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

    @Override
    public String parseWeiXinUserData(String code, String encryptedData, String iv) throws Exception {
        JSONObject result = WXUtils.getSessionKeyOropenid(code);
        String openId = (String) result.get(HardCode.Key.WEI_XIN_OPEN_ID.toString());
        String sessionKey = (String) result.get(HardCode.Key.WEI_XIN_SESSION_KEY.toString());
        Integer errcode = (Integer) result.get(HardCode.Key.WEI_XIN_ERR_CODE.toString());
        String errmsg = (String) result.get(HardCode.Key.WEI_XIN_ERR_MSG.toString());
        if(null != errcode && 40029==errcode){
            result = WXUtils.getSessionKeyOropenid(code);
            openId = (String) result.get(HardCode.Key.WEI_XIN_OPEN_ID.toString());
            sessionKey = (String) result.get(HardCode.Key.WEI_XIN_SESSION_KEY.toString());
            errcode = (Integer) result.get(HardCode.Key.WEI_XIN_ERR_CODE.toString());
            errmsg = (String) result.get(HardCode.Key.WEI_XIN_ERR_MSG.toString());
        }
        if(errcode != null){
            throw new BusinessException("微信获取openId错误，错误代码："+errcode+",错误信息："+errmsg);
        }
        JSONObject userInfoJson = WXUtils.getUserInfo(encryptedData,sessionKey,iv);
        return userInfoJson.toJSONString();
    }

    @Override
    public SysUserDTO findDtoById(String id) throws Exception {
        SysUserDTO sysUserDTO = sysUserMapper.findById(id);
        List<SysUserDTO> sysUserDTOs = new ArrayList<>();
        sysUserDTOs.add(sysUserDTO);
        sysUserDTOs = addRelInfoSysUserDTO(sysUserDTOs);
        return sysUserDTOs.get(0);
    }

    @Override
    public SysUserDTO updateDto(SysUserDTO sysUserDTO) throws Exception {
        SysUser sysUser = new SysUser();
        PropertyUtils.copyProperties(sysUser,sysUserDTO);
        sysUserRepository.save(sysUser);
        return sysUserDTO;
    }


    private void setLocation(BigDecimal userZbX,BigDecimal userZbY,List<SysUserDTO> sysUserDTOs){
        if(!CollectionUtils.isEmpty(sysUserDTOs)){
            sysUserDTOs.forEach(sysUserDTO -> {
                if(userZbX != null && userZbY != null && sysUserDTO.getShopZbX() != null && sysUserDTO.getShopZbY() != null){
                    double location = LocationUtils.getDistance(userZbX.doubleValue(),userZbY.doubleValue(),sysUserDTO.getShopZbX().doubleValue(),sysUserDTO.getShopZbY().doubleValue());
                    BigDecimal result = BigDecimal.valueOf(location);
                    result = result.setScale(2,BigDecimal.ROUND_HALF_UP);
                    sysUserDTO.setLocation(result.toString());
                }
            });
        }
    }
    
    
    @Override
    public Page<SysUser> adminFind(SysUser sysUser, Query query) throws Exception {
        Pageable pageable = query.getPageable();
        Page<SysUser> sysUserPages = sysUserRepository.find(sysUser,pageable);
        List<SysUser> sysYuyues = sysUserPages.getContent();
        //List<SysYuyue> sysYuyue = convertToSysUserDTO(sysUsers);
        return new PageImpl<SysUser>(sysYuyues,pageable,sysUserPages.getTotalElements());
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

    private List<SysUserDTO> addRelInfoSysUserDTO(List<SysUserDTO> sysUserDTOs) throws Exception{
        List<String> userIds = sysUserDTOs.stream().map(SysUser::getId).collect(Collectors.toList());
        //车辆信息
        List<CarInfoDTO> carInfoDTOs = new ArrayList<>();
        if(!CollectionUtils.isEmpty(userIds)){
           carInfoDTOs = carInfoService.findCarInfoDTOByUserIds(userIds.toArray(new String[userIds.size()]));
        }
        Map<String, List<CarInfoDTO>> userIdMap = carInfoDTOs.stream().collect(Collectors.groupingBy(CarInfoDTO::getOwerUserId));
        for (SysUserDTO sysUserDTO:sysUserDTOs){
            List<CarInfoDTO> carInfoDTOList = userIdMap.get(sysUserDTO.getId());
            sysUserDTO.setCarInfoDTOs(carInfoDTOList);
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
        PropertyUtils.copyProperties(sysShop,sysUserShopDTO.getSysShop());
        sysShop.setOwerUserId(sysUser.getId());
        sysShopRepository.save(sysShop);
        sysUserShopDTO.setId(sysShop.getId());
        saveRelationInfo(sysUserShopDTO);
        //保存关联数据
        return sysUserShopDTO;
	}

    /**
     * 保存关联信息
     * @param sysUserShopDTO
     */
    private void saveRelationInfo(SysUserShopDTO sysUserShopDTO){
        List<SysAttachment> businessLicenses = sysUserShopDTO.getBusinessLicenses();
        List<SysAttachment>  doorHeadImgs = sysUserShopDTO.getDoorHeadImgs();
        //营业执照
        sysUserRelService.createRelations(sysUserShopDTO.getId(),SysUserRelEnum.BUSINESS_LICENSE.toString(),businessLicenses);
        //门头照
        sysUserRelService.createRelations(sysUserShopDTO.getId(),SysUserRelEnum.DOOR_HEAD_IMG.toString(),doorHeadImgs);
    }

	@Override
	public SysUserDTO findDtoByOpenId(String id) throws Exception {
        SysUserDTO sysUserDTO = new SysUserDTO();
        sysUserDTO.setOpenId(id);
        List<SysUserDTO> sysUserDTOs = sysUserMapper.find(sysUserDTO);
        if(!CollectionUtils.isEmpty(sysUserDTOs)){
           addRelInfoSysUserDTO(sysUserDTOs);
            return sysUserDTOs.get(0);
        }else {
        	SysUser u= new SysUser();
        	u.setUserType(0);//0为车主
        	u.setOpenId(id);
        	
        	u = sysUserRepository.save(u);
        	PropertyUtils.copyProperties(sysUserDTO,u);
            //result.setDTOStatus(SysUserDTO.IS_NOT_IXEST);
            return sysUserDTO;
        }
	}
}
