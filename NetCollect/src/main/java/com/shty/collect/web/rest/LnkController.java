package com.shty.collect.web.rest;

import java.lang.Thread.State;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.shty.collect.domain.TbLinkedinSysKeyword;
import com.shty.collect.domain.TbLinkedinSysTask;
import com.shty.collect.domain.TbSysThirdTask;
import com.shty.collect.domain.TbSystemHttp;
import com.shty.collect.domain.TbSystemNode;
import com.shty.collect.domain.TbSystemRelation;
import com.shty.collect.domain.TbSystemSocialaccount;
import com.shty.collect.service.Linkedin_taskServiceI;
import com.shty.collect.service.System_AccountServiceI;
import com.shty.collect.service.System_NodeServiceI;
import com.shty.collect.service.System_RelationServiceI;
import com.shty.collect.service.System_linkedin_keywordI;
import com.shty.collect.service.TbSystemHttpServiceI;
import com.shty.collect.service.ThridTaskServiceI;
import com.shty.collect.service.lnk.PeopleService;
import com.shty.collect.service.lnk.impl.AnalyseData;
import com.shty.collect.web.rest.dto.IPAccountDto;
import com.shty.collect.web.rest.dto.LnkCollectDto;
import com.shty.linkedin.controller.LinkedinController;
import com.shty.linkedin.controller.LnkGoogleSearch;
import com.shty.linkedin.dao.domain.GoogleResoult;
import com.shty.linkedin.dao.domain.GoogleTarget;
import com.shty.linkedin.http.entity.LnkDetailResponse;

@Controller
// @RequestMapping(value = "/lnkbegin")
public class LnkController {

	private String ip = ResourceBundle.getBundle("systemconfig").getString("ip");// 设置访问的主节点
	String getTaskUrl = "http://" + ip + "/NetCollect/rest/getLinkedinTask";
	String getAccount = "http://" + ip + "/NetCollect/rest/select_type_facebook?accountType=2";
	String getOneLnkKeyword = "http://" + ip + "/NetCollect/rest/getOneLnkKeyword";
	String getOneThirdKeyword = "http://" + ip + "/NetCollect/rest/third/getThirdTask?tasktype=linkedin";

	@Autowired
	AnalyseData analyseDataThread;

	@Autowired
	Linkedin_taskServiceI linkedin_taskServiceI;

	@Autowired
	System_linkedin_keywordI system_linkedin_keywordI;

	@Autowired
	System_NodeServiceI system_NodeServiceI;

	@Autowired
	System_AccountServiceI system_AccountServiceI;

	@Autowired
	System_RelationServiceI system_RelationServiceI;

	@Autowired
	TbSystemHttpServiceI tbSystemHttpServiceI;

	@Autowired
	ThridTaskServiceI thirdService;

	@Autowired
	PeopleService peopleServiceI;

	@Autowired
	System_AccountServiceI system_accountServiceI;

	public static IPAccountDto localIpAccount;
	private String localhostIp = null;
	private static Thread thread;
	private static LinkedinController linkedinController;
	LnkCollectDto threadStatus = new LnkCollectDto();
	private TbSystemSocialaccount tbSystemSocialaccount = new TbSystemSocialaccount();

	@RequestMapping(value = "/dotest")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public IPAccountDto dotest() {
		return getIpAccount();
	}

	@RequestMapping(value = "/lnkLogintest")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public LnkCollectDto lnkLogintest(@RequestParam(value = "ip", required = true) String ip) {

		// TbSystemHttp tbSystemHttp = getIPandAccount(ip);
		LnkCollectDto lnk = new LnkCollectDto();
		lnk.setHttpIp(localIpAccount.getHttpip());
		lnk.setUsername(localIpAccount.getAccountName());
		localhostIp = ip;

		if (localIpAccount == null) {
			lnk.setStatus(200);
			lnk.setMessage("未执行任务！");
			return lnk;
		}

		try {
			lnk.setHttpIp(localIpAccount.getHttpip());
			if (linkedinController.isLogin(localIpAccount.getAccountName()) != 0) {
				if (linkedinController.login(localIpAccount.getAccountName(), localIpAccount.getPassword()) == 0) {
					lnk.setStatus(200);
					lnk.setMessage("登陆成功");
				} else {
					lnk.setStatus(500);
					lnk.setMessage("登陆失败");
				}
			} else {
				lnk.setStatus(200);
				lnk.setMessage("已经登录");
			}
		} catch (Exception e) {
			lnk.setStatus(500);
			lnk.setMessage("登陆失败");
		}
		return lnk;
	}

