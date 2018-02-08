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
    public Page<SysUser> find(SysUser sysUser, Query query) throws Exception {
        Pageable pageable = query.getPageable();
        Page<SysUser> sysUserPages = sysUserRepository.find(sysUser,pageable);
        return sysUserPages;
    }

    @Override
    public List<SysUser> find(SysUser sysUser) {
        return sysUserRepository.find(sysUser);
    }

    @Override
    public List<SysUser> findByShopId(String shopId) {
        return sysUserRepository.findByShopId(shopId);
    }
}
