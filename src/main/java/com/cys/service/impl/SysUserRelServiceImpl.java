package com.cys.service.impl;

import com.cys.model.SysUserRel;
import com.cys.service.ISysUserRelService;
import org.springframework.stereotype.Service;

/**
 * Created by liyuan on 2018/3/1.
 */
@Service("sysUserRelService")
public class SysUserRelServiceImpl extends BaseRelationServiceImpl<SysUserRel,String> implements ISysUserRelService {
}
