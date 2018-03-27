package com.shty.collect.fb.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.codec.binary.Base64;

import com.gargoylesoftware.htmlunit.WebClient;
import com.shty.collect.domain.fb.FbTarget;
import com.shty.collect.domain.fb.TimelineComment;
import com.shty.collect.domain.fb.TimelineStory;
import com.shty.collect.domain.fb.TimelineStoryimgCodeList;
import com.shty.collect.domain.fb.TimelineYear;
import com.shty.collect.service.fb.TimelineComment_ServiceI;
import com.shty.collect.service.fb.TimelineStory_ServiceI;
import com.shty.collect.service.fb.TimelineStoryimgCodeList_ServiceI;
import com.shty.collect.service.fb.TimelineYear_ServiceI;
import com.shty.collect.util.FileUpload;
import com.smit.fb.controller.FbDetailController;
import com.smit.fb.entity.search.People;
import com.smit.fb.entity.timeline.CommentEntity;
import com.smit.fb.entity.timeline.StoryEntity;
import com.smit.fb.entity.timeline.TimelineEntity;
import com.smit.fb.entity.timeline.TmPaginateEntity;
import com.smit.fb.util.MyUtil;
import com.smit.fb.util.Utils;
import com.smit.fbAppclient.MyRequestEntity;
import com.smit.fbAppclient.MyResponseEntity;

/**
 * 线程开启采集Timelies
 * 
 * @author Administrator
 *
 */
public class Facebook_TimelineThread implements Runnable {

	private WebClient webClient;
	private String account;
	private MyRequestEntity request;
	private MyResponseEntity response;
	private String password;
	private String fburl;
	private FbTarget target;
	private String checkin = "true";
	private String imgcode = "true";
	private TimelineYear_ServiceI timelineYear_ServiceI;
	private TimelineStory_ServiceI timelineStory_ServiceI;
	private TimelineComment_ServiceI timelineComment_ServiceI;
	private TimelineStoryimgCodeList_ServiceI timelineStoryimgCodeList_ServiceI;
	private String imgPath = ResourceBundle.getBundle("systemconfig").getString("imgPath");// 设置图片存储路径
	private String relativePath = "";
	String getYear = null;
	// 评论
	private String comments;
	// 点赞
	private String likes;
	// 最大时间
	private String maxtime;
	// 最小时间
	private String mintime;
	private List<TimelineYear> listTimeLinyear;
	private static TimelineEntity timelineEntityYear = null;
	private static TimelineEntity timelineEntiy = null;
	public volatile static boolean facebooktimline_status = false; // 这个关键字的目的是使status同步，也就是说在同一时刻只能由一个线程来修改status的值。

