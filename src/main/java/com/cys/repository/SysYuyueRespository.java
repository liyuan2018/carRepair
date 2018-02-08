package com.cys.repository;

import com.cys.model.SysYuyue;

import java.util.List;

/**
 * Created by liyuan on 2018/2/8.
 */
public interface SysYuyueRespository extends BaseJpaRepository<SysYuyue,String>{

    int countByYyQyUserIdIn(List<String> yyQyUserIds);
}
