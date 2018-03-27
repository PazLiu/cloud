package com.shty.collect.domain.lnk;

public class Task {

	String netUserName;
	String netPassword;
	String netType;

	// boolean ifVpn;
	// String vpnIP;
	// String connectType;
	// String connectName;
	// String ConnectPassword;
	// String shareKey;

	boolean ifHttp;
	String proxyHost;
	int proxyPort;

	String targetUrl;

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getNetUserName() {
		return netUserName;
	}

	public void setNetUserName(String netUserName) {
		this.netUserName = netUserName;
	}

	public String getNetPassword() {
		return netPassword;
	}

	public void setNetPassword(String netPassword) {
		this.netPassword = netPassword;
	}

	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	public boolean isIfHttp() {
		return ifHttp;
	}

	public void setIfHttp(boolean ifHttp) {
		this.ifHttp = ifHttp;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

}
