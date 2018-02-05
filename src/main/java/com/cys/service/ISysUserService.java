package com.cys.service;

import com.cys.common.domain.Query;
import com.cys.dto.SysUserDTO;
import com.cys.exception.BusinessException;
import com.cys.model.SysUser;
import org.springframework.data.domain.Page;

import java.util.List;

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
    Page<SysUser> find(SysUserDTO sysUserDTO, Query query) throws Exception;

    /**
     * 查询集合
     * @param sysUserDTO
     * @return
     * @throws Exception
     */
    List<SysUser> find(SysUserDTO sysUserDTO) throws Exception;


    /**
     * 创建
     * @param sysUserDTO
     * @throws Exception
     */
    void create(SysUserDTO sysUserDTO) throws Exception;

    /**
     * 更新
     * @param sysUserDTO
     * @throws Exception
     */
    void update(SysUserDTO sysUserDTO) throws Exception;

}
