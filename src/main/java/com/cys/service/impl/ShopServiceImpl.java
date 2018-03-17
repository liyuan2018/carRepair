package com.cys.service.impl;

import com.cys.dto.CarInfoDTO;
import com.cys.model.CarInfo;
import com.cys.model.SysShop;
import com.cys.repository.CarInfoRepository;
import com.cys.repository.SysShopRepository;
import com.cys.service.ICarInfoService;
import com.cys.service.IShopService;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liyuan on 2018/3/11.
 */
@Service("shopService")
public class ShopServiceImpl extends BaseServiceImpl<SysShop,String> implements IShopService{
    @Autowired
    private SysShopRepository sysRepository;
    
}
