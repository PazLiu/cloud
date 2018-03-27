package com.shty.collect.web.rest.dto;



public class GroupOrginizationDto {

	private Long id;
	private String username;
	private String password;
	private String ipaddress;
	private String sharedkey;
	private String vpntype;
	private String isused;
	private String creattime;
	private String updatetime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getSharedkey() {
		return sharedkey;
	}
	public void setSharedkey(String sharedkey) {
		this.sharedkey = sharedkey;
	}
	public String getVpntype() {
		return vpntype;
	}
	public void setVpntype(String vpntype) {
		this.vpntype = vpntype;
	}
	public String getIsused() {
		return isused;
	}
	public void setIsused(String isused) {
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
