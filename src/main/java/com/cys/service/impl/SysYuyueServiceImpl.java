package com.cys.service.impl;

import com.cys.dto.SysYuyueDTO;
import com.cys.exception.BusinessException;
import com.cys.model.SysUser;
import com.cys.model.SysYuyue;
import com.cys.repository.SysYuyueRespository;
import com.cys.service.ISysUserService;
import com.cys.service.ISysYuyueService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
