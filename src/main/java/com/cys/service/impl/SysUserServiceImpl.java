package com.cys.service.impl;

import com.cys.common.domain.Query;
import com.cys.dto.SysUserDTO;
import com.cys.model.SysUser;
import com.cys.repository.SysUserRepository;
import com.cys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyuan on 2018/1/31.
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser,String> implements ISysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;
    @Override
    public Page<SysUser> find(SysUserDTO sysUserDTO, Query query) throws Exception {
        Pageable pageable = query.getPageable();
        SysUser sysUser = new SysUser();
        PropertyUtils.copyProperties(sysUser,sysUserDTO);
        Page<SysUser> sysUserPages = sysUserRepository.find(sysUserDTO,pageable);
        return sysUserPages;
    }

    @Override
    public List<SysUser> find(SysUserDTO sysUserDTO) throws Exception {
        SysUser sysUser = new SysUser();
        PropertyUtils.copyProperties(sysUser,sysUserDTO);
        return sysUserRepository.find(sysUser);
    }

    @Override
    public void create(SysUserDTO sysUserDTO) throws Exception {
        SysUser sysUser = new SysUser();
        PropertyUtils.copyProperties(sysUser,sysUserDTO);
        sysUserRepository.save(sysUser);
    }

    @Override
    public void update(SysUserDTO sysUserDTO) throws Exception {
        SysUser sysUser = new SysUser();
        PropertyUtils.copyProperties(sysUser,sysUserDTO);
        sysUserRepository.save(sysUser);
    }
}