	@RequestMapping(value = "/getlnkstatus")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public LnkCollectDto getlnkstatus(@RequestParam(value = "ip", required = true) String ip) {

		try {
			if (thread != null) {
				State state = thread.getState();
				threadStatus.setThreadStatus(state.toString());
				threadStatus.setStatus(200);

			} else {
				threadStatus.setStatus(500);
				threadStatus.setMessage("线程未启动");
			}
		} catch (Exception e) {
			threadStatus.setStatus(500);
			threadStatus.setMessage("线程以停止");
		}
		return threadStatus;
	}

	/*
	 * 改变账户状态
	 * 
	 * @id 账户状态码
	 */
	private void changeStatus(String id) {
		tbSystemSocialaccount.setIsused(id);
		system_accountServiceI.updateAccount(tbSystemSocialaccount);
	}

	@RequestMapping(value = "/lnkbegin")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public LnkCollectDto lnkbegin(@RequestParam(value = "ip", required = true) String ip) {
		// 采集任务
		LnkCollectDto lnk = new LnkCollectDto();

		try {
			if (thread == null) {
				localIpAccount = getIpAccount();
				localhostIp = ip;

				if (localIpAccount == null) {
					System.out.println("无闲置账号可用");
					lnk.setStatus(500);
					lnk.setMessage("无闲置账号可用");
					return lnk;
				}
				lnk.setHttpIp(localIpAccount.getHttpip());
				lnk.setUsername(localIpAccount.getAccountName());

				if (linkedinController == null) {
					Integer port = null;
					String hip = null;
					try {
						port = Integer.parseInt(localIpAccount.getPort());
						hip = localIpAccount.getHttpip();
					} catch (Exception e) {
						hip = null;
						port = null;
					}
					linkedinController = new LinkedinController(localIpAccount.getAccountName(),
							localIpAccount.getPassword(), hip, port);
				}

				if (localIpAccount.getAccountName() == null || localIpAccount.getPassword() == null) {
					System.out.println("无法获取用户名和密码");
					lnk.setStatus(500);
					lnk.setMessage("无法获取用户名和密码");
					return lnk;
				}

				thread = new doCollect();
				thread.start();
				lnk.setStatus(200);
				lnk.setMessage("启动成功");
			} else {
				lnk.setStatus(200);
				lnk.setMessage("节点已经启动");
				return lnk;
			}
		} catch (Exception e) {
			e.printStackTrace();
			lnk.setStatus(500);
			lnk.setMessage("任务启动失败");
			return lnk;
		}
		return lnk;
	}

	// 改变状态
	public void changeState(Long id, String state) {
		TbSystemSocialaccount tbSystemSocialaccount = system_AccountServiceI.select_socialAcount_id(id);
		tbSystemSocialaccount.setIsused(state);
		system_AccountServiceI.updateAccount(tbSystemSocialaccount);
	}

	public IPAccountDto getIpAccount() {
		Response resTask = null;

		TbSystemHttp http = new TbSystemHttp();
		IPAccountDto ipAccountDto = new IPAccountDto();
		try {
			System.out.println(getAccount);
			resTask = Jsoup.connect(URLDecoder.decode(getAccount, "utf-8")).userAgent(LinkedinController.USER_AGENT)
					.timeout(120000).ignoreHttpErrors(true).ignoreContentType(true).execute();
		} catch (Exception e) {
			e.printStackTrace();
			localIpAccount = null;
			return null;
		}

		if (null != resTask && resTask.statusCode() == 200) {
			String strTask = resTask.body();

			try {
				tbSystemSocialaccount = new ObjectMapper().readValue(strTask, TbSystemSocialaccount.class);
				if (tbSystemSocialaccount.getId() != null || tbSystemSocialaccount.getHttpid() != null) {
					http = tbSystemHttpServiceI.getByidHttp(tbSystemSocialaccount.getHttpid());
				} else {
					localIpAccount = null;
					return null;
				}

				ipAccountDto.setId(tbSystemSocialaccount.getId());
				ipAccountDto.setAccountName(tbSystemSocialaccount.getAccountname());
				ipAccountDto.setPassword(tbSystemSocialaccount.getPassword());

				if (http.getHttpip() != null && http.getPort() != null) {
					ipAccountDto.setHttpid(tbSystemSocialaccount.getHttpid().toString());
					ipAccountDto.setHttpip(http.getHttpip());
					ipAccountDto.setPort(http.getPort());
					ipAccountDto.setHttpname(http.getHttpname());
					ipAccountDto.setHttppassword(http.getHttppassword());
				}
				localIpAccount = ipAccountDto;

				System.out.println(new ObjectMapper().writeValueAsString(localIpAccount));
				return ipAccountDto;
			} catch (Exception e) {
				e.printStackTrace();
				localIpAccount = null;
				return null;
			}
		} else {
			localIpAccount = null;
			return null;
		}
	}

