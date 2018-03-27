package com.shty.collect.fb.test;

public class Facebook_Status {
	// 节点状态
	/**
	 * 100 = 未启动 200 = 空闲 300 = 忙碌 400 = 采集好友 500 = 采集timeline 600 = 采集photo 700
	 * = 出错
	 */
	static String facebook_nodeStatus = "100";
	// 账号状态
	static String facebook_SocialStatus = "未检测";
	// vpn状态
	static String facebook_VpnStatus;
	
	// 账号
	static String facebook_Socialaccount = "";
	//密码
	static String facebook_Socialpassword = "";
	
	//http
	static String facebook_httpaccount = "";
	//http端口
	static String facebook_httpport = "";
	
	public static String getFacebook_httpport() {
		return facebook_httpport;
	}
	public static void setFacebook_httpport(String facebook_httpport) {
		Facebook_Status.facebook_httpport = facebook_httpport;
	}
	public static String getFacebook_Socialpassword() {
		return facebook_Socialpassword;
	}
	public static void setFacebook_Socialpassword(String facebook_Socialpassword) {
		Facebook_Status.facebook_Socialpassword = facebook_Socialpassword;
	}
	
	public static String getFacebook_httpaccount() {
		return facebook_httpaccount;
	}
	public static void setFacebook_httpaccount(String facebook_httpaccount) {
		Facebook_Status.facebook_httpaccount = facebook_httpaccount;
	}
	public static String getFacebook_Socialaccount() {
		return facebook_Socialaccount;
	}
	public static void setFacebook_Socialaccount(String facebook_Socialaccount) {
		Facebook_Status.facebook_Socialaccount = facebook_Socialaccount;
	}
	public static String getFacebook_nodeStatus() {
		return facebook_nodeStatus;
	}
	public static void setFacebook_nodeStatus(String facebook_nodeStatus) {
		Facebook_Status.facebook_nodeStatus = facebook_nodeStatus;
	}
	public static String getFacebook_SocialStatus() {
		return facebook_SocialStatus;
	}
	public static void setFacebook_SocialStatus(String facebook_SocialStatus) {
		Facebook_Status.facebook_SocialStatus = facebook_SocialStatus;
	}
	public static String getFacebook_VpnStatus() {
		return facebook_VpnStatus;
	}
	public static void setFacebook_VpnStatus(String facebook_VpnStatus) {
		Facebook_Status.facebook_VpnStatus = facebook_VpnStatus;
	}



}
