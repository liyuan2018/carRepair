package com.cys.controller;

import com.cys.common.annotation.Rest;
import com.cys.common.domain.ResultData;
import com.cys.dto.CarInfoDTO;
import com.cys.dto.SysUserDTO;
import com.cys.model.CarInfo;
import com.cys.model.SysShop;
import com.cys.service.ICarInfoService;
import com.cys.service.IShopService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liyuan on 2018/3/11.
 */
@Rest(SysShop.class)

public class ShopController {

    @Autowired
    private IShopService shopService;

    /**
     * 查询详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findDetail(@PathVariable("id") String id) throws Exception {
        SysShop sysShop = shopService.findById(id);
        return new ResultData(SysShop.class, sysShop);
    }
}
