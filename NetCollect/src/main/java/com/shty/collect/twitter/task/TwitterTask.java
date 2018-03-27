package com.shty.collect.twitter.task;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.ResourceBundle;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shty.collect.domain.TbSystemHttp;
import com.shty.collect.domain.TbSystemNode;
import com.shty.collect.domain.TbSystemRelation;
import com.shty.collect.domain.TbSystemSocialaccount;
import com.shty.collect.domain.TbTwitterSysTask;
import com.shty.collect.service.TbSystemHttpServiceI;
import com.shty.collect.fb.test.FacebookTest;
import com.shty.collect.fb.test.Facebook_Status;
import com.shty.collect.service.System_Twitter_TaskServiceI;
import com.shty.collect.service.System_NodeServiceI;
import com.shty.collect.service.System_RelationServiceI;
import com.shty.collect.service.System_AccountServiceI;
import com.shty.collect.service.twitter.ProfileService;
import com.shty.collect.service.twitter.TimelineService;
import com.shty.collect.util.Utils;
import com.shty.collect.web.rest.dto.System_Twitter_TaskGroupAttrDto;

public class TwitterTask extends Thread {
	private ProfileService profileService;
	private TimelineService timelineService;
	private System_Twitter_TaskServiceI system_Twitter_TaskServiceI;
	private System_NodeServiceI system_NodeServiceI;
	private System_RelationServiceI system_RelationServiceI;
	private System_AccountServiceI system_AccountServiceI;
	private TbSystemHttpServiceI tbSystemHttpServiceI;
	public volatile static boolean twitter_status = false; // 这个关键字的目的是使status同步，也就是说在同一时刻只能由一个线程来修改status的值。

	public TwitterTask(ProfileService profileService, TimelineService timelineService,
			System_Twitter_TaskServiceI system_Twitter_TaskServiceI, System_NodeServiceI system_NodeServiceI,
			System_RelationServiceI system_RelationServiceI, System_AccountServiceI system_AccountServiceI,
			TbSystemHttpServiceI tbSystemHttpServiceI) {
		super();
		this.profileService = profileService;
		this.timelineService = timelineService;
		this.system_Twitter_TaskServiceI = system_Twitter_TaskServiceI;
		this.system_AccountServiceI = system_AccountServiceI;
		this.system_RelationServiceI = system_RelationServiceI;
		this.system_NodeServiceI = system_NodeServiceI;
		this.tbSystemHttpServiceI = tbSystemHttpServiceI;
	}

	private String ip = ResourceBundle.getBundle("systemconfig").getString("ip");// 设置访问的主节点
	protected static final String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0";
	ObjectMapper mapper = new ObjectMapper();
	String taskUrl = "http://" + ip +"/NetCollect/rest/selectTwitterTaskUrl?taskStatus=" + "1";
	private int taskid;

	private WebClient webClient;

