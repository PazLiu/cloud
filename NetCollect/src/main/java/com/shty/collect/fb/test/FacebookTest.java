package com.shty.collect.fb.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.shty.collect.domain.TbFacebookSysTask;
import com.shty.collect.domain.TbFacebookSysTaskgroup;
import com.shty.collect.domain.TbSysThirdTask;
import com.shty.collect.domain.TbSystemHttp;
import com.shty.collect.domain.TbSystemSocialaccount;
import com.shty.collect.domain.fb.FbOverviewWork;
import com.shty.collect.domain.fb.FbTarget;
import com.shty.collect.domain.fb.OverviewBackgroung;
import com.shty.collect.domain.fb.OverviewBackgroungDescriptions;
import com.shty.collect.domain.fb.OverviewContacts;
import com.shty.collect.domain.fb.OverviewEducation;
import com.shty.collect.domain.fb.OverviewEducationDescriptions;
import com.shty.collect.domain.fb.OverviewFamily;
import com.shty.collect.domain.fb.OverviewLivings;
import com.shty.collect.domain.fb.OverviewSkills;
import com.shty.collect.domain.fb.OverviewWorkdescriptions;
import com.shty.collect.service.System_AccountServiceI;
import com.shty.collect.service.System_Facebook_TaskServiceI;
import com.shty.collect.service.System_Facebook_TaskgroupService;
import com.shty.collect.service.System_NodeServiceI;
import com.shty.collect.service.System_RelationServiceI;
import com.shty.collect.service.TbSystemHttpServiceI;
import com.shty.collect.service.ThridTaskServiceI;
import com.shty.collect.service.fb.FBPhoto_ServiceI;
import com.shty.collect.service.fb.FbOverviewWork_ServiceI;
import com.shty.collect.service.fb.FbTarget_ServiceI;
import com.shty.collect.service.fb.Friends_ServiceI;
import com.shty.collect.service.fb.OverviewBackgroung_ServiceI;
import com.shty.collect.service.fb.OverviewContacts_ServiceI;
import com.shty.collect.service.fb.OverviewEducation_ServiceI;
import com.shty.collect.service.fb.OverviewFamily_ServiceI;
import com.shty.collect.service.fb.OverviewLivings_ServiceI;
import com.shty.collect.service.fb.OverviewSkills_ServiceI;
import com.shty.collect.service.fb.OverviewWorkdescriptions_ServiceI;
import com.shty.collect.service.fb.TimelineComment_ServiceI;
import com.shty.collect.service.fb.TimelineStory_ServiceI;
import com.shty.collect.service.fb.TimelineStoryimgCodeList_ServiceI;
import com.shty.collect.service.fb.TimelineYear_ServiceI;
import com.shty.collect.util.FileUpload;
import com.shty.collect.web.rest.dto.Syste_Facebook_Task_Attribute_Group;
import com.shty.collect.web.rest.dto.System_GroupAnd_AttributeDto;
import com.shty.collect.web.rest.dto.Task_StatusDto;
import com.smit.fb.controller.FbDetailController;
import com.smit.fb.controller.FbSearchController;
import com.smit.fb.entity.apps.OverviewEntity;
import com.smit.fb.entity.http.SearchResponseEntity;
import com.smit.fb.entity.overview.EducationEntity;
import com.smit.fb.entity.overview.WorkEntity;
import com.smit.fb.entity.search.People;
import com.smit.fb.http.FbHttpUtils;
import com.smit.fb.util.MyStringUtils;
import com.smit.fb.util.MyUtil;
import com.smit.fb.util.Utils;
import com.smit.fbAppclient.MyRequestEntity;
import com.smit.fbAppclient.MyResponseEntity;

public class FacebookTest extends Thread {

	private FbTarget_ServiceI fbTarget_ServiceI;
	private FbOverviewWork_ServiceI fbOverview_ServiceI;
	private OverviewWorkdescriptions_ServiceI overviewWorkdescriptions_ServiceI;
	private OverviewEducation_ServiceI overviewEducation_ServiceI;
	private OverviewLivings_ServiceI overviewLivings_ServiceI;
	private OverviewSkills_ServiceI overviewSkills_ServiceI;
	private OverviewContacts_ServiceI overviewContacts_ServiceI;

	private OverviewFamily_ServiceI overviewFamily_ServiceI;
	private OverviewBackgroung_ServiceI overviewBackgroung_ServiceI;
	private Friends_ServiceI friends_ServiceI;
	private TimelineYear_ServiceI timelineYear_ServiceI;
	private TimelineStory_ServiceI timelineStory_ServiceI;
	private TimelineStoryimgCodeList_ServiceI timelineStoryimgCodeList_ServiceI;
	private TimelineComment_ServiceI timelineComment_ServiceI;
	private FBPhoto_ServiceI fBPhoto_ServiceI;
	private System_NodeServiceI system_NodeServiceI;
	private System_RelationServiceI system_RelationServiceI;
	private System_AccountServiceI system_AccountServiceI;
	private System_Facebook_TaskServiceI system_Facebook_TaskServiceI;
	private System_Facebook_TaskgroupService system_Facebook_TaskgroupService;
	private TbSystemHttpServiceI tbSystemHttpServiceI;
	private ThridTaskServiceI thirdService;
	private String socialid;
	private String loginResult = "";
	private String account;
	static MyRequestEntity request = new MyRequestEntity();
	static MyResponseEntity response = new MyResponseEntity();
	private WebClient webClient;
	private String imgPath = ResourceBundle.getBundle("systemconfig").getString("imgPath");// 设置图片存储路径
	private String ip = ResourceBundle.getBundle("systemconfig").getString("ip");// 设置访问的主节点
	private String relativePath = "";
	public static final String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0";
	private Long task_id;
	public volatile static boolean facebook_status = false; // 这个关键字的目的是使status同步，也就是说在同一时刻只能由一个线程来修改status的值。

	public FacebookTest(FbTarget_ServiceI fbTarget_ServiceI, FbOverviewWork_ServiceI fbOverview_ServiceI,
			OverviewWorkdescriptions_ServiceI overviewWorkdescriptions_ServiceI,
			OverviewEducation_ServiceI overviewEducation_ServiceI, OverviewLivings_ServiceI overviewLivings_ServiceI,
			OverviewSkills_ServiceI overviewSkills_ServiceI, OverviewContacts_ServiceI overviewContacts_ServiceI,
			OverviewFamily_ServiceI overviewFamily_ServiceI, OverviewBackgroung_ServiceI overviewBackgroung_ServiceI,
			Friends_ServiceI friends_ServiceI, TimelineYear_ServiceI timelineYear_ServiceI,
			TimelineStory_ServiceI timelineStory_ServiceI,
			TimelineStoryimgCodeList_ServiceI timelineStoryimgCodeList_ServiceI,
			TimelineComment_ServiceI timelineComment_ServiceI, FBPhoto_ServiceI fBPhoto_ServiceI,
			System_NodeServiceI system_NodeServiceI, System_RelationServiceI system_RelationServiceI,
			System_AccountServiceI system_AccountServiceI, System_Facebook_TaskServiceI system_Facebook_TaskServiceI,
			System_Facebook_TaskgroupService system_Facebook_TaskgroupService,
			TbSystemHttpServiceI tbSystemHttpServiceI, ThridTaskServiceI thirdService) {
		super();
		this.fbTarget_ServiceI = fbTarget_ServiceI;
		this.fbOverview_ServiceI = fbOverview_ServiceI;
		this.overviewWorkdescriptions_ServiceI = overviewWorkdescriptions_ServiceI;
		this.overviewEducation_ServiceI = overviewEducation_ServiceI;
		this.overviewLivings_ServiceI = overviewLivings_ServiceI;
		this.overviewSkills_ServiceI = overviewSkills_ServiceI;
		this.overviewContacts_ServiceI = overviewContacts_ServiceI;
		this.overviewFamily_ServiceI = overviewFamily_ServiceI;
		this.overviewBackgroung_ServiceI = overviewBackgroung_ServiceI;
		this.friends_ServiceI = friends_ServiceI;
		this.timelineYear_ServiceI = timelineYear_ServiceI;
		this.timelineStory_ServiceI = timelineStory_ServiceI;
		this.timelineStoryimgCodeList_ServiceI = timelineStoryimgCodeList_ServiceI;
		this.timelineComment_ServiceI = timelineComment_ServiceI;
		this.fBPhoto_ServiceI = fBPhoto_ServiceI;
		this.system_NodeServiceI = system_NodeServiceI;
		this.system_RelationServiceI = system_RelationServiceI;
		this.system_AccountServiceI = system_AccountServiceI;
		this.system_Facebook_TaskServiceI = system_Facebook_TaskServiceI;
		this.system_Facebook_TaskgroupService = system_Facebook_TaskgroupService;
		this.tbSystemHttpServiceI = tbSystemHttpServiceI;
		this.thirdService = thirdService;
	}

