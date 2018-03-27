package com.shty.collect.web.rest.dto;

public class IPAccountDto {

	private Long id;
	private String accountName;
	private String password;
	private String accountType;
	private String httpid;
	private String limitTableId;
	private String httpip;
	private String port;
	private String httpname;
	private String httppassword;
	private String isused;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getHttpid() {
		return httpid;
	}

	public void setHttpid(String httpid) {
		this.httpid = httpid;
	}

	public String getLimitTableId() {
		return limitTableId;
	}

	public void setLimitTableId(String limitTableId) {
		this.limitTableId = limitTableId;
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

	public String getIsused() {
		return isused;
	}

	public void setIsused(String isused) {
		this.isused = isused;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
