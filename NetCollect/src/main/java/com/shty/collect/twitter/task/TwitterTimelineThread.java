package com.shty.collect.twitter.task;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.UnexpectedPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.shty.collect.domain.TbTwitterSysTask;
import com.shty.collect.domain.twitter.Timeline;
import com.shty.collect.domain.twitter.TwitterProfile;
import com.shty.collect.service.twitter.ProfileService;
import com.shty.collect.service.twitter.TimelineService;
import com.shty.collect.util.Utils;
import com.twitter.controller.TwitterTests;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class TwitterTimelineThread implements Runnable {
	private ProfileService profileService;
	private TimelineService timelineService;
	private WebClient webClient;
	private String twitterUrl;
	private String twitterNickName;
	private int taskid;
	private String imgPath = ResourceBundle.getBundle("systemconfig").getString("imgPath");
	private String twitterPath = "TwitterImages/";

	public TwitterTimelineThread(ProfileService profileService, TimelineService timelineService, WebClient webClient,
			String twitterUrl, String twitterNickName, int taskid) {
		// TODO Auto-generated constructor stub
		super();
		this.profileService = profileService;
		this.timelineService = timelineService;
		this.webClient = webClient;
		this.twitterNickName = twitterNickName;
		this.twitterUrl = twitterUrl;
		this.taskid = taskid;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("---开始采集---");
		System.out.println("000:" + webClient.getBrowserVersion());
		TwitterTests twitterTests = new TwitterTests(webClient);
		String twitterProfile = null;
		ObjectMapper mapper = new ObjectMapper();
		String profileId = null;
		// String nickName = null;
		// String url = null;
		String profileImagePath = null;
		String timelineImagePath = null;
		try {

			profileId = profileService.findProfileID(twitterNickName);
			System.out.println("---采集个人简介---");
			if (profileId == null || profileId == "") {
				System.out.println("twitter:"+twitterUrl);
				twitterProfile = twitterTests.getProfile(twitterUrl, Long.parseLong("0"), 0);
				if(twitterProfile!=null){
				TwitterProfile twitterProfileObj = mapper.readValue(twitterProfile, TwitterProfile.class);
				TwitterProfile profile = new TwitterProfile();
				profile.setID(twitterProfileObj.getID());
				profile.setUserAvatarName(twitterProfileObj.getUserAvatarName());
				profile.setUserAvatarUrl(twitterProfileObj.getUserAvatarUrl());
				profile.setUserCardBio(twitterProfileObj.getUserCardBio());
				profile.setUserCardJoinDateDesc(twitterProfileObj.getUserCardJoinDateDesc());
				profile.setUserCardLocation(twitterProfileObj.getUserCardLocation());
				profile.setUserCardName(twitterProfileObj.getUserCardName()
						.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", ""));
				profile.setUserCardUrl(twitterProfileObj.getUserCardUrl());
				profile.setUserNickName(twitterProfileObj.getUserNickName());
				profile.setTargetID((long) taskid);
				System.out.println("ID:"+twitterProfileObj.getID());
				System.out.println("name:"+twitterProfileObj.getUserAvatarName());
				System.out.println("Bio:"+twitterProfileObj.getUserCardBio());
				// String cardName = twitterProfileObj.getUserCardName().trim();
				profileImagePath = imgPath + twitterPath + twitterNickName + "/headImage/";
				//twitterTests.createDir(profileImagePath);
				downloads(twitterProfileObj.getUserAvatarUrl(),
						profileImagePath, twitterProfileObj.getUserNickName() + ".jpg");
				profileService.insertNew(profile);
				System.out.println("个人简历入库成功");
				}else{
					System.out.println("Prifile为空");
				}
			} else {
				System.out.println("数据库已有此人简介");

			}
		} catch (Exception e) {
			System.out.println("个人简历采集出现异常");
			e.printStackTrace();
			// TODO: handle exception
		}
		// 采集帖子
		System.out.println("---开始采集帖子---");
		String twitterTimeline = null;
		String minPubTime = null;
		String minPubTimeTwid = null;
		String maxPubTime = null;
		// String twitterUrl = null;
		String positionValue = null;
		// String profileId = null;
		// String userName = null;
		profileId = profileService.findProfileID(twitterNickName);
		timelineImagePath = imgPath + twitterPath + twitterNickName + "/newsImage/";
		//twitterTests.createDir(timelineImagePath);
		int count = 0;
		while (true) {
			if (null != profileId) {
				try {
					Timeline maxTimeline = timelineService.findMaxValues(profileId);
					Timeline minTimeline = timelineService.findMinValues(profileId);
					if (minTimeline != null) {
						minPubTime = minTimeline.getTwPubTime();
						minPubTimeTwid = minTimeline.getTwid();
						positionValue = minTimeline.getTwPositionValue();
					}
					if (maxTimeline != null) {
						maxPubTime = maxTimeline.getTwPubTime();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

//				System.out.println("minPubTime:" + minPubTime);
//				System.out.println("minPubTimeTwid:" + minPubTimeTwid);
//				System.out.println("positionValue:" + positionValue);
//				System.out.println("maxPubTime:" + maxPubTime);
//				System.out.println("profileid:" + profileId);
//				System.out.println("url:" + twitterUrl);
//				System.out.println("name:" + twitterNickName);
				twitterTimeline = twitterTests.getTwitterCollect(minPubTime, minPubTimeTwid, maxPubTime, twitterUrl,
						positionValue, profileId, twitterNickName);
				try {

					if (null != twitterTimeline) {
						List<Timeline> timelineList = mapper.readValue(twitterTimeline,
								new TypeReference<List<Timeline>>() {
								});
						Timeline timeline = new Timeline();
						for (Timeline t : timelineList) {
							timeline.setId(t.getId());
							timeline.setProfileID(t.getProfileID());
							timeline.setTwContent(t.getTwContent().replaceAll("[\\x{10000}-\\x{10FFFF}]", ""));
							timeline.setTweet(t.getTweet());
							timeline.setTwid(t.getTwid());
							timeline.setTwImgName(t.getTwImgName());
							timeline.setTwImgURL(t.getTwImgURL());
							timeline.setTwImgURLSize(t.getTwImgURLSize());
							timeline.setTwPositionValue(t.getTwPositionValue());
							timeline.setTwPubTime(t.getTwPubTime());
							timeline.setTwUserAvatarURL(t.getTwUserAvatarURL());
							timeline.setTwUserID(t.getTwUserID());
							timeline.setTwUserName(t.getTwUserName().replaceAll("[\\x{10000}-\\x{10FFFF}]", ""));
							if (t.getTweet() == 1) {
								downloads(t.getTwUserAvatarURL(), profileImagePath , t.getId() + ".jpg");
							} else {
								downloads(t.getTwUserAvatarURL(),
										profileImagePath , t.getProfileID() + ".jpg");
							}
							if (t.getTwImgURLSize() > 0) {
								String[] imageArray = t.getTwImgURL().split(",");
								for (int i = 0; i < t.getTwImgURLSize(); i++) {
									downloads(imageArray[i],
											timelineImagePath , t.getId() + "-" + i + ".jpg");

								}
							}
							timelineService.insertNew(timeline);
						}
						count++;
						System.out.println("第几次入库：" + count);
					} else {
						System.out.println("---没有新的数据---");
						break;
					}
					System.out.println("---帖子入库成功---");
				} catch (Exception e) {
					System.out.println("采集Timeline出现异常");
					e.printStackTrace();
					// TODO: handle exception
				}
			}

		}
	}
	
    public  void downloads(String urlString, String remoteUrl,  String filename) throws Exception {     
    	UnexpectedPage page = webClient.getPage(urlString);
    	InputStream is = page.getWebResponse().getContentAsStream();
        byte[] bs = new byte[1024];
        int len;
        SmbFile remoteFileDir = new SmbFile(remoteUrl);
		if (!remoteFileDir.exists())
			remoteFileDir.mkdirs();
		SmbFile remoteFile = new SmbFile(remoteUrl+filename);
		OutputStream os = new BufferedOutputStream(new SmbFileOutputStream(remoteFile, true));
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        os.close();
        is.close();
    }
	public String imgToBase64(String Url) {
		byte[] data = null;
		try {
			UnexpectedPage page = webClient.getPage(Url);
			InputStream in = page.getWebResponse().getContentAsStream();
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Base64.encodeBase64(data));
	}

}