	@Override
	public void run() {
		System.out.println("Facebook-------采集工作开始");
		while (!facebook_status) {
			// 获取本机IP地址得到所绑定的账号
			TbSystemHttp tbSystemHttp = new TbSystemHttp();
			TbSysThirdTask tbSysThirdTask = null;
			TbFacebookSysTask task_thrid = null;
			// 得到登录的账号用户名密码
			String ResultAccount = null;
			String password = null;

			// 线程启动拿账号
			String select_type_facebook = "http://" + ip + "/NetCollect/rest/select_type_facebook?accountType=1";
			Response select_type_facebook_res = null;
			try {
				if (!Utils.isNullOrEmpty(select_type_facebook)) {
					select_type_facebook_res = Jsoup.connect(select_type_facebook).userAgent(userAgent).timeout(0)
							.ignoreContentType(true).execute();
				}
			} catch (Exception e) {
				// TODO: handle exception
				FacebookTest.facebook_status = true;
				e.printStackTrace();
			}
			String select_type_res = select_type_facebook_res.body();
			if (select_type_res != null) {
				// 将json数据转为实体类数据
				TbSystemSocialaccount list_type_facebook = Utils.getGson().fromJson(select_type_res,
						TbSystemSocialaccount.class);
				if (list_type_facebook != null) {
					ResultAccount = list_type_facebook.getAccountname();
					Facebook_Status.setFacebook_Socialaccount(ResultAccount);
					password = list_type_facebook.getPassword();
					Facebook_Status.setFacebook_Socialpassword(password);
					socialid = list_type_facebook.getId().toString();
					TbSystemHttp http = tbSystemHttpServiceI.getByidHttp(list_type_facebook.getHttpid());
					if (http.getHttpip() != null && !"".equals(http.getHttpip())) {
						Facebook_Status.setFacebook_httpaccount(http.getHttpip());
						tbSystemHttp.setHttpip(http.getHttpip());
					}
					if (http.getPort() != null && !"".equals(http.getPort())) {
						Facebook_Status.setFacebook_httpport(http.getPort());
						tbSystemHttp.setPort(http.getPort());
					}
					if (http.getHttpname() != null && !"".equals(http.getHttpname())) {
						tbSystemHttp.setHttpname(http.getHttpname());
					}
					if (http.getHttppassword() != null && !"".equals(http.getHttppassword())) {
						tbSystemHttp.setHttppassword(http.getHttppassword());
					}
				}
			}

			// 设置节点状态为200
			Facebook_Status.setFacebook_nodeStatus("200");
			if (ResultAccount != null) {
				facebookLogin(ResultAccount, password, tbSystemHttp);
				FbDetailController fb = new FbDetailController(webClient);
				if (loginResult.equals("2")) {
					System.out.println("账号异常");
				} else if (loginResult.equals("3")) {
					System.out.println("网络异常");
				}
				// 首先采集第三方的任务
				do {
					if (!"".equals(loginResult) && loginResult != null && loginResult.equals("0")) {
						String third_url = "http://" + ip + "/NetCollect/rest/third/getThirdTask?tasktype=facebook";
						Response third_res = null;
						try {
							if (!Utils.isNullOrEmpty(third_url)) {
								third_res = Jsoup.connect(third_url).userAgent(userAgent).timeout(0)
										.ignoreContentType(true).execute();
							}
						} catch (IOException e) {
							Facebook_Status.setFacebook_nodeStatus("700");
							e.printStackTrace();
						}
						try {
							if (third_res != null) {
								String third_json = third_res.body();
								tbSysThirdTask = Utils.getGson().fromJson(third_json, TbSysThirdTask.class);
								// 根据url进行采集
								if (tbSysThirdTask != null) {
									if (tbSysThirdTask.getUrl() != null && !"".equals(tbSysThirdTask.getUrl())) {
										System.out.println("根据第三方url采集数据开始");
										String Overview = "true";
										String Friends = "true";
										String Timeline = "true";
										String Photo = "true";
										String comments = "true";
										String likes = "true";

										boolean OverviewFlag = false;
										if (!MyStringUtils.isNullOrEmpty(Overview)) {
											OverviewFlag = Boolean.valueOf(Overview);
										}
										boolean FriendsFlag = false;
										if (!MyStringUtils.isNullOrEmpty(Friends)) {
											FriendsFlag = Boolean.valueOf(Friends);
										}
										boolean TimelineFlag = false;
										if (!MyStringUtils.isNullOrEmpty(Timeline)) {
											TimelineFlag = Boolean.valueOf(Timeline);
										}
										boolean PhotoFlag = false;
										if (!MyStringUtils.isNullOrEmpty(Photo)) {
											PhotoFlag = Boolean.valueOf(Photo);
										}

										String fburl = tbSysThirdTask.getUrl();
										String urlpattern = FbHttpUtils.getsubdomainUrl(fburl);
										Facebook_Status.setFacebook_nodeStatus("300");

										System.out.println("采集人物的URL：" + fburl);
										// 查询数据库中是否有这个人
										FbTarget target = fbTarget_ServiceI.getFbTarget_urlpattern(urlpattern);
										if (OverviewFlag) {
											// facebook采集个人简介
											if (target == null) {
												// 得到Overview返回数据
												String resultOverview = null;
												try {
													resultOverview = fb.getOverview(account, fburl, "true", request,
															response);
												} catch (Exception e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
													tbSysThirdTask.setTaskstatus("0");
													tbSysThirdTask.setUpdatetime(
															new Timestamp(System.currentTimeMillis()).toString());
													thirdService.updateTask(tbSysThirdTask);
												}
												if (resultOverview != null) {
													target = insert_Overview(resultOverview, urlpattern, new Long(""));
													tbSysThirdTask.setFullname(target.getFullName());
													tbSysThirdTask.setProfileid(
															Integer.parseInt(String.valueOf(target.getId())));
													tbSysThirdTask.setUpdatetime(
															new Timestamp(System.currentTimeMillis()).toString());
													thirdService.updateTask(tbSysThirdTask);
												} else {
													System.out.println("采集Overview失败");
													tbSysThirdTask.setTaskstatus("0");
													tbSysThirdTask.setUpdatetime(
															new Timestamp(System.currentTimeMillis()).toString());
													thirdService.updateTask(tbSysThirdTask);
												}
											} else {
												if (target.getOverview() == null) {
													System.out.println("更新Overview" + fburl);
													String resultOverview = null;
													try {
														resultOverview = fb.getOverview(account, fburl, "true", request,
																response);
													} catch (Exception e) {
														// TODO Auto-generated
														// catch
														e.printStackTrace();
														tbSysThirdTask.setTaskstatus("0");
														tbSysThirdTask.setUpdatetime(
																new Timestamp(System.currentTimeMillis()).toString());
														thirdService.updateTask(tbSysThirdTask);
													}
													if (resultOverview != null) {
														update_Overview(target, resultOverview, urlpattern,
																new Long(""));
														tbSysThirdTask.setFullname(target.getFullName());
														tbSysThirdTask.setProfileid(
																Integer.parseInt(String.valueOf(target.getId())));
														tbSysThirdTask.setUpdatetime(
																new Timestamp(System.currentTimeMillis()).toString());
														thirdService.updateTask(tbSysThirdTask);
													} else {
														System.out.println("采集Overview失败");
														tbSysThirdTask.setTaskstatus("0");
														tbSysThirdTask.setUpdatetime(
																new Timestamp(System.currentTimeMillis()).toString());
														thirdService.updateTask(tbSysThirdTask);
													}
												}
											}
										}
										if (target != null) {
											try {
												// // 采集好友
												if (FriendsFlag) {
													if (target.getFriendsurl() != "finished") {
														Facebook_Status.setFacebook_nodeStatus("400");
														Facebook_FriendsThread friendsThread = new Facebook_FriendsThread(
																webClient, account, password, fburl, request, response,
																target, friends_ServiceI, fbTarget_ServiceI,
																urlpattern);
														friendsThread.run();
													}
												}
											} catch (Exception e) {
												// TODO: handle exception
												e.printStackTrace();
												loginResult = "2";
												tbSysThirdTask.setTaskstatus("0");
												tbSysThirdTask.setUpdatetime(
														new Timestamp(System.currentTimeMillis()).toString());
												thirdService.updateTask(tbSysThirdTask);
											}

											try {
												// 采集Timeline
												if (TimelineFlag) {
													Facebook_Status.setFacebook_nodeStatus("500");
													Facebook_TimelineThread timelineThread = new Facebook_TimelineThread(
															webClient, account, password, fburl, request, response,
															target, comments, likes, "", "", timelineYear_ServiceI,
															timelineStory_ServiceI, timelineStoryimgCodeList_ServiceI,
															timelineComment_ServiceI);
													timelineThread.run();
												}
											} catch (Exception e) {
												// TODO: handle exception
												e.printStackTrace();
												loginResult = "2";
												tbSysThirdTask.setTaskstatus("0");
												tbSysThirdTask.setUpdatetime(
														new Timestamp(System.currentTimeMillis()).toString());
												thirdService.updateTask(tbSysThirdTask);
											}

											try {
												if (PhotoFlag) {
													if (target.getPhotoUrl() != "finished") {
														Facebook_Status.setFacebook_nodeStatus("600");
														Facebook_PhotoThread photoThread = new Facebook_PhotoThread(
																account, fburl, request, response, webClient, target,
																fBPhoto_ServiceI, fbTarget_ServiceI);
														photoThread.run();
													}
												}

											} catch (Exception e) {
												// TODO: handle exception
												e.printStackTrace();
												loginResult = "2";
												tbSysThirdTask.setTaskstatus("0");
												tbSysThirdTask.setUpdatetime(
														new Timestamp(System.currentTimeMillis()).toString());
												thirdService.updateTask(tbSysThirdTask);
											}
											// 采集完成之后修改该任务的属性为1，代表今天采集过了
											System.out.println("该人物采集完成fburl:" + fburl);
											tbSysThirdTask.setTaskstatus("1");
											tbSysThirdTask.setUpdatetime(
													new Timestamp(System.currentTimeMillis()).toString());
											thirdService.updateTask(tbSysThirdTask);
										}

										// 第三方搜索任务
									} else if (tbSysThirdTask.getKeyword() != null
											|| !"".equals(tbSysThirdTask.getKeyword())) {
										System.out.println("第三方搜索关键词开始。。。。");
										int third_id = tbSysThirdTask.getId();
										FbSearchController fbsearch = new FbSearchController(webClient);
										// 搜索关键字keyword
										String keyword = tbSysThirdTask.getKeyword();
										Facebook_Status.setFacebook_nodeStatus("300");
										boolean hasNext = true;
										int pn = 1;
										while (hasNext) {
											if (!"".equals(loginResult) && loginResult != null
													&& loginResult.equals("0")) {
												String pn1 = Integer.toString(pn);
												String resultSearch = fbsearch.searchKeyword(keyword, pn1, account,
														"true", request, response);
												if (resultSearch != null && !"".equals(resultSearch)) {
													SearchResponseEntity Searchresponse = Utils.getGson()
															.fromJson(resultSearch, SearchResponseEntity.class);
													if (Searchresponse.getStatuscode() == 200
															|| Searchresponse.getStatuscode() == 203) {
														List<People> peopleList = Searchresponse.getSearchResult();
														TbFacebookSysTask resulttbFacebookSysTask = new TbFacebookSysTask();
														TbFacebookSysTask tbFacebookSysTask = new TbFacebookSysTask();
														if (null != peopleList) {
															for (People p : peopleList) {
																String fbUrl = p.getFburl();
																String urlpattern = FbHttpUtils.getsubdomainUrl(fbUrl);
																if (!MyStringUtils.isNullOrEmpty(urlpattern)) {
																	tbFacebookSysTask.setUrl(
																			"https://www.facebook.com/" + urlpattern);
																}
																tbFacebookSysTask.setTaskName(p.getFmt_name());
																tbFacebookSysTask.setTaskstatus("1");
																tbFacebookSysTask.setThird_id(third_id);
																resulttbFacebookSysTask = system_Facebook_TaskServiceI
																		.select_taskUrl(urlpattern);
																if (resulttbFacebookSysTask == null) {
																	int i = system_Facebook_TaskServiceI
																			.addthirdTask(tbFacebookSysTask);
																	if (i != 0) {
																		System.out.println(("搜索人物入库" + fbUrl));
																	} else {
																		System.out.println(("搜索入库失败！！"));
																	}
																} else {
																	System.out.println("数据库中已有该人物！！！");
																}
															}
														} else {
															System.out.println(("Search result list is null"));
														}
													}
													hasNext = Searchresponse.isHasNextPage();
													if (hasNext) {
														pn++;
													}

												} else {
													System.out.println("搜索----错误");
												}
											} else {
												break;
											}
											Utils.sleep(10000);
										}
										// // 修改第三方搜索采集状态
										tbSysThirdTask.setTaskstatus("1");
										tbSysThirdTask
												.setUpdatetime(new Timestamp(System.currentTimeMillis()).toString());
										thirdService.updateTask(tbSysThirdTask);
									} else {
										System.out.println("关键词搜索没有");
									}
								} else {
									System.out.println("根据第三方url采集和关键词搜索都没有任务");
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							loginResult = "2";
							tbSysThirdTask.setTaskstatus("0");
							tbSysThirdTask.setUpdatetime(new Timestamp(System.currentTimeMillis()).toString());
							thirdService.updateTask(tbSysThirdTask);
						}
					} else {
						System.out.println("登录失败");
						Facebook_Status.setFacebook_SocialStatus("登录异常。。。");
						break;
					}
				} while (tbSysThirdTask != null);

				// 使用jsoup进行访问接口，得到第三方关键词搜索到的url
				do {
					if (!"".equals(loginResult) && loginResult != null && loginResult.equals("0")) {
						String taskStatus = "1";
						String third_url = "http://" + ip + "/NetCollect/rest/getFacebookTask?taskStatus=" + taskStatus;
						Response third_res = null;
						try {
							if (!Utils.isNullOrEmpty(third_url)) {
								third_res = Jsoup.connect(third_url).userAgent(userAgent).timeout(0)
										.ignoreContentType(true).execute();
							}
						} catch (IOException e) {
							Facebook_Status.setFacebook_nodeStatus("700");
						}
						try {
							if (third_res != null) {
								String response_json = third_res.body();
								task_thrid = Utils.getGson().fromJson(response_json, TbFacebookSysTask.class);
								if (task_thrid != null) {
									String Overview = "true";
									String Friends = "true";
									String Timeline = "true";
									String Photo = "true";
									String comments = "true";
									String likes = "true";

									boolean OverviewFlag = false;
									if (!MyStringUtils.isNullOrEmpty(Overview)) {
										OverviewFlag = Boolean.valueOf(Overview);
									}
									boolean FriendsFlag = false;
									if (!MyStringUtils.isNullOrEmpty(Friends)) {
										FriendsFlag = Boolean.valueOf(Friends);
									}
									boolean TimelineFlag = false;
									if (!MyStringUtils.isNullOrEmpty(Timeline)) {
										TimelineFlag = Boolean.valueOf(Timeline);
									}
									boolean PhotoFlag = false;
									if (!MyStringUtils.isNullOrEmpty(Photo)) {
										PhotoFlag = Boolean.valueOf(Photo);
									}
									String fburl = task_thrid.getUrl();
									String urlpattern = FbHttpUtils.getsubdomainUrl(fburl);
									System.out.println("采集人物的URL：" + fburl);
									Facebook_Status.setFacebook_nodeStatus("300");

									// 查询数据库中是否有这个人
									FbTarget target = fbTarget_ServiceI.getFbTarget_urlpattern(urlpattern);
									if (OverviewFlag) {
										// facebook采集个人简介
										if (target == null) {
											// 得到Overview返回数据
											String resultOverview = null;
											try {
												resultOverview = fb.getOverview(account, fburl, "true", request,
														response);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
												Task_StatusDto status = new Task_StatusDto();
												status.setId(task_thrid.getId());
												status.setTaskStatus("1");
												system_Facebook_TaskgroupService.updateGroup_task_static(status);
											}
											if (resultOverview != null) {
												target = insert_Overview(resultOverview, urlpattern,
														task_thrid.getId());
											} else {
												Task_StatusDto status = new Task_StatusDto();
												status.setId(task_thrid.getId());
												status.setTaskStatus("1");
												system_Facebook_TaskgroupService.updateGroup_task_static(status);
												System.out.println("采集Overview失败");
											}
										} else {
											if (target.getOverview() == null) {
												System.out.println("更新Overview" + fburl);
												String resultOverview = null;
												try {
													resultOverview = fb.getOverview(account, fburl, "true", request,
															response);
												} catch (Exception e) {
													// TODO Auto-generated catch
													e.printStackTrace();
													Task_StatusDto status = new Task_StatusDto();
													status.setId(task_thrid.getId());
													status.setTaskStatus("1");
													system_Facebook_TaskgroupService.updateGroup_task_static(status);
												}
												if (resultOverview != null) {
													update_Overview(target, resultOverview, urlpattern,
															task_thrid.getId());
												} else {
													Task_StatusDto status = new Task_StatusDto();
													status.setId(task_thrid.getId());
													status.setTaskStatus("1");
													system_Facebook_TaskgroupService.updateGroup_task_static(status);
													System.out.println("采集Overview失败");
												}
											}
										}
									}
									if (target != null) {
										try {
											// // 采集好友
											if (FriendsFlag) {
												if (target.getFriendsurl() != "finished") {
													Facebook_Status.setFacebook_nodeStatus("400");
													Facebook_FriendsThread friendsThread = new Facebook_FriendsThread(
															webClient, account, password, fburl, request, response,
															target, friends_ServiceI, fbTarget_ServiceI, urlpattern);
													friendsThread.run();
												}
											}
										} catch (Exception e) {
											// TODO: handle exception
											e.printStackTrace();
											Task_StatusDto status = new Task_StatusDto();
											status.setId(task_thrid.getId());
											status.setTaskStatus("1");
											system_Facebook_TaskgroupService.updateGroup_task_static(status);
											Facebook_Status.setFacebook_SocialStatus("登录异常。。。");
										}

										try {
											// 采集Timeline
											if (TimelineFlag) {
												Facebook_Status.setFacebook_nodeStatus("500");
												Facebook_TimelineThread timelineThread = new Facebook_TimelineThread(
														webClient, account, password, fburl, request, response, target,
														comments, likes, "", "", timelineYear_ServiceI,
														timelineStory_ServiceI, timelineStoryimgCodeList_ServiceI,
														timelineComment_ServiceI);
												timelineThread.run();
											}
										} catch (Exception e) {
											// TODO: handle exception
											e.printStackTrace();
											Task_StatusDto status = new Task_StatusDto();
											status.setId(task_thrid.getId());
											status.setTaskStatus("1");
											system_Facebook_TaskgroupService.updateGroup_task_static(status);
											Facebook_Status.setFacebook_SocialStatus("登录异常。。。");
										}

										try {
											if (PhotoFlag) {
												if (target.getPhotoUrl() != "finished") {
													Facebook_Status.setFacebook_nodeStatus("600");
													Facebook_PhotoThread photoThread = new Facebook_PhotoThread(account,
															fburl, request, response, webClient, target,
															fBPhoto_ServiceI, fbTarget_ServiceI);
													photoThread.run();
												}
											}
										} catch (Exception e) {
											// TODO: handle exception
											e.printStackTrace();
											Task_StatusDto status = new Task_StatusDto();
											status.setId(task_thrid.getId());
											status.setTaskStatus("1");
											system_Facebook_TaskgroupService.updateGroup_task_static(status);
											Facebook_Status.setFacebook_SocialStatus("登录异常。。。");
										}
										// 采集完成之后修改该任务的属性为3，代表今天采集过了
										System.out.println("该人物采集完成fburl:" + fburl);
										Task_StatusDto status = new Task_StatusDto();
										status.setId(task_thrid.getId());
										status.setTaskStatus("3");
										system_Facebook_TaskgroupService.updateGroup_task_static(status);
									}

								} else {
									System.out.println("第三方关键词搜索URL无任务");
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							Task_StatusDto status = new Task_StatusDto();
							status.setId(task_thrid.getId());
							status.setTaskStatus("1");
							system_Facebook_TaskgroupService.updateGroup_task_static(status);
						}
					} else {
						System.out.println("登录失败");
						Facebook_Status.setFacebook_SocialStatus("登录异常。。。");
						break;
					}
				} while (task_thrid != null);

				// 执行登录判断
				if (!"".equals(loginResult) && loginResult != null && loginResult.equals("0")) {
					String url = "http://" + ip + "/NetCollect/rest/select_Task?task_static=" + "1";
					// 使用jsoup进行访问接口，得到本地任务
					Response res = null;
					try {
						if (!Utils.isNullOrEmpty(url)) {
							res = Jsoup.connect(url).userAgent(userAgent).timeout(0).ignoreContentType(true).execute();
						}
					} catch (IOException e) {
						Facebook_Status.setFacebook_nodeStatus("700");
						e.printStackTrace();
					}

					try {
						if (res != null) {
							String response_json = res.body();
							Syste_Facebook_Task_Attribute_Group task = Utils.getGson().fromJson(response_json,
									Syste_Facebook_Task_Attribute_Group.class);
							if (task.getId() != 0) {
								task_id = (long) task.getId();
								String Overview = task.getOverview();
								String Friends = task.getFriends();
								String Timeline = task.getTimelin();
								String Photo = task.getPhotos();
								String comments = task.getComments();
								String likes = task.getLikes();

								boolean OverviewFlag = true;
								if (!MyStringUtils.isNullOrEmpty(Overview)) {
									OverviewFlag = Boolean.valueOf(Overview);
								}
								boolean FriendsFlag = false;
								if (!MyStringUtils.isNullOrEmpty(Friends)) {
									FriendsFlag = Boolean.valueOf(Friends);
								}
								boolean TimelineFlag = false;
								if (!MyStringUtils.isNullOrEmpty(Timeline)) {
									TimelineFlag = Boolean.valueOf(Timeline);
								}
								boolean PhotoFlag = false;
								if (!MyStringUtils.isNullOrEmpty(Photo)) {
									PhotoFlag = Boolean.valueOf(Photo);
								}
								String fburl = task.getUrl();
								String urlpattern = FbHttpUtils.getsubdomainUrl(fburl);
								System.out.println("采集人物的URL：" + fburl);
								Facebook_Status.setFacebook_nodeStatus("300");

								// 查询数据库中是否有这个人
								FbTarget target = fbTarget_ServiceI.getFbTarget_urlpattern(urlpattern);
								if (OverviewFlag) {
									// facebook采集个人简介
									if (target == null) {
										// 得到Overview返回数据
										String resultOverview = null;
										try {
											resultOverview = fb.getOverview(account, fburl, "true", request, response);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											Task_StatusDto status = new Task_StatusDto();
											status.setId(task_id);
											status.setTaskStatus("1");
											system_Facebook_TaskgroupService.updateGroup_task_static(status);
										}
										if (resultOverview != null) {
											target = insert_Overview(resultOverview, urlpattern,
													new Long(task.getId()));
										} else {
											System.out.println("采集Overview失败");
											Task_StatusDto status = new Task_StatusDto();
											status.setId(task_id);
											status.setTaskStatus("1");
											system_Facebook_TaskgroupService.updateGroup_task_static(status);
										}
									} else {
										if (target.getOverview() == null) {
											System.out.println("更新Overview" + fburl);
											String resultOverview = null;
											try {
												resultOverview = fb.getOverview(account, fburl, "true", request,
														response);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
												Task_StatusDto status = new Task_StatusDto();
												status.setId(task_id);
												status.setTaskStatus("1");
												system_Facebook_TaskgroupService.updateGroup_task_static(status);
											}
											if (resultOverview != null) {
												update_Overview(target, resultOverview, urlpattern,
														new Long(task.getId()));
											} else {
												System.out.println("采集Overview失败");
												Task_StatusDto status = new Task_StatusDto();
												status.setId(task_id);
												status.setTaskStatus("1");
												system_Facebook_TaskgroupService.updateGroup_task_static(status);
											}
										}
									}
								}
								if (target != null) {
									try {
										// // 采集好友
										if (FriendsFlag) {
											if (target.getFriendsurl() != "finished") {
												Facebook_Status.setFacebook_nodeStatus("400");
												Facebook_FriendsThread friendsThread = new Facebook_FriendsThread(
														webClient, account, password, fburl, request, response, target,
														friends_ServiceI, fbTarget_ServiceI, urlpattern);
												friendsThread.run();
											}
										}
									} catch (Exception e) {
										// TODO: handle exception
										e.printStackTrace();
										Task_StatusDto status = new Task_StatusDto();
										status.setId(task_id);
										status.setTaskStatus("1");
										system_Facebook_TaskgroupService.updateGroup_task_static(status);
									}

									try {
										// 采集Timeline
										if (TimelineFlag) {
											Facebook_Status.setFacebook_nodeStatus("500");
											Facebook_TimelineThread timelineThread = new Facebook_TimelineThread(
													webClient, account, password, fburl, request, response, target,
													comments, likes, "", "", timelineYear_ServiceI,
													timelineStory_ServiceI, timelineStoryimgCodeList_ServiceI,
													timelineComment_ServiceI);
											timelineThread.run();
										}

									} catch (Exception e) {
										// TODO: handle exception
										e.printStackTrace();
										Task_StatusDto status = new Task_StatusDto();
										status.setId(task_id);
										status.setTaskStatus("1");
										system_Facebook_TaskgroupService.updateGroup_task_static(status);
									}
									try {
										if (PhotoFlag) {
											if (target.getPhotoUrl() != "finished") {
												Facebook_Status.setFacebook_nodeStatus("600");
												Facebook_PhotoThread photoThread = new Facebook_PhotoThread(account,
														fburl, request, response, webClient, target, fBPhoto_ServiceI,
														fbTarget_ServiceI);
												photoThread.run();
											}
										}

									} catch (Exception e) {
										// TODO: handle exception
										e.printStackTrace();
										Task_StatusDto status = new Task_StatusDto();
										status.setId(task_id);
										status.setTaskStatus("1");
										system_Facebook_TaskgroupService.updateGroup_task_static(status);
									}
									// 采集完成之后修改该任务的属性为3，代表今天采集过了
									System.out.println("该人物采集完成fburl:" + fburl);
									Task_StatusDto status = new Task_StatusDto();
									status.setId(task_id);
									status.setTaskStatus("3");
									system_Facebook_TaskgroupService.updateGroup_task_static(status);
								}

							} else {
								System.out.println("监控-----无任务！");
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						Task_StatusDto status = new Task_StatusDto();
						status.setId(task_id);
						status.setTaskStatus("1");
						system_Facebook_TaskgroupService.updateGroup_task_static(status);
					}
				} else {
					System.out.println("登录失败");
					Facebook_Status.setFacebook_SocialStatus("登录异常。。。");
				}

				// 搜索开始
				if (!"".equals(loginResult) && loginResult != null && loginResult.equals("0")) {
					String Searchurl = "http://" + ip + "/NetCollect/rest/getTaskgroupKeyWord";
					Response Searcres = null;
					System_GroupAnd_AttributeDto groupAndAttributeDto = null;
					try {
						if (!Utils.isNullOrEmpty(Searchurl)) {
							Searcres = Jsoup.connect(Searchurl).userAgent(userAgent).timeout(0).ignoreContentType(true)
									.execute();
						}
					} catch (IOException e) {
						Facebook_Status.setFacebook_nodeStatus("700");
						e.printStackTrace();
					}
					try {
						if (Searcres != null) {
							String Searcres_response = Searcres.body();
							groupAndAttributeDto = Utils.getGson().fromJson(Searcres_response,
									System_GroupAnd_AttributeDto.class);
							if (groupAndAttributeDto.getId() != null) {
								task_id = (long) groupAndAttributeDto.getId();
								FbSearchController fbsearch = new FbSearchController(webClient);
								// 搜索关键字keyword
								String keyword = groupAndAttributeDto.getKeyword();
								Facebook_Status.setFacebook_nodeStatus("300");
								// 执行登录
								boolean hasNext = true;
								int pn = 1;
								while (hasNext) {
									String pn1 = Integer.toString(pn);
									String resultSearch = fbsearch.searchKeyword(keyword, pn1, account, "true", request,
											response);
									if (resultSearch != null && !"".equals(resultSearch)) {
										try {
											SearchResponseEntity Searchresponse = Utils.getGson().fromJson(resultSearch,
													SearchResponseEntity.class);
											if (Searchresponse.getStatuscode() == 200
													|| Searchresponse.getStatuscode() == 203) {
												List<People> peopleList = Searchresponse.getSearchResult();
												TbFacebookSysTask resulttbFacebookSysTask = new TbFacebookSysTask();
												TbFacebookSysTask tbFacebookSysTask = new TbFacebookSysTask();
												if (null != peopleList) {
													for (People p : peopleList) {
														String fbUrl = p.getFburl();
														String urlpattern = FbHttpUtils.getsubdomainUrl(fbUrl);
														if (!MyStringUtils.isNullOrEmpty(urlpattern)) {
															tbFacebookSysTask
																	.setUrl("https://www.facebook.com/" + urlpattern);
														}
														tbFacebookSysTask.setTaskName(p.getFmt_name());
														tbFacebookSysTask.setTaskattributeid(Long
																.parseLong(groupAndAttributeDto.getTaskattributeid()));
														tbFacebookSysTask.setTaskstatus("1");
														tbFacebookSysTask.setTaskgroupid(task_id);
														resulttbFacebookSysTask = system_Facebook_TaskServiceI
																.select_taskUrl(urlpattern);

														if (resulttbFacebookSysTask == null) {
															int i = system_Facebook_TaskServiceI
																	.addTask(tbFacebookSysTask);
															if (i != 0) {
																System.out.println(("搜索人物入库" + fbUrl));
															} else {
																System.out.println(("搜索入库失败！！"));
															}
														} else {
															System.out.println("数据库中已有该人物！！！");
														}
													}
												} else {
													System.out.println(("Search result list is null"));
												}
											}
											hasNext = Searchresponse.isHasNextPage();
											if (hasNext) {
												pn++;
											}

										} catch (Exception e) {
											// TODO: handle exception
											e.printStackTrace();
											// 修改group搜索采集状态
											TbFacebookSysTaskgroup taskGroup = new TbFacebookSysTaskgroup();
											taskGroup.setId(groupAndAttributeDto.getId());
											taskGroup.setGroupname(groupAndAttributeDto.getGroupname());
											taskGroup.setGroupstatus("1");
											taskGroup.setGrouptype(groupAndAttributeDto.getGrouptype());
											taskGroup.setPriority(groupAndAttributeDto.getPriority());
											taskGroup.setKeyword(groupAndAttributeDto.getKeyword());
											taskGroup.setTaskattributeid(
													Long.parseLong(groupAndAttributeDto.getTaskattributeid()));
											system_Facebook_TaskgroupService.updateGroup(taskGroup);
										}
									} else {
										System.out.println("搜索----错误");
									}

								}
								// 修改group搜索采集状态
								TbFacebookSysTaskgroup taskGroup = new TbFacebookSysTaskgroup();
								taskGroup.setId(groupAndAttributeDto.getId());
								taskGroup.setGroupname(groupAndAttributeDto.getGroupname());
								taskGroup.setGroupstatus("3");
								taskGroup.setGrouptype(groupAndAttributeDto.getGrouptype());
								taskGroup.setPriority(groupAndAttributeDto.getPriority());
								taskGroup.setKeyword(groupAndAttributeDto.getKeyword());
								taskGroup.setTaskattributeid(Long.parseLong(groupAndAttributeDto.getTaskattributeid()));
								system_Facebook_TaskgroupService.updateGroup(taskGroup);
							} else {
								System.out.println("搜索----无数据");
							}

						} else {
							System.out.println("搜索没有任务");
						}
					} catch (Exception e) {
						// TODO: handle exception
						// 修改group搜索采集状态
						TbFacebookSysTaskgroup taskGroup = new TbFacebookSysTaskgroup();
						taskGroup.setId(groupAndAttributeDto.getId());
						taskGroup.setGroupname(groupAndAttributeDto.getGroupname());
						taskGroup.setGroupstatus("1");
						taskGroup.setGrouptype(groupAndAttributeDto.getGrouptype());
						taskGroup.setPriority(groupAndAttributeDto.getPriority());
						taskGroup.setKeyword(groupAndAttributeDto.getKeyword());
						taskGroup.setTaskattributeid(Long.parseLong(groupAndAttributeDto.getTaskattributeid()));
						system_Facebook_TaskgroupService.updateGroup(taskGroup);
					}
				}
				TbSystemSocialaccount socialaccount = system_AccountServiceI.select_socialAcount_id(Long.parseLong(socialid));
				// 修改账号状态为初始状态
				socialaccount.setIsused("0");
				system_AccountServiceI.updateAccount(socialaccount);
			}
			System.out.println("休眠2分钟");
			Utils.sleep(120000);
		}
		Facebook_Status.setFacebook_nodeStatus("100");
	}

	/**
	 * 创建Webclient进行登录账号
	 */
	public void WebclientCreat(TbSystemHttp tbSystemHttp) {
		// 用http代理
		webClient = new WebClient();
		if (tbSystemHttp != null) {
			// 设置代理
			ProxyConfig proxyConfig = webClient.getOptions().getProxyConfig();
			DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient
					.getCredentialsProvider();
			if (tbSystemHttp.getHttpip() != null && !tbSystemHttp.getHttpip().equals("")) {
				proxyConfig.setProxyHost(tbSystemHttp.getHttpip());
				proxyConfig.setProxyPort(Integer.parseInt(tbSystemHttp.getPort()));
				if (tbSystemHttp.getHttpname() != null && !tbSystemHttp.getHttpname().equals("")
						&& tbSystemHttp.getHttppassword() != null && !tbSystemHttp.equals("")) {
					credentialsProvider.addCredentials(tbSystemHttp.getHttpname(), tbSystemHttp.getHttppassword());
				}
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

	/**
	 * 判断是大陆手机号，还是香港手机号，还是是邮箱登录
	 * 
	 * @param account
	 * @return
	 */
	public static String Account(String account) {
		String str3 = null;

		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(account);
		if (account != null || !"".equals(account)) {
			if (matcher.matches()) {
				str3 = account;
			}
			String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
			Pattern p = Pattern.compile(regExp);
			Matcher m = p.matcher(account);
			if (m.matches()) {
				String str = account.substring(0, 3);
				String str1 = account.substring(3, 7);
				String str2 = account.substring(7, 11);
				str3 = str + " " + str1 + " " + str2;
			} else {
				String regExp_HongKong = "^(5|6|8|9)\\d{7}$";
				Pattern p_HongKong = Pattern.compile(regExp_HongKong);
				Matcher m_HongKong = p_HongKong.matcher(account);
				if (m_HongKong.matches()) {
					String str = account.substring(0, 4);
					String str1 = account.substring(4, 8);
					str3 = str + " " + str1;
				}
			}
		}
		return str3;
	}

	public void facebookLogin(String ResultAccount, String password, TbSystemHttp tbSystemHttp) {
		// 执行登录操作
		// 创建Webclient
		WebclientCreat(tbSystemHttp);
		account = Account(ResultAccount);
		FbDetailController fb = new FbDetailController(webClient);
		try {
			if (account != null || !"".equals(account) && password != null || !"".equals(password)) {
				loginResult = fb.login(account, password, request, response);
			}
		} catch (Exception e) {
			FacebookTest.facebook_status = true;
			e.printStackTrace();
		}
		if (!"".equals(loginResult) && loginResult != null && loginResult.equals("1") || loginResult.equals("2")) {
			Facebook_Status.setFacebook_nodeStatus("700");
			TbSystemSocialaccount socialaccount = system_AccountServiceI
					.select_socialAcount_id(Long.parseLong(socialid));
			if (loginResult.equals("1")) {
				// 修改账号状态账号问题
				socialaccount.setIsused("2");
				system_AccountServiceI.updateAccount(socialaccount);
			} else if (loginResult.equals("2")) {
				// 修改账号状态链路问题
				socialaccount.setIsused("3");
				system_AccountServiceI.updateAccount(socialaccount);
			}
		}
	}

	public FbTarget insert_Overview(String resultOverview, String urlpattern, Long Task_id) {
		System.out.println("数据库没有该人数据，添加Overview");
		OverviewEntity overviewentity = Utils.getGson().fromJson(resultOverview, OverviewEntity.class);
		FbTarget targetint = null;
		// 设置图片保存相对路径
		try {
			FbTarget fbtarget = new FbTarget();
			fbtarget.setFullName(overviewentity.getFullname());
			fbtarget.setOverview(resultOverview.getBytes("utf-8"));
			fbtarget.setUrlpattern(urlpattern);
			fbtarget.setTask_id(Task_id);
			fbTarget_ServiceI.insert_FbTarget_urlpattern(fbtarget);
			// 获取数据库这个人
			targetint = fbTarget_ServiceI.getFbTarget_urlpattern(urlpattern);
			try {
				// 设置图片保存相对路径文件夹，根据ID为后缀
				relativePath = "facebook_img/images/facebookimg/fbtargetid_" + targetint.getId().toString(); // 设置图片保存相对路径
				// 根据获取时间戳为图片名字
				String fullPath = relativePath + "/" + MyUtil.getTimeFormat() + "_main.jpg";
				// 为取出配置文件的路径
				byte[] decodeByteArray = Base64.decodeBase64(overviewentity.getImgcode());
				FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);
				fbtarget.setImgpath(fullPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fbtarget.setId(targetint.getId());
			fbTarget_ServiceI.update_FbTarget_urlpattern(fbtarget);
			if (overviewentity != null && !"".equals(overviewentity)) {
				List<WorkEntity> lsitwork = overviewentity.getWork();
				for (WorkEntity workEntity : lsitwork) {
					FbOverviewWork work = new FbOverviewWork();
					work.setCompany(workEntity.getCompany());
					work.setFBTarget_id(targetint.getId());
					// 添加wrok表中
					fbOverview_ServiceI.insert_FbOverview(work);
					List<String> descriptions = workEntity.getDescriptions();
					// 给description position 计数
					int position = -1;
					for (String string : descriptions) {
						// overviewWorkdescriptions_ServiceI
						position++;
						OverviewWorkdescriptions overviewdescriptions = new OverviewWorkdescriptions();
						overviewdescriptions.setFBoverview_work_id(work.getId());
						System.out.println(string);
						overviewdescriptions.setDescriptions(string);
						overviewdescriptions.setPosition(position);
						overviewWorkdescriptions_ServiceI.insert_OverviewWorkdescriptions(overviewdescriptions);
					}
				}

				try {
					// 解析Education 教育，学校
					List<EducationEntity> listEducation = overviewentity.getEducation();
					for (EducationEntity educationEntity : listEducation) {
						OverviewEducation education = new OverviewEducation();
						education.setSchool(educationEntity.getSchool());
						education.setFBTarget_id(targetint.getId());
						overviewEducation_ServiceI.insert_OverviewEducation(education);
						List<String> education_descriptions = educationEntity.getDescriptions();
						int position = -1;
						for (String string : education_descriptions) {
							position++;
							OverviewEducationDescriptions overviewEducationDescriptions = new OverviewEducationDescriptions();
							overviewEducationDescriptions.setFBoverview_education_id(education.getId());
							overviewEducationDescriptions.setDescriptions(string);
							overviewEducationDescriptions.setPosition(position);
							overviewEducation_ServiceI
									.insert_OverviewEducationDescriptions(overviewEducationDescriptions);
						}
						System.out.println("添加Education成功");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				try {
					// 解析Skills
					Map<String, String> skills = overviewentity.getSkills();
					if (!skills.keySet().isEmpty()) {
						for (String obj : skills.keySet()) {
							String key = obj;
							String value = skills.get(key);
							OverviewSkills overviewSkills = new OverviewSkills();
							overviewSkills.setInfoKey(key);
							overviewSkills.setInfoValue(value);
							overviewSkills.setFBTarget_id(targetint.getId());
							overviewSkills_ServiceI.insert_overviewSkills(overviewSkills);
						}
						System.out.println("添加skills成功");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				try {
					// 解析Livings 生活地方
					Map<String, String> livings = overviewentity.getLivings();
					if (!livings.keySet().isEmpty()) {
						for (String obj : livings.keySet()) {
							OverviewLivings overviewLivings = new OverviewLivings();
							String key = obj;
							String value = livings.get(key);
							overviewLivings.setInfoKey(key);
							overviewLivings.setInfoValue(value);
							overviewLivings.setFBTarget_id(targetint.getId());
							overviewLivings_ServiceI.insert_OverviewLivings(overviewLivings);
						}
						System.out.println("添加living成功");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				try {
					// 更新contacts 联系方式
					Map<String, String> contancts = overviewentity.getContacts();
					if (!contancts.keySet().isEmpty()) {
						for (String obj : contancts.keySet()) {
							OverviewContacts overviewContacts = new OverviewContacts();
							String key = obj;
							String value = contancts.get(key);
							overviewContacts.setInfoKey(key);
							overviewContacts.setInfoValue(value);
							overviewContacts.setFBTarget_id(targetint.getId());
							overviewContacts_ServiceI.insert_OverviewContacts(overviewContacts);
						}
						System.out.println("添加contacts成功");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				try {
					// 更新basicInfos 语言
					Map<String, String> basicInfos = overviewentity.getBasicInfos();
					if (!basicInfos.keySet().isEmpty()) {
						for (String obj : basicInfos.keySet()) {
							OverviewContacts overviewBasicInfos = new OverviewContacts();
							String key = obj;
							String value = basicInfos.get(key);
							overviewBasicInfos.setInfoKey(key);
							overviewBasicInfos.setInfoValue(value);
							overviewBasicInfos.setFBTarget_id(targetint.getId());
							overviewContacts_ServiceI.insert_OverviewBasicinfos(overviewBasicInfos);
						}
						System.out.println("添加basicInfos成功");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				try {
					// 更新nicknames 名称
					Map<String, String> nicknames = overviewentity.getNicknames();
					if (!nicknames.keySet().isEmpty()) {
						for (String obj : nicknames.keySet()) {
							OverviewContacts overviewNicknames = new OverviewContacts();
							String key = obj;
							String value = nicknames.get(key);
							overviewNicknames.setInfoKey(key);
							overviewNicknames.setInfoValue(value);
							overviewNicknames.setFBTarget_id(targetint.getId());
							overviewContacts_ServiceI.insert_OverviewNicknames(overviewNicknames);
						}
						System.out.println("添加nicknames成功");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				try {
					// 更新relationship
					Map<String, String> relationship = overviewentity.getRelationship();
					if (!relationship.keySet().isEmpty()) {
						for (String obj : relationship.keySet()) {
							OverviewContacts overviewRelationship = new OverviewContacts();
							String key = obj;
							String value = relationship.get(key);
							overviewRelationship.setInfoKey(key);
							overviewRelationship.setInfoValue(value);
							overviewRelationship.setFBTarget_id(targetint.getId());
							overviewContacts_ServiceI.insert_OverviewRelationship(overviewRelationship);
						}
						System.out.println("添加relationship成功");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				try {
					// 更新bio
					Map<String, String> bio = overviewentity.getBio();
					if (!bio.keySet().isEmpty()) {
						for (String obj : bio.keySet()) {
							OverviewContacts overviewBio = new OverviewContacts();
							String key = obj;
							String value = bio.get(key);
							overviewBio.setInfoKey(key);
							overviewBio.setInfoValue(value);
							overviewBio.setFBTarget_id(targetint.getId());
							overviewContacts_ServiceI.insert_OverviewBio(overviewBio);
						}
						System.out.println("添加bio成功");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				try {
					// 更新quote
					Map<String, String> quote = overviewentity.getBio();
					if (!quote.keySet().isEmpty()) {
						for (String obj : quote.keySet()) {
							OverviewContacts overviewQuote = new OverviewContacts();
							String key = obj;
							String value = quote.get(key);
							overviewQuote.setInfoKey(key);
							overviewQuote.setInfoValue(value);
							overviewQuote.setFBTarget_id(targetint.getId());
							overviewContacts_ServiceI.insert_OverviewQuote(overviewQuote);
						}
						System.out.println("添加quote成功");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				try {
					List<People> listFamily = overviewentity.getFamily();
					for (People people : listFamily) {
						OverviewFamily overviewFamily = new OverviewFamily();
						overviewFamily.setFbid(people.getFbid());
						overviewFamily.setFburl(people.getFburl());
						overviewFamily.setFmt_headline(people.getFmt_headline());
						overviewFamily.setFmt_name(people.getFmt_name());
						// 处理图片路径
						if (people.getImgcode() != null) {
							overviewFamily.setImgcode(people.getImgcode());
							// 设置图片保存相对路径文件夹，根据ID为后缀
							relativePath = "facebook_img/images/facebookimg/fbtargetid_" + targetint.getId().toString()
									+ "/family/"; // 设置图片保存相对路径
							String fullPath = relativePath + MyUtil.getTimeFormat() + ".jpg";
							try {
								// 为取出配置文件的路径
								byte[] decodeByteArray = Base64.decodeBase64(people.getImgcode());
								FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);
								overviewFamily.setImgPath(fullPath);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						overviewFamily.setImgurl(people.getImgurl());
						overviewFamily.setReferenceKey(people.getReferenceKey());
						overviewFamily.setReferenceValue(people.getReferenceValue());
						overviewFamily.setFBTarget_id(targetint.getId());
						overviewFamily_ServiceI.insert_OverviewFamily(overviewFamily);
					}
					System.out.println("添加Family成功");
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				try {
					Map<String, HashMap<String, String>> listBackgroung = overviewentity.getBackground();
					if (!listBackgroung.keySet().isEmpty()) {
						for (Entry<String, HashMap<String, String>> m : listBackgroung.entrySet()) {
							OverviewBackgroung overviewBackgroung = new OverviewBackgroung();
							overviewBackgroung.setInfoKey(m.getKey());
							overviewBackgroung.setFBTarget_id(targetint.getId());
							overviewBackgroung_ServiceI.insert_OverviewBackgroun(overviewBackgroung);

							Map<String, String> entityDescriptions = m.getValue();
							int position = -1;
							if (!entityDescriptions.isEmpty()) {
								position++;
								for (Entry<String, String> t : entityDescriptions.entrySet()) {
									OverviewBackgroungDescriptions overviewBackgroungDescriptions = new OverviewBackgroungDescriptions();
									overviewBackgroungDescriptions
											.setFBoverview_backgroung_id(overviewBackgroung.getId());
									overviewBackgroungDescriptions.setInfoKey(t.getKey());
									overviewBackgroungDescriptions.setInfoValue(t.getValue());
									overviewBackgroungDescriptions.setPosition(position);
									overviewBackgroung_ServiceI
											.insert_OverviewBackgroungDescriptions(overviewBackgroungDescriptions);
								}
							}
						}
						System.out.println("Backgroung添加成功");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return targetint;
	}

	/**
	 * 数据库中有这个人则更新这个人
	 * 
	 * @param target
	 * @param resultOverview
	 * @param urlpattern
	 */
	public void update_Overview(FbTarget target, String resultOverview, String urlpattern, Long task_id) {
		FbTarget fbtarget = new FbTarget();
		System.out.println("数据库已有该人数据，更新该人的Overview");
		// 将返回的字符串解析为实体类
		OverviewEntity overviewentity = Utils.getGson().fromJson(resultOverview, OverviewEntity.class);
		// 设置图片保存相对路径
		try {
			relativePath = "facebook_img/images/facebookimg/fbtargetid_" + target.getId().toString(); // 设置图片保存相对路径
			// fbimg/fbtargetid_I+fullPath
			String fullPath = relativePath + "/" + MyUtil.getTimeFormat() + "_main.jpg";
			try {
				// 为取出配置文件的路径
				byte[] decodeByteArray = Base64.decodeBase64(overviewentity.getImgcode());
				FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fbtarget.setId(target.getId());
			fbtarget.setImgpath(fullPath);
			fbtarget.setFullName(overviewentity.getFullname());
			fbtarget.setOverview(resultOverview.getBytes("utf-8"));
			fbtarget.setUrlpattern(urlpattern);
			fbtarget.setTask_id(task_id);
			fbTarget_ServiceI.update_FbTarget_urlpattern(fbtarget);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 将返回的数据入库
		System.out.println("执行Overview采集更新FBtarget！");
		// 更新Overview work表数据
		if (overviewentity != null && !"".equals(overviewentity)) {

			try {
				// 先执行将work中该人的数据删除
				fbOverview_ServiceI.delete_FbOverviewWork(target.getId());
				// i = 1 则该人的数据删除
				List<WorkEntity> lsitwork = overviewentity.getWork();
				for (WorkEntity workEntity : lsitwork) {
					FbOverviewWork work = new FbOverviewWork();
					work.setCompany(workEntity.getCompany());
					work.setFBTarget_id(target.getId());
					// 添加wrok表中
					fbOverview_ServiceI.insert_FbOverview(work);
					List<String> work_descriptions = workEntity.getDescriptions();
					// 给description position 计数
					int position = -1;
					for (String string : work_descriptions) {
						position++;
						OverviewWorkdescriptions overviewdescriptions = new OverviewWorkdescriptions();
						overviewdescriptions.setFBoverview_work_id(work.getId());
						overviewdescriptions.setDescriptions(string);
						overviewdescriptions.setPosition(position);
						overviewWorkdescriptions_ServiceI.insert_OverviewWorkdescriptions(overviewdescriptions);
					}
					System.out.println("添加work成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {

				// 现将Education 该人的数据删除
				overviewEducation_ServiceI.delete_OverviewEducation(target.getId());
				// 解析Education 教育，学校
				List<EducationEntity> listEducation = overviewentity.getEducation();
				for (EducationEntity educationEntity : listEducation) {
					OverviewEducation education = new OverviewEducation();
					education.setSchool(educationEntity.getSchool());
					education.setFBTarget_id(target.getId());
					overviewEducation_ServiceI.insert_OverviewEducation(education);
					List<String> education_descriptions = educationEntity.getDescriptions();
					int position = -1;
					for (String string : education_descriptions) {
						position++;
						OverviewEducationDescriptions overviewEducationDescriptions = new OverviewEducationDescriptions();
						overviewEducationDescriptions.setFBoverview_education_id(education.getId());
						overviewEducationDescriptions.setDescriptions(string);
						overviewEducationDescriptions.setPosition(position);
						overviewEducation_ServiceI.insert_OverviewEducationDescriptions(overviewEducationDescriptions);
					}
					System.out.println("添加Education成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				// 将skills 技能 关于本人的数据删除
				overviewSkills_ServiceI.delete_overviewSkills(target.getId());
				// 解析Skills
				Map<String, String> skills = overviewentity.getSkills();
				if (!skills.keySet().isEmpty()) {
					for (String obj : skills.keySet()) {
						String key = obj;
						String value = skills.get(key);
						OverviewSkills overviewSkills = new OverviewSkills();
						overviewSkills.setInfoKey(key);
						overviewSkills.setInfoValue(value);
						overviewSkills.setFBTarget_id(target.getId());
						overviewSkills_ServiceI.insert_overviewSkills(overviewSkills);
					}
					System.out.println("添加skills成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				// 将livings 关于本人的数据删除
				overviewLivings_ServiceI.delete_OverviewLivings(target.getId());
				// 解析Livings 生活地方
				Map<String, String> livings = overviewentity.getLivings();
				if (!livings.keySet().isEmpty()) {
					for (String obj : livings.keySet()) {
						OverviewLivings overviewLivings = new OverviewLivings();
						String key = obj;
						String value = livings.get(key);
						overviewLivings.setInfoKey(key);
						overviewLivings.setInfoValue(value);
						overviewLivings.setFBTarget_id(target.getId());
						overviewLivings_ServiceI.insert_OverviewLivings(overviewLivings);
					}
					System.out.println("添加living成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				// 将contacts 数据删除
				overviewContacts_ServiceI.delete_OverviewContacts(target.getId());
				// 更新contacts 联系方式
				Map<String, String> contancts = overviewentity.getContacts();
				if (!contancts.keySet().isEmpty()) {
					for (String obj : contancts.keySet()) {
						OverviewContacts overviewContacts = new OverviewContacts();
						String key = obj;
						String value = contancts.get(key);
						overviewContacts.setInfoKey(key);
						overviewContacts.setInfoValue(value);
						overviewContacts.setFBTarget_id(target.getId());
						overviewContacts_ServiceI.insert_OverviewContacts(overviewContacts);
					}
					System.out.println("添加contacts成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				// 将basicInfos 数据删除 因为这些表的字段都是一样的，所以复用前面的实体类
				overviewContacts_ServiceI.delete_OverviewBasicinfos(target.getId());
				// 更新basicInfos 语言
				Map<String, String> basicInfos = overviewentity.getBasicInfos();
				if (!basicInfos.keySet().isEmpty()) {
					for (String obj : basicInfos.keySet()) {
						OverviewContacts overviewBasicInfos = new OverviewContacts();
						String key = obj;
						String value = basicInfos.get(key);
						overviewBasicInfos.setInfoKey(key);
						overviewBasicInfos.setInfoValue(value);
						overviewBasicInfos.setFBTarget_id(target.getId());
						overviewContacts_ServiceI.insert_OverviewBasicinfos(overviewBasicInfos);
					}
					System.out.println("添加basicInfos成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				// 将nicknames 数据删除 因为这些表的字段都是一样的，所以复用前面的实体类
				overviewContacts_ServiceI.delete_OverviewNicknames(target.getId());
				// 更新nicknames 名称
				Map<String, String> nicknames = overviewentity.getNicknames();
				if (!nicknames.keySet().isEmpty()) {
					for (String obj : nicknames.keySet()) {
						OverviewContacts overviewNicknames = new OverviewContacts();
						String key = obj;
						String value = nicknames.get(key);
						overviewNicknames.setInfoKey(key);
						overviewNicknames.setInfoValue(value);
						overviewNicknames.setFBTarget_id(target.getId());
						overviewContacts_ServiceI.insert_OverviewNicknames(overviewNicknames);
					}
					System.out.println("添加nicknames成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				// 将relationship 数据删除 因为这些表的字段都是一样的，所以复用前面的实体类
				overviewContacts_ServiceI.delete_OverviewRelationship(target.getId());
				// 更新relationship
				Map<String, String> relationship = overviewentity.getRelationship();
				if (!relationship.keySet().isEmpty()) {
					for (String obj : relationship.keySet()) {
						OverviewContacts overviewRelationship = new OverviewContacts();
						String key = obj;
						String value = relationship.get(key);
						overviewRelationship.setInfoKey(key);
						overviewRelationship.setInfoValue(value);
						overviewRelationship.setFBTarget_id(target.getId());
						overviewContacts_ServiceI.insert_OverviewRelationship(overviewRelationship);
					}
					System.out.println("添加relationship成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				// 将bio 数据删除 因为这些表的字段都是一样的，所以复用前面的实体类
				overviewContacts_ServiceI.delete_OverviewBio(target.getId());
				// 更新bio
				Map<String, String> bio = overviewentity.getBio();
				if (!bio.keySet().isEmpty()) {
					for (String obj : bio.keySet()) {
						OverviewContacts overviewBio = new OverviewContacts();
						String key = obj;
						String value = bio.get(key);
						overviewBio.setInfoKey(key);
						overviewBio.setInfoValue(value);
						overviewBio.setFBTarget_id(target.getId());
						overviewContacts_ServiceI.insert_OverviewBio(overviewBio);
					}
					System.out.println("添加bio成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				// 将quote 数据删除 因为这些表的字段都是一样的，所以复用前面的实体类
				overviewContacts_ServiceI.delete_OverviewQuote(target.getId());
				// 更新quote
				Map<String, String> quote = overviewentity.getBio();
				if (!quote.keySet().isEmpty()) {
					for (String obj : quote.keySet()) {
						OverviewContacts overviewQuote = new OverviewContacts();
						String key = obj;
						String value = quote.get(key);
						overviewQuote.setInfoKey(key);
						overviewQuote.setInfoValue(value);
						overviewQuote.setFBTarget_id(target.getId());
						overviewContacts_ServiceI.insert_OverviewQuote(overviewQuote);
					}
					System.out.println("添加quote成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				overviewFamily_ServiceI.delete_OverviewFamily(target.getId());
				List<People> listFamily = overviewentity.getFamily();
				for (People people : listFamily) {
					OverviewFamily overviewFamily = new OverviewFamily();
					overviewFamily.setFbid(people.getFbid());
					overviewFamily.setFburl(people.getFburl());
					overviewFamily.setFmt_headline(people.getFmt_headline());
					overviewFamily.setFmt_name(people.getFmt_name());
					// 处理图片路径
					if (people.getImgcode() != null) {
						overviewFamily.setImgcode(people.getImgcode());
						relativePath = "facebook_img/images/facebookimg/fbtargetid_" + target.getId().toString()
								+ "/family/"; // 设置图片保存相对路径
						String fullPath = relativePath + MyUtil.getTimeFormat() + ".jpg";
						try {
							// 为取出配置文件的路径
							byte[] decodeByteArray = Base64.decodeBase64(overviewentity.getImgcode());
							FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);
							overviewFamily.setImgPath(fullPath);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					overviewFamily.setImgurl(people.getImgurl());
					overviewFamily.setReferenceKey(people.getReferenceKey());
					overviewFamily.setReferenceValue(people.getReferenceValue());
					overviewFamily.setFBTarget_id(target.getId());
					overviewFamily_ServiceI.insert_OverviewFamily(overviewFamily);
				}
				System.out.println("添加Family成功");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				overviewBackgroung_ServiceI.delete_OverviewBackgroun(target.getId());
				Map<String, HashMap<String, String>> listBackgroung = overviewentity.getBackground();
				if (!listBackgroung.keySet().isEmpty()) {
					for (Entry<String, HashMap<String, String>> m : listBackgroung.entrySet()) {
						OverviewBackgroung overviewBackgroung = new OverviewBackgroung();
						overviewBackgroung.setInfoKey(m.getKey());
						overviewBackgroung.setFBTarget_id(target.getId());
						overviewBackgroung_ServiceI.insert_OverviewBackgroun(overviewBackgroung);

						Map<String, String> entityDescriptions = m.getValue();
						int position = -1;
						if (!entityDescriptions.isEmpty()) {
							position++;
							for (Entry<String, String> t : entityDescriptions.entrySet()) {
								OverviewBackgroungDescriptions overviewBackgroungDescriptions = new OverviewBackgroungDescriptions();
								overviewBackgroungDescriptions.setFBoverview_backgroung_id(overviewBackgroung.getId());
								overviewBackgroungDescriptions.setInfoKey(t.getKey());
								overviewBackgroungDescriptions.setInfoValue(t.getValue());
								overviewBackgroungDescriptions.setPosition(position);
								overviewBackgroung_ServiceI
										.insert_OverviewBackgroungDescriptions(overviewBackgroungDescriptions);
							}
						}
					}
					System.out.println("Backgroung添加成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
