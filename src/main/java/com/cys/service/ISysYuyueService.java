package com.cys.service;

import org.springframework.data.domain.Page;

import com.cys.common.domain.Query;
import com.cys.dto.SysUserDTO;
import com.cys.dto.SysYuyueDTO;
import com.cys.dto.YuyueOrderDTO;
import com.cys.model.SysUser;
import com.cys.model.SysYuyue;

/**
 * Created by liyuan on 2018/2/8.
 */
public interface ISysYuyueService extends IBaseService<SysYuyue,String> {

    public int findCountByShopId(String shopId) throws Exception;

    public SysYuyueDTO findDetails(String id) throws Exception;
    
    Page<SysYuyue> find(SysYuyue sysYuyue, Query query) throws Exception;
    
    public void saveYuyueOrder (YuyueOrderDTO yuyueOrderDTO)throws Exception;
}
