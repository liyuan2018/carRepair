package com.cys.service.impl;

import com.cys.common.domain.Query;
import com.cys.dto.SysUserDTO;
import com.cys.dto.SysYuyueDTO;
import com.cys.dto.YuyueOrderDTO;
import com.cys.exception.BusinessException;
import com.cys.model.ServerProject;
import com.cys.model.SysUser;
import com.cys.model.SysYuyue;
import com.cys.model.YuyueOrder;
import com.cys.repository.ServerProjectRepository;
import com.cys.repository.SysYuyueRespository;
import com.cys.repository.YuyueOrderRepository;
import com.cys.service.ISysUserService;
import com.cys.service.ISysYuyueService;
import com.cys.util.OrderUtil;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.apache.jackrabbit.webdav.ordering.OrderingDavServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liyuan on 2018/2/8.
 */
@Service("sysYuyueService")
public class SysYuyueServiceImpl extends BaseServiceImpl<SysYuyue,String>  implements ISysYuyueService{
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private SysYuyueRespository sysYuyueRespository;
    
    @Autowired
    private ServerProjectRepository serverProjectRespository;
    
    @Autowired
    private YuyueOrderRepository yuyueOrderRepository;
    
    @Override
    public int findCountByShopId(String shopId) throws Exception {
        if(StringUtils.isEmpty(shopId)){
            throw new BusinessException("店铺ID不能为空！");
        }
        List<SysUser> sysUsers = sysUserService.findByShopId(shopId);
        List<String> userIds = sysUsers.stream().map(SysUser::getId).collect(Collectors.toList());
        int count = 0;
        if(!CollectionUtils.isEmpty(userIds)){
            count = sysYuyueRespository.countByYyQyUserIdIn(userIds);
        }
        return count;
    }

    @Override
    public SysYuyueDTO findDetails(String id) throws Exception {
        SysYuyueDTO sysYuyueDTO = new SysYuyueDTO();
        SysYuyue sysYuyue = sysYuyueRespository.findOne(id);
        if(sysYuyue == null){
            throw new BusinessException("预约单不存在！");
        }
        PropertyUtils.copyProperties(sysYuyueDTO,sysYuyue);
        return sysYuyueDTO;
    }
    
    @Override
    public Page<SysYuyue> find(SysYuyue sysYuyue, Query query) throws Exception {
        Pageable pageable = query.getPageable();
        Page<SysYuyue> sysUserPages = sysYuyueRespository.find(sysYuyue,pageable);
        List<SysYuyue> sysYuyues = sysUserPages.getContent();
        //List<SysYuyue> sysYuyue = convertToSysUserDTO(sysUsers);
        return new PageImpl<SysYuyue>(sysYuyues,pageable,sysUserPages.getTotalElements());
    }
    
    public Page<SysYuyue> findgh(SysYuyue sysYuyue, Query query) throws Exception {
        Pageable pageable = query.getPageable();
        Page<SysYuyue> sysUserPages = sysYuyueRespository.find(sysYuyue,pageable);
        List<SysYuyue> sysYuyues = sysUserPages.getContent();
        //List<SysYuyue> sysYuyue = convertToSysUserDTO(sysUsers);
        return new PageImpl<SysYuyue>(sysYuyues,pageable,sysUserPages.getTotalElements());
    }

	@Override
	public void saveYuyueOrder(YuyueOrderDTO yuyueOrderDTO) throws Exception {
		// TODO Auto-generated method stub
		YuyueOrder yuyueOrder = new YuyueOrder();
		SysYuyue sy = sysYuyueRespository.findOne(yuyueOrderDTO.getSysYuyue());
		
		PropertyUtils.copyProperties(yuyueOrder,yuyueOrderDTO);
		if(sy.getYyCzUser()!=null){
			yuyueOrder.setYyCzId(sy.getYyCzUser().getId());
		}
		if(sy.getYyQyUser()!=null){
			yuyueOrder.setYyQxId(sy.getYyQyUser().getId());
		}
		yuyueOrder.setYl5(OrderUtil.getOrderIdByUUId());
        yuyueOrderRepository.save(yuyueOrder);
        if(yuyueOrderDTO.getServerProject()!=null){
        	for(int i=0;i<yuyueOrderDTO.getServerProject().size();i++){
        		yuyueOrderDTO.getServerProject().get(i).setOrderId(yuyueOrder.getId());
        	}
        }
		
		serverProjectRespository.saveInBatch(yuyueOrderDTO.getServerProject());
		
		
        PropertyUtils.copyProperties(yuyueOrder,yuyueOrderDTO);
        yuyueOrderRepository.save(yuyueOrder);
	}
}
