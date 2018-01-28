package com.cys.repository;

import com.cys.model.SysUser;

import java.util.List;

/**
 * Created by liyuan on 2018/1/28.
 */
public interface SysUserRepository extends BaseJpaRepository {

    SysUser findByaAccount(String account);

    List<SysUser>  findAll();
}
