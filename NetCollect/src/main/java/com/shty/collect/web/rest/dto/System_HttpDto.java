package com.shty.collect.web.rest.dto;

public class System_HttpDto {
	private Long id;
	private String httpip;
	private String port;
	private String httpname;
	private String httppassword;
	private int isused;
	private String creattime;
	private String updatetime;
	
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
	public int getIsused() {
		return isused;
	}
	public void setIsused(int isused) {
		this.isused = isused;
	}
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
	
}
