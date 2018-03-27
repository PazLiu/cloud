package com.shty.collect.web.rest.dto;

public class CollectAccountDto {

	private String accountName;
	private String password;

	public String getAccount() {
		return accountName;
	}

	public void setAccount(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
