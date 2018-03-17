package com.cys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cys.common.domain.Query;
import com.cys.model.YuyueOrder;
import com.cys.repository.YuyueOrderRepository;
import com.cys.service.YuyueOrderService;

/**
 * Created by liyuan on 2018/3/11.
 */
@Service("yuyueOrderService")
public class YuyueOrderServiceImpl extends BaseServiceImpl<YuyueOrder,String> implements YuyueOrderService{
    @Autowired
    private YuyueOrderRepository yuyueOrderRepository;
   
    @Override
    public Page<YuyueOrder> find(YuyueOrder sysYuyue, Query query) throws Exception {
        Pageable pageable = query.getPageable();
        Page<YuyueOrder> sysUserPages = yuyueOrderRepository.find(sysYuyue,pageable);
        List<YuyueOrder> sysYuyues = sysUserPages.getContent();
        //List<SysYuyue> sysYuyue = convertToSysUserDTO(sysUsers);
        return new PageImpl<YuyueOrder>(sysYuyues,pageable,sysUserPages.getTotalElements());
    }

	@Override
	public List<YuyueOrder> findByHql(String Hql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
    
}
