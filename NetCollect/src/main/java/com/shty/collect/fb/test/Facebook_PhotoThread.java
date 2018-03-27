package com.shty.collect.fb.test;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.codec.binary.Base64;

import com.gargoylesoftware.htmlunit.WebClient;
import com.shty.collect.domain.fb.FBPhoto;
import com.shty.collect.domain.fb.FbTarget;
import com.shty.collect.domain.fb.TimelineStoryimgCodeList;
import com.shty.collect.service.fb.FBPhoto_ServiceI;
import com.shty.collect.service.fb.FbTarget_ServiceI;
import com.shty.collect.util.FileUpload;
import com.smit.fb.controller.FbDetailController;
import com.smit.fb.entity.photo.Photo;
import com.smit.fb.entity.photo.PhotoEntity;
import com.smit.fb.http.FbHttpUtils;
import com.smit.fb.util.MyUtil;
import com.smit.fb.util.Utils;
import com.smit.fbAppclient.MyRequestEntity;
import com.smit.fbAppclient.MyResponseEntity;

public class Facebook_PhotoThread implements Runnable {

	private String account;
	private String fburl;
	private String nextUrl;
	private WebClient webClient;
	private MyRequestEntity request;
	private MyResponseEntity response;
	private String checkin = "true";
	private FbTarget target;
	private FBPhoto_ServiceI fBPhoto_ServiceI;
	private FbTarget_ServiceI fbTarget_ServiceI;
	private String imgPath = ResourceBundle.getBundle("systemconfig").getString("imgPath");// 设置图片存储路径
	private String relativePath = "";

	public Facebook_PhotoThread(String account, String fburl, MyRequestEntity request, MyResponseEntity response,
			WebClient webClient, FbTarget target, FBPhoto_ServiceI fBPhoto_ServiceI,
			FbTarget_ServiceI fbTarget_ServiceI) {
		this.account = account;
		this.fburl = fburl;
		this.webClient = webClient;
		this.request = request;
		this.response = response;
		this.target = target;
		this.fBPhoto_ServiceI = fBPhoto_ServiceI;
		this.fbTarget_ServiceI = fbTarget_ServiceI;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		PhotoEntity listPhoto = null;
		FbDetailController detailController = new FbDetailController(webClient);
		do {
			// Utils.sleep(60000);
			String urlpattern = FbHttpUtils.getsubdomainUrl(fburl);
			FbTarget target = fbTarget_ServiceI.getFbTarget_urlpattern(urlpattern);
			if (target.getPhotoUrl() == null || target.getPhotoUrl().equals("")) {
				System.out.println("未采集，开始采集相册");
				listPhoto = detailController.getPhoto(account, fburl, checkin, "", request, response, webClient);
			} else if (target.getPhotoUrl() != null && !target.getPhotoUrl().equals("")
					&& !target.getPhotoUrl().equals("finished")) {
				System.out.println("已采集过，从某一页开始采集相册");
				listPhoto = detailController.getPhoto(account, "", checkin, target.getPhotoUrl(), request, response,
						webClient);
			} else if (target.getPhotoUrl().equals("finished")) {
				listPhoto = null;
				nextUrl = "";
			}
			// 获取到采集的图片
			if (listPhoto != null && !"".equals(listPhoto) && "200".equals(listPhoto.getStatuscode())) {
				List<Photo> photoResult = listPhoto.getPhoto();
				for (Photo photoEntity : photoResult) {
					FBPhoto photo = new FBPhoto();
					photo.setImgCode(photoEntity.getImgcode());
					if (photoEntity.getImgcode() != null && !photoEntity.getImgcode().equals("")) {
						try {
							// 设置图片保存相对路径文件夹，根据ID为后缀
							relativePath = "facebook_img/images/facebookimg/fbtargetid_" + target.getId().toString()+ "/photo/"; // 设置图片保存相对路径
							// 根据获取时间戳为图片名字
							String fullPath = relativePath + MyUtil.getTimeFormat() + "_main.jpg";
							// 为取出配置文件的路径
							byte[] decodeByteArray = Base64.decodeBase64(photoEntity.getImgcode());
							FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);	
						} catch (Exception e) {
							// TODO Auto-generated catch
							// block
							e.printStackTrace();
						}
					}
					photo.setImgUrl(photoEntity.getImgurl());
					photo.setFBTarget_id(target.getId());
					fBPhoto_ServiceI.insert_FBPhoto(photo);
				}
				if (listPhoto.getMoreUrl() != null && !listPhoto.getMoreUrl().equals("")) {
					nextUrl = listPhoto.getMoreUrl();
					// 保存到数据库target表中
					// 修改friendsurl
					FbTarget photoUrl = new FbTarget();
					photoUrl.setId(target.getId());
					photoUrl.setPhotoUrl(nextUrl);
					fbTarget_ServiceI.update_FbTarget_photoUrl(photoUrl);
					System.out.println("开始采集下一页");
				}
			}
		} while (nextUrl != null && !nextUrl.equals("") && "200".equals(listPhoto.getStatuscode()));
		if ("200".equals(listPhoto.getStatuscode())) {
			System.out.println("----------采集完成----------");
			FbTarget photoUrl = new FbTarget();
			photoUrl.setId(target.getId());
			photoUrl.setPhotoUrl("finished");
			fbTarget_ServiceI.update_FbTarget_photoUrl(photoUrl);
		}
	}
}