	// 构造函数接收需要的参数
	public Facebook_TimelineThread(WebClient webClient, String account, String password, String fburl,
			MyRequestEntity request, MyResponseEntity response, FbTarget target, String comments, String likes,
			String maxtime, String mintime, TimelineYear_ServiceI timelineYear_ServiceI,
			TimelineStory_ServiceI timelineStory_ServiceI,
			TimelineStoryimgCodeList_ServiceI timelineStoryimgCodeList_ServiceI,
			TimelineComment_ServiceI timelineComment_ServiceI) {
		super();
		// TODO Auto-generated constructor stub
		this.webClient = webClient;
		this.account = account;
		this.request = request;
		this.response = response;
		this.password = password;
		this.fburl = fburl;
		this.target = target;
		this.comments = comments;
		this.likes = likes;
		this.maxtime = maxtime;
		this.mintime = mintime;
		this.timelineYear_ServiceI = timelineYear_ServiceI;
		this.timelineStory_ServiceI = timelineStory_ServiceI;
		this.timelineStoryimgCodeList_ServiceI = timelineStoryimgCodeList_ServiceI;
		this.timelineComment_ServiceI = timelineComment_ServiceI;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 采集需要用到转为json的
		String selected_tm_key = null;
		String selected_tm_nextpageurl = null;
		TmPaginateEntity paginate = new TmPaginateEntity();
		FbDetailController detailController = new FbDetailController(webClient);
		listTimeLinyear = timelineYear_ServiceI.getAllTimeline(target.getId());
		// 解析年份
		if (listTimeLinyear.size() == 0) {
			System.out.println("数据库没有，入库");
			do {
				if (selected_tm_nextpageurl != null && !"".equals(selected_tm_nextpageurl)) {
					paginate.setTm(null);
					paginate.setSelected_tm_key(selected_tm_key);
					paginate.setSelected_tm_nextpageurl(selected_tm_nextpageurl);
					String paginajson = Utils.getGson().toJson(paginate);
					getYear = detailController.getyear(account, checkin, paginajson, null, imgcode, comments, likes, "",
							"", request, response);
				}
				// 首先采集Timelin年限
				String getYear = detailController.getyear(account, checkin, "", fburl, imgcode, comments, likes, "", "",
						request, response);
				System.out.println("采集 timelin 年限成功");

				// 调用入库的方法
				getYear(getYear, target, selected_tm_key, selected_tm_nextpageurl, timelineYear_ServiceI,
						timelineStory_ServiceI, timelineStoryimgCodeList_ServiceI, timelineComment_ServiceI,
						relativePath, imgPath);
			} while (selected_tm_nextpageurl != null && !"".equals(selected_tm_nextpageurl));

		} else {
			// 年份判断，数据库是否有该年份
			do {
				System.out.println("数据库有，解析不在数据库的年份入库");
				if (selected_tm_nextpageurl != null && !"".equals(selected_tm_nextpageurl)) {
					paginate.setTm(null);
					paginate.setSelected_tm_key(selected_tm_key);
					paginate.setSelected_tm_nextpageurl(selected_tm_nextpageurl);
					String paginajson = Utils.getGson().toJson(paginate);
					getYear = detailController.getyear(account, checkin, paginajson, null, imgcode, comments, likes, "",
							"", request, response);
				}
				getYear = detailController.getyear(account, checkin, null, fburl, imgcode, comments, likes, "", "",
						request, response);
				// 调用入库的方法
				getYear(getYear, target, selected_tm_key, selected_tm_nextpageurl, timelineYear_ServiceI,
						timelineStory_ServiceI, timelineStoryimgCodeList_ServiceI, timelineComment_ServiceI,
						relativePath, imgPath);
			} while (selected_tm_nextpageurl != null && !"".equals(selected_tm_nextpageurl));
		}

		// 正式开始采集Timeline分别从年限开始采
		listTimeLinyear = timelineYear_ServiceI.getAllTimeline(target.getId());
		for (TimelineYear timelineYear : listTimeLinyear) {
			System.out.println(timelineYear.getTimeyear());
			// 判断该年限是否采集过
			if (timelineYear.getFinished() == 0) {
				if (timelineYear.getNextpageurl() == null || timelineYear.getNextpageurl().equals("")) {
					System.out.println(timelineYear.getTimeyear() + "---该年限可能采集过！，但是未采集完");
					selected_tm_key = timelineYear.getTimeyear();
					selected_tm_nextpageurl = timelineYear.getYearurl();
					do {
						// Utils.sleep(60000);
						if (selected_tm_key != null && !"".equals(selected_tm_key) && selected_tm_nextpageurl != null
								&& !"".equals(selected_tm_nextpageurl)) {
							paginate.setTm(null);
							paginate.setSelected_tm_key(selected_tm_key);
							paginate.setSelected_tm_nextpageurl(selected_tm_nextpageurl);
							String paginajson = Utils.getGson().toJson(paginate);
							String timelinejson = detailController.getTimeline(account, checkin, paginajson, null,
									imgcode, comments, likes, "", "", request, response);
							// 调用入库
							selected_tm_nextpageurl = Timeline(timelinejson, target, selected_tm_key,
									selected_tm_nextpageurl, timelineYear_ServiceI, timelineStory_ServiceI,
									timelineStoryimgCodeList_ServiceI, timelineComment_ServiceI, relativePath, imgPath,
									timelineYear);
						} else {
							System.out.println("出现异常----selected_tm_key   selected_tm_nextpageurl等于null");
						}
					} while (selected_tm_nextpageurl != null && !"".equals(selected_tm_nextpageurl));
					// 该年份采集完成，将数据库Finished改为1
					if (selected_tm_nextpageurl != null && !"".equals(selected_tm_nextpageurl)) {
						System.out.println("该年份采集完成，修改Finished为 --1");
						TimelineYear timelineYearYes = new TimelineYear();
						timelineYearYes.setId(timelineYear.getId());
						timelineYearYes.setNextpageurl("");
						timelineYearYes.setFinished(1);
						timelineYear_ServiceI.update_TimelineYearYes(timelineYearYes);
					}
				} else {
					System.out.println("该年份已经采集过，从某一页开始采集");
					// 年份采集过，但是看是否有下一页的url，有的话表示从这一页开始采集
					selected_tm_key = timelineYear.getTimeyear();
					// 这里改成netxpageurl
					selected_tm_nextpageurl = timelineYear.getNextpageurl();
					do {
						if (selected_tm_nextpageurl != null && !"".equals(selected_tm_nextpageurl)) {
							paginate.setTm(null);
							paginate.setSelected_tm_key(selected_tm_key);
							paginate.setSelected_tm_nextpageurl(selected_tm_nextpageurl);
							String paginajson = Utils.getGson().toJson(paginate);
							String timelinejson = detailController.getTimeline(account, checkin, paginajson, null,
									imgcode, comments, likes, "", "", request, response);
							// 调用入库
							selected_tm_nextpageurl = Timeline(timelinejson, target, selected_tm_key,
									selected_tm_nextpageurl, timelineYear_ServiceI, timelineStory_ServiceI,
									timelineStoryimgCodeList_ServiceI, timelineComment_ServiceI, relativePath, imgPath,
									timelineYear);
						} else {
							System.out.println("出现异常----selected_tm_key   selected_tm_nextpageurl等于null");
						}
					} while (selected_tm_key != null && !"".equals(selected_tm_key) && selected_tm_nextpageurl != null
							&& !"".equals(selected_tm_nextpageurl));
					// 该年份采集完成，将数据库Finished改为1
					if (selected_tm_nextpageurl != null && !"".equals(selected_tm_nextpageurl)) {
						System.out.println("该年份采集完成，修改Finished为 --1");
						TimelineYear timelineYearYes = new TimelineYear();
						timelineYearYes.setId(timelineYear.getId());
						timelineYearYes.setNextpageurl("");
						timelineYearYes.setFinished(1);
						timelineYear_ServiceI.update_TimelineYearYes(timelineYearYes);
					}
				}
			} else {
				System.out.println("该年限采集过，采集下一年");
			}
		}

		System.out.println("--------------帖子采集完成------------");
		System.out.println("--------------采集最近帖子------------");
		// 采集最近的帖子
		// 获取当前年
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String datetime = "year_" + df.format(new Date()).substring(0, 4);
		TimelineYear timeyear = new TimelineYear();
		timeyear.setFBTarget_id(target.getId());
		timeyear.setTimeyear(datetime);
		TimelineYear select_year = timelineYear_ServiceI.select_TimelineYear_timeyear(timeyear);
		if (select_year != null) {
			// 采集今年的最近帖子
			if (select_year.getYearurl() != null || !select_year.getYearurl().equals("")) {
				System.out.println(select_year.getTimeyear() + "---该年限可能采集过！，但是未采集完");
				selected_tm_key = select_year.getTimeyear();
				selected_tm_nextpageurl = select_year.getYearurl();
				do {
					// Utils.sleep(60000);
					if (selected_tm_key != null && !"".equals(selected_tm_key) && selected_tm_nextpageurl != null
							&& !"".equals(selected_tm_nextpageurl)) {
						paginate.setTm(null);
						paginate.setSelected_tm_key(selected_tm_key);
						paginate.setSelected_tm_nextpageurl(selected_tm_nextpageurl);
						String paginajson = Utils.getGson().toJson(paginate);
						String timelinejson = detailController.getTimeline(account, checkin, paginajson, null, imgcode,
								comments, likes, "", "", request, response);
						// 调用入库
						selected_tm_nextpageurl = recently_Timeline(timelinejson, target, selected_tm_key,
								selected_tm_nextpageurl, timelineYear_ServiceI, timelineStory_ServiceI,
								timelineStoryimgCodeList_ServiceI, timelineComment_ServiceI, relativePath, imgPath,
								select_year);
					} else {
						System.out.println("出现异常----selected_tm_key   selected_tm_nextpageurl等于null");
					}
				} while (selected_tm_nextpageurl != null && !"".equals(selected_tm_nextpageurl));
				// 该年份采集完成，将数据库Finished改为1
				if (selected_tm_nextpageurl != null && !"".equals(selected_tm_nextpageurl)) {
					System.out.println("该年份采集完成，修改Finished为 ------1");
					TimelineYear timelineYearYes = new TimelineYear();
					timelineYearYes.setId(select_year.getId());
					timelineYearYes.setNextpageurl("");
					timelineYearYes.setFinished(1);
					timelineYear_ServiceI.update_TimelineYearYes(timelineYearYes);
				}
			}
		} else {
			System.out.println("不需要采集");
		}
	}

