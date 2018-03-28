package com.cys.model;

import java.io.Serializable;

public class MessageDTO implements Serializable {
	private String msg="";
	private String status="fail";
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