	public TbSystemHttp getIPandAccount(String localIp) {
		// 获取本机IP地址得到所绑定的账号

		TbSystemHttp tbSystemHttp = new TbSystemHttp();
		try {
			String ip = localIp;
			TbSystemNode tbSystemNode = system_NodeServiceI.selectNodeip(ip);
			if (tbSystemNode != null) {
				List<TbSystemRelation> relation = system_RelationServiceI.select_Reation_nodeid(tbSystemNode.getId());
				if (relation.size() != 0) {
					for (TbSystemRelation tbSystemRelation : relation) {
						TbSystemSocialaccount sociala = system_AccountServiceI
								.select_socialAcount_id(tbSystemRelation.getSocialid());
						if (sociala.getAccounttype().equals("2")) {
						}
						TbSystemHttp http = tbSystemHttpServiceI.getByidHttp(tbSystemRelation.getLinkid());
						tbSystemHttp.setHttpip(http.getHttpip());
						tbSystemHttp.setPort(http.getPort());
						tbSystemHttp.setHttpname(http.getHttpname());
						tbSystemHttp.setHttppassword(http.getHttppassword());

					}
				}
			}
		} catch (Exception e1) {
			// Facebook_Status.setNodeStatus("700");
			e1.printStackTrace();
		}

		return tbSystemHttp;
	}

	public static void main(String[] args) {
		LnkController l = new LnkController();
		l.lnkbegin("");
	}

	public class doCollect extends Thread {

