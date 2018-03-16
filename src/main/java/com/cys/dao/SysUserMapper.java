package com.cys.dao;

import com.cys.annotation.mybatis.MyBatisRepository;
import com.cys.dto.SysUserDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by liyuan on 2018/3/15.
 */
@MyBatisRepository
public interface SysUserMapper {

    /**
     * 分页查询
     * @param sysUserDTO
     * @param pageable
     * @return
     */
    Page<SysUserDTO> find(@Param("sysUserDTO") SysUserDTO sysUserDTO, Pageable pageable);

    /**
     *  查询所有集合
     * @param sysUserDTO
     * @return
     */
    List<SysUserDTO> find (@Param("sysUserDTO") SysUserDTO sysUserDTO);

    /**
     * 根据ID查询
     * @param id
     * @return
     * @throws Exception
     */
    SysUserDTO findById(@Param("id") String id) throws Exception;
}
