package com.cys.controller;

import com.cys.common.annotation.Rest;
import com.cys.common.domain.Query;
import com.cys.common.domain.ResultData;
import com.cys.dto.SysUserDTO;
import com.cys.model.SysUser;
import com.cys.model.SysYuyue;
import com.cys.repository.BaseJpaRepository;
import com.cys.repository.SysUserRepository;
import com.cys.service.ISysUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liyuan on 2018/2/5.
 */
@Rest(SysYuyueController.class)
public class SysYuyueController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private BaseJpaRepository sysUserRepository;

    @RequestMapping(value = "save",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData save(HttpServletRequest request,HttpServletResponse response,SysYuyue sysyuyue) throws Exception {
       
        JSONObject json = new JSONObject();
        String ret= "fail";
        sysUserRepository.save(sysyuyue);
        ret="success";
        json.put("result", ret);
        return new ResultData(JSONObject.class, json);
    }
}
