package com.cys.controller;

import com.cys.common.annotation.Rest;
import com.cys.common.domain.Query;
import com.cys.common.domain.ResultData;
import com.cys.dto.SysUserDTO;
import com.cys.model.SysUser;
import com.cys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liyuan on 2018/2/5.
 */
@Rest(SysUser.class)
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findPmoPrjList(Query query) throws Exception {
        SysUserDTO sysUserDTO= (SysUserDTO) query.getBean(SysUserDTO.class);
        Page<SysUser> pageList = sysUserService.find(sysUserDTO, query);
        return new ResultData(SysUserDTO.class, pageList);
    }
}
