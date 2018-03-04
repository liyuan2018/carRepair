package com.cys.controller;

import com.cys.common.annotation.Rest;
import com.cys.common.domain.Query;
import com.cys.common.domain.ResultData;
import com.cys.dto.SysUserDTO;
import com.cys.dto.SysUserShopDTO;
import com.cys.model.SysAttachment;
import com.cys.model.SysUser;
import com.cys.service.ISysAttachmentService;
import com.cys.service.ISysUserRelService;
import com.cys.service.ISysUserService;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liyuan on 2018/2/5.
 */
@Rest(SysUser.class)
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData find(Query query) throws Exception {
        SysUserDTO sysUserDTO= (SysUserDTO) query.getBean(SysUserDTO.class);
        Page<SysUserDTO> pageList = sysUserService.find(sysUserDTO, query);
        return new ResultData(SysUserDTO.class, pageList);
    }


    @RequestMapping(value = "shopRegister", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData find(@RequestBody SysUserShopDTO sysUserShopDTO) throws Exception {
    	sysUserShopDTO = sysUserService.registerShop(sysUserShopDTO);
        return new ResultData(SysUserShopDTO.class, sysUserShopDTO);
    }


    /**
     * 批量上传图片
     * @param mFile
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData upload( @RequestParam("mFile") MultipartFile mFile) throws Exception{
        return new ResultData(SysAttachment.class,sysUserService.upload(mFile));
    }
}
