package com.cys.service;

import com.cys.common.domain.Query;
import com.cys.dto.SysUserDTO;
import com.cys.dto.SysUserShopDTO;
import com.cys.exception.BusinessException;
import com.cys.model.SysAttachment;
import com.cys.model.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by liyuan on 2018/1/31.
 */
public interface ISysUserService extends IBaseService<SysUser,String> {

    /**
     * 分页查询接口
     * @param sysUserDTO
     * @param query
     * @return
     * @throws Exception
     */
    Page<SysUserDTO> find(SysUserDTO sysUserDTO, Query query) throws Exception;

    /**
     * 查询集合
     * @param sysUserDTO
     * @return
     * @throws Exception
     */
    List<SysUserDTO> find(SysUserDTO sysUserDTO) throws Exception;

    /**
     * 根据店铺ID查询
     * @param shopId
     * @return
     */
    List<SysUserDTO> findDTOByShopId(String shopId) throws Exception;

    /**
     * 根据店铺ID查询
     * @param shopId
     * @return
     */
    List<SysUser> findByShopId(String shopId) throws Exception;

    /**
     * 注册
     * @param sysUserDTO
     * @return
     * @throws Exception
     */
    SysUserDTO register(SysUserDTO sysUserDTO) throws Exception;
    
    /**
     * 注册
     * @param sysUserDTO
     * @return
     * @throws Exception
     */
    SysUserShopDTO registerShop(SysUserShopDTO sysUserShopDTO) throws Exception;

    /**
     * 上传文件
     * @param mFile
     * @return
     * @throws Exception
     */
    SysAttachment upload(MultipartFile mFile)throws Exception;

    /**
     * 解析微信用户数据
     * @return
     */
    Map parseWeiXinUserData(String code,String encryptedData,String iv) throws Exception;

}
