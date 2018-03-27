package com.shty.collect.web.rest.dto;

public class System_Node_Socialacount_LinkaccountDto {
	private int id;
	private String nodeip;
	private String physicalip;
	private String accountName;
	private String accountType;
	private String httpip;
	private Long nodeid;
	private Long linkid;
	private Long socialid;
	private String node_status;
	private String account_status;
	private String status;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNode_status() {
		return node_status;
	}
	public void setNode_status(String node_status) {
		this.node_status = node_status;
	}
	public String getAccount_status() {
		return account_status;
	}
	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}
	public Long getNodeid() {
		return nodeid;
	}
	public void setNodeid(Long nodeid) {
		this.nodeid = nodeid;
	}
	public Long getLinkid() {
		return linkid;
	}
	public void setLinkid(Long linkid) {
		this.linkid = linkid;
	}
	public Long getSocialid() {
		return socialid;
	}
	public void setSocialid(Long socialid) {
		this.socialid = socialid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNodeip() {
		return nodeip;
	}
	public void setNodeIp(String nodeip) {
		this.nodeip = nodeip;
	}
	public String getPhysicalip() {
		return physicalip;
	}
	public void setPhysicalip(String physicalip) {
		this.physicalip = physicalip;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getHttpip() {
		return httpip;
	}
	public void setHttpip(String httpip) {
		this.httpip = httpip;
	}
	
}
