package com.cys.service;

import com.cys.dto.SysYuyueDTO;
import com.cys.model.SysYuyue;

/**
 * Created by liyuan on 2018/2/8.
 */
public interface ISysYuyueService extends IBaseService<SysYuyue,String> {

    public int findCountByShopId(String shopId) throws Exception;

    public SysYuyueDTO findDetails(String id) throws Exception;
}
