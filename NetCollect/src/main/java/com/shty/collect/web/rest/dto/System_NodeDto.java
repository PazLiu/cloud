package com.shty.collect.web.rest.dto;


public class System_NodeDto {
	private Long id;
	private String nodeip;
	private String physicalip;
	private String isused;
	private String creattime;
	private String updatetime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNodeip() {
		return nodeip;
	}
	public void setNodeip(String nodeip) {
		this.nodeip = nodeip;
	}
	public String getPhysicalip() {
		return physicalip;
	}
	public void setPhysicalip(String physicalip) {
		this.physicalip = physicalip;
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
