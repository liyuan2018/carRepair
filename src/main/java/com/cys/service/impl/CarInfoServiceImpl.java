package com.cys.service.impl;

import com.cys.dto.CarInfoDTO;
import com.cys.model.CarInfo;
import com.cys.repository.CarInfoRepository;
import com.cys.service.ICarInfoService;
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
@Service("carInfoService")
public class CarInfoServiceImpl extends BaseServiceImpl<CarInfo,String> implements ICarInfoService{
    @Autowired
    private CarInfoRepository carInfoRepository;
    @Override
    public CarInfoDTO createDto(CarInfoDTO carInfoDTO) throws Exception {
        carInfoDTO.setCreateTime(new Date());
        CarInfo carInfo = new CarInfo();
        PropertyUtils.copyProperties(carInfo,carInfoDTO);
        carInfoRepository.save(carInfo);
        return carInfoDTO;
    }

    @Override
    public List<CarInfoDTO> findCarInfoDTOByUserIds(String... userId) throws Exception {
        List<CarInfo> carInfos = carInfoRepository.findByOwerUserIdIn(userId);
        List<CarInfoDTO> carInfoDTOs = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(carInfos)){
            for(CarInfo carInfo:carInfos){
                CarInfoDTO carInfoDTO = new CarInfoDTO();
                PropertyUtils.copyProperties(carInfoDTO,carInfo);
                carInfoDTOs.add(carInfoDTO);
            }
        }
        return carInfoDTOs;    }
}
