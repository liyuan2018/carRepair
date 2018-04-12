package com.cys.dto;

import java.util.List;

import com.cys.model.ServerProject;
import com.cys.model.SysUser;
import com.cys.model.YuyueOrder;

/**
 * Created by liyuan on 2018/3/11.
 */
public class YuyueOrderDTO extends YuyueOrder {

    private static final long serialVersionUID = 1L;
    /**
     * 金额详细
     */
    private List<ServerProject> serverProject;
    
    private SysUser czUser;
    
    private SysUser qxUser;

	public List<ServerProject> getServerProject() {
		return serverProject;
	}

	public void setServerProject(List<ServerProject> serverProject) {
		this.serverProject = serverProject;
	}

	public SysUser getCzUser() {
		return czUser;
	}

	public void setCzUser(SysUser czUser) {
		this.czUser = czUser;
	}

	public SysUser getQxUser() {
		return qxUser;
	}

	public void setQxUser(SysUser qxUser) {
		this.qxUser = qxUser;
	}
    
}
