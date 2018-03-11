package com.cys.repository;

import com.cys.model.CarInfo;

import java.util.List;

/**
 * Created by liyuan on 2018/3/11.
 */
public interface CarInfoRepository extends BaseJpaRepository<CarInfo,String>{

    List<CarInfo> findByOwerUserId(String owerUserId);

    List<CarInfo> findByOwerUserIdIn(String... owerUserIds);

}
