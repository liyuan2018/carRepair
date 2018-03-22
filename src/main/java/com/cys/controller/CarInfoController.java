package com.cys.controller;

import com.cys.common.annotation.Rest;
import com.cys.common.domain.ResultData;
import com.cys.dto.CarInfoDTO;
import com.cys.model.CarInfo;
import com.cys.model.MessageDTO;
import com.cys.service.ICarInfoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liyuan on 2018/3/11.
 */
@Rest(CarInfo.class)

public class CarInfoController {

    @Autowired
    private ICarInfoService carInfoService;

    /**
     * 用户注册
     * @param carInfoDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData find(@RequestBody CarInfoDTO carInfoDTO) throws Exception {
        carInfoDTO = carInfoService.createDto(carInfoDTO);
        return new ResultData(CarInfo.class, carInfoDTO);
    }
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData deleteById(@RequestParam("id") String id) throws Exception {
    	
    	carInfoService.deleteById(id);
    	MessageDTO ms= new  MessageDTO();
    	String mss = "删除成功";
    	String status ="success";
    	ms.setMsg(mss);
    	ms.setStatus(status);
        return new ResultData(MessageDTO.class, ms);
    }
    
    @RequestMapping(value = "/findByUserId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findByUserId(@RequestParam("owerUserId") String owerUserId) throws Exception {
    	CarInfo car= new CarInfo(); 
    	car.setOwerUserId(owerUserId);
    	List<CarInfo>carInfoDTOLi = carInfoService.find(car);
        return new ResultData(CarInfo.class, carInfoDTOLi);
    }
}
