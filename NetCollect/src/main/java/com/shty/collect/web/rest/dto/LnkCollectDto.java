package com.shty.collect.web.rest.dto;

public class LnkCollectDto {

	private int status;
	private String message;
	private String threadStatus;
	private String httpIp;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHttpIp() {
		return httpIp;
	}

	public void setHttpIp(String httpIp) {
		this.httpIp = httpIp;
	}

	public String getThreadStatus() {
		return threadStatus;
	}

	public void setThreadStatus(String threadStatus) {
		this.threadStatus = threadStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
