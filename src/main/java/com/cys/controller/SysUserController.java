package com.cys.controller;

import com.cys.common.annotation.Rest;
import com.cys.common.domain.Query;
import com.cys.common.domain.ResultData;
import com.cys.dto.SysUserDTO;
import com.cys.dto.SysUserShopDTO;
import com.cys.model.SysAttachment;
import com.cys.model.SysUser;
import com.cys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by liyuan on 2018/2/5.
 */
@Rest(SysUser.class)
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData find(Query query) throws Exception {
        SysUserDTO sysUser= (SysUserDTO) query.getBean(SysUserDTO.class);
        Page<SysUserDTO> pageList = sysUserService.find(sysUser, query);
        return new ResultData(SysUserDTO.class, pageList);
    }


    /**
     * 店铺注册
     * @param sysUserShopDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shopRegister", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData find(@RequestBody SysUserShopDTO sysUserShopDTO) throws Exception {
    	sysUserShopDTO = sysUserService.registerShop(sysUserShopDTO);
        return new ResultData(SysUserShopDTO.class, sysUserShopDTO);
    }
    /**
     * 用户注册
     * @param sysUserDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData find(@RequestBody SysUserDTO sysUserDTO) throws Exception {
    	sysUserDTO = sysUserService.register(sysUserDTO);
        return new ResultData(SysUserDTO.class, sysUserDTO);
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

    @RequestMapping(value = "/parseWeiXinUserData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData parseWeiXinUserData( @RequestParam("code") String code,@RequestParam("encryptedData")String encryptedData,@RequestParam("iv")String iv) throws Exception{
        code = code.trim();
        encryptedData = encryptedData.trim();
        iv = iv.trim();
        return new ResultData("userInfo",sysUserService.parseWeiXinUserData(code,encryptedData,iv));
    }



    /**
     * 查询详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findDetail(@PathVariable("id") String id) throws Exception {
        SysUserDTO sysUserDTO = sysUserService.findDtoById(id);
        return new ResultData(SysUserDTO.class, sysUserDTO);
    }
    
    /**
     * 查询详情
     * @param openId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/openId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findByOpendId(@RequestParam("openId") String openId) throws Exception {
        SysUserDTO sysUserDTO = sysUserService.findDtoByOpenId(openId);
        return new ResultData(SysUserDTO.class, sysUserDTO);
    }

    /**
     * 更新
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData update(@PathVariable("id") String id,@RequestBody SysUserDTO sysUserDTO) throws Exception {
        sysUserDTO.setId(id);
        sysUserDTO = sysUserService.updateDto(sysUserDTO);
        return new ResultData(SysUserDTO.class, sysUserDTO);
    }
}
