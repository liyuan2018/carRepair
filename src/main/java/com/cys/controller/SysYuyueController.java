package com.cys.controller;

import com.cys.common.annotation.Rest;
import com.cys.common.domain.Query;
import com.cys.common.domain.ResultData;
import com.cys.dto.SysUserDTO;
import com.cys.model.SysUser;
import com.cys.model.SysYuyue;
import com.cys.service.ISysUserService;
import com.cys.service.ISysYuyueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by liyuan on 2018/2/5.
 */
@Scope("prototype")
@Rest(SysYuyueController.class)
public class SysYuyueController extends BaseController {

    @Autowired
    private ISysYuyueService sysYuyueService;

    /**
     * 保存预约信息
     * @param request
     * @param response
     * @param sysyuyue
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData save(HttpServletRequest request,HttpServletResponse response,@RequestBody SysYuyue sysyuyue) throws Exception {
        sysyuyue.setCreateTime(new Date());
        sysyuyue.setStatus("1");
        sysYuyueService.create(sysyuyue);
        return new ResultData(SysYuyue.class, sysyuyue);
    }

    @RequestMapping(value = "findById/{id}",method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@RequestParam(name = "id") String id) throws Exception {
        return new ResultData(SysYuyue.class, sysYuyueService.findDetails(id));
    }

    @RequestMapping(value = "getYuyueCount/{shopId}",method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getYuyueCount(@RequestParam(name = "shopId") String shopId) throws Exception {
        return new ResultData("count", sysYuyueService.findCountByShopId(shopId));
    }
    
    
    @RequestMapping(value = "getByUserId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData find(Query query) throws Exception {
    	SysYuyue sysYuyue= (SysYuyue) query.getBean(SysYuyue.class);
        Page<SysYuyue> pageList = sysYuyueService.find(sysYuyue, query);
        return new ResultData(SysYuyue.class, pageList);
    }
    
    
}
