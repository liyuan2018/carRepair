package com.cys.controller;

import com.cys.common.annotation.Rest;
import com.cys.common.domain.Query;
import com.cys.common.domain.ResultData;
import com.cys.dto.CarInfoDTO;
import com.cys.model.CarInfo;
import com.cys.model.SysUser;
import com.cys.model.SysYuyue;
import com.cys.model.YuyueOrder;
import com.cys.service.ICarInfoService;
import com.cys.service.YuyueOrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liyuan on 2018/3/11.
 */
@Rest(YuyueOrder.class)

public class YuyueOrderController {

    @Autowired
    private YuyueOrderService yuyueOrderService;

    /**
     * 获取个人预约记录
     * @param owerUserId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findYuyueOrderByUserId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findByUserId(Query query) throws Exception {
    	/*YuyueOrder car= new YuyueOrder(); 
    	SysYuyue yuyue =new SysYuyue();
    	SysUser yyCzUser = new SysUser();
    	yyCzUser.setId(owerUserId);
    	yuyue.setYyCzUser(yyCzUser);
    	car.setSysYuyue(yuyue);*/
    	YuyueOrder sysYuyue= (YuyueOrder) query.getBean(YuyueOrder.class);
        Page<YuyueOrder> pageList = yuyueOrderService.find(sysYuyue, query);
        return new ResultData(YuyueOrder.class, pageList);
    	/*List<YuyueOrder>carInfoDTOLi = yuyueOrderService.find(car);
        return new ResultData(YuyueOrder.class, carInfoDTOLi);*/
    }
    
    @RequestMapping(value = "/findById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@RequestParam(name = "id")String id) throws Exception {
    	YuyueOrder car= new YuyueOrder(); 
    	/*YuyueOrder car= new YuyueOrder(); 
    	SysYuyue yuyue =new SysYuyue();
    	SysUser yyCzUser = new SysUser();
    	yyCzUser.setId(owerUserId);
    	yuyue.setYyCzUser(yyCzUser);
    	car.setSysYuyue(yuyue);*/
    	//YuyueOrder sysYuyue= (YuyueOrder) query.getBean(YuyueOrder.class);
    	car.setId(id);
    	YuyueOrder sysYuyue = yuyueOrderService.findOne(car);
        return new ResultData(YuyueOrder.class, sysYuyue);
    	/*List<YuyueOrder>carInfoDTOLi = yuyueOrderService.find(car);
        return new ResultData(YuyueOrder.class, carInfoDTOLi);*/
    }
}
