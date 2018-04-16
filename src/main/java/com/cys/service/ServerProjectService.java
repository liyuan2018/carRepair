package com.cys.service;

import org.springframework.data.domain.Page;

import com.cys.common.domain.Query;
import com.cys.dto.SysUserDTO;
import com.cys.dto.SysYuyueDTO;
import com.cys.dto.YuyueOrderDTO;
import com.cys.model.ServerProject;
import com.cys.model.SysUser;
import com.cys.model.SysYuyue;

/**
 * Created by liyuan on 2018/2/8.
 */
public interface ServerProjectService extends IBaseService<ServerProject,String> {

    public void findByid(String id);
}
