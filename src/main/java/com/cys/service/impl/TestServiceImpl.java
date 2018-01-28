package com.cys.service.impl;

import com.cys.model.SysUser;
import com.cys.repository.SysUserRepository;
import com.cys.service.TestService;
import com.cys.repository.TestResitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyuan on 2018/1/24.
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestResitory testResitory;

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public String say() throws Exception {
        List<SysUser> sysUsers = sysUserRepository.findAll();
        return "sucess";
    }
}