	// 年份不全入库方法
	public static void getYear(String getYear, FbTarget target, String selected_tm_key, String selected_tm_nextpageurl,
			TimelineYear_ServiceI timelineYear_ServiceI, TimelineStory_ServiceI timelineStory_ServiceI,
			TimelineStoryimgCodeList_ServiceI timelineStoryimgCodeList_ServiceI,
			TimelineComment_ServiceI timelineComment_ServiceI, String relativePath, String imgPath) {
		// 首先采集Timelin年限
		System.out.println("采集 timelin 年限成功");
		// 将获取年限解析为实体类
		timelineEntityYear = Utils.getGson().fromJson(getYear, TimelineEntity.class);
			// 将年限tm取到
			TmPaginateEntity tmPaginateEntity = timelineEntityYear.getPaginate();
			Map<String, String> timeYear = tmPaginateEntity.getTm();
			// timeyear不等于空 保存该人的帖子年份
			if (!timeYear.keySet().isEmpty()) {
				for (String obj : timeYear.keySet()) {
					String key = obj;
					String value = timeYear.get(key);
					TimelineYear timeyear_select = new TimelineYear();
					timeyear_select.setFBTarget_id(target.getId());
					timeyear_select.setTimeyear(key);
					// 判断如果数据库没有则入库
					TimelineYear timelineYear_timeuear = timelineYear_ServiceI
							.select_TimelineYear_timeyear(timeyear_select);
					if (timelineYear_timeuear == null) {
						TimelineYear timelineYear = new TimelineYear();
						timelineYear.setTimeyear(key);
						timelineYear.setYearurl(value);
						timelineYear.setFinished(0);
						timelineYear.setFBTarget_id(target.getId());
						timelineYear_ServiceI.insert_TimelineYear(timelineYear);
					}
				}
				selected_tm_key = tmPaginateEntity.getSelected_tm_key();
				selected_tm_nextpageurl = tmPaginateEntity.getSelected_tm_nextpageurl();
				// 判断采集年份问题，因为有时是不全的
				List<StoryEntity> storyEntity = timelineEntityYear.getStories();
				if (storyEntity.size() != 0) {
					for (StoryEntity storyEntity2 : storyEntity) {
						// 如果数据库没有这个年份代表该年份是缺的
						// if (timelineYear_timeuear == null) {
						System.out.println("年份不全");
						// 判断数据库中是否有这条帖子
						TimelineStory i = timelineStory_ServiceI
								.select_insert_TimelineStory_id(storyEntity2.getStoryId());
						if (i == null) {
							System.out.println("数据库中没有该帖子，帖子入库-----" + storyEntity2.getStoryId());
							TimelineStory story = new TimelineStory();
							if (storyEntity2.getComments() != null) {
								story.setCommentcount((long) storyEntity2.getComments().size());
							} else {
								story.setCommentcount((long) 0);
							}
							story.setContent(storyEntity2.getContent());
							story.setImageCodeListSize(0);
							if (storyEntity2.getLikes() != null) {
								story.setLikecount((long) storyEntity2.getLikes().size());
							} else {
								story.setLikecount((long) 0);
							}
							story.setPubdate(storyEntity2.getPubdate());
							story.setStoryId(storyEntity2.getStoryId());
							story.setTitle(storyEntity2.getTitle());
							story.setFBTarget_id(target.getId());
							// 将这条帖子入库
							timelineStory_ServiceI.insert_TimelineStory(story);
							// ImgCodeList入库
							List<String> listImgCodeList = storyEntity2.getImgCodeList();
							TimelineStoryimgCodeList timelineStoryimgCodeList = new TimelineStoryimgCodeList();
							if (listImgCodeList.size() != 0) {
								int positionImgCode = -1;
								for (String string : listImgCodeList) {
									positionImgCode++;
									timelineStoryimgCodeList.setFBStory_id(story.getId());
									timelineStoryimgCodeList.setImgCodeList(string);
									timelineStoryimgCodeList.setPosition(positionImgCode);
									timelineStoryimgCodeList_ServiceI
											.insert_TimelineStoryimgCodeList(timelineStoryimgCodeList);

									// ImgPath 因为在数据库中只是字段不一样，所以采用相同的实体类
									try {
										// 设置图片保存相对路径文件夹，根据ID为后缀
										relativePath = "facebook_img/images/facebookimg/fbtargetid_"
												+ target.getId().toString()+"/story/"; // 设置图片保存相对路径
										// 根据获取时间戳为图片名字
										String fullPath = relativePath + MyUtil.getTimeFormat()
												+ "_main.jpg";
										// 为取出配置文件的路径
										
										// 为取出配置文件的路径
										byte[] decodeByteArray = Base64.decodeBase64(string);
										FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);
										
										TimelineStoryimgCodeList timelineStoryimgPath = new TimelineStoryimgCodeList();
										timelineStoryimgPath.setFBStory_id(story.getId());
										timelineStoryimgPath.setImgCodeList(fullPath);
										timelineStoryimgPath.setPosition(positionImgCode);
										timelineStoryimgCodeList_ServiceI
												.insert_timelineStoryimgPath(timelineStoryimgPath);
									} catch (Exception e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
								}
							}
							// ImgUrlList 因为在数据库中只是字段不一样，所以采用相同的实体类
							int positionimgurl = -1;
							List<String> listImgUrlList = storyEntity2.getImgurlList();
							if (listImgUrlList.size() != 0) {
								for (String string : listImgUrlList) {
									positionimgurl++;
									timelineStoryimgCodeList.setFBStory_id(story.getId());
									timelineStoryimgCodeList.setImgCodeList(string);
									timelineStoryimgCodeList.setPosition(positionimgurl);
									timelineStoryimgCodeList_ServiceI
											.insert_timelineStoryimgUrlList(timelineStoryimgCodeList);
								}
							}
							// 解析comment，评论
							List<CommentEntity> comments = storyEntity2.getComments();
							if (comments != null) {
								for (CommentEntity commentEntity : comments) {
									TimelineComment timelineComment = new TimelineComment();
									timelineComment.setContent(commentEntity.getContent());
									timelineComment.setFbid(commentEntity.getPeople().getFbid());
									timelineComment.setFburl(commentEntity.getPeople().getFburl());
									timelineComment.setFmt_headline(commentEntity.getPeople().getFmt_headline());
									timelineComment.setFmt_name(commentEntity.getPeople().getFmt_name());
									timelineComment.setImgcode(commentEntity.getPeople().getImgcode());
									timelineComment.setImgurl(commentEntity.getPeople().getImgurl());
									timelineComment.setReferenceKey(commentEntity.getPeople().getReferenceKey());
									timelineComment.setReferenceValue(commentEntity.getPeople().getReferenceValue());
									timelineComment.setPubdate(commentEntity.getPubdate());
									timelineComment.setFBStory_id(story.getId());
									// 解析comment的imgPath
									try {
										// 设置图片保存相对路径文件夹，根据ID为后缀
										relativePath = "facebook_img/images/facebookimg/fbtargetid_"
												+ target.getId().toString()+ "/comments/"; // 设置图片保存相对路径
										// 根据获取时间戳为图片名字
										String fullPath = relativePath + MyUtil.getTimeFormat()
												+ "_main.jpg";
										// 为取出配置文件的路径
										byte[] decodeByteArray = Base64.decodeBase64(commentEntity.getPeople().getImgcode());
										FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);
										timelineComment.setImgPath(fullPath);
									} catch (Exception e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
									timelineComment_ServiceI.insert_TimelineComment(timelineComment);
								}
							}

							// 解析likes，点赞,like表中的字段与Family相同，所以用相同实体类
							List<People> likes = storyEntity2.getLikes();
							if (likes != null) {
								if (likes.size() != 0) {
									for (People people : likes) {
										TimelineComment like = new TimelineComment();
										like.setFbid(people.getFbid());
										like.setFburl(people.getFburl());
										like.setFmt_headline(people.getFmt_headline());
										like.setFmt_name(people.getFmt_name());
										like.setImgcode(people.getImgcode());
										like.setImgurl(people.getImgurl());
										like.setReferenceKey(people.getReferenceKey());
										like.setReferenceValue(people.getReferenceValue());
										like.setFBStory_id(story.getId());
										if (people.getImgcode() != null && !"".equals(people.getImgcode())) {
											// 解析likes的imgPath
											try {
												// 设置图片保存相对路径文件夹，根据ID为后缀
												relativePath = "facebook_img/images/facebookimg/fbtargetid_"
														+ target.getId().toString()+ "/likes/"; // 设置图片保存相对路径
												// 根据获取时间戳为图片名字
												String fullPath = relativePath + MyUtil.getTimeFormat()
														+ "_main.jpg";
												// 为取出配置文件的路径
												byte[] decodeByteArray = Base64.decodeBase64(people.getImgcode());
												FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);
												like.setImgPath(fullPath);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
										timelineComment_ServiceI.inset_TimelineLike(like);
									}
								}
							}
						} else {
							System.out.println("数据库中有该帖子，不用添加");
						}
					}
				} else {
					System.out.println("年份是完全的");
				}

			} else {
				System.out.println("为空，异常");
			}
	}

	// 帖子 Timeline 入库
	public static String Timeline(String timelinejson, FbTarget target, String selected_tm_key,
			String selected_tm_nextpageurl, TimelineYear_ServiceI timelineYear_ServiceI,
			TimelineStory_ServiceI timelineStory_ServiceI,
			TimelineStoryimgCodeList_ServiceI timelineStoryimgCodeList_ServiceI,
			TimelineComment_ServiceI timelineComment_ServiceI, String relativePath, String imgPath,
			TimelineYear timelineYear) {
		timelineEntiy = Utils.getGson().fromJson(timelinejson, TimelineEntity.class);
			List<StoryEntity> storyEntity = timelineEntiy.getStories();
			for (StoryEntity storyEntity2 : storyEntity) {
				// 判断数据库中是否有这条帖子
				TimelineStory i = timelineStory_ServiceI.select_insert_TimelineStory_id(storyEntity2.getStoryId());
				if (i == null) {
					System.out.println("数据库中没有该帖子，帖子入库-----" + storyEntity2.getStoryId());
					TimelineStory story = new TimelineStory();
					if (storyEntity2.getComments() != null) {
						story.setCommentcount((long) storyEntity2.getComments().size());
					} else {
						story.setCommentcount((long) 0);
					}
					story.setContent(storyEntity2.getContent());
					story.setImageCodeListSize(0);
					if (storyEntity2.getLikes() != null) {
						story.setLikecount((long) storyEntity2.getLikes().size());
					} else {
						story.setLikecount((long) 0);
					}
					story.setPubdate(storyEntity2.getPubdate());
					story.setStoryId(storyEntity2.getStoryId());
					story.setTitle(storyEntity2.getTitle());
					story.setFBTarget_id(target.getId());
					// 将这条帖子入库
					timelineStory_ServiceI.insert_TimelineStory(story);
					// ImgCodeList入库
					List<String> listImgCodeList = storyEntity2.getImgCodeList();
					TimelineStoryimgCodeList timelineStoryimgCodeList = new TimelineStoryimgCodeList();
					if (listImgCodeList.size() != 0) {
						int positionImgCode = -1;
						for (String string : listImgCodeList) {
							positionImgCode++;
							timelineStoryimgCodeList.setFBStory_id(story.getId());
							timelineStoryimgCodeList.setImgCodeList(string);
							timelineStoryimgCodeList.setPosition(positionImgCode);
							timelineStoryimgCodeList_ServiceI.insert_TimelineStoryimgCodeList(timelineStoryimgCodeList);

							// ImgPath 因为在数据库中只是字段不一样，所以采用相同的实体类
							try {
								// 设置图片保存相对路径文件夹，根据ID为后缀
								relativePath = "facebook_img/images/facebookimg/fbtargetid_"
										+ target.getId().toString()+ "/story/"; // 设置图片保存相对路径
								// 根据获取时间戳为图片名字
								String fullPath = relativePath + MyUtil.getTimeFormat() + "_main.jpg";
								// 为取出配置文件的路径
								byte[] decodeByteArray = Base64.decodeBase64(string);
								FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);
								TimelineStoryimgCodeList timelineStoryimgPath = new TimelineStoryimgCodeList();
								timelineStoryimgPath.setFBStory_id(story.getId());
								timelineStoryimgPath.setImgCodeList(fullPath);
								timelineStoryimgPath.setPosition(positionImgCode);
								timelineStoryimgCodeList_ServiceI.insert_timelineStoryimgPath(timelineStoryimgPath);
							} catch (Exception e) {
								// TODO Auto-generated catch
								// block
								e.printStackTrace();
							}
						}
					}
					// ImgUrlList 因为在数据库中只是字段不一样，所以采用相同的实体类
					int positionimgurl = -1;
					List<String> listImgUrlList = storyEntity2.getImgurlList();
					if (listImgUrlList.size() != 0) {
						for (String string : listImgUrlList) {
							positionimgurl++;
							timelineStoryimgCodeList.setFBStory_id(story.getId());
							timelineStoryimgCodeList.setImgCodeList(string);
							timelineStoryimgCodeList.setPosition(positionimgurl);
							timelineStoryimgCodeList_ServiceI.insert_timelineStoryimgUrlList(timelineStoryimgCodeList);
						}
					}
					// 解析comment，评论
					List<CommentEntity> comments = storyEntity2.getComments();
					if (comments != null) {
						for (CommentEntity commentEntity : comments) {
							TimelineComment timelineComment = new TimelineComment();
							timelineComment.setContent(commentEntity.getContent());
							timelineComment.setFbid(commentEntity.getPeople().getFbid());
							timelineComment.setFburl(commentEntity.getPeople().getFburl());
							timelineComment.setFmt_headline(commentEntity.getPeople().getFmt_headline());
							timelineComment.setFmt_name(commentEntity.getPeople().getFmt_name());
							timelineComment.setImgcode(commentEntity.getPeople().getImgcode());
							timelineComment.setImgurl(commentEntity.getPeople().getImgurl());
							timelineComment.setReferenceKey(commentEntity.getPeople().getReferenceKey());
							timelineComment.setReferenceValue(commentEntity.getPeople().getReferenceValue());
							timelineComment.setPubdate(commentEntity.getPubdate());
							timelineComment.setFBStory_id(story.getId());
							// 解析comment的imgPath
							try {
								// 设置图片保存相对路径文件夹，根据ID为后缀
								relativePath = "facebook_img/images/facebookimg/fbtargetid_"
										+ target.getId().toString()+ "/comments/"; // 设置图片保存相对路径
								// 根据获取时间戳为图片名字
								String fullPath = relativePath + MyUtil.getTimeFormat() + "_main.jpg";
								// 为取出配置文件的路径
								byte[] decodeByteArray = Base64.decodeBase64(commentEntity.getPeople().getImgcode());
								FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);
								timelineComment.setImgPath(fullPath);
							} catch (Exception e) {
								// TODO Auto-generated catch
								// block
								e.printStackTrace();
							}
							timelineComment_ServiceI.insert_TimelineComment(timelineComment);
						}
					}

					// 解析likes，点赞,like表中的字段与Family相同，所以用相同实体类
					List<People> likes = storyEntity2.getLikes();
					if (likes != null) {
						if (likes.size() != 0) {
							for (People people : likes) {
								TimelineComment like = new TimelineComment();
								like.setFbid(people.getFbid());
								like.setFburl(people.getFburl());
								like.setFmt_headline(people.getFmt_headline());
								like.setFmt_name(people.getFmt_name());
								like.setImgcode(people.getImgcode());
								like.setImgurl(people.getImgurl());
								like.setReferenceKey(people.getReferenceKey());
								like.setReferenceValue(people.getReferenceValue());
								like.setFBStory_id(story.getId());
								if (people.getImgcode() != null && !"".equals(people.getImgcode())) {
									// 解析likes的imgPath
									try {
										// 设置图片保存相对路径文件夹，根据ID为后缀
										relativePath = "facebook_img/images/facebookimg/fbtargetid_"
												+ target.getId().toString()+ "/likes/"; // 设置图片保存相对路径
										// 根据获取时间戳为图片名字
										String fullPath = relativePath + MyUtil.getTimeFormat()
												+ "_main.jpg";
										// 为取出配置文件的路径
										byte[] decodeByteArray = Base64.decodeBase64(people.getImgcode());
										FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);
										like.setImgPath(fullPath);
									} catch (Exception e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
								}
								timelineComment_ServiceI.inset_TimelineLike(like);
							}
						}
					}
				} else {
					System.out.println("---------数据库中已有该帖子，开始下一条帖子");
				}
			}
			// 开始采集下一页
			System.out.println("判断否有下一页，有则采，没有则不采");
			TmPaginateEntity tmPaginate = timelineEntiy.getPaginate();
			selected_tm_key = tmPaginate.getSelected_tm_key();
			selected_tm_nextpageurl = tmPaginate.getSelected_tm_nextpageurl();
			// 将下一页的url放入数据库
			TimelineYear timelineYearNexturl = new TimelineYear();
			timelineYearNexturl.setId(timelineYear.getId());
			timelineYearNexturl.setNextpageurl(selected_tm_nextpageurl);
			timelineYear_ServiceI.update_TimelineYear(timelineYearNexturl);
		return selected_tm_nextpageurl;
	}

	// 帖子 Timeline 入库
	public static String recently_Timeline(String timelinejson, FbTarget target, String selected_tm_key,
			String selected_tm_nextpageurl, TimelineYear_ServiceI timelineYear_ServiceI,
			TimelineStory_ServiceI timelineStory_ServiceI,
			TimelineStoryimgCodeList_ServiceI timelineStoryimgCodeList_ServiceI,
			TimelineComment_ServiceI timelineComment_ServiceI, String relativePath, String imgPath,
			TimelineYear timelineYear) {

		timelineEntiy = Utils.getGson().fromJson(timelinejson, TimelineEntity.class);
			List<StoryEntity> storyEntity = timelineEntiy.getStories();
			for (StoryEntity storyEntity2 : storyEntity) {
				// 判断数据库中是否有这条帖子
				TimelineStory i = timelineStory_ServiceI.select_insert_TimelineStory_id(storyEntity2.getStoryId());
				if (i == null) {
					System.out.println("数据库中没有该帖子，帖子入库-----" + storyEntity2.getStoryId());
					TimelineStory story = new TimelineStory();
					story.setCommentcount((long) storyEntity2.getComments().size());
					story.setContent(storyEntity2.getContent());
					story.setImageCodeListSize(0);
					story.setLikecount((long) storyEntity2.getLikes().size());
					story.setPubdate(storyEntity2.getPubdate());
					story.setStoryId(storyEntity2.getStoryId());
					story.setTitle(storyEntity2.getTitle());
					story.setFBTarget_id(target.getId());
					// 将这条帖子入库
					timelineStory_ServiceI.insert_TimelineStory(story);
					System.out.println(story.getId());
					// ImgCodeList入库
					List<String> listImgCodeList = storyEntity2.getImgCodeList();
					TimelineStoryimgCodeList timelineStoryimgCodeList = new TimelineStoryimgCodeList();
					if (listImgCodeList.size() != 0) {
						int positionImgCode = -1;
						for (String string : listImgCodeList) {
							positionImgCode++;
							timelineStoryimgCodeList.setFBStory_id(story.getId());
							timelineStoryimgCodeList.setImgCodeList(string);
							timelineStoryimgCodeList.setPosition(positionImgCode);
							timelineStoryimgCodeList_ServiceI.insert_TimelineStoryimgCodeList(timelineStoryimgCodeList);

							// ImgPath 因为在数据库中只是字段不一样，所以采用相同的实体类
							try {
								// 设置图片保存相对路径文件夹，根据ID为后缀
								relativePath = "facebook_img/images/facebookimg/fbtargetid_"
										+ target.getId().toString()+ "/story/"; // 设置图片保存相对路径
								// 根据获取时间戳为图片名字
								String fullPath = relativePath + MyUtil.getTimeFormat() + "_main.jpg";
								// 为取出配置文件的路径
								byte[] decodeByteArray = Base64.decodeBase64(string);
								FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);
								TimelineStoryimgCodeList timelineStoryimgPath = new TimelineStoryimgCodeList();
								timelineStoryimgPath.setFBStory_id(story.getId());
								timelineStoryimgPath.setImgCodeList(fullPath);
								timelineStoryimgPath.setPosition(positionImgCode);
								timelineStoryimgCodeList_ServiceI.insert_timelineStoryimgPath(timelineStoryimgPath);
							} catch (Exception e) {
								// TODO Auto-generated catch
								// block
								e.printStackTrace();
							}
						}
					}
					// ImgUrlList 因为在数据库中只是字段不一样，所以采用相同的实体类
					int positionimgurl = -1;
					List<String> listImgUrlList = storyEntity2.getImgurlList();
					if (listImgUrlList.size() != 0) {
						for (String string : listImgUrlList) {
							positionimgurl++;
							timelineStoryimgCodeList.setFBStory_id(story.getId());
							timelineStoryimgCodeList.setImgCodeList(string);
							timelineStoryimgCodeList.setPosition(positionimgurl);
							timelineStoryimgCodeList_ServiceI.insert_timelineStoryimgUrlList(timelineStoryimgCodeList);
						}
					}
					// 解析comment，评论
					List<CommentEntity> comments = storyEntity2.getComments();
					System.out.println(comments.size());
					if (comments != null) {
						for (CommentEntity commentEntity : comments) {
							TimelineComment timelineComment = new TimelineComment();
							timelineComment.setContent(commentEntity.getContent());
							timelineComment.setFbid(commentEntity.getPeople().getFbid());
							timelineComment.setFburl(commentEntity.getPeople().getFburl());
							timelineComment.setFmt_headline(commentEntity.getPeople().getFmt_headline());
							timelineComment.setFmt_name(commentEntity.getPeople().getFmt_name());
							timelineComment.setImgcode(commentEntity.getPeople().getImgcode());
							timelineComment.setImgurl(commentEntity.getPeople().getImgurl());
							timelineComment.setReferenceKey(commentEntity.getPeople().getReferenceKey());
							timelineComment.setReferenceValue(commentEntity.getPeople().getReferenceValue());
							timelineComment.setPubdate(commentEntity.getPubdate());
							timelineComment.setFBStory_id(story.getId());
							// 解析comment的imgPath
							try {
								// 设置图片保存相对路径文件夹，根据ID为后缀
								relativePath = "facebook_img/images/facebookimg/fbtargetid_"
										+ target.getId().toString()+ "/comments/" ; // 设置图片保存相对路径
								// 根据获取时间戳为图片名字
								String fullPath = relativePath + MyUtil.getTimeFormat() + "_main.jpg";
								// 为取出配置文件的路径
								byte[] decodeByteArray = Base64.decodeBase64(commentEntity.getPeople().getImgcode());
								FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);
								timelineComment.setImgPath(fullPath);
							} catch (Exception e) {
								// TODO Auto-generated catch
								// block
								e.printStackTrace();
							}
							timelineComment_ServiceI.insert_TimelineComment(timelineComment);
						}
					}

					// 解析likes，点赞,like表中的字段与Family相同，所以用相同实体类
					List<People> likes = storyEntity2.getLikes();
					if (likes.size() != 0) {
						for (People people : likes) {
							TimelineComment like = new TimelineComment();
							like.setFbid(people.getFbid());
							like.setFburl(people.getFburl());
							like.setFmt_headline(people.getFmt_headline());
							like.setFmt_name(people.getFmt_name());
							like.setImgcode(people.getImgcode());
							like.setImgurl(people.getImgurl());
							like.setReferenceKey(people.getReferenceKey());
							like.setReferenceValue(people.getReferenceValue());
							like.setFBStory_id(story.getId());
							if (people.getImgcode() != null && !"".equals(people.getImgcode())) {
								// 解析likes的imgPath
								try {
									// 设置图片保存相对路径文件夹，根据ID为后缀
									relativePath = "facebook_img/images/facebookimg/fbtargetid_"
											+ target.getId().toString()+ "/likes/"; // 设置图片保存相对路径
									// 根据获取时间戳为图片名字
									String fullPath = relativePath + MyUtil.getTimeFormat() + "_main.jpg";
									// 为取出配置文件的路径
									byte[] decodeByteArray = Base64.decodeBase64(people.getImgcode());
									FileUpload.smbPut(imgPath, decodeByteArray, relativePath, fullPath);
									like.setImgPath(fullPath);
								} catch (Exception e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
							}
							timelineComment_ServiceI.inset_TimelineLike(like);
						}
					}
				} else {
					System.out.println("---------数据库中已有该帖子,增量采集完成--------------");
					selected_tm_nextpageurl = "";
					// 将下一页的url放入数据库
					TimelineYear timelineYearNexturl = new TimelineYear();
					timelineYearNexturl.setId(timelineYear.getId());
					timelineYearNexturl.setNextpageurl(selected_tm_nextpageurl);
					timelineYear_ServiceI.update_TimelineYear(timelineYearNexturl);
					return selected_tm_nextpageurl;
				}
		}
		// 开始采集下一页
		System.out.println("判断否有下一页，有则采，没有则不采");
		TmPaginateEntity tmPaginate = timelineEntiy.getPaginate();
		selected_tm_key = tmPaginate.getSelected_tm_key();
		selected_tm_nextpageurl = tmPaginate.getSelected_tm_nextpageurl();
		// 将下一页的url放入数据库
		TimelineYear timelineYearNexturl = new TimelineYear();
		timelineYearNexturl.setId(timelineYear.getId());
		timelineYearNexturl.setNextpageurl(selected_tm_nextpageurl);
		timelineYear_ServiceI.update_TimelineYear(timelineYearNexturl);

		return selected_tm_nextpageurl;
	}

}
