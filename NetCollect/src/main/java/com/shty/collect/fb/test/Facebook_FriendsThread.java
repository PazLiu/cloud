package com.shty.collect.fb.test;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.codec.binary.Base64;

import com.gargoylesoftware.htmlunit.WebClient;
import com.shty.collect.domain.fb.FbTarget;
import com.shty.collect.domain.fb.OverviewFamily;
import com.shty.collect.service.fb.FbTarget_ServiceI;
import com.shty.collect.service.fb.Friends_ServiceI;
import com.shty.collect.util.FileUpload;
import com.smit.fb.controller.FbDetailController;
import com.smit.fb.entity.friends.FriendsEntity;
import com.smit.fb.entity.search.People;
import com.smit.fb.util.MyStringUtils;
import com.smit.fb.util.MyUtil;
import com.smit.fb.util.Utils;
import com.smit.fbAppclient.MyRequestEntity;
import com.smit.fbAppclient.MyResponseEntity;

public class Facebook_FriendsThread extends Thread {
	private WebClient webClient;
	private String account;
	private MyRequestEntity request;
	private MyResponseEntity response;
	private String password;
	private String fburl;
	private FbTarget target;
	private Friends_ServiceI friends_ServiceI;
	private FbTarget_ServiceI fbTarget_ServiceI;
	private String urlpattern;
	private String imgPath = ResourceBundle.getBundle("systemconfig").getString("imgPath");// 设置图片存储路径
	private String relativePath = "";
	public volatile static boolean facebookfriends_status = false; // 这个关键字的目的是使status同步，也就是说在同一时刻只能由一个线程来修改status的值。

	public Facebook_FriendsThread() {

	}

	public Facebook_FriendsThread(WebClient webClient, String account, String password, String fburl,
			MyRequestEntity request, MyResponseEntity response, FbTarget target, Friends_ServiceI friends_ServiceI,
			FbTarget_ServiceI fbTarget_ServiceI, String urlpattern) {
		super();
		this.webClient = webClient;
		this.account = account;
		this.request = request;
		this.response = response;
		this.password = password;
		this.fburl = fburl;
		this.target = target;
		this.friends_ServiceI = friends_ServiceI;
		this.fbTarget_ServiceI = fbTarget_ServiceI;
		this.urlpattern = urlpattern;
	}

	@Override
	public void run() {
		FbDetailController detailController = new FbDetailController(webClient);
		String friends = null;
		String morurl = "";
		FriendsEntity friendsEntity = null;
		do {
			FbTarget target = fbTarget_ServiceI.getFbTarget_urlpattern(urlpattern);
			if (target.getFriendsurl() == null || target.getFriendsurl().equals("")) {
				friends = detailController.getFriends(account, fburl, "true", "true", "false", request, response);
			} else if (target.getFriendsurl() != null && !target.getFriendsurl().equals("")
					&& !target.getFriendsurl().equals("finished")) {
				friends = detailController.getFriends(account, target.getFriendsurl(), "true", "true", "true", request,
						response);
			} else if (target.getFriendsurl().equals("finished")) {
				System.out.println("采集完成");
				friends = null;
				morurl = "";
			}
			if (friends != null && !"".equals(friends)) {
				friendsEntity = Utils.getGson().fromJson(friends, FriendsEntity.class);
				morurl = friendsEntity.getMoreUrl();
				List<People> listfriends = friendsEntity.getFriendsList();
				System.out.println("采集好友数量为："+listfriends.size());
				// 设置图片保存相对路径文件夹，根据ID为后缀
				relativePath = "facebook_img/images/facebookimg/fbtargetid_" + target.getId().toString()+"/friends/"; // 设置图片保存相对路径
				for (People people : listfriends) {
					OverviewFamily Friends = new OverviewFamily();
					Friends.setFbid(people.getFbid());
					Friends.setFburl(people.getFburl());
					Friends.setFmt_headline(people.getFmt_headline());
					Friends.setFmt_name(people.getFmt_name());
					// 处理图片路径
					if (people.getImgcode() != null) {
						Friends.setImgcode(people.getImgcode());
						String fullPath = relativePath + MyUtil.getTimeFormat() + ".jpg";
						try {
							// 为取出配置文件的路径
							byte[] decodeByteArray = Base64.decodeBase64(people.getImgcode());
							FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);	
							Friends.setImgPath(fullPath);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					Friends.setImgurl(people.getImgurl());
					Friends.setReferenceKey(people.getReferenceKey());
					Friends.setReferenceValue(people.getReferenceValue());
					Friends.setFBTarget_id(target.getId());
					friends_ServiceI.insert_Friends(Friends);
				}
				System.out.println("保存好友成功");
				// 修改friendsurl
				FbTarget friendsurl = new FbTarget();
				friendsurl.setId(target.getId());
				friendsurl.setFriendsurl(morurl);
				fbTarget_ServiceI.update_FbTarget_friendsurl(friendsurl);
				}
			if (morurl == null || "".equals(morurl)) {
				// 修改friendsurl
				FbTarget friendsurl = new FbTarget();
				friendsurl.setId(target.getId());
				friendsurl.setFriendsurl("finished");
				fbTarget_ServiceI.update_FbTarget_friendsurl(friendsurl);
				System.out.println("------好友采集完成------");
			}
		} while (!MyStringUtils.isNullOrEmpty(morurl));
	}
}