		// 搜索
		private void searchByKerword(TbLinkedinSysKeyword keyword) {
			LnkGoogleSearch lnkGoogleSearch = new LnkGoogleSearch();
			try {
				threadStatus.setMessage("搜索关键词中");
				Integer port = null;
				String hip = null;
				try {
					port = Integer.parseInt(localIpAccount.getPort());
					hip = localIpAccount.getHttpip();
				} catch (Exception e) {
					hip = null;
					port = null;
				}
				GoogleResoult googleResoult = lnkGoogleSearch.searchFromYahoo(keyword.getKeyword(), "first", hip, port);

				List<GoogleTarget> list = googleResoult.getList();

				if (googleResoult.getStatusCode() != 200) {
					System.err.println("搜索失败!");
					keyword.setFinished(0);
					keyword.setUpdatatime(new Timestamp(System.currentTimeMillis()));
					system_linkedin_keywordI.updateKeywordbyid(keyword);
					return;
				} else {
					for (GoogleTarget gTarget : list) {
						TbLinkedinSysTask tbTask = linkedin_taskServiceI.getTaskByurlpattern(gTarget.getUrlpattern());
						if (tbTask == null) {

							TbLinkedinSysTask tbLinkedinSysTask = new TbLinkedinSysTask();

							tbLinkedinSysTask.setFinished(0);
							tbLinkedinSysTask.setTaskstatus("0");
							tbLinkedinSysTask.setFullname(gTarget.getFullName());
							tbLinkedinSysTask.setHeadline(gTarget.getHeadline());
							tbLinkedinSysTask.setLocation(gTarget.getLocation());
							tbLinkedinSysTask.setUrl(gTarget.getUrl());
							tbLinkedinSysTask.setKeywordid(keyword.getId());
							tbLinkedinSysTask.setCreattime(new Timestamp(System.currentTimeMillis()));
							tbLinkedinSysTask.setUpdatetime(new Timestamp(System.currentTimeMillis()));
							tbLinkedinSysTask.setUrlpattern(gTarget.getUrlpattern());

							linkedin_taskServiceI.insertLnkTask(tbLinkedinSysTask);
						} else {
							System.err.println("重复的目标!");
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				keyword.setFinished(0);
				keyword.setUpdatatime(new Timestamp(System.currentTimeMillis()));
				system_linkedin_keywordI.updateKeywordbyid(keyword);
				threadStatus.setMessage("空闲中");
				return;
			}

			keyword.setFinished(1);
			keyword.setUpdatatime(new Timestamp(System.currentTimeMillis()));
			system_linkedin_keywordI.updateKeywordbyid(keyword);
			threadStatus.setMessage("搜索关键词中");
		}

		private void lnkCollect(TbLinkedinSysTask tlt) {
			try {
				threadStatus.setMessage("数据采集中");
				TbLinkedinSysTask tbLinkedinSysTask = tlt;
				String targetUrl = tbLinkedinSysTask.getUrl();
				String lnkString = linkedinController.getDetail(targetUrl);
				LnkDetailResponse lnkDetailResponse = new Gson().fromJson(lnkString, LnkDetailResponse.class);

				if (lnkDetailResponse.getStatuscode() != 200) {
					lnkString = linkedinController.getDetail(targetUrl);
					lnkDetailResponse = new Gson().fromJson(lnkString, LnkDetailResponse.class);

					if (lnkDetailResponse.getStatuscode() != 200) {

						if (linkedinController.isLogin(localIpAccount.getAccountName()) == 0) {
							tbLinkedinSysTask.setTaskstatus("3");
							tbLinkedinSysTask.setUpdatetime(new Timestamp(System.currentTimeMillis()));
							linkedin_taskServiceI.updateTask(tbLinkedinSysTask);
						} else {
							System.out.println("网络异常或账号受限，将休眠10分钟");
							tbLinkedinSysTask.setTaskstatus("0");
							tbLinkedinSysTask.setUpdatetime(new Timestamp(System.currentTimeMillis()));
							linkedin_taskServiceI.updateTask(tbLinkedinSysTask);
							try {
								Thread.sleep(1000 * 600);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}

				if (lnkDetailResponse.getProfile() != null) {

					analyseDataThread.setData(lnkString);
					analyseDataThread.setWebSitesData(lnkDetailResponse.getProfile().getOverview().getWebsites());
					analyseDataThread.analyse();
					tbLinkedinSysTask.setLnkid(lnkDetailResponse.getProfile().getLnkid());
					tbLinkedinSysTask.setFullname(lnkDetailResponse.getProfile().getOverview().getFullname());
					tbLinkedinSysTask.setTaskstatus("1");
					tbLinkedinSysTask.setUpdatetime(new Timestamp(System.currentTimeMillis()));

					try {
						tbLinkedinSysTask.setSummary(lnkDetailResponse.getProfile().getSummary().getSummary());
					} catch (Exception e) {
					}

					linkedin_taskServiceI.updateTask(tbLinkedinSysTask);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 第三方搜索
		private void searchByThirdKerword(TbSysThirdTask tlt) {
			LnkGoogleSearch lnkGoogleSearch = new LnkGoogleSearch();
			TbSysThirdTask tbSysThirdTask = tlt;
			try {
				System.err.println("开始第三方搜索:");
				Integer port = null;
				String hip = null;
				try {
					port = Integer.parseInt(localIpAccount.getPort());
					hip = localIpAccount.getHttpip();
				} catch (Exception e) {
					hip = null;
					port = null;
				}
				GoogleResoult googleResoult = lnkGoogleSearch.searchFromYahoo(tbSysThirdTask.getKeyword(), "first", hip,
						port);

				List<GoogleTarget> list = googleResoult.getList();

				if (googleResoult.getStatusCode() != 200) {
					System.err.println("搜索失败!");
					tbSysThirdTask.setTaskstatus("0");
					tbSysThirdTask.setUpdatetime(new Timestamp(System.currentTimeMillis()).toString());
					thirdService.updateTask(tbSysThirdTask);
					return;
				} else {
					for (GoogleTarget gTarget : list) {
						TbLinkedinSysTask tbTask = linkedin_taskServiceI.getTaskByurlpattern(gTarget.getUrlpattern());
						if (tbTask == null) {

							TbLinkedinSysTask tbLinkedinSysTask = new TbLinkedinSysTask();

							tbLinkedinSysTask.setFinished(0);
							tbLinkedinSysTask.setTaskstatus("0");
							tbLinkedinSysTask.setFullname(gTarget.getFullName());
							tbLinkedinSysTask.setHeadline(gTarget.getHeadline());
							tbLinkedinSysTask.setLocation(gTarget.getLocation());
							tbLinkedinSysTask.setUrl(gTarget.getUrl());
							tbLinkedinSysTask.setPriority("t3_" + tbSysThirdTask.getId());
							tbLinkedinSysTask.setCreattime(new Timestamp(System.currentTimeMillis()));
							tbLinkedinSysTask.setUpdatetime(new Timestamp(System.currentTimeMillis()));
							tbLinkedinSysTask.setUrlpattern(gTarget.getUrlpattern());

							linkedin_taskServiceI.insertLnkTask(tbLinkedinSysTask);
						} else {
							System.err.println("重复的目标!");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				tbSysThirdTask.setTaskstatus("0");
				tbSysThirdTask.setUpdatetime(new Timestamp(System.currentTimeMillis()).toString());
				thirdService.updateTask(tbSysThirdTask);
				threadStatus.setMessage("空闲中");
				return;
			}

			tbSysThirdTask.setTaskstatus("1");
			tbSysThirdTask.setUpdatetime(new Timestamp(System.currentTimeMillis()).toString());
			thirdService.updateTask(tbSysThirdTask);
		}

		private void doThirdTask(TbSysThirdTask tlt) {
			System.out.println("=======开始执行第三方任务=======");
			threadStatus.setMessage("执行第三方任务");
			try {

				if (tlt.getUrl() != null) {
					System.out.println("===开始执行第三方采集任务===");
					TbSysThirdTask tbSysThirdTask = tlt;
					String targetUrl = tbSysThirdTask.getUrl();
					String lnkString = linkedinController.getDetail(targetUrl);
					LnkDetailResponse lnkDetailResponse = new Gson().fromJson(lnkString, LnkDetailResponse.class);

					if (lnkDetailResponse.getStatuscode() != 200) {
						lnkString = linkedinController.getDetail(targetUrl);
						lnkDetailResponse = new Gson().fromJson(lnkString, LnkDetailResponse.class);

						if (lnkDetailResponse.getStatuscode() != 200) {

							if (linkedinController.isLogin(localIpAccount.getAccountName()) == 0) {
								tbSysThirdTask.setTaskstatus("3");
								tbSysThirdTask.setUpdatetime(new Timestamp(System.currentTimeMillis()).toString());
								thirdService.updateTask(tbSysThirdTask);
							} else {
								System.out.println("网络异常或账号受限，将休眠10分钟");
								tbSysThirdTask.setTaskstatus("0");
								tbSysThirdTask.setUpdatetime(new Timestamp(System.currentTimeMillis()).toString());
								thirdService.updateTask(tbSysThirdTask);
								try {
									Thread.sleep(1000 * 600);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}

					if (lnkDetailResponse.getProfile() != null) {

						analyseDataThread.setData(lnkString);
						analyseDataThread.setWebSitesData(lnkDetailResponse.getProfile().getOverview().getWebsites());
						analyseDataThread.analyse();
						tbSysThirdTask.setPersonid(lnkDetailResponse.getProfile().getLnkid());
						tbSysThirdTask.setFullname(lnkDetailResponse.getProfile().getOverview().getFullname());
						tbSysThirdTask.setTaskstatus("1");
						tbSysThirdTask.setUpdatetime(new Timestamp(System.currentTimeMillis()).toString());
						tbSysThirdTask
								.setProfileid(peopleServiceI.getIdByLnkid(lnkDetailResponse.getProfile().getLnkid()));
						thirdService.updateTask(tbSysThirdTask);
					}
				}

				if (tlt.getKeyword() != null) {
					System.out.println("===开始执行第三方搜索任务===");
					searchByThirdKerword(tlt);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("=======本次第三方采集任务结束=======");
		}

		private void doTask() {
			System.out.println("=======开始执行本地行采集任务=======");
			Response resTask = null;
			TbLinkedinSysTask tbLinkedinSysTask = null;
			try {
				resTask = Jsoup.connect(URLDecoder.decode(getTaskUrl, "utf-8")).userAgent(LinkedinController.USER_AGENT)
						.timeout(120000).ignoreHttpErrors(true).ignoreContentType(true).execute();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (null != resTask && resTask.statusCode() == 200) {
				String strTask = resTask.body();
				try {

					tbLinkedinSysTask = new ObjectMapper().readValue(strTask, TbLinkedinSysTask.class);
					lnkCollect(tbLinkedinSysTask);

				} catch (Exception e) {
					System.out.println("未获取到目标，执行搜索任务");
					// 搜索任务
					try {
						resTask = Jsoup.connect(URLDecoder.decode(getOneLnkKeyword, "utf-8"))
								.userAgent(LinkedinController.USER_AGENT).timeout(120000).ignoreHttpErrors(true)
								.ignoreContentType(true).execute();
						TbLinkedinSysKeyword tbLinkedinSysKeyword = new ObjectMapper().readValue(resTask.body(),
								TbLinkedinSysKeyword.class);
						tbLinkedinSysKeyword.setFinished(5);
						system_linkedin_keywordI.updateKeywordbyid(tbLinkedinSysKeyword);
						System.err.println(tbLinkedinSysKeyword.getKeyword());
						searchByKerword(tbLinkedinSysKeyword);

					} catch (Exception e1) {
						System.out.println("无搜索任务");
					}
				}

			} else {
				System.err.println("与主机失去连接，稍后重试！");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			threadStatus.setMessage("空闲中");
			System.out.println("=======本次本地采集任务结束=======");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {

			// 采集任务
			while (true) {

				if (linkedinController == null) {
					Integer port = null;
					String hip = null;
					try {
						port = Integer.parseInt(localIpAccount.getPort());
						hip = localIpAccount.getHttpip();
					} catch (Exception e) {
						hip = null;
						port = null;
					}
					linkedinController = new LinkedinController(localIpAccount.getAccountName(),
							localIpAccount.getPassword(), hip, port);
				}

				if (linkedinController.isLogin(localIpAccount.getAccountName()) != 0) {
					System.out.println("开始登录");
					if (linkedinController.login(localIpAccount.getAccountName(), localIpAccount.getPassword()) == 0) {
						// continue;
					} else {
						changeStatus("3");
						getIpAccount();
						try {
							Integer port = null;
							String ip = null;
							try {
								port = Integer.parseInt(localIpAccount.getPort());
								ip = localIpAccount.getHttpip();
							} catch (Exception e) {
								ip = null;
								port = null;
							}
							linkedinController = new LinkedinController(localIpAccount.getAccountName(),
									localIpAccount.getPassword(), ip, port);
							if (linkedinController.isLogin(localIpAccount.getAccountName()) != 0) {
								System.out.println("登录失败，休眠10分钟");
								changeStatus("3");
								try {
									Thread.sleep(1000 * 60 * 10);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								continue;
							}
						} catch (Exception e) {
							e.printStackTrace();
							threadStatus.setMessage("空闲中");
							System.out.println("任务失败，休眠10分钟");
							try {
								Thread.sleep(1000 * 60 * 10);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							changeStatus("3");
							continue;
						}
					}
				}

				Response resTask = null;
				try {
					resTask = Jsoup.connect(URLDecoder.decode(getOneThirdKeyword, "utf-8"))
							.userAgent(LinkedinController.USER_AGENT).timeout(120000).ignoreHttpErrors(true)
							.ignoreContentType(true).execute();
				} catch (Exception e) {
				}

				if (null != resTask && resTask.statusCode() == 200) {
					String strTask = resTask.body();
					try {
						TbSysThirdTask tbSysThirdTask = new ObjectMapper().readValue(strTask, TbSysThirdTask.class);
						if (tbSysThirdTask.getUrl() != null || tbSysThirdTask.getKeyword() != null) {
							doThirdTask(tbSysThirdTask);
							continue;
						}
					} catch (Exception e) {
					}
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 本地任务
				doTask();

			}
		}
	}
}