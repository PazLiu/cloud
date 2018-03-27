package com.shty.collect.twitter.task;

public class TwitterNodeStatus {
	/*节点状态
	 * 100 = 未启动
	 * 200 = 空闲
	 * 300 = 采集中
	 * 400 = 采集出错
	 */
	static String nodeStatus = "100";
	
	// 账号
	static String twitter_Socialaccount = "";
	
	//密码
	static String twitter_Socialpassword = "";
	
	//http
	static String twitter_httpaccount = "";
	//http端口
	static String twitter_httpport = "";
	
	public static String getTwitter_Socialaccount() {
		return twitter_Socialaccount;
	}

	public static void setTwitter_Socialaccount(String twitter_Socialaccount) {
		TwitterNodeStatus.twitter_Socialaccount = twitter_Socialaccount;
	}

	public static String getTwitter_httpaccount() {
		return twitter_httpaccount;
	}

	public static void setTwitter_httpaccount(String twitter_httpaccount) {
		TwitterNodeStatus.twitter_httpaccount = twitter_httpaccount;
	}

	public static String getNodeStatus() {
		return nodeStatus;
	}

	public static void setNodeStatus(String nodeStatus) {
		TwitterNodeStatus.nodeStatus = nodeStatus;
	}

	public static String getTwitter_Socialpassword() {
		return twitter_Socialpassword;
	}

	public static void setTwitter_Socialpassword(String twitter_Socialpassword) {
		TwitterNodeStatus.twitter_Socialpassword = twitter_Socialpassword;
	}

	public static String getTwitter_httpport() {
		return twitter_httpport;
	}

	public static void setTwitter_httpport(String twitter_httpport) {
		TwitterNodeStatus.twitter_httpport = twitter_httpport;
	}


}
