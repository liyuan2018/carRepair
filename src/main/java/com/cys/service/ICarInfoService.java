package com.cys.service;

import com.cys.dto.CarInfoDTO;
import com.cys.model.CarInfo;

import java.util.List;

/**
 * Created by liyuan on 2018/3/11.
 */
public interface ICarInfoService extends IBaseService<CarInfo,String> {


    /**
     * 创建
     * @param carInfoDTO
     * @return
     * @throws Exception
     */
    CarInfoDTO createDto(CarInfoDTO carInfoDTO) throws Exception;

    /**
     * 根据用户ID查询车辆信息
     * @param userId
     * @return
     * @throws Exception
     */
    List<CarInfoDTO> findCarInfoDTOByUserIds(String... userId) throws Exception;
}
