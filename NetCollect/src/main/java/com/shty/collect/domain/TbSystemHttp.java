package com.shty.collect.domain;

import java.util.Date;

public class TbSystemHttp {
	private Long id;
	private String httpip;
	private String port;
	private String httpname;
	private String httppassword;
	private int isused;
	private Date creatTime;
	private Date updateTime;
	
	public String getHttpname() {
		return httpname;
	}
	public void setHttpname(String httpname) {
		this.httpname = httpname;
	}
	public String getHttppassword() {
		return httppassword;
	}
	public void setHttppassword(String httppassword) {
		this.httppassword = httppassword;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHttpip() {
		return httpip;
	}
	public void setHttpip(String httpip) {
		this.httpip = httpip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public int getIsused() {
		return isused;
	}
	public void setIsused(int isused) {
		this.isused = isused;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
	
}