	@Override
	public void run() {
		while (!twitter_status) {
			System.out.println("Twitter 采集工作开始！");
			Response res = null;
			String twitterUrl = null;
			String twitterNickName = null;
			TbSystemHttp tbSystemHttp = new TbSystemHttp();
			// 线程启动拿账号
			String select_type_facebook = "http://" + ip + "/NetCollect/rest/select_type_twitter?accountType=3";
			Response select_type_facebook_res = null;
			try {
				if (!Utils.isNullOrEmpty(select_type_facebook)) {
					select_type_facebook_res = Jsoup.connect(select_type_facebook).userAgent(userAgent).timeout(0)
							.ignoreContentType(true).execute();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			String select_type_res = select_type_facebook_res.body();
			if (select_type_res != null) {
				//将json数据转为实体类数据
				TbSystemSocialaccount list_type_facebook = Utils.getGson().fromJson(select_type_res,
						TbSystemSocialaccount.class);
				
				if (list_type_facebook != null) {
					TbSystemHttp http = tbSystemHttpServiceI.getByidHttp(list_type_facebook.getHttpid());
					if (http.getHttpip() != null && !"".equals(http.getHttpip())) {
						tbSystemHttp.setHttpip(http.getHttpip());
						TwitterNodeStatus.setTwitter_httpaccount(http.getHttpip());
					}
					if (http.getPort() != null && !"".equals(http.getPort())) {
						tbSystemHttp.setPort(http.getPort());
						TwitterNodeStatus.setTwitter_httpport(http.getPort());
					}
					if (http.getHttpname() != null && !"".equals(http.getHttpname())) {
						tbSystemHttp.setHttpname(http.getHttpname());
					}
					if (http.getHttppassword() != null && !"".equals(http.getHttppassword())) {
						tbSystemHttp.setHttppassword(http.getHttppassword());
					}

				}
			}
			WebclientCreat(tbSystemHttp);
			try {
				if (!Utils.isNullOrEmpty(taskUrl)) {
					res = Jsoup.connect(taskUrl).userAgent(userAgent).timeout(0).ignoreContentType(true).execute();

					if (null != res) {
						String resBody = res.body();
						System_Twitter_TaskGroupAttrDto tbTwitterSysTask = mapper.readValue(resBody,
								System_Twitter_TaskGroupAttrDto.class);
						twitterUrl = tbTwitterSysTask.getURL();
						taskid = tbTwitterSysTask.getId();
					}
					// System.out.println("url:"+url);
					if (null != twitterUrl) {
						twitterNickName = twitterUrl.substring(twitterUrl.indexOf("com/") + 4);
					}

				}
			} catch (IOException e) {
				TwitterNodeStatus.setNodeStatus("400");
				e.printStackTrace();
			}
			System.out.println("URL:" + twitterUrl);
			try {
				if (null != twitterUrl && null != twitterNickName) {
					TwitterNodeStatus.setNodeStatus("300");
					System.out.println("version:"+webClient.getBrowserVersion());
					TwitterTimelineThread twitterTimelineThread = new TwitterTimelineThread(profileService,
							timelineService, webClient, twitterUrl, twitterNickName, taskid);
					twitterTimelineThread.run();
					TwitterNodeStatus.setNodeStatus("500");
				} else {
					System.out.println("采集出错");
					TwitterNodeStatus.setNodeStatus("400");
				}
			} catch (Exception e) {
				TwitterNodeStatus.setNodeStatus("400");
				e.printStackTrace();
			}

			TbTwitterSysTask tbTwitterSysTask = new TbTwitterSysTask();
			tbTwitterSysTask.setId((long) taskid);
			tbTwitterSysTask.setTaskstatus("3");
			system_Twitter_TaskServiceI.updateTwitterTaskStatusStatic(tbTwitterSysTask);
			System.out.println("采集完成");
			
			System.out.println("休眠2分钟");
			Utils.sleep(120000);
		}
	}

	/**
	 * 创建Webclient进行登录账号
	 */
	public void WebclientCreat(TbSystemHttp tbSystemHttp) {
		// 用http代理
		String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36";
		webClient = new WebClient(BrowserVersion.CHROME);
		// 设置代理
		ProxyConfig proxyConfig = webClient.getOptions().getProxyConfig();
		/*DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient
				.getCredentialsProvider();*/
		if (tbSystemHttp != null) {
			if (tbSystemHttp.getHttpip() != null && !tbSystemHttp.getHttpip().equals("")) {
				//System.out.println("ip:"+tbSystemHttp.getHttpip());
				//System.out.println("port:"+Integer.parseInt(tbSystemHttp.getPort()));
				proxyConfig.setProxyHost(tbSystemHttp.getHttpip());
				proxyConfig.setProxyPort(Integer.parseInt(tbSystemHttp.getPort()));
				//credentialsProvider.addCredentials(tbSystemHttp.getHttpname(), tbSystemHttp.getHttppassword());
			}
		}
		try {
			webClient.getBrowserVersion().setUserAgent(userAgent);
			webClient.setRefreshHandler(new ThreadedRefreshHandler());
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(false);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setRedirectEnabled(true);
			webClient.getOptions().setTimeout(120000);
			webClient.getOptions().setUseInsecureSSL(true);
			webClient.setJavaScriptTimeout(300000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
