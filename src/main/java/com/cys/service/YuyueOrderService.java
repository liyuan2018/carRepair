package com.cys.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cys.common.domain.Query;
import com.cys.model.YuyueOrder;

/**
 * Created by liyuan on 2018/3/11.
 */
public interface YuyueOrderService extends IBaseService<YuyueOrder,String> {


	Page<YuyueOrder> find(YuyueOrder sysYuyue, Query query) throws Exception;
	
	List<YuyueOrder> findByHql(String Hql) throws Exception;
 
}
